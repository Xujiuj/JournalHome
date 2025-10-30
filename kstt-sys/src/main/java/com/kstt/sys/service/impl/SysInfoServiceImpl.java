package com.kstt.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kstt.sys.entity.SysInfo;
import com.kstt.sys.mapper.SysInfoMapper;
import com.kstt.sys.service.SysInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统信息Service实现类
 */
@Service
public class SysInfoServiceImpl extends ServiceImpl<SysInfoMapper, SysInfo> implements SysInfoService {
    
    @Autowired
    private SysInfoMapper sysInfoMapper;
    
    /**
     * 查询系统信息列表
     */
    @Override
    public List<SysInfo> selectSysInfoList(SysInfo sysInfo) {
        return sysInfoMapper.selectSysInfoList(sysInfo);
    }
    
    /**
     * 根据系统信息ID查询系统信息
     * 
     * @param infoId 系统信息ID
     * @return 系统信息
     */
    @Override
    @Cacheable(value = "sysInfo", key = "#infoId", unless = "#result == null")
    public SysInfo selectSysInfoById(Long infoId) {
        return sysInfoMapper.selectSysInfoById(infoId);
    }
    
    /**
     * 新增系统信息
     * 
     * @param sysInfo 系统信息
     * @return 结果
     */
    @Override
    @CacheEvict(value = "sysInfo", allEntries = true)
    public int insertSysInfo(SysInfo sysInfo) {
        return sysInfoMapper.insertSysInfo(sysInfo);
    }
    
    /**
     * 修改系统信息
     * 
     * @param sysInfo 系统信息
     * @return 结果
     */
    @Override
    @CacheEvict(value = "sysInfo", allEntries = true)
    public int updateSysInfo(SysInfo sysInfo) {
        return sysInfoMapper.updateSysInfo(sysInfo);
    }
    
    /**
     * 删除系统信息
     * 
     * @param infoId 系统信息ID
     * @return 结果
     */
    @Override
    public int deleteSysInfoById(Long infoId) {
        return sysInfoMapper.deleteSysInfoById(infoId);
    }
    
    /**
     * 批量删除系统信息
     * 
     * @param infoIds 需要删除的系统信息ID
     * @return 结果
     */
    @Override
    public int deleteSysInfoByIds(Long[] infoIds) {
        return sysInfoMapper.deleteSysInfoByIds(infoIds);
    }
    
}