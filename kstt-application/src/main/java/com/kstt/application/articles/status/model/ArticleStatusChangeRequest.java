package com.kstt.application.articles.status.model;

import com.kstt.common.enums.ArticleStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 文章状态变更请求
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleStatusChangeRequest {

    /**
     * 文章ID
     */
    private Long articleId;

    /**
     * 目标状态
     */
    private ArticleStatusEnum targetStatus;

    /**
     * 操作人ID
     */
    private Long operatorId;

    /**
     * 操作人名称
     */
    private String operatorName;

    /**
     * 备注信息
     */
    private String remark;

    /**
     * 审稿截止时间（可选）
     */
    private LocalDateTime dueDate;

    /**
     * 流程决策描述（可选）
     */
    private String decisionReason;
}
