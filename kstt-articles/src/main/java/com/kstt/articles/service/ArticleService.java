package com.kstt.articles.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kstt.articles.entity.Article;

import java.util.List;
import java.util.Map;

/**
 * 文章Service接口
 * 
 * @author kstt
 * @date 2025-01-01
 */
public interface ArticleService extends IService<Article> {
    
    /**
     * 查询文章列表
     * 
     * @param article 文章信息
     * @return 文章集合
     */
    List<Article> selectArticleList(Article article);
    
    /**
     * 根据文章ID查询文章信息
     * 
     * @param paperId 文章ID
     * @return 文章信息
     */
    Article selectArticleByPaperId(Long paperId);
    
    /**
     * 根据稿件编号查询文章信息
     * 
     * @param manuscriptId 稿件编号
     * @return 文章信息
     */
    Article selectArticleByManuscriptId(String manuscriptId);
    
    /**
     * 新增文章
     * 
     * @param article 文章信息
     * @return 结果
     */
    int insertArticle(Article article);
    
    /**
     * 修改文章
     * 
     * @param article 文章信息
     * @return 结果
     */
    int updateArticle(Article article);
    
    /**
     * 批量删除文章
     * 
     * @param paperIds 需要删除的文章ID
     * @return 结果
     */
    int deleteArticleByIds(Long[] paperIds);
    
    /**
     * 删除文章信息
     *
     * @param paperId 文章ID
     * @return 结果
     */
    int deleteArticleById(Long paperId);

    /**
     * 按期刊统计文章数量
     */
    Map<Long, Long> countByJournalIds(List<Long> journalIds);

    List<Article> selectArticleLastList(Integer limits);

     /**
      * 生成稿件编号
      */
     String generateManuscriptId();
}
