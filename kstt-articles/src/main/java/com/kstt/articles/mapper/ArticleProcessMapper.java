package com.kstt.articles.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kstt.articles.entity.ArticleProcess;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 论文处理流程Mapper接口
 */
@Mapper
public interface ArticleProcessMapper extends BaseMapper<ArticleProcess> {
    
    /**
     * 查询论文处理流程列表
     */
    List<ArticleProcess> selectProcessList(ArticleProcess articleProcess);
    
    /**
     * 根据流程ID查询处理流程信息
     * 
     * @param processId 流程ID
     * @return 处理流程信息
     */
    ArticleProcess selectProcessByProcessId(@Param("processId") Long processId);
    
    /**
     * 新增论文处理流程
     */
    int insertProcess(ArticleProcess articleProcess);
    
    /**
     * 修改论文处理流程
     */
    int updateProcess(ArticleProcess articleProcess);
    
    /**
     * 批量删除论文处理流程
     */
    int deleteProcessByIds(@Param("processIds") Long[] processIds);
    
    /**
     * 删除论文处理流程信息
     */
    int deleteProcessById(@Param("processId") Long processId);
    
}
