package com.kstt.articles.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.kstt.common.entity.BaseEntity;
import com.kstt.sys.entity.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 评审邀请实体类
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("article_review_invitation")
public class ArticleReviewInvitation extends BaseEntity {

    /**
     * 邀请ID
     */
    @TableId(type = IdType.AUTO)
    private Long invitationId;

    /**
     * 关联流程ID
     */
    private Long invitationProcessId;

    /**
     * 评审人ID
     */
    private Long invitationReviewerId;

    /**
     * 评审轮次
     */
    private Integer invitationRound;

    /**
     * 邀请状态(pending, accepted, declined, expired)
     */
    private String invitationStatus;

    /**
     * 发送时间
     */
    private LocalDateTime invitationSentTime;

    /**
     * 响应时间
     */
    private LocalDateTime invitationResponseTime;

    /**
     * 评审截止日期
     */
    private LocalDateTime invitationDueDate;

    /**
     * 邀请备注
     */
    private String invitationNotes;

    /**
     * 关联的流程信息（不作为数据库字段）
     */
    @TableField(exist = false)
    private ArticleProcess invitationProcess;

    /**
     * 关联的评审人信息（不作为数据库字段）
     */
    @TableField(exist = false)
    private SysUser invitationReviewer;
}
