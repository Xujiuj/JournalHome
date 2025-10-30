package com.kstt.application.articles.service;

import com.kstt.application.articles.command.SubmitArticleCommand;
import com.kstt.application.articles.command.UpdateArticleCommand;
import com.kstt.application.articles.dto.ArticleDetailDTO;

/**
 * 文章命令服务
 */
public interface ArticleCommandService {

    /**
     * 提交文章
     */
    ArticleDetailDTO submitArticle(SubmitArticleCommand command);

    /**
     * 更新文章
     */
    ArticleDetailDTO updateArticle(UpdateArticleCommand command);

    /**
     * 删除文章
     */
    void deleteArticle(Long articleId, Long operatorId);
}
