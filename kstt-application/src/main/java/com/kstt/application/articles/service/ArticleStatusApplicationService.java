package com.kstt.application.articles.service;

import com.kstt.application.articles.status.model.ArticleStatusChangeRequest;
import com.kstt.articles.entity.Article;

/**
 * 文章状态应用服务
 */
public interface ArticleStatusApplicationService {

    /**
     * 变更文章状态
     */
    Article changeStatus(ArticleStatusChangeRequest request);
}
