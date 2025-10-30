package com.kstt.articles.service.impl;

import com.kstt.articles.entity.ArticleKeywords;
import com.kstt.articles.mapper.ArticleKeywordsMapper;
import com.kstt.articles.service.ArticleKeywordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文章关键词Service业务层处理
 */
@Service
public class ArticleKeywordsServiceImpl implements ArticleKeywordsService {

    @Autowired
    private ArticleKeywordsMapper keywordMapper;

    /**
     * 查询关键词列表
     */
    @Override
    public List<ArticleKeywords> selectKeywordList(ArticleKeywords keyword) {
        return keywordMapper.selectKeywordList(keyword);
    }

    /**
     * 根据关键词ID查询关键词
     */
    @Override
    public ArticleKeywords selectKeywordByKeywordId(Long keywordId) {
        return keywordMapper.selectKeywordByKeywordId(keywordId);
    }

    /**
     * 根据文章ID查询关键词列表
     */
    @Override
    public List<ArticleKeywords> selectKeywordsByArticleId(Long articleId) {
        return keywordMapper.selectKeywordsByArticleId(articleId);
    }

    /**
     * 新增关键词
     */
    @Override
    public int insertKeyword(ArticleKeywords keyword) {
        return keywordMapper.insertKeyword(keyword);
    }

    /**
     * 修改关键词
     */
    @Override
    public int updateKeyword(ArticleKeywords keyword) {
        return keywordMapper.updateKeyword(keyword);
    }

    /**
     * 删除关键词
     */
    @Override
    public int deleteKeywordById(Long keywordId) {
        return keywordMapper.deleteKeywordById(keywordId);
    }

    /**
     * 批量删除关键词
     */
    @Override
    public int deleteKeywordByIds(Long[] keywordIds) {
        return keywordMapper.deleteKeywordByIds(keywordIds);
    }

    /**
     * 根据文章ID删除关键词
     */
    @Override
    public int deleteKeywordsByArticleId(Long articleId) {
        return keywordMapper.deleteKeywordsByArticleId(articleId);
    }
}

