package com.kstt.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kstt.sys.entity.SysArea;
import com.kstt.sys.mapper.SysAreaMapper;
import com.kstt.sys.service.SysAreaService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 学科领域信息Service业务层处理
 *
 * @author kstt
 * @date 2025-10-28
 */
@Service
@RequiredArgsConstructor
public class SysAreaServiceImpl extends ServiceImpl<SysAreaMapper, SysArea> implements SysAreaService {

    private final SysAreaMapper sysAreaMapper;

    /**
     * 查询领域列表
     *
     * @param area 领域信息
     * @return 领域集合
     */
    @Override
    public List<SysArea> selectAreaList(SysArea area) {
        return sysAreaMapper.selectAreaList(area);
    }

    /**
     * 根据领域ID查询领域信息
     *
     * @param areaId 领域ID
     * @return 领域信息
     */
    @Override
    @Cacheable(value = "sysArea", key = "#areaId", unless = "#result == null")
    public SysArea selectAreaByAreaId(Integer areaId) {
        return sysAreaMapper.selectAreaByAreaId(areaId);
    }

    /**
     * 根据学科ID查询领域列表
     *
     * @param subjectId 学科ID
     * @return 领域集合
     */
    @Override
    @Cacheable(value = "sysArea", key = "'subject:' + #subjectId", unless = "#result == null || #result.isEmpty()")
    public List<SysArea> selectAreasBySubjectId(Integer subjectId) {
        return sysAreaMapper.selectAreasBySubjectId(subjectId);
    }

    /**
     * 新增领域信息
     *
     * @param area 领域信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "sysArea", allEntries = true)
    public int insertArea(SysArea area) {
        return sysAreaMapper.insertSysArea(area);
    }

    /**
     * 修改领域信息
     *
     * @param area 领域信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "sysArea", allEntries = true)
    public int updateArea(SysArea area) {
        return sysAreaMapper.updateArea(area);
    }

    /**
     * 批量删除领域信息
     *
     * @param areaIds 需要删除的领域ID
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "sysArea", allEntries = true)
    public int deleteAreaByAreaIds(Integer[] areaIds) {
        return sysAreaMapper.deleteAreaByAreaIds(areaIds);
    }

    /**
     * 删除领域信息
     *
     * @param areaId 领域ID
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "sysArea", allEntries = true)
    public int deleteAreaByAreaId(Integer areaId) {
        return sysAreaMapper.deleteAreaByAreaId(areaId);
    }
}
