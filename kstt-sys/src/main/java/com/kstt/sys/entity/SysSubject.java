package com.kstt.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 学科实体类
 */
@Data
@TableName("sys_subject")
public class SysSubject implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 学科ID
     */
    @TableId(type = IdType.AUTO)
    private Integer subjectId;

    /**
     * 学科名称
     */
    private String subjectName;
}
