package com.kstt.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kstt.sys.entity.SysUserAcademicDegree;

import java.util.List;

/**
 * 用户学术学位信息Service接口
 *
 * @author kstt
 * @date 2025-10-28
 */
public interface SysUserAcademicDegreeService extends IService<SysUserAcademicDegree> {

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
