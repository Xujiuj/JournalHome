package com.kstt.articles.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kstt.articles.entity.ArticleOpposedReviewer;

import java.util.List;

/**
 * 避免审稿人Service接口
 */
public interface ArticleOpposedReviewerService extends IService<ArticleOpposedReviewer> {

    /**
     * 根据文章ID查询避免审稿人列表
     */
    List<ArticleOpposedReviewer> selectByArticleId(Long articleId);

    /**
     * 根据用户ID查询避免审稿人记录
     */
    List<ArticleOpposedReviewer> selectByReviewerUserId(Long reviewerUserId);

    /**
     * 新增避免审稿人
     */
    int insertOpposedReviewer(ArticleOpposedReviewer reviewer);

    /**
     * 批量新增避免审稿人
     */
    int batchInsertOpposedReviewers(List<ArticleOpposedReviewer> reviewers);

    /**
     * 删除文章的所有避免审稿人
     */
    int deleteByArticleId(Long articleId);
}
