package com.kstt.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kstt.sys.entity.SysUserAcademicDegree;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户学术学位信息Mapper接口
 *
 * @author kstt
 * @date 2025-10-28
 */
@Mapper
public interface SysUserAcademicDegreeMapper extends BaseMapper<SysUserAcademicDegree> {

    /**
     * 查询学位列表
     *
     * @param degree 学位信息
     * @return 学位集合
     */
    List<SysUserAcademicDegree> selectDegreeList(SysUserAcademicDegree degree);

    /**
     * 根据学位ID查询学位信息
     *
     * @param degreeId 学位ID
     * @return 学位信息
     */
    SysUserAcademicDegree selectDegreeByDegreeId(Integer degreeId);
}
