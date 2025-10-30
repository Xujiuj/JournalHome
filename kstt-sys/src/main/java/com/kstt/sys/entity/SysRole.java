package com.kstt.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.kstt.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色实体类
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_role")
public class SysRole extends BaseEntity {
    
    /**
     * 角色ID
     */
    @TableId(type = IdType.AUTO)
    private Long roleId;
    
    /**
     * 角色名称
     */
    private String roleName;
    
    /**
     * 排序
     */
    private Integer roleSort;
    
    /**
     * 角色权限范围(1.投稿、2.审稿、3.最终决定、0.管理员)
     */
    private Integer roleScope;
    
    /**
     * 状态(0：禁用，1：启用)
     */
    private Integer roleStatus;
}
