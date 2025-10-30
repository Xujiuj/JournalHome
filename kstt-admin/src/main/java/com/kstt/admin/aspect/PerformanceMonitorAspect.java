package com.kstt.admin.aspect;

import com.kstt.admin.config.properties.AppProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

/**
 * 性能监控切面
 * 监控关键业务方法的执行时间，当超过阈值时发出警告
 */
@Slf4j
@Aspect
@Order(2)
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "app.performance", name = "enable-method-monitor", havingValue = "true", matchIfMissing = true)
public class PerformanceMonitorAspect {

    private final AppProperties appProperties;

    /**
     * 切点：所有 Service 层方法
     */
    @Pointcut("execution(* com.kstt..service..*.*(..))")
    public void servicePointcut() {
    }

    @Around("servicePointcut()")
    public Object monitorPerformance(ProceedingJoinPoint joinPoint) throws Throwable {
        Instant start = Instant.now();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        
        try {
            Object result = joinPoint.proceed();
            long elapsed = Duration.between(start, Instant.now()).toMillis();
            
            long slowThreshold = appProperties.getPerformance().getSlowThresholdMs();
            long verySlowThreshold = appProperties.getPerformance().getVerySlowThresholdMs();
            
            if (elapsed > verySlowThreshold) {
                log.warn("非常慢的方法调用 -> {}.{} 耗时: {}ms (阈值: {}ms)",
                        className, methodName, elapsed, verySlowThreshold);
            } else if (elapsed > slowThreshold) {
                log.info("慢方法调用 -> {}.{} 耗时: {}ms (阈值: {}ms)",
                        className, methodName, elapsed, slowThreshold);
            } else if (log.isDebugEnabled()) {
                log.debug("{}.{} 耗时: {}ms", className, methodName, elapsed);
            }
            
            return result;
            
        } catch (Throwable e) {
            long elapsed = Duration.between(start, Instant.now()).toMillis();
            log.error("方法执行失败 -> {}.{} 耗时: {}ms | 异常: {}",
                    className, methodName, elapsed, e.getMessage());
            throw e;
        }
    }
}

