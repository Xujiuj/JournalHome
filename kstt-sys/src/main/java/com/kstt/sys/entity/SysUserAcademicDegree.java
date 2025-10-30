package com.kstt.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户学术学位实体类
 */
@Data
@TableName("sys_user_academic_degree")
public class SysUserAcademicDegree {

    /**
     * 学位ID
     */
    @TableId
    private Integer degreeId;

    /**
     * 学位名称
     */
    private String degreeName;

    /**
     * 学位级别
     */
    private String degreeLevel;

    /**
     * 排序
     */
    private Integer sortOrder;
}
