package com.kstt.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.kstt.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 学科领域实体类
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_area")
public class SysArea extends BaseEntity {

    /**
     * 领域ID
     */
    @TableId
    private Integer areaId;

    /**
     * 领域名称
     */
    private String areaName;

    /**
     * 所属学科ID
     */
    private Integer areaSubjectId;

    /**
     * 关联的学科信息（不作为数据库字段）
     */
    @TableField(exist = false)
    private SysSubject subject;
}
