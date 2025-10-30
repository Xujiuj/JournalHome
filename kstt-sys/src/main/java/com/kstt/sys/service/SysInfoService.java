package com.kstt.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kstt.sys.entity.SysInfo;

import java.util.List;

/**
 * 系统信息Service接口
 */
public interface SysInfoService extends IService<SysInfo> {
    
    /**
     * 查询系统信息列表
     */
    List<SysInfo> selectSysInfoList(SysInfo sysInfo);
    
    /**
     * 根据系统信息ID查询系统信息
     * 
     * @param infoId 系统信息ID
     * @return 系统信息
     */
    SysInfo selectSysInfoById(Long infoId);
    
    /**
     * 新增系统信息
     * 
     * @param sysInfo 系统信息
     * @return 结果
     */
    int insertSysInfo(SysInfo sysInfo);
    
    /**
     * 修改系统信息
     * 
     * @param sysInfo 系统信息
     * @return 结果
     */
    int updateSysInfo(SysInfo sysInfo);
    
    /**
     * 删除系统信息
     * 
     * @param infoId 系统信息ID
     * @return 结果
     */
    int deleteSysInfoById(Long infoId);
    
    /**
     * 批量删除系统信息
     * 
     * @param infoIds 需要删除的系统信息ID
     * @return 结果
     */
    int deleteSysInfoByIds(Long[] infoIds);
    
}

