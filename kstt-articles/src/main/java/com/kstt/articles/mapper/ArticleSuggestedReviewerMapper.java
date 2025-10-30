package com.kstt.articles.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kstt.articles.entity.ArticleSuggestedReviewer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 建议审稿人Mapper接口
 */
@Mapper
public interface ArticleSuggestedReviewerMapper extends BaseMapper<ArticleSuggestedReviewer> {

    /**
     * 根据文章ID查询建议审稿人列表
     */
    List<ArticleSuggestedReviewer> selectByArticleId(@Param("articleId") Long articleId);

    /**
     * 根据用户ID查询建议审稿人记录
     */
    List<ArticleSuggestedReviewer> selectByReviewerUserId(@Param("reviewerUserId") Long reviewerUserId);

    /**
     * 删除文章的所有建议审稿人
     */
    int deleteByArticleId(@Param("articleId") Long articleId);

    /**
     * 批量插入建议审稿人
     */
    int batchInsert(@Param("list") List<ArticleSuggestedReviewer> list);
}
