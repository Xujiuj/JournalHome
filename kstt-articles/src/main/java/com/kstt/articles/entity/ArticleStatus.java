package com.kstt.articles.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 文章状态实体类
 */
@Data
@TableName("article_status")
public class ArticleStatus {
    
    /**
     * 状态ID
     */
    @TableId(type = IdType.INPUT)
    private Integer statusId;
    
    /**
     * 状态名称
     */
    private String statusName;
    
    /**
     * 状态描述
     */
    private String statusDesc;
    
    /**
     * 排序
     */
    private Integer sortOrder;
}

