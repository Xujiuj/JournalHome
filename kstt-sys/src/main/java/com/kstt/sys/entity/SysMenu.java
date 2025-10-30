package com.kstt.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.kstt.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 菜单实体类
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_menu")
public class SysMenu extends BaseEntity {
    
    /**
     * 菜单ID
     */
    @TableId(type = IdType.AUTO)
    private Long menuId;
    
    /**
     * 菜单名称
     */
    private String menuName;
    
    /**
     * 父菜单ID
     */
    private Long menuParentId;
    
    /**
     * 排序
     */
    private Integer menuOrderNum;
    
    /**
     * 路由地址
     */
    private String menuPath;
    
    /**
     * 组件路径
     */
    private String menuComponent;
    
    /**
     * 菜单类型(M：目录，C：菜单，F：按钮)
     */
    private String menuType;
    
    /**
     * 是否显示(0：隐藏，1：显示)
     */
    private Integer menuVisible;
    
    /**
     * 状态(0：禁用，1：启用)
     */
    private Integer menuStatus;
    
    /**
     * 权限标识
     */
    private String menuPerms;
    
    /**
     * 菜单图标
     */
    private String menuIcon;
}
