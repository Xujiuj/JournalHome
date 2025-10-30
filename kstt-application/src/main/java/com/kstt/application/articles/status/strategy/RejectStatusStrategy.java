package com.kstt.application.articles.status.strategy;

import com.kstt.application.articles.status.model.ArticleStatusChangeRequest;
import com.kstt.articles.entity.Article;
import com.kstt.articles.service.ArticleProcessService;
import com.kstt.common.enums.ArticleStatusEnum;
import org.springframework.stereotype.Component;

/**
 * 审稿拒绝策略
 */
@Component
public class RejectStatusStrategy extends AbstractArticleStatusStrategy {

    public RejectStatusStrategy(ArticleProcessService articleProcessService) {
        super(articleProcessService);
    }

    @Override
    public ArticleStatusEnum targetStatus() {
        return ArticleStatusEnum.REJECTED;
    }

    @Override
    public void validate(Article article, ArticleStatusChangeRequest request) {
        assertArticleExists(article);
        assertCurrentStatusIn(article, ArticleStatusEnum.UNDER_REVIEW, ArticleStatusEnum.REVISION_REQUESTED);
    }

    @Override
    public void apply(Article article, ArticleStatusChangeRequest request, ArticleStatusEnum previousStatus) {
        article.setArticleStatusId(targetStatus().getStatusId());
        recordProcess(article, request, previousStatus, "REJECT", request.getRemark());
    }
}
