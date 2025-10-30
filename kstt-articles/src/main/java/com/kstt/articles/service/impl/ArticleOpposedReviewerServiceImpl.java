package com.kstt.articles.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kstt.articles.entity.Article;
import com.kstt.articles.entity.ArticleOpposedReviewer;
import com.kstt.articles.mapper.ArticleMapper;
import com.kstt.articles.mapper.ArticleOpposedReviewerMapper;
import com.kstt.articles.service.ArticleOpposedReviewerService;
import com.kstt.sys.entity.SysUser;
import com.kstt.sys.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 避免审稿人Service实现类
 */
@Service
public class ArticleOpposedReviewerServiceImpl extends ServiceImpl<ArticleOpposedReviewerMapper, ArticleOpposedReviewer> implements ArticleOpposedReviewerService {

    @Autowired
    private ArticleOpposedReviewerMapper mapper;
    
    @Autowired
    private ArticleMapper articleMapper;
    
    @Autowired
    private SysUserMapper sysUserMapper;
    
    /**
     * 填充避免审稿人关联对象
     */
    private void fillOpposedReviewerAssociations(ArticleOpposedReviewer reviewer) {
        if (reviewer == null) {
            return;
        }
        
        // 填充文章信息
        if (reviewer.getArticleId() != null) {
            Article article = articleMapper.selectById(reviewer.getArticleId());
            reviewer.setArticle(article);
        }
        
        // 填充审稿人用户信息
        if (reviewer.getReviewerUserId() != null) {
            SysUser user = sysUserMapper.selectById(reviewer.getReviewerUserId());
            reviewer.setReviewerUser(user);
        }
    }

    @Override
    public List<ArticleOpposedReviewer> selectByArticleId(Long articleId) {
        List<ArticleOpposedReviewer> list = mapper.selectByArticleId(articleId);
        list.forEach(this::fillOpposedReviewerAssociations);
        return list;
    }

    @Override
    public List<ArticleOpposedReviewer> selectByReviewerUserId(Long reviewerUserId) {
        List<ArticleOpposedReviewer> list = mapper.selectByReviewerUserId(reviewerUserId);
        list.forEach(this::fillOpposedReviewerAssociations);
        return list;
    }

    @Override
    @Transactional
    public int insertOpposedReviewer(ArticleOpposedReviewer reviewer) {
        return mapper.insert(reviewer);
    }

    @Override
    @Transactional
    public int batchInsertOpposedReviewers(List<ArticleOpposedReviewer> reviewers) {
        return mapper.batchInsert(reviewers);
    }

    @Override
    @Transactional
    public int deleteByArticleId(Long articleId) {
        return mapper.deleteByArticleId(articleId);
    }
}
