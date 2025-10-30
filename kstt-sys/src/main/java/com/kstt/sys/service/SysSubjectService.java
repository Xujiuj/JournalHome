package com.kstt.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kstt.sys.entity.SysSubject;

import java.util.List;

/**
 * 学科信息Service接口
 *
 * @author kstt
 * @date 2025-10-28
 */
public interface SysSubjectService extends IService<SysSubject> {

    /**
     * 查询学科列表
     *
     * @param subject 学科信息
     * @return 学科集合
     */
    List<SysSubject> selectSubjectList(SysSubject subject);

    /**
     * 根据学科ID查询学科信息
     *
     * @param subjectId 学科ID
     * @return 学科信息
     */
    SysSubject selectSubjectBySubjectId(Integer subjectId);

    /**
     * 新增学科信息
     *
     * @param subject 学科信息
     * @return 结果
     */
    int insertSubject(SysSubject subject);

    /**
     * 修改学科信息
     *
     * @param subject 学科信息
     * @return 结果
     */
    int updateSubject(SysSubject subject);

    /**
     * 批量删除学科信息
     *
     * @param subjectIds 需要删除的学科ID
     * @return 结果
     */
    int deleteSubjectBySubjectIds(Integer[] subjectIds);

    /**
     * 删除学科信息
     *
     * @param subjectId 学科ID
     * @return 结果
     */
    int deleteSubjectBySubjectId(Integer subjectId);
}
