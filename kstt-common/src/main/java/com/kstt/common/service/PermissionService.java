package com.kstt.common.service;

import org.springframework.stereotype.Service;

/**
 * 权限服务类
 * 
 * @author kstt
 * @date 2025-01-01
 */
@Service("ss")
public class PermissionService {
    
    /**
     * 检查是否有指定权限
     * 
     * @param permission 权限字符串
     * @return 是否有权限
     */
    public boolean hasPermi(String permission) {
        // 这里可以根据实际需求实现权限检查逻辑
        // 目前简化处理，返回true表示有权限
        return true;
    }
    
    /**
     * 检查是否有指定角色
     * 
     * @param role 角色字符串
     * @return 是否有角色
     */
    public boolean hasRole(String role) {
        // 这里可以根据实际需求实现角色检查逻辑
        // 目前简化处理，返回true表示有角色
        return true;
    }
}
