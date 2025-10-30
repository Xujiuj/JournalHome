package com.kstt.application.articles.status.strategy;

import com.kstt.application.articles.status.model.ArticleStatusChangeRequest;
import com.kstt.application.common.exception.ApplicationException;
import com.kstt.application.common.exception.ErrorCode;
import com.kstt.articles.entity.Article;
import com.kstt.articles.entity.ArticleProcess;
import com.kstt.articles.service.ArticleProcessService;
import com.kstt.common.enums.ArticleStatusEnum;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * 文章状态变更策略抽象基类
 */
@RequiredArgsConstructor
public abstract class AbstractArticleStatusStrategy implements ArticleStatusStrategy {

    private final ArticleProcessService articleProcessService;

    protected void assertArticleExists(Article article) {
        if (article == null) {
            throw new ApplicationException(ErrorCode.ARTICLE_NOT_FOUND, "文章不存在，无法完成状态变更");
        }
    }

    protected void assertCurrentStatusIn(Article article, ArticleStatusEnum... allowedStatuses) {
        ArticleStatusEnum current = ArticleStatusEnum.getByStatusId(article.getArticleStatusId());
        boolean matched = Arrays.stream(allowedStatuses).anyMatch(status -> status == current);
        if (!matched) {
            String message = String.format("文章当前状态 %s 不支持转换为 %s", 
                    current != null ? current.getStatusDesc() : "未知", 
                    targetStatus().getStatusDesc());
            throw new ApplicationException(ErrorCode.ARTICLE_STATUS_INVALID, message);
        }
    }

    protected ArticleStatusEnum resolveCurrentStatus(Article article) {
        return ArticleStatusEnum.getByStatusId(article.getArticleStatusId());
    }

    protected void recordProcess(Article article,
                                 ArticleStatusChangeRequest request,
                                 ArticleStatusEnum previousStatus,
                                 String decision,
                                 String notes) {
        ArticleProcess process = new ArticleProcess();
        process.setProcessArticleId(article.getArticleId());
        process.setProcessCurrentStatusId(targetStatus().getStatusId());
        process.setProcessPreviousStatusId(previousStatus != null ? previousStatus.getStatusId() : null);
        process.setProcessEditorId(request.getOperatorId());
        process.setProcessNotes(notes);
        process.setProcessDecision(decision);
        process.setProcessDecisionReason(request.getDecisionReason() != null ? request.getDecisionReason() : request.getRemark());
        if (decision != null) {
            process.setProcessDecisionTime(LocalDateTime.now());
        }
        process.setProcessAssignTime(LocalDateTime.now());
        process.setProcessDueDate(request.getDueDate());
        articleProcessService.insertProcess(process);
    }
}
