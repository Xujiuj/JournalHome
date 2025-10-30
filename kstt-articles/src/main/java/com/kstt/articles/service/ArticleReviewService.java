package com.kstt.articles.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kstt.articles.entity.ArticleReview;

import java.util.List;

/**
 * 文章评审Service接口
 */
public interface ArticleReviewService extends IService<ArticleReview> {
    
    /**
     * 查询评审列表
     */
    List<ArticleReview> selectReviewList(ArticleReview articleReview);
    
    /**
     * 根据评审ID查询评审信息
     * 
     * @param reviewId 评审ID
     * @return 评审信息
     */
    ArticleReview selectReviewByReviewId(Long reviewId);
    
    /**
     * 新增评审
     * 
     * @param articleReview 评审信息
     * @return 结果
     */
    int insertReview(ArticleReview articleReview);
    
    /**
     * 修改评审
     * 
     * @param articleReview 评审信息
     * @return 结果
     */
    int updateReview(ArticleReview articleReview);
    
    /**
     * 删除评审信息
     * 
     * @param reviewId 评审ID
     * @return 结果
     */
    int deleteReviewById(Long reviewId);
    
    /**
     * 批量删除评审信息
     * 
     * @param reviewIds 需要删除的评审ID
     * @return 结果
     */
    int deleteReviewByIds(Long[] reviewIds);
}
