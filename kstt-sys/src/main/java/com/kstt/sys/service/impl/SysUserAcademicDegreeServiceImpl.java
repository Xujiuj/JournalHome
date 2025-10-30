package com.kstt.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kstt.sys.entity.SysUserAcademicDegree;
import com.kstt.sys.mapper.SysUserAcademicDegreeMapper;
import com.kstt.sys.service.SysUserAcademicDegreeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户学术学位信息Service业务层处理
 *
 * @author kstt
 * @date 2025-10-28
 */
@Service
@RequiredArgsConstructor
public class SysUserAcademicDegreeServiceImpl extends ServiceImpl<SysUserAcademicDegreeMapper, SysUserAcademicDegree> implements SysUserAcademicDegreeService {

    private final SysUserAcademicDegreeMapper sysUserAcademicDegreeMapper;

    /**
     * 查询学位列表
     *
     * @param degree 学位信息
     * @return 学位集合
     */
    @Override
    public List<SysUserAcademicDegree> selectDegreeList(SysUserAcademicDegree degree) {
        return sysUserAcademicDegreeMapper.selectDegreeList(degree);
    }

    /**
     * 根据学位ID查询学位信息
     *
     * @param degreeId 学位ID
     * @return 学位信息
     */
    @Override
    public SysUserAcademicDegree selectDegreeByDegreeId(Integer degreeId) {
        return sysUserAcademicDegreeMapper.selectDegreeByDegreeId(degreeId);
    }
}
