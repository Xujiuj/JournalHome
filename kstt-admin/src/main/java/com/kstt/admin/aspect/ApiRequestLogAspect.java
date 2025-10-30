package com.kstt.admin.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kstt.admin.config.properties.AppProperties;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * API 请求日志切面
 * 记录所有 Controller 层的请求日志，包括请求参数、响应结果、执行时间等
 */
@Slf4j
@Aspect
@Order(1)
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "app.performance", name = "enable-api-log", havingValue = "true", matchIfMissing = true)
public class ApiRequestLogAspect {

    private final ObjectMapper objectMapper;
    private final AppProperties appProperties;

    /**
     * 切点：所有 Controller
     */
    @Pointcut("execution(* com.kstt.admin.controller..*.*(..))")
    public void apiRequestPointcut() {
    }

    /**
     * 环绕通知：记录请求和响应
     */
    @Around("apiRequestPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Instant start = Instant.now();
        
        HttpServletRequest request = getHttpServletRequest();
        if (request == null) {
            return joinPoint.proceed();
        }

        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        String requestUri = request.getRequestURI();
        String httpMethod = request.getMethod();
        String clientIp = getClientIp(request);

        try {
            Object[] args = joinPoint.getArgs();
            String params = buildParamsString(args);

            log.info("API请求开始 -> {} {} | 方法: {}.{} | 客户端: {} | 参数: {}",
                    httpMethod, requestUri, className, methodName, clientIp, params);

            Object result = joinPoint.proceed();

            long elapsed = Duration.between(start, Instant.now()).toMillis();
            String response = buildResponseString(result);

            log.info("API请求结束 -> {} {} | 耗时: {}ms | 响应: {}",
                    httpMethod, requestUri, elapsed, response);

            long slowThreshold = appProperties.getPerformance().getVerySlowThresholdMs();
            if (elapsed > slowThreshold) {
                log.warn("API响应较慢 -> {} {} | 耗时: {}ms (阈值: {}ms)", 
                        httpMethod, requestUri, elapsed, slowThreshold);
            }

            return result;

        } catch (Exception e) {
            long elapsed = Duration.between(start, Instant.now()).toMillis();
            log.error("API请求异常 -> {} {} | 方法: {}.{} | 耗时: {}ms | 异常: {}",
                    httpMethod, requestUri, className, methodName, elapsed, e.getMessage(), e);
            throw e;
        }
    }

    /**
     * 获取 HTTP 请求对象
     */
    private HttpServletRequest getHttpServletRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attributes != null ? attributes.getRequest() : null;
    }

    /**
     * 获取客户端 IP 地址
     */
    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip != null && ip.contains(",") ? ip.split(",")[0].trim() : ip;
    }

    /**
     * 构建参数字符串
     */
    private String buildParamsString(Object[] args) {
        if (args == null || args.length == 0) {
            return "无参数";
        }

        try {
            String params = Arrays.stream(args)
                    .filter(arg -> arg != null)
                    .filter(arg -> !(arg instanceof HttpServletRequest))
                    .filter(arg -> !(arg instanceof MultipartFile))
                    .map(this::formatArgument)
                    .collect(Collectors.joining(", "));

            return params.isEmpty() ? "无参数" : params.length() > 500 
                    ? params.substring(0, 500) + "..." 
                    : params;
        } catch (Exception e) {
            return "参数序列化失败";
        }
    }

    /**
     * 格式化单个参数
     */
    private String formatArgument(Object arg) {
        if (arg == null) {
            return "null";
        }
        if (arg instanceof String) {
            return "\"" + arg + "\"";
        }
        if (arg instanceof Number || arg instanceof Boolean) {
            return String.valueOf(arg);
        }
        if (arg.getClass().isArray()) {
            return "数组[长度=" + ((Object[]) arg).length + "]";
        }
        try {
            return objectMapper.writeValueAsString(arg);
        } catch (Exception e) {
            return arg.getClass().getSimpleName() + "@" + Integer.toHexString(arg.hashCode());
        }
    }

    /**
     * 构建响应字符串
     */
    private String buildResponseString(Object result) {
        if (result == null) {
            return "null";
        }
        try {
            String json = objectMapper.writeValueAsString(result);
            return json.length() > 300 ? json.substring(0, 300) + "..." : json;
        } catch (Exception e) {
            return result.getClass().getSimpleName();
        }
    }
}

