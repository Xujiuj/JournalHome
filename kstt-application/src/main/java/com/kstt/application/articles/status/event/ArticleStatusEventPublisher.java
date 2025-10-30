package com.kstt.application.articles.status.event;

import com.kstt.application.articles.status.model.ArticleStatusChangedMessage;

/**
 * 文章状态事件发布接口
 */
@FunctionalInterface
public interface ArticleStatusEventPublisher {

    void publishStatusChangedEvent(ArticleStatusChangedMessage message);

    static ArticleStatusEventPublisher noop() {
        return message -> { };
    }
}
