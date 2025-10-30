package com.kstt.articles.mapper;

import com.kstt.articles.entity.ArticleKeywords;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 文章关键词Mapper接口
 */
@Mapper
public interface ArticleKeywordsMapper {

    /**
     * 查询关键词列表
     */
    List<ArticleKeywords> selectKeywordList(ArticleKeywords keyword);

    /**
     * 根据关键词ID查询关键词
     */
    ArticleKeywords selectKeywordByKeywordId(Long keywordId);

    /**
     * 根据文章ID查询关键词列表
     */
    List<ArticleKeywords> selectKeywordsByArticleId(Long articleId);

    /**
     * 新增关键词
     */
    int insertKeyword(ArticleKeywords keyword);

    /**
     * 修改关键词
     */
    int updateKeyword(ArticleKeywords keyword);

    /**
     * 删除关键词
     */
    int deleteKeywordById(Long keywordId);

    /**
     * 批量删除关键词
     */
    int deleteKeywordByIds(Long[] keywordIds);

    /**
     * 根据文章ID删除关键词
     */
    int deleteKeywordsByArticleId(Long articleId);
}

