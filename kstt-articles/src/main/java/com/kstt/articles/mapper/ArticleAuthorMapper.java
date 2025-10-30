package com.kstt.articles.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kstt.articles.entity.ArticleAuthor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 论文作者关联Mapper接口
 */
@Mapper
public interface ArticleAuthorMapper extends BaseMapper<ArticleAuthor> {
    
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
    ArticleAuthor selectAuthorByAuthorId(@Param("authorId") Long authorId);
    
    /**
     * 根据论文ID查询作者列表
     */
    List<ArticleAuthor> selectAuthorsByArticleId(Long articleId);
    
    /**
     * 根据论文ID集合批量查询作者列表
     */
    List<ArticleAuthor> selectAuthorsByArticleIds(@Param("articleIds") List<Long> articleIds);
    
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
    int deleteAuthorByIds(@Param("authorIds") Long[] authorIds);
    
    /**
     * 删除论文作者信息
     */
    int deleteAuthorById(@Param("authorId") Long authorId);
    
    /**
     * 根据论文ID删除所有作者
     */
    int deleteAuthorsByArticleId(@Param("articleId") Long articleId);
}
