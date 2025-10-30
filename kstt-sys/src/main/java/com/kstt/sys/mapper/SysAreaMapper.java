package com.kstt.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kstt.sys.entity.SysArea;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 学科领域信息Mapper接口
 *
 * @author kstt
 * @date 2025-10-28
 */
@Mapper
public interface SysAreaMapper extends BaseMapper<SysArea> {

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
     * @param sysArea 领域信息
     * @return 结果
     */
    int insertSysArea(SysArea sysArea);

    /**
     * 修改领域信息
     *
     * @param area 领域信息
     * @return 结果
     */
    int updateArea(SysArea area);

    /**
     * 删除领域信息
     *
     * @param areaId 领域ID
     * @return 结果
     */
    int deleteAreaByAreaId(Integer areaId);

    /**
     * 批量删除领域信息
     *
     * @param areaIds 需要删除的领域ID
     * @return 结果
     */
    int deleteAreaByAreaIds(Integer[] areaIds);
}
