package com.kstt.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kstt.sys.entity.SysArea;

import java.util.List;

/**
 * 学科领域信息Service接口
 *
 * @author kstt
 * @date 2025-10-28
 */
public interface SysAreaService extends IService<SysArea> {

    /**
     * 查询领域列表
     *
     * @param area 领域信息
     * @return 领域集合
     */
    List<SysArea> selectAreaList(SysArea area);

    /**
     * 根据领域ID查询领域信息
     *
     * @param areaId 领域ID
     * @return 领域信息
     */
    SysArea selectAreaByAreaId(Integer areaId);

    /**
     * 根据学科ID查询领域列表
     *
     * @param subjectId 学科ID
     * @return 领域集合
     */
    List<SysArea> selectAreasBySubjectId(Integer subjectId);

    /**
     * 新增领域信息
     *
     * @param area 领域信息
     * @return 结果
     */
    int insertArea(SysArea area);

    /**
     * 修改领域信息
     *
     * @param area 领域信息
     * @return 结果
     */
    int updateArea(SysArea area);

    /**
     * 批量删除领域信息
     *
     * @param areaIds 需要删除的领域ID
     * @return 结果
     */
    int deleteAreaByAreaIds(Integer[] areaIds);

    /**
     * 删除领域信息
     *
     * @param areaId 领域ID
     * @return 结果
     */
    int deleteAreaByAreaId(Integer areaId);
}
