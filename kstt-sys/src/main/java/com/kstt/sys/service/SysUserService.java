package com.kstt.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kstt.sys.entity.SysUser;

import java.util.List;

/**
 * 用户信息Service接口
 * 
 * @author kstt
 * @date 2025-10-23
 */
public interface SysUserService extends IService<SysUser> {
    
    
    /**
     * 查询用户列表
     * 
     * @param user 用户信息
     * @return 用户集合
     */
    List<SysUser> selectUserList(SysUser user);
    
    /**
     * 根据用户ID查询用户信息
     * 
     * @param userId 用户ID
     * @return 用户信息
     */
    SysUser selectUserByUserId(Long userId);
    
    /**
     * 根据用户名获取用户
     */
    SysUser getByUsername(String username);
    
    /**
     * 根据邮箱获取用户
     */
    SysUser getByEmail(String email);
    
    /**
     * 新增用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    int insertUser(SysUser user);
    
    /**
     * 修改用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    int updateUser(SysUser user);
    
    /**
     * 批量删除用户信息
     * 
     * @param userIds 需要删除的用户ID
     * @return 结果
     */
    int deleteUserByUserIds(Long[] userIds);
    
    /**
     * 删除用户信息
     * 
     * @param userId 用户ID
     * @return 结果
     */
    int deleteUserByUserId(Long userId);
    
    /**
     * 重置用户密码
     */
    int resetUserPwd(SysUser user);
    
    /**
     * 修改用户状态
     */
    int updateUserStatus(SysUser user);
    
}