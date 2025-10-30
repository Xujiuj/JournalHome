package com.kstt.application.articles.status.strategy;

import com.kstt.application.articles.status.model.ArticleStatusChangeRequest;
import com.kstt.articles.entity.Article;
import com.kstt.articles.service.ArticleProcessService;
import com.kstt.common.enums.ArticleStatusEnum;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 文章发布策略
 */
@Component
public class PublishStatusStrategy extends AbstractArticleStatusStrategy {

    public PublishStatusStrategy(ArticleProcessService articleProcessService) {
        super(articleProcessService);
    }

    @Override
    public ArticleStatusEnum targetStatus() {
        return ArticleStatusEnum.PUBLISHED;
    }

    @Override
    public void validate(Article article, ArticleStatusChangeRequest request) {
        assertArticleExists(article);
        assertCurrentStatusIn(article, ArticleStatusEnum.ACCEPTED);
    }

    @Override
    public void apply(Article article, ArticleStatusChangeRequest request, ArticleStatusEnum previousStatus) {
        article.setArticleStatusId(targetStatus().getStatusId());
        LocalDateTime now = LocalDateTime.now();
        article.setArticlePublishTime(now);
        if (article.getArticleOnlineTime() == null) {
            article.setArticleOnlineTime(now);
        }
        recordProcess(article, request, previousStatus, "PUBLISH", request.getRemark());
    }
}
