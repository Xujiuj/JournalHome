package com.kstt.articles.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kstt.articles.entity.ArticleReview;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 文章评审Mapper接口
 */
@Mapper
public interface ArticleReviewMapper extends BaseMapper<ArticleReview> {
    
    /**
     * 查询文章评审列表
     * 
     * @param articleReview 文章评审信息
     * @return 文章评审集合
     */
    List<ArticleReview> selectReviewList(ArticleReview articleReview);
    
    /**
     * 根据评审ID查询文章评审信息
     * 
     * @param reviewId 评审ID
     * @return 文章评审信息
     */
    ArticleReview selectReviewByReviewId(Long reviewId);
    
    /**
     * 新增文章评审
     * 
     * @param articleReview 文章评审信息
     * @return 结果
     */
    int insertReview(ArticleReview articleReview);
    
    /**
     * 修改文章评审
     * 
     * @param articleReview 文章评审信息
     * @return 结果
     */
    int updateReview(ArticleReview articleReview);
    
    /**
     * 删除文章评审
     * 
     * @param reviewId 评审ID
     * @return 结果
     */
    int deleteReviewById(Long reviewId);
    
    /**
     * 批量删除文章评审
     * 
     * @param reviewIds 需要删除的评审ID
     * @return 结果
     */
    int deleteReviewByIds(Long[] reviewIds);
}
