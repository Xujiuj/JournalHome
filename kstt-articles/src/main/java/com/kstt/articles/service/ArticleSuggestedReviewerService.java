package com.kstt.articles.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kstt.articles.entity.ArticleSuggestedReviewer;

import java.util.List;

/**
 * 建议审稿人Service接口
 */
public interface ArticleSuggestedReviewerService extends IService<ArticleSuggestedReviewer> {

    /**
     * 根据文章ID查询建议审稿人列表
     */
    List<ArticleSuggestedReviewer> selectByArticleId(Long articleId);

    /**
     * 根据用户ID查询建议审稿人记录
     */
    List<ArticleSuggestedReviewer> selectByReviewerUserId(Long reviewerUserId);

    /**
     * 新增建议审稿人
     */
    int insertSuggestedReviewer(ArticleSuggestedReviewer reviewer);

    /**
     * 批量新增建议审稿人
     */
    int batchInsertSuggestedReviewers(List<ArticleSuggestedReviewer> reviewers);

    /**
     * 删除文章的所有建议审稿人
     */
    int deleteByArticleId(Long articleId);

    /**
     * 更新建议审稿人状态
     */
    int updateStatus(Long id, Integer status);
}
