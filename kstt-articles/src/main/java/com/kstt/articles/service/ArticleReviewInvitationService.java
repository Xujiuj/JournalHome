package com.kstt.articles.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kstt.articles.entity.ArticleReviewInvitation;

import java.util.List;

/**
 * 评审邀请Service接口
 */
public interface ArticleReviewInvitationService extends IService<ArticleReviewInvitation> {
    
    /**
     * 查询评审邀请列表
     */
    List<ArticleReviewInvitation> selectInvitationList(ArticleReviewInvitation articleReviewInvitation);
    
    /**
     * 根据邀请ID查询邀请信息
     * 
     * @param invitationId 邀请ID
     * @return 邀请信息
     */
    ArticleReviewInvitation selectInvitationByInvitationId(Long invitationId);
    
    /**
     * 新增评审邀请
     */
    int insertInvitation(ArticleReviewInvitation articleReviewInvitation);
    
    /**
     * 修改评审邀请
     */
    int updateInvitation(ArticleReviewInvitation articleReviewInvitation);
    
    /**
     * 批量删除评审邀请
     */
    int deleteInvitationByIds(Long[] invitationIds);
    
    /**
     * 删除评审邀请信息
     */
    int deleteInvitationById(Long invitationId);
    
}

