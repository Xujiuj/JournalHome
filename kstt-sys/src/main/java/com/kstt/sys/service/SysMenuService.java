package com.kstt.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kstt.sys.entity.SysMenu;

import java.util.List;

/**
 * 菜单Service接口
 */
public interface SysMenuService extends IService<SysMenu> {
    
    /**
     * 查询菜单列表
     */
    List<SysMenu> selectMenuList(SysMenu menu);
    
    /**
     * 根据菜单ID查询菜单信息
     * 
     * @param menuId 菜单ID
     * @return 菜单信息
     */
    SysMenu selectMenuByMenuId(Long menuId);
    
    /**
     * 新增菜单
     * 
     * @param menu 菜单信息
     * @return 结果
     */
    int insertMenu(SysMenu menu);
    
    /**
     * 修改菜单
     * 
     * @param menu 菜单信息
     * @return 结果
     */
    int updateMenu(SysMenu menu);
    
    /**
     * 删除菜单信息
     * 
     * @param menuId 菜单ID
     * @return 结果
     */
    int deleteMenuByMenuId(Long menuId);
    
    /**
     * 批量删除菜单信息
     * 
     * @param menuIds 需要删除的菜单ID
     * @return 结果
     */
    int deleteMenuByMenuIds(Long[] menuIds);
}
