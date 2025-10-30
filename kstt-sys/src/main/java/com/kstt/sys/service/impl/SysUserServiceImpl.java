package com.kstt.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kstt.sys.entity.SysRole;
import com.kstt.sys.entity.SysUser;
import com.kstt.sys.mapper.SysRoleMapper;
import com.kstt.sys.mapper.SysUserMapper;
import com.kstt.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 用户信息Service实现类
 * 
 * @author kstt
 * @date 2025-10-23
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    
    @Autowired
    private SysUserMapper sysUserMapper;
    
    @Autowired
    private SysRoleMapper sysRoleMapper;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    private void enrichUsers(List<SysUser> users) {
        if (CollectionUtils.isEmpty(users)) {
            return;
        }

        Set<Long> roleIds = users.stream()
                .map(SysUser::getUserRoleId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        Map<Long, SysRole> roleMap = roleIds.isEmpty() ? Collections.emptyMap() :
                sysRoleMapper.selectBatchIds(roleIds).stream()
                        .collect(Collectors.toMap(SysRole::getRoleId, Function.identity()));

        for (SysUser user : users) {
            if (user == null) {
                continue;
            }
            if (user.getUserRoleId() != null) {
                user.setUserRole(roleMap.get(user.getUserRoleId()));
            }
        }
    }
    
    /**
     * 查询用户列表
     * 
     * @param user 用户信息
     * @return 用户集合
     */
    @Override
    public List<SysUser> selectUserList(SysUser user) {
        List<SysUser> list = sysUserMapper.selectUserList(user);
        enrichUsers(list);
        return list;
    }
    
    /**
     * 根据用户ID查询用户信息
     * 
     * @param userId 用户ID
     * @return 用户信息
     */
    @Override
    public SysUser selectUserByUserId(Long userId) {
        SysUser user = sysUserMapper.selectUserByUserId(userId);
        if (user != null) {
            enrichUsers(Collections.singletonList(user));
        }
        return user;
    }
    
    /**
     * 根据用户名获取用户
     */
    @Override
    public SysUser getByUsername(String username) {
        return lambdaQuery()
                .eq(SysUser::getUserName, username)
                .one();
    }
    
    /**
     * 根据邮箱获取用户
     */
    @Override
    public SysUser getByEmail(String email) {
        return lambdaQuery()
                .eq(SysUser::getUserEmail, email)
                .one();
    }
    
    /**
     * 重置用户密码
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int resetUserPwd(SysUser user) {
        String encodedPassword = passwordEncoder.encode(user.getUserPassword());
        user.setUserPassword(encodedPassword);
        return updateById(user) ? 1 : 0;
    }
    
    /**
     * 修改用户状态
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateUserStatus(SysUser user) {
        return updateById(user) ? 1 : 0;
    }
    
    /**
     * 新增用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertUser(SysUser user) {
        if (StringUtils.hasText(user.getUserPassword())) {
            user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        }
        return sysUserMapper.insertSysUser(user);
    }
    
    /**
     * 修改用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateUser(SysUser user) {
        if (StringUtils.hasText(user.getUserPassword())) {
            SysUser existing = user.getUserId() != null ? sysUserMapper.selectUserByUserId(user.getUserId()) : null;
            if (existing == null) {
                user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
            } else if (StringUtils.hasText(existing.getUserPassword())) {
                if (user.getUserPassword().equals(existing.getUserPassword())) {
                    // 已经是编码后的密码，直接复用
                    user.setUserPassword(existing.getUserPassword());
                } else if (passwordEncoder.matches(user.getUserPassword(), existing.getUserPassword())) {
                    user.setUserPassword(existing.getUserPassword());
                } else {
                    user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
                }
            } else {
                user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
            }
        }
        return sysUserMapper.updateUser(user);
    }
    
    /**
     * 批量删除用户信息
     * 
     * @param userIds 需要删除的用户ID
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteUserByUserIds(Long[] userIds) {
        return sysUserMapper.deleteUserByUserIds(userIds);
    }
    
    /**
     * 删除用户信息
     * 
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteUserByUserId(Long userId) {
        return sysUserMapper.deleteUserByUserId(userId);
    }
    
}