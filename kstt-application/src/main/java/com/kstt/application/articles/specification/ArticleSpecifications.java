package com.kstt.application.articles.specification;

import com.kstt.application.common.specification.Specification;
import com.kstt.articles.entity.Article;
import com.kstt.common.enums.ArticleStatusEnum;

import java.time.LocalDateTime;

/**
 * 文章规范工厂
 */
public class ArticleSpecifications {

    public static Specification<Article> isPublished() {
        return article -> article.getArticleStatusId() != null 
                && article.getArticleStatusId().equals(ArticleStatusEnum.PUBLISHED.getStatusId());
    }

    public static Specification<Article> isAccepted() {
        return article -> article.getArticleStatusId() != null 
                && article.getArticleStatusId().equals(ArticleStatusEnum.ACCEPTED.getStatusId());
    }

    public static Specification<Article> belongsToJournal(Long journalId) {
        return article -> journalId != null 
                && journalId.equals(article.getArticleJournalId());
    }

    public static Specification<Article> submittedAfter(LocalDateTime date) {
        return article -> article.getArticleSubmitTime() != null 
                && article.getArticleSubmitTime().isAfter(date);
    }

    public static Specification<Article> isOpenAccess() {
        return article -> article.getArticleIsOpenAccess() != null 
                && article.getArticleIsOpenAccess() == 1;
    }

    public static Specification<Article> hasMinWordCount(int minWords) {
        return article -> article.getArticleWordCount() != null 
                && article.getArticleWordCount() >= minWords;
    }

    private ArticleSpecifications() {
    }
}
