package com.kstt.articles.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kstt.articles.entity.Article;
import com.kstt.articles.entity.ArticleSuggestedReviewer;
import com.kstt.articles.mapper.ArticleMapper;
import com.kstt.articles.mapper.ArticleSuggestedReviewerMapper;
import com.kstt.articles.service.ArticleSuggestedReviewerService;
import com.kstt.sys.entity.SysUser;
import com.kstt.sys.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 建议审稿人Service实现类
 */
@Service
public class ArticleSuggestedReviewerServiceImpl extends ServiceImpl<ArticleSuggestedReviewerMapper, ArticleSuggestedReviewer> implements ArticleSuggestedReviewerService {

    @Autowired
    private ArticleSuggestedReviewerMapper mapper;
    
    @Autowired
    private ArticleMapper articleMapper;
    
    @Autowired
    private SysUserMapper sysUserMapper;
    
    /**
     * 填充建议审稿人关联对象
     */
    private void fillSuggestedReviewerAssociations(ArticleSuggestedReviewer reviewer) {
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
    public List<ArticleSuggestedReviewer> selectByArticleId(Long articleId) {
        List<ArticleSuggestedReviewer> list = mapper.selectByArticleId(articleId);
        list.forEach(this::fillSuggestedReviewerAssociations);
        return list;
    }

    @Override
    public List<ArticleSuggestedReviewer> selectByReviewerUserId(Long reviewerUserId) {
        List<ArticleSuggestedReviewer> list = mapper.selectByReviewerUserId(reviewerUserId);
        list.forEach(this::fillSuggestedReviewerAssociations);
        return list;
    }

    @Override
    @Transactional
    public int insertSuggestedReviewer(ArticleSuggestedReviewer reviewer) {
        return mapper.insert(reviewer);
    }

    @Override
    @Transactional
    public int batchInsertSuggestedReviewers(List<ArticleSuggestedReviewer> reviewers) {
        return mapper.batchInsert(reviewers);
    }

    @Override
    @Transactional
    public int deleteByArticleId(Long articleId) {
        return mapper.deleteByArticleId(articleId);
    }

    @Override
    @Transactional
    public int updateStatus(Long id, Integer status) {
        ArticleSuggestedReviewer reviewer = new ArticleSuggestedReviewer();
        reviewer.setId(id);
        reviewer.setStatus(status);
        return mapper.updateById(reviewer);
    }
}
