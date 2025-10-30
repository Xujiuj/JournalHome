package com.kstt.about.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kstt.about.entity.AboutEditorialTeam;
import com.kstt.about.mapper.AboutEditorialTeamMapper;
import com.kstt.about.service.AboutEditorialTeamService;
import com.kstt.sys.entity.SysUser;
import com.kstt.sys.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 关于我们-编辑团队Service实现类
 */
@Service
public class AboutEditorialTeamServiceImpl extends ServiceImpl<AboutEditorialTeamMapper, AboutEditorialTeam> implements AboutEditorialTeamService {
    
    @Autowired
    private AboutEditorialTeamMapper aboutEditorialTeamMapper;
    
    @Autowired
    private SysUserMapper sysUserMapper;
    
    /**
     * 填充编辑团队关联对象
     */
    private void fillTeamAssociations(AboutEditorialTeam team) {
        if (team == null) {
            return;
        }
        
        // 填充用户信息
        if (team.getTeamUserId() != null) {
            SysUser user = sysUserMapper.selectById(team.getTeamUserId());
            team.setTeamUser(user);
        }
    }
    
    @Override
    public List<AboutEditorialTeam> selectAboutEditorialTeamList(AboutEditorialTeam aboutEditorialTeam) {
        List<AboutEditorialTeam> list = aboutEditorialTeamMapper.selectAboutEditorialTeamList(aboutEditorialTeam);
        list.forEach(this::fillTeamAssociations);
        return list;
    }
    
    @Override
    public AboutEditorialTeam selectAboutEditorialTeamByTeamId(Long teamId) {
        AboutEditorialTeam team = aboutEditorialTeamMapper.selectAboutEditorialTeamByTeamId(teamId);
        fillTeamAssociations(team);
        return team;
    }
    
    @Override
    public int insertAboutEditorialTeam(AboutEditorialTeam aboutEditorialTeam) {
        return aboutEditorialTeamMapper.insertAboutEditorialTeam(aboutEditorialTeam);
    }
    
    @Override
    public int updateAboutEditorialTeam(AboutEditorialTeam aboutEditorialTeam) {
        return aboutEditorialTeamMapper.updateAboutEditorialTeam(aboutEditorialTeam);
    }
    
    @Override
    public int deleteAboutEditorialTeamByTeamIds(Long[] teamIds) {
        return aboutEditorialTeamMapper.deleteAboutEditorialTeamByTeamIds(teamIds);
    }
    
    @Override
    public int deleteAboutEditorialTeamById(Long teamId) {
        return aboutEditorialTeamMapper.deleteAboutEditorialTeamById(teamId);
    }
}