package com.kstt.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kstt.sys.entity.SysMenu;
import com.kstt.sys.mapper.SysMenuMapper;
import com.kstt.sys.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 菜单Service实现类
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {
    
    @Autowired
    private SysMenuMapper sysMenuMapper;
    
    /**
     * 查询菜单列表
     */
    @Override
    public List<SysMenu> selectMenuList(SysMenu menu) {
        return sysMenuMapper.selectMenuList(menu);
    }
    
    /**
     * 根据菜单ID查询菜单信息
     * 
     * @param menuId 菜单ID
     * @return 菜单信息
     */
    @Override
    @Cacheable(value = "sysMenu", key = "#menuId", unless = "#result == null")
    public SysMenu selectMenuByMenuId(Long menuId) {
        return sysMenuMapper.selectMenuByMenuId(menuId);
    }
    
    /**
     * 新增菜单
     * 
     * @param menu 菜单信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "sysMenu", allEntries = true)
    public int insertMenu(SysMenu menu) {
        return sysMenuMapper.insertMenu(menu);
    }
    
    /**
     * 修改菜单
     * 
     * @param menu 菜单信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "sysMenu", allEntries = true)
    public int updateMenu(SysMenu menu) {
        return sysMenuMapper.updateMenu(menu);
    }
    
    /**
     * 删除菜单信息
     * 
     * @param menuId 菜单ID
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "sysMenu", allEntries = true)
    public int deleteMenuByMenuId(Long menuId) {
        return sysMenuMapper.deleteMenuByMenuId(menuId);
    }
    
    /**
     * 批量删除菜单信息
     * 
     * @param menuIds 需要删除的菜单ID
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "sysMenu", allEntries = true)
    public int deleteMenuByMenuIds(Long[] menuIds) {
        return sysMenuMapper.deleteMenuByMenuIds(menuIds);
    }
}