package com.kstt.articles.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 提交类型实体类
 */
@Data
@TableName("article_submission_type")
public class ArticleSubmissionType {
    
    /**
     * 类型ID
     */
    @TableId(type = IdType.INPUT)
    private Integer typeId;
    
    /**
     * 类型名称
     */
    private String typeName;
    
    /**
     * 类型描述
     */
    private String typeDesc;
    
    /**
     * 排序
     */
    private Integer sortOrder;
}

