package com.kstt.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kstt.sys.entity.SysRole;
import com.kstt.sys.mapper.SysRoleMapper;
import com.kstt.sys.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色Service实现类
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    
    @Autowired
    private SysRoleMapper sysRoleMapper;
    
    /**
     * 查询角色列表
     */
    @Override
    public List<SysRole> selectRoleList(SysRole role) {
        return sysRoleMapper.selectRoleList(role);
    }
    
    /**
     * 根据角色ID查询角色信息
     * 
     * @param roleId 角色ID
     * @return 角色信息
     */
    @Override
    @Cacheable(value = "sysRole", key = "#roleId", unless = "#result == null")
    public SysRole selectRoleByRoleId(Long roleId) {
        return sysRoleMapper.selectRoleByRoleId(roleId);
    }
    
    /**
     * 新增角色
     * 
     * @param role 角色信息
     * @return 结果
     */
    @Override
    @CacheEvict(value = "sysRole", allEntries = true)
    public int insertRole(SysRole role) {
        return sysRoleMapper.insertRole(role);
    }
    
    /**
     * 修改角色
     * 
     * @param role 角色信息
     * @return 结果
     */
    @Override
    @CacheEvict(value = "sysRole", allEntries = true)
    public int updateRole(SysRole role) {
        return sysRoleMapper.updateRole(role);
    }
    
    /**
     * 删除角色信息
     * 
     * @param roleId 角色ID
     * @return 结果
     */
    @Override
    public int deleteRoleByRoleId(Long roleId) {
        return sysRoleMapper.deleteRoleByRoleId(roleId);
    }
    
    /**
     * 批量删除角色信息
     * 
     * @param roleIds 需要删除的角色ID
     * @return 结果
     */
    @Override
    public int deleteRoleByRoleIds(Long[] roleIds) {
        return sysRoleMapper.deleteRoleByRoleIds(roleIds);
    }
}