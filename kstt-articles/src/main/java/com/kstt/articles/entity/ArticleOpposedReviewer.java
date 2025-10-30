package com.kstt.articles.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kstt.sys.entity.SysUser;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 避免审稿人实体类
 * 直接关联到用户表，通过外键关联
 */
@Data
@TableName("article_opposed_reviewer")
public class ArticleOpposedReviewer {

    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    @TableField("opposed_id")
    private Long id;

    /**
     * 关联论文ID
     */
    @TableField("opposed_article_id")
    private Long articleId;

    /**
     * 避免的审稿人用户ID
     */
    @TableField("opposed_user_id")
    private Long reviewerUserId;

    /**
     * 避免理由
     */
    @TableField("opposed_reason")
    private String reason;

    /**
     * 创建者
     */
    @TableField("created_by")
    private String createBy;
    
    /**
     * 创建时间
     */
    @TableField("created_time")
    private LocalDateTime createTime;
    
    /**
     * 更新者
     */
    @TableField("updated_by")
    private String updateBy;
    
    /**
     * 更新时间
     */
    @TableField("updated_time")
    private LocalDateTime updateTime;
    
    /**
     * 备注
     */
    private String remark;

    /**
     * 关联的论文信息（不作为数据库字段）
     */
    @TableField(exist = false)
    @JsonIgnore
    private Article article;

    /**
     * 关联的审稿人用户信息（不作为数据库字段）
     */
    @TableField(exist = false)
    private SysUser reviewerUser;
}
