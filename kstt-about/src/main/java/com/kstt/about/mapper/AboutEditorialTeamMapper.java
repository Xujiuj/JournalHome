package com.kstt.about.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kstt.about.entity.AboutEditorialTeam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 关于我们-编辑团队Mapper接口
 */
@Mapper
public interface AboutEditorialTeamMapper extends BaseMapper<AboutEditorialTeam> {
    
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
    AboutEditorialTeam selectAboutEditorialTeamByTeamId(@Param("teamId") Long teamId);
    
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
    int deleteAboutEditorialTeamById(@Param("teamId") Long teamId);
    
    /**
     * 批量删除编辑团队
     */
    int deleteAboutEditorialTeamByTeamIds(@Param("teamIds") Long[] teamIds);
}