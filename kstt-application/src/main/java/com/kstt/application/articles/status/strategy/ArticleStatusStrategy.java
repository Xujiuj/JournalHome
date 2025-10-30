package com.kstt.application.articles.status.strategy;

import com.kstt.application.articles.status.model.ArticleStatusChangeRequest;
import com.kstt.articles.entity.Article;
import com.kstt.common.enums.ArticleStatusEnum;

/**
 * 文章状态变更策略接口
 */
public interface ArticleStatusStrategy {

    /**
     * 获取策略对应的目标状态
     */
    ArticleStatusEnum targetStatus();

    /**
     * 校验当前文章是否允许执行该状态转换
     */
    void validate(Article article, ArticleStatusChangeRequest request);

    /**
     * 执行状态转换并处理业务扩展行为
     */
    void apply(Article article, ArticleStatusChangeRequest request, ArticleStatusEnum previousStatus);
}
