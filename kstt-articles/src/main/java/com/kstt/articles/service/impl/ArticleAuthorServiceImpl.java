package com.kstt.articles.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kstt.articles.entity.Article;
import com.kstt.articles.entity.ArticleAuthor;
import com.kstt.articles.mapper.ArticleAuthorMapper;
import com.kstt.articles.mapper.ArticleMapper;
import com.kstt.articles.service.ArticleAuthorService;
import com.kstt.sys.entity.SysUser;
import com.kstt.sys.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 论文作者关联Service实现类
 */
@Service
public class ArticleAuthorServiceImpl extends ServiceImpl<ArticleAuthorMapper, ArticleAuthor> implements ArticleAuthorService {
    
    @Autowired
    private ArticleAuthorMapper articleAuthorMapper;
    
    @Autowired
    private SysUserMapper sysUserMapper;
    
    @Autowired
    private ArticleMapper articleMapper;
    
    /**
     * 填充作者关联对象
     */
    private void fillAuthorAssociations(ArticleAuthor author) {
        if (author == null) {
            return;
        }
        
        // 填充用户信息
        if (author.getAuthorUserId() != null) {
            SysUser user = sysUserMapper.selectById(author.getAuthorUserId());
            author.setAuthorUser(user);
        }
        
        // 填充文章信息
        if (author.getAuthorArticleId() != null) {
            Article article = articleMapper.selectById(author.getAuthorArticleId());
            author.setAuthorArticle(article);
        }
    }
    
    @Override
    public List<ArticleAuthor> selectAuthorList(ArticleAuthor articleAuthor) {
        List<ArticleAuthor> list = articleAuthorMapper.selectAuthorList(articleAuthor);
        list.forEach(this::fillAuthorAssociations);
        return list;
    }
    
    @Override
    public ArticleAuthor selectAuthorByAuthorId(Long authorId) {
        ArticleAuthor author = articleAuthorMapper.selectAuthorByAuthorId(authorId);
        fillAuthorAssociations(author);
        return author;
    }
    
    @Override
    public List<ArticleAuthor> getAuthorsByArticleId(Long articleId) {
        LambdaQueryWrapper<ArticleAuthor> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ArticleAuthor::getAuthorArticleId, articleId)
               .orderByAsc(ArticleAuthor::getAuthorOrder);
        List<ArticleAuthor> list = list(wrapper);
        list.forEach(this::fillAuthorAssociations);
        return list;
    }
    
    @Override
    public int insertAuthor(ArticleAuthor articleAuthor) {
        return articleAuthorMapper.insertAuthor(articleAuthor);
    }
    
    @Override
    public int updateAuthor(ArticleAuthor articleAuthor) {
        return articleAuthorMapper.updateAuthor(articleAuthor);
    }
    
    @Override
    public int deleteAuthorByIds(Long[] authorIds) {
        return articleAuthorMapper.deleteAuthorByIds(authorIds);
    }
    
    @Override
    public int deleteAuthorById(Long authorId) {
        return articleAuthorMapper.deleteAuthorById(authorId);
    }
    
    @Override
    public int deleteAuthorsByArticleId(Long articleId) {
        return articleAuthorMapper.deleteAuthorsByArticleId(articleId);
    }
    
    @Override
    @Transactional
    public int saveAuthorsForArticle(Long articleId, List<ArticleAuthor> authors) {
        // 先删除现有作者
        deleteAuthorsByArticleId(articleId);
        
        // 保存新作者
        int count = 0;
        for (ArticleAuthor author : authors) {
            author.setAuthorArticleId(articleId);
            count += insertAuthor(author);
        }
        return count;
    }
    
}
