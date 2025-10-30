package com.kstt.articles.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.kstt.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 论文评审实体类
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("article_review")
public class ArticleReview extends BaseEntity {
    
    /**
     * 评审ID
     */
    @TableId(type = IdType.AUTO)
    private Long reviewId;
    
    /**
     * 关联邀请ID
     */
    private Long reviewInvitationId;
    
    /**
     * 评分(1-5)
     */
    private Integer reviewRating;
    
    /**
     * 推荐意见(accept, reject, minor_revision, major_revision)
     */
    private String reviewRecommendation;
    
    /**
     * 给编辑的意见
     */
    private String reviewCommentsEditor;
    
    /**
     * 给作者的意见
     */
    private String reviewCommentsAuthor;
    
    /**
     * 提交时间
     */
    private LocalDateTime reviewSubmitTime;
    
    /**
     * 状态(0：待评审，1：已评审)
     */
    private Integer reviewStatus;
    
    /**
     * 论文质量评分(1-10)
     */
    private Integer reviewQualityScore;
    
    /**
     * 创新性评分(1-10)
     */
    private Integer reviewInnovationScore;
    
    /**
     * 清晰度评分(1-10)
     */
    private Integer reviewClarityScore;
    
    /**
     * 重要性评分(1-10)
     */
    private Integer reviewSignificanceScore;

    /**
     * 关联的邀请信息（不作为数据库字段）
     */
    @TableField(exist = false)
    private ArticleReviewInvitation reviewInvitation;
}
