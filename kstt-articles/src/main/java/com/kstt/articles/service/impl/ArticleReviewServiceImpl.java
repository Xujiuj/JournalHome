package com.kstt.articles.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kstt.articles.entity.ArticleReview;
import com.kstt.articles.entity.ArticleReviewInvitation;
import com.kstt.articles.mapper.ArticleReviewInvitationMapper;
import com.kstt.articles.mapper.ArticleReviewMapper;
import com.kstt.articles.service.ArticleReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 文章评审Service实现类
 */
@Service
public class ArticleReviewServiceImpl extends ServiceImpl<ArticleReviewMapper, ArticleReview> implements ArticleReviewService {
    
    @Autowired
    private ArticleReviewMapper articleReviewMapper;
    
    @Autowired
    private ArticleReviewInvitationMapper articleReviewInvitationMapper;
    
    private void enrichReviewInvitations(List<ArticleReview> reviews) {
        if (reviews == null || reviews.isEmpty()) {
            return;
        }

        Set<Long> invitationIds = reviews.stream()
                .map(ArticleReview::getReviewInvitationId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        if (invitationIds.isEmpty()) {
            return;
        }

        Map<Long, ArticleReviewInvitation> invitationMap = articleReviewInvitationMapper.selectBatchIds(invitationIds).stream()
                .collect(Collectors.toMap(ArticleReviewInvitation::getInvitationId, Function.identity()));

        reviews.forEach(review -> {
            if (review.getReviewInvitationId() != null) {
                review.setReviewInvitation(invitationMap.get(review.getReviewInvitationId()));
            }
        });
    }
    
    @Override
    public List<ArticleReview> selectReviewList(ArticleReview articleReview) {
        List<ArticleReview> list = articleReviewMapper.selectReviewList(articleReview);
        enrichReviewInvitations(list);
        return list;
    }
    
    @Override
    public ArticleReview selectReviewByReviewId(Long reviewId) {
        ArticleReview review = articleReviewMapper.selectReviewByReviewId(reviewId);
        enrichReviewInvitations(review == null ? Collections.emptyList() : List.of(review));
        return review;
    }
    
    @Override
    public int insertReview(ArticleReview articleReview) {
        return articleReviewMapper.insertReview(articleReview);
    }
    
    @Override
    public int updateReview(ArticleReview articleReview) {
        return articleReviewMapper.updateReview(articleReview);
    }
    
    @Override
    public int deleteReviewById(Long reviewId) {
        return articleReviewMapper.deleteReviewById(reviewId);
    }
    
    @Override
    public int deleteReviewByIds(Long[] reviewIds) {
        return articleReviewMapper.deleteReviewByIds(reviewIds);
    }
}
