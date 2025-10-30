package com.kstt.about.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.kstt.common.entity.BaseEntity;
import com.kstt.sys.entity.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 关于我们-编辑团队实体类
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("about_editorial_team")
public class AboutEditorialTeam extends BaseEntity {
    
    /**
     * 团队ID
     */
    @TableId(type = IdType.AUTO)
    private Long teamId;
    
    /**
     * 关联用户ID
     */
    private Long teamUserId;
    
    /**
     * 编辑团队中的职位
     */
    private String teamTitle;
    
    /**
     * 排序
     */
    private Integer teamOrder;
    
    /**
     * 状态(0：禁用，1：启用)
     */
    private Integer teamStatus;

    /**
     * 关联的用户信息（不作为数据库字段）
     */
    @TableField(exist = false)
    private SysUser teamUser;
}