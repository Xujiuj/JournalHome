package com.kstt.articles.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kstt.articles.entity.ArticleReviewInvitation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 评审邀请Mapper接口
 */
@Mapper
public interface ArticleReviewInvitationMapper extends BaseMapper<ArticleReviewInvitation> {
    
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
    ArticleReviewInvitation selectInvitationByInvitationId(@Param("invitationId") Long invitationId);
    
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
    int deleteInvitationByIds(@Param("invitationIds") Long[] invitationIds);
    
    /**
     * 删除评审邀请信息
     */
    int deleteInvitationById(@Param("invitationId") Long invitationId);
    
}

