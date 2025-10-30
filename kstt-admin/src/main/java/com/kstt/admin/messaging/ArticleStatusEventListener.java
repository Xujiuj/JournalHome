package com.kstt.admin.messaging;

import com.kstt.application.articles.status.model.ArticleStatusChangedMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 文章状态变更消息监听器
 */
@Slf4j
@Component
public class ArticleStatusEventListener {

    @RabbitListener(queues = "${app.rabbitmq.article-status.queue}")
    public void onArticleStatusChanged(ArticleStatusChangedMessage message) {
        log.info("收到文章状态变更消息: articleId={}, status={}, remark={}",
                message.getArticleId(),
                message.getCurrentStatus(),
                message.getRemark());
        // TODO: 在此处添加实际的异步处理逻辑，例如发送通知、生成报告等
    }
}
