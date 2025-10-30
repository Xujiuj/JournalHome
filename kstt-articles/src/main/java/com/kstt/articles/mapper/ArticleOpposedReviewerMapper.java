package com.kstt.articles.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kstt.articles.entity.ArticleOpposedReviewer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 避免审稿人Mapper接口
 */
@Mapper
public interface ArticleOpposedReviewerMapper extends BaseMapper<ArticleOpposedReviewer> {

    /**
     * 根据文章ID查询避免审稿人列表
     */
    List<ArticleOpposedReviewer> selectByArticleId(@Param("articleId") Long articleId);

    /**
     * 根据用户ID查询避免审稿人记录
     */
    List<ArticleOpposedReviewer> selectByReviewerUserId(@Param("reviewerUserId") Long reviewerUserId);

    /**
     * 删除文章的所有避免审稿人
     */
    int deleteByArticleId(@Param("articleId") Long articleId);

    /**
     * 批量插入避免审稿人
     */
    int batchInsert(@Param("list") List<ArticleOpposedReviewer> list);
}
