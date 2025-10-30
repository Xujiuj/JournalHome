package com.kstt.application.articles.service.impl;

import com.kstt.application.articles.command.SubmitArticleCommand;
import com.kstt.application.articles.command.UpdateArticleCommand;
import com.kstt.application.articles.converter.ArticleConverter;
import com.kstt.application.articles.dto.ArticleDetailDTO;
import com.kstt.application.articles.service.ArticleCommandService;
import com.kstt.application.common.exception.ApplicationException;
import com.kstt.application.common.exception.ErrorCode;
import com.kstt.articles.entity.Article;
import com.kstt.articles.entity.ArticleAuthor;
import com.kstt.articles.service.ArticleAuthorService;
import com.kstt.articles.service.ArticleService;
import com.kstt.common.enums.ArticleStatusEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 文章命令服务实现
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ArticleCommandServiceImpl implements ArticleCommandService {

    private final ArticleService articleService;
    private final ArticleAuthorService articleAuthorService;
    private final ArticleConverter articleConverter;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ArticleDetailDTO submitArticle(SubmitArticleCommand command) {
        validateSubmitCommand(command);

        Article article = buildArticleFromCommand(command);
        article.setArticleStatusId(ArticleStatusEnum.SUBMITTED.getStatusId());
        article.setArticleSubmitTime(LocalDateTime.now());

        int result = articleService.insertArticle(article);
        if (result <= 0) {
            throw new ApplicationException(ErrorCode.SYSTEM_ERROR, "文章提交失败");
        }

        if (command.getAuthors() != null && !command.getAuthors().isEmpty()) {
            saveAuthors(article.getArticleId(), command.getAuthors());
        }

        Article saved = articleService.selectArticleByPaperId(article.getArticleId());
        return articleConverter.toDetailDTO(saved);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ArticleDetailDTO updateArticle(UpdateArticleCommand command) {
        if (command == null || command.getArticleId() == null) {
            throw new ApplicationException(ErrorCode.PARAM_ERROR, "文章ID不能为空");
        }

        Article existing = articleService.selectArticleByPaperId(command.getArticleId());
        if (existing == null) {
            throw new ApplicationException(ErrorCode.ARTICLE_NOT_FOUND);
        }

        Article article = updateArticleFromCommand(existing, command);
        int result = articleService.updateArticle(article);
        if (result <= 0) {
            throw new ApplicationException(ErrorCode.SYSTEM_ERROR, "文章更新失败");
        }

        if (command.getAuthors() != null) {
            articleAuthorService.deleteAuthorsByArticleId(command.getArticleId());
            saveAuthors(command.getArticleId(), command.getAuthors());
        }

        Article updated = articleService.selectArticleByPaperId(command.getArticleId());
        return articleConverter.toDetailDTO(updated);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteArticle(Long articleId, Long operatorId) {
        if (articleId == null) {
            throw new ApplicationException(ErrorCode.PARAM_ERROR, "文章ID不能为空");
        }

        Article article = articleService.selectArticleByPaperId(articleId);
        if (article == null) {
            throw new ApplicationException(ErrorCode.ARTICLE_NOT_FOUND);
        }

        int result = articleService.deleteArticleById(articleId);
        if (result <= 0) {
            throw new ApplicationException(ErrorCode.SYSTEM_ERROR, "文章删除失败");
        }

        log.info("文章已删除: articleId={}, operatorId={}", articleId, operatorId);
    }

    private void validateSubmitCommand(SubmitArticleCommand command) {
        if (command == null) {
            throw new ApplicationException(ErrorCode.PARAM_ERROR, "提交命令不能为空");
        }
        if (command.getTitle() == null || command.getTitle().isBlank()) {
            throw new ApplicationException(ErrorCode.PARAM_ERROR, "文章标题不能为空");
        }
        if (command.getAbstractText() == null || command.getAbstractText().isBlank()) {
            throw new ApplicationException(ErrorCode.PARAM_ERROR, "文章摘要不能为空");
        }
        if (command.getJournalId() == null) {
            throw new ApplicationException(ErrorCode.PARAM_ERROR, "期刊ID不能为空");
        }
        if (command.getAuthors() == null || command.getAuthors().isEmpty()) {
            throw new ApplicationException(ErrorCode.PARAM_ERROR, "文章必须至少有一位作者");
        }
    }

    private Article buildArticleFromCommand(SubmitArticleCommand command) {
        Article article = new Article();
        article.setArticleTitle(command.getTitle());
        article.setArticleAbstract(command.getAbstractText());
        article.setArticleKeywords(command.getKeywords());
        article.setArticleCoverLetter(command.getCoverLetter());
        article.setArticleJournalId(command.getJournalId());
        article.setArticleManuscriptTypeId(command.getManuscriptTypeId());
        return article;
    }

    private Article updateArticleFromCommand(Article existing, UpdateArticleCommand command) {
        if (command.getTitle() != null) {
            existing.setArticleTitle(command.getTitle());
        }
        if (command.getAbstractText() != null) {
            existing.setArticleAbstract(command.getAbstractText());
        }
        if (command.getKeywords() != null) {
            existing.setArticleKeywords(command.getKeywords());
        }
        if (command.getCoverLetter() != null) {
            existing.setArticleCoverLetter(command.getCoverLetter());
        }
        if (command.getWordCount() != null) {
            existing.setArticleWordCount(command.getWordCount());
        }
        if (command.getFigureCount() != null) {
            existing.setArticleFigureCount(command.getFigureCount());
        }
        if (command.getTableCount() != null) {
            existing.setArticleTableCount(command.getTableCount());
        }
        return existing;
    }

    private void saveAuthors(Long articleId, List<SubmitArticleCommand.AuthorInfo> authorInfos) {
        List<ArticleAuthor> authors = authorInfos.stream()
                .map(info -> {
                    ArticleAuthor author = new ArticleAuthor();
                    author.setAuthorArticleId(articleId);
                    author.setAuthorUserId(info.getUserId());
                    author.setAuthorContribution(info.getContribution());
                    author.setAuthorOrder(info.getSequence());
                    author.setAuthorIsCorresponding(Boolean.TRUE.equals(info.getIsCorresponding()) ? 1 : 0);
                    return author;
                })
                .collect(Collectors.toList());

        authors.forEach(articleAuthorService::insertAuthor);
    }
}
