package com.kstt.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kstt.sys.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户信息Mapper接口
 * 
 * @author kstt
 * @date 2025-10-23
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
    
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
     * 新增用户信息
     * 
     * @param sysUser 用户信息
     * @return 结果
     */
    int insertSysUser(SysUser sysUser);
    
    /**
     * 修改用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    int updateUser(SysUser user);
    
    /**
     * 删除用户信息
     * 
     * @param userId 用户ID
     * @return 结果
     */
    int deleteUserByUserId(Long userId);
    
    /**
     * 批量删除用户信息
     * 
     * @param userIds 需要删除的用户ID
     * @return 结果
     */
    int deleteUserByUserIds(Long[] userIds);
    
}