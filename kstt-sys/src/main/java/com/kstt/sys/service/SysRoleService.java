package com.kstt.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kstt.sys.entity.SysRole;

import java.util.List;

/**
 * 角色Service接口
 */
public interface SysRoleService extends IService<SysRole> {
    
    /**
     * 查询角色列表
     */
    List<SysRole> selectRoleList(SysRole role);
    
    /**
     * 根据角色ID查询角色信息
     * 
     * @param roleId 角色ID
     * @return 角色信息
     */
    SysRole selectRoleByRoleId(Long roleId);
    
    /**
     * 新增角色
     * 
     * @param role 角色信息
     * @return 结果
     */
    int insertRole(SysRole role);
    
    /**
     * 修改角色
     * 
     * @param role 角色信息
     * @return 结果
     */
    int updateRole(SysRole role);
    
    /**
     * 删除角色信息
     * 
     * @param roleId 角色ID
     * @return 结果
     */
    int deleteRoleByRoleId(Long roleId);
    
    /**
     * 批量删除角色信息
     * 
     * @param roleIds 需要删除的角色ID
     * @return 结果
     */
    int deleteRoleByRoleIds(Long[] roleIds);
}
