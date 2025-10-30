package com.kstt.application.articles.status.strategy;

import com.kstt.application.articles.status.model.ArticleStatusChangeRequest;
import com.kstt.articles.entity.Article;
import com.kstt.articles.service.ArticleProcessService;
import com.kstt.common.enums.ArticleStatusEnum;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 审稿通过策略
 */
@Component
public class AcceptStatusStrategy extends AbstractArticleStatusStrategy {

    public AcceptStatusStrategy(ArticleProcessService articleProcessService) {
        super(articleProcessService);
    }

    @Override
    public ArticleStatusEnum targetStatus() {
        return ArticleStatusEnum.ACCEPTED;
    }

    @Override
    public void validate(Article article, ArticleStatusChangeRequest request) {
        assertArticleExists(article);
        assertCurrentStatusIn(article, ArticleStatusEnum.UNDER_REVIEW, ArticleStatusEnum.REVISION_REQUESTED);
    }

    @Override
    public void apply(Article article, ArticleStatusChangeRequest request, ArticleStatusEnum previousStatus) {
        article.setArticleStatusId(targetStatus().getStatusId());
        article.setArticleAcceptTime(LocalDateTime.now());
        recordProcess(article, request, previousStatus, "ACCEPT", request.getRemark());
    }
}
