package com.kstt.application.articles.query;

import com.kstt.application.common.query.QueryBuilder;
import com.kstt.articles.entity.Article;
import org.springframework.stereotype.Component;

/**
 * 文章查询构建器
 */
@Component
public class ArticleQueryBuilder implements QueryBuilder<Article, ArticleQuery> {

    @Override
    public Article build(ArticleQuery query) {
        if (query == null) {
            return new Article();
        }

        Article condition = new Article();
        
        if (query.getTitle() != null && !query.getTitle().isBlank()) {
            condition.setArticleTitle(query.getTitle());
        }
        
        if (query.getStatusId() != null) {
            condition.setArticleStatusId(query.getStatusId());
        }
        
        if (query.getJournalId() != null) {
            condition.setArticleJournalId(query.getJournalId());
        }
        
        if (query.getManuscriptId() != null && !query.getManuscriptId().isBlank()) {
            condition.setArticleManuscriptId(query.getManuscriptId());
        }

        return condition;
    }

    @Override
    public void validate(ArticleQuery query) {
        if (query == null) {
            return;
        }

        if (query.getPageNum() != null && query.getPageNum() < 1) {
            query.setPageNum(1);
        }

        if (query.getPageSize() != null && (query.getPageSize() < 1 || query.getPageSize() > 100)) {
            query.setPageSize(10);
        }
    }
}
