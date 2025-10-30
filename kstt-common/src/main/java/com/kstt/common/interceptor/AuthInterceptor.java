package com.kstt.common.interceptor;

import com.kstt.common.annotation.RequireRole;
import com.kstt.common.exception.BusinessException;
import com.kstt.common.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.lang.reflect.Method;

/**
 * 权限拦截器
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {
    
    private static final Logger log = LoggerFactory.getLogger(AuthInterceptor.class);
    
    @Autowired
    private JwtUtils jwtUtils;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果不是方法处理器，直接放行
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        
        // 检查方法上的权限注解
        RequireRole requireRole = method.getAnnotation(RequireRole.class);
        if (requireRole == null) {
            // 检查类上的权限注解
            requireRole = method.getDeclaringClass().getAnnotation(RequireRole.class);
        }
        
        // 如果没有权限注解，直接放行
        if (requireRole == null) {
            return true;
        }
        
        // 获取请求头中的token
        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            throw new BusinessException("认证", "未提供认证令牌");
        }
        
        // 移除Bearer前缀
        token = token.substring(7);
        
        try {
            // 验证token
            String username = jwtUtils.getUsernameFromToken(token);
            String userRole = jwtUtils.getRoleFromToken(token);
            
            // 检查角色权限
            if (!hasRequiredRole(userRole, requireRole)) {
                throw new BusinessException("权限", "权限不足");
            }
            
            // 将用户信息存储到请求属性中
            request.setAttribute("currentUser", username);
            request.setAttribute("currentRole", userRole);
            
            return true;
        } catch (Exception e) {
            log.error("权限验证失败：{}", e.getMessage());
            throw new BusinessException("认证", "认证失败");
        }
    }
    
    /**
     * 检查用户是否具有所需角色
     */
    private boolean hasRequiredRole(String userRole, RequireRole requireRole) {
        String[] requiredRoles = requireRole.value();
        RequireRole.Logical logical = requireRole.logical();
        
        if (requiredRoles.length == 0) {
            return true;
        }
        
        if (logical == RequireRole.Logical.AND) {
            // 需要拥有所有角色
            for (String role : requiredRoles) {
                if (!role.equals(userRole)) {
                    return false;
                }
            }
            return true;
        } else {
            // 需要拥有任意一个角色
            for (String role : requiredRoles) {
                if (role.equals(userRole)) {
                    return true;
                }
            }
            return false;
        }
    }
}
