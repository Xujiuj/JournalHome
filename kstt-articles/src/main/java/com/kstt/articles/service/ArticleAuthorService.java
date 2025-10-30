package com.kstt.articles.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kstt.articles.entity.ArticleAuthor;

import java.util.List;

/**
 * 论文作者关联Service接口
 */
public interface ArticleAuthorService extends IService<ArticleAuthor> {
    
    /**
     * 查询论文作者列表
     */
    List<ArticleAuthor> selectAuthorList(ArticleAuthor articleAuthor);
    
    /**
     * 根据作者ID查询作者信息
     * 
     * @param authorId 作者ID
     * @return 作者信息
     */
    ArticleAuthor selectAuthorByAuthorId(Long authorId);
    
    /**
     * 根据论文ID查询作者列表
     */
    List<ArticleAuthor> getAuthorsByArticleId(Long articleId);
    
    /**
     * 新增论文作者
     */
    int insertAuthor(ArticleAuthor articleAuthor);
    
    /**
     * 修改论文作者
     */
    int updateAuthor(ArticleAuthor articleAuthor);
    
    /**
     * 批量删除论文作者
     */
    int deleteAuthorByIds(Long[] authorIds);
    
    /**
     * 删除论文作者信息
     */
    int deleteAuthorById(Long authorId);
    
    /**
     * 根据论文ID删除所有作者
     */
    int deleteAuthorsByArticleId(Long articleId);
    
    /**
     * 批量保存论文作者
     */
    int saveAuthorsForArticle(Long articleId, List<ArticleAuthor> authors);
    
}
