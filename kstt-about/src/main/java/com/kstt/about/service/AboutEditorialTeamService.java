package com.kstt.about.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kstt.about.entity.AboutEditorialTeam;

import java.util.List;

/**
 * 关于我们-编辑团队Service接口
 */
public interface AboutEditorialTeamService extends IService<AboutEditorialTeam> {
    
    /**
     * 查询编辑团队列表
     */
    List<AboutEditorialTeam> selectAboutEditorialTeamList(AboutEditorialTeam aboutEditorialTeam);
    
    /**
     * 根据团队ID查询编辑团队信息
     * 
     * @param teamId 团队ID
     * @return 编辑团队信息
     */
    AboutEditorialTeam selectAboutEditorialTeamByTeamId(Long teamId);
    
    /**
     * 新增编辑团队
     */
    int insertAboutEditorialTeam(AboutEditorialTeam aboutEditorialTeam);
    
    /**
     * 修改编辑团队
     */
    int updateAboutEditorialTeam(AboutEditorialTeam aboutEditorialTeam);
    
    /**
     * 删除编辑团队
     */
    int deleteAboutEditorialTeamById(Long teamId);
    
    /**
     * 批量删除编辑团队
     */
    int deleteAboutEditorialTeamByTeamIds(Long[] teamIds);
}