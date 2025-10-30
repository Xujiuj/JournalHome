package com.kstt.articles.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 文章关键词实体类
 */
@Data
@TableName("article_keywords")
public class ArticleKeywords {

    /**
     * 关键词ID
     */
    @TableId(type = IdType.AUTO)
    private Long keywordId;

    /**
     * 关联论文ID
     */
    @TableField("keyword_article_id")
    private Long articleId;

    /**
     * 关键词
     */
    private String keyword;

    /**
     * 排序
     */
    @TableField("keyword_sort_order")
    private Integer sortOrder;

    /**
     * 创建者
     */
    private String createdBy;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    /**
     * 关联的论文信息（不作为数据库字段）
     */
    @TableField(exist = false)
    @JsonIgnore
    private Article article;
}

