package com.kstt.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 角色权限注解
 * 
 * @author kstt
 * @date 2025-01-01
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequireRole {
    
    /**
     * 需要的角色
     */
    String[] value() default {};
    
    /**
     * 逻辑关系
     */
    Logical logical() default Logical.OR;
    
    /**
     * 逻辑关系枚举
     */
    enum Logical {
        AND, OR
    }
}