package com.kstt.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kstt.sys.entity.SysSubject;
import com.kstt.sys.mapper.SysSubjectMapper;
import com.kstt.sys.service.SysSubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 学科信息Service业务层处理
 *
 * @author kstt
 * @date 2025-10-28
 */
@Service
@RequiredArgsConstructor
public class SysSubjectServiceImpl extends ServiceImpl<SysSubjectMapper, SysSubject> implements SysSubjectService {

    private final SysSubjectMapper sysSubjectMapper;

    /**
     * 查询学科列表
     *
     * @param subject 学科信息
     * @return 学科集合
     */
    @Override
    public List<SysSubject> selectSubjectList(SysSubject subject) {
        return sysSubjectMapper.selectSubjectList(subject);
    }

    /**
     * 根据学科ID查询学科信息
     *
     * @param subjectId 学科ID
     * @return 学科信息
     */
    @Override
    @Cacheable(value = "sysSubject", key = "#subjectId", unless = "#result == null")
    public SysSubject selectSubjectBySubjectId(Integer subjectId) {
        return sysSubjectMapper.selectSubjectBySubjectId(subjectId);
    }

    /**
     * 新增学科信息
     *
     * @param subject 学科信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "sysSubject", allEntries = true)
    public int insertSubject(SysSubject subject) {
        return sysSubjectMapper.insertSysSubject(subject);
    }

    /**
     * 修改学科信息
     *
     * @param subject 学科信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "sysSubject", allEntries = true)
    public int updateSubject(SysSubject subject) {
        return sysSubjectMapper.updateSubject(subject);
    }

    /**
     * 批量删除学科信息
     *
     * @param subjectIds 需要删除的学科ID
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "sysSubject", allEntries = true)
    public int deleteSubjectBySubjectIds(Integer[] subjectIds) {
        return sysSubjectMapper.deleteSubjectBySubjectIds(subjectIds);
    }

    /**
     * 删除学科信息
     *
     * @param subjectId 学科ID
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "sysSubject", allEntries = true)
    public int deleteSubjectBySubjectId(Integer subjectId) {
        return sysSubjectMapper.deleteSubjectBySubjectId(subjectId);
    }
}
