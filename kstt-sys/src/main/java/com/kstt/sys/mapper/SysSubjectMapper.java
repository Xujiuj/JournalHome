package com.kstt.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kstt.sys.entity.SysSubject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 学科信息Mapper接口
 *
 * @author kstt
 * @date 2025-10-28
 */
@Mapper
public interface SysSubjectMapper extends BaseMapper<SysSubject> {

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
     * @param sysSubject 学科信息
     * @return 结果
     */
    int insertSysSubject(SysSubject sysSubject);

    /**
     * 修改学科信息
     *
     * @param subject 学科信息
     * @return 结果
     */
    int updateSubject(SysSubject subject);

    /**
     * 删除学科信息
     *
     * @param subjectId 学科ID
     * @return 结果
     */
    int deleteSubjectBySubjectId(Integer subjectId);

    /**
     * 批量删除学科信息
     *
     * @param subjectIds 需要删除的学科ID
     * @return 结果
     */
    int deleteSubjectBySubjectIds(Integer[] subjectIds);
}
