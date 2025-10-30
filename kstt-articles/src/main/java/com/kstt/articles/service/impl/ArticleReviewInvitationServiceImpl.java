package com.kstt.articles.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kstt.articles.entity.ArticleProcess;
import com.kstt.articles.entity.ArticleReviewInvitation;
import com.kstt.articles.mapper.ArticleProcessMapper;
import com.kstt.articles.mapper.ArticleReviewInvitationMapper;
import com.kstt.articles.service.ArticleReviewInvitationService;
import com.kstt.sys.entity.SysUser;
import com.kstt.sys.mapper.SysUserMapper;
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
 * 评审邀请Service实现类
 */
@Service
public class ArticleReviewInvitationServiceImpl extends ServiceImpl<ArticleReviewInvitationMapper, ArticleReviewInvitation> implements ArticleReviewInvitationService {
    
    @Autowired
    private ArticleReviewInvitationMapper articleReviewInvitationMapper;
    
    @Autowired
    private ArticleProcessMapper articleProcessMapper;
    
    @Autowired
    private SysUserMapper sysUserMapper;
    
    private void enrichInvitationAssociations(List<ArticleReviewInvitation> invitations) {
        if (invitations == null || invitations.isEmpty()) {
            return;
        }

        Set<Long> processIds = invitations.stream()
                .map(ArticleReviewInvitation::getInvitationProcessId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
        Map<Long, ArticleProcess> processMap = processIds.isEmpty() ? Collections.emptyMap()
                : articleProcessMapper.selectBatchIds(processIds).stream()
                .collect(Collectors.toMap(ArticleProcess::getProcessId, Function.identity()));

        Set<Long> reviewerIds = invitations.stream()
                .map(ArticleReviewInvitation::getInvitationReviewerId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
        Map<Long, SysUser> reviewerMap = reviewerIds.isEmpty() ? Collections.emptyMap()
                : sysUserMapper.selectBatchIds(reviewerIds).stream()
                .collect(Collectors.toMap(SysUser::getUserId, Function.identity()));

        invitations.forEach(invitation -> {
            if (invitation.getInvitationProcessId() != null) {
                invitation.setInvitationProcess(processMap.get(invitation.getInvitationProcessId()));
            }
            if (invitation.getInvitationReviewerId() != null) {
                invitation.setInvitationReviewer(reviewerMap.get(invitation.getInvitationReviewerId()));
            }
        });
    }
    
    @Override
    public List<ArticleReviewInvitation> selectInvitationList(ArticleReviewInvitation articleReviewInvitation) {
        List<ArticleReviewInvitation> list = articleReviewInvitationMapper.selectInvitationList(articleReviewInvitation);
        enrichInvitationAssociations(list);
        return list;
    }
    
    @Override
    public ArticleReviewInvitation selectInvitationByInvitationId(Long invitationId) {
        ArticleReviewInvitation invitation = articleReviewInvitationMapper.selectInvitationByInvitationId(invitationId);
        enrichInvitationAssociations(invitation == null ? Collections.emptyList() : List.of(invitation));
        return invitation;
    }
    
    @Override
    public int insertInvitation(ArticleReviewInvitation articleReviewInvitation) {
        return articleReviewInvitationMapper.insertInvitation(articleReviewInvitation);
    }
    
    @Override
    public int updateInvitation(ArticleReviewInvitation articleReviewInvitation) {
        return articleReviewInvitationMapper.updateInvitation(articleReviewInvitation);
    }
    
    @Override
    public int deleteInvitationByIds(Long[] invitationIds) {
        return articleReviewInvitationMapper.deleteInvitationByIds(invitationIds);
    }
    
    @Override
    public int deleteInvitationById(Long invitationId) {
        return articleReviewInvitationMapper.deleteInvitationById(invitationId);
    }
    
}

