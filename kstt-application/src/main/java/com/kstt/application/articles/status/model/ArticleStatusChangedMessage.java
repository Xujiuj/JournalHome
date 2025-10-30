package com.kstt.application.articles.status.model;

import com.kstt.common.enums.ArticleStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 文章状态变更消息
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleStatusChangedMessage implements Serializable {

    private Long articleId;
    private String manuscriptId;
    private ArticleStatusEnum previousStatus;
    private ArticleStatusEnum currentStatus;
    private Long operatorId;
    private String operatorName;
    private String remark;
    private LocalDateTime eventTime;
}
