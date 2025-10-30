package com.kstt.admin.config.datasource.aspect;

import com.kstt.admin.config.datasource.DynamicDataSourceContextHolder;
import com.kstt.admin.config.datasource.annotation.MasterRoute;
import com.kstt.admin.config.datasource.annotation.ReadOnlyRoute;
import com.kstt.admin.config.properties.AppProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 动态数据源切面，根据方法特性自动切换主从
 */
@Slf4j
@Aspect
@Order(-1)
@Component
@RequiredArgsConstructor
public class DynamicDataSourceAspect {

    private final AppProperties appProperties;

    @Pointcut("execution(* com.kstt..service..*.*(..))")
    public void dataSourcePointcut() {
    }

    @Before("dataSourcePointcut()")
    public void before(JoinPoint joinPoint) {
        Method method = getMethod(joinPoint);

        if (method == null) {
            DynamicDataSourceContextHolder.useMaster();
            return;
        }

        if (hasAnnotation(method, MasterRoute.class)) {
            DynamicDataSourceContextHolder.useMaster();
            return;
        }

        if (hasAnnotation(method, ReadOnlyRoute.class)) {
            useSlaveIfPossible(method, "@ReadOnlyRoute 标记");
            return;
        }

        if (TransactionSynchronizationManager.isCurrentTransactionReadOnly()) {
            useSlaveIfPossible(method, "@Transactional(readOnly=true)");
            return;
        }

        if (isReadMethod(method.getName())) {
            useSlaveIfPossible(method, "方法命名约定");
            return;
        }

        DynamicDataSourceContextHolder.useMaster();
    }

    @After("dataSourcePointcut()")
    public void after(JoinPoint joinPoint) {
        DynamicDataSourceContextHolder.clear();
    }

    private Method getMethod(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Class<?> targetClass = joinPoint.getTarget() != null ? joinPoint.getTarget().getClass() : null;
        if (targetClass == null) {
            return method;
        }
        try {
            return targetClass.getMethod(method.getName(), method.getParameterTypes());
        } catch (NoSuchMethodException e) {
            return method;
        }
    }

    private boolean hasAnnotation(Method method, Class<? extends Annotation> annotationClass) {
        return AnnotatedElementUtils.hasAnnotation(method, annotationClass)
                || AnnotatedElementUtils.hasAnnotation(method.getDeclaringClass(), annotationClass);
    }

    private boolean isReadMethod(String methodName) {
        List<String> readMethods = appProperties.getDatasource().getReadMethods();
        if (readMethods == null || readMethods.isEmpty()) {
            return false;
        }
        return readMethods.stream().anyMatch(methodName::startsWith);
    }

    private void useSlaveIfPossible(Method method, String reason) {
        if (DynamicDataSourceContextHolder.hasSlave()) {
            DynamicDataSourceContextHolder.useSlave();
            log.debug("数据源切换为 SLAVE, 原因: {} -> {}", reason, method.getName());
        } else {
            log.debug("未检测到可用从库, 方法 {} 继续使用 MASTER", method.getName());
            DynamicDataSourceContextHolder.useMaster();
        }
    }
}
