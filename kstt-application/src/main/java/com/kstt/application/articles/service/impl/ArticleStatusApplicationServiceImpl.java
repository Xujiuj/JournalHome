package com.kstt.application.articles.service.impl;

import com.kstt.application.articles.service.ArticleStatusApplicationService;
import com.kstt.application.articles.status.event.ArticleStatusEventPublisher;
import com.kstt.application.articles.status.model.ArticleStatusChangeRequest;
import com.kstt.application.articles.status.model.ArticleStatusChangedMessage;
import com.kstt.application.articles.status.strategy.ArticleStatusStrategy;
import com.kstt.application.common.exception.ApplicationException;
import com.kstt.application.common.exception.ErrorCode;
import com.kstt.articles.entity.Article;
import com.kstt.articles.service.ArticleService;
import com.kstt.common.enums.ArticleStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * 文章状态应用服务实现
 */
@Slf4j
@Service
public class ArticleStatusApplicationServiceImpl implements ArticleStatusApplicationService {

    private final ArticleService articleService;
    private final Map<ArticleStatusEnum, ArticleStatusStrategy> strategyMap;
    private final ArticleStatusEventPublisher articleStatusEventPublisher;

    public ArticleStatusApplicationServiceImpl(ArticleService articleService,
                                               List<ArticleStatusStrategy> strategies,
                                               @Nullable ArticleStatusEventPublisher articleStatusEventPublisher) {
        this.articleService = articleService;
        this.strategyMap = new EnumMap<>(ArticleStatusEnum.class);
        for (ArticleStatusStrategy strategy : strategies) {
            ArticleStatusStrategy existing = this.strategyMap.put(strategy.targetStatus(), strategy);
            if (existing != null) {
                log.warn("文章状态策略 {} 重复定义，已覆盖旧实现 {}", strategy.targetStatus(), existing.getClass().getSimpleName());
            }
        }
        this.articleStatusEventPublisher = articleStatusEventPublisher != null
                ? articleStatusEventPublisher
                : ArticleStatusEventPublisher.noop();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Article changeStatus(ArticleStatusChangeRequest request) {
        validateRequest(request);

        Article article = articleService.selectArticleByPaperId(request.getArticleId());
        if (article == null) {
            throw new ApplicationException(ErrorCode.ARTICLE_NOT_FOUND);
        }

        ArticleStatusStrategy strategy = strategyMap.get(request.getTargetStatus());
        if (strategy == null) {
            throw new ApplicationException(ErrorCode.SYSTEM_ERROR, 
                    "未找到文章状态" + request.getTargetStatus() + "的处理策略");
        }

        ArticleStatusEnum previousStatus = ArticleStatusEnum.getByStatusId(article.getArticleStatusId());
        strategy.validate(article, request);
        strategy.apply(article, request, previousStatus);

        if (request.getRemark() != null) {
            article.setRemark(request.getRemark());
        }
        article.setUpdateBy(Optional.ofNullable(request.getOperatorName()).orElse("system"));

        articleService.updateArticle(article);

        Article updatedArticle = articleService.selectArticleByPaperId(article.getArticleId());
        publishStatusChangedEvent(request, previousStatus, updatedArticle);

        return updatedArticle;
    }

    private void publishStatusChangedEvent(ArticleStatusChangeRequest request,
                                           ArticleStatusEnum previousStatus,
                                           Article updatedArticle) {
        ArticleStatusChangedMessage message = ArticleStatusChangedMessage.builder()
                .articleId(updatedArticle.getArticleId())
                .manuscriptId(updatedArticle.getArticleManuscriptId())
                .previousStatus(previousStatus)
                .currentStatus(request.getTargetStatus())
                .operatorId(request.getOperatorId())
                .operatorName(Optional.ofNullable(request.getOperatorName()).orElse("system"))
                .remark(Optional.ofNullable(request.getRemark()).orElse(request.getDecisionReason()))
                .eventTime(LocalDateTime.now())
                .build();
        try {
            articleStatusEventPublisher.publishStatusChangedEvent(message);
        } catch (Exception ex) {
            log.error("发送文章状态变更消息失败，articleId={}, targetStatus={}",
                    updatedArticle.getArticleId(), request.getTargetStatus(), ex);
        }
    }

    private void validateRequest(ArticleStatusChangeRequest request) {
        if (request == null) {
            throw new ApplicationException(ErrorCode.PARAM_ERROR, "状态变更请求不能为空");
        }
        if (request.getArticleId() == null) {
            throw new ApplicationException(ErrorCode.PARAM_ERROR, "文章ID不能为空");
        }
        if (request.getTargetStatus() == null) {
            throw new ApplicationException(ErrorCode.PARAM_ERROR, "目标状态不能为空");
        }
        if (Objects.equals(request.getTargetStatus(), ArticleStatusEnum.REJECTED)
                && (request.getRemark() == null || request.getRemark().isBlank())
                && (request.getDecisionReason() == null || request.getDecisionReason().isBlank())) {
            throw new ApplicationException(ErrorCode.PARAM_ERROR, "拒稿操作必须提供备注或决策原因");
        }
    }
}
