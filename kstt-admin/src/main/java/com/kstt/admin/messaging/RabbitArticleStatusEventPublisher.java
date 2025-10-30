package com.kstt.admin.messaging;

import com.kstt.admin.config.properties.AppProperties;
import com.kstt.application.articles.status.event.ArticleStatusEventPublisher;
import com.kstt.application.articles.status.model.ArticleStatusChangedMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * 基于 RabbitMQ 的文章状态事件发布实现
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class RabbitArticleStatusEventPublisher implements ArticleStatusEventPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final AppProperties appProperties;

    @Override
    public void publishStatusChangedEvent(ArticleStatusChangedMessage message) {
        AppProperties.RabbitMqProperties.ArticleStatusProperties config = 
            appProperties.getRabbitmq().getArticleStatus();
        
        rabbitTemplate.convertAndSend(
            config.getExchange(),
            config.getRoutingKey(),
            message
        );
        
        if (log.isDebugEnabled()) {
            log.debug("已发送文章状态变更消息: articleId={}, currentStatus={}",
                    message.getArticleId(),
                    message.getCurrentStatus());
        }
    }
}
