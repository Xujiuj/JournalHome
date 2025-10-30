package com.kstt.articles.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kstt.articles.entity.ArticleProcess;

import java.util.List;

/**
 * 论文处理流程Service接口
 */
public interface ArticleProcessService extends IService<ArticleProcess> {
    
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
    ArticleProcess selectProcessByProcessId(Long processId);
    
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
    int deleteProcessByIds(Long[] processIds);
    
    /**
     * 删除论文处理流程信息
     */
    int deleteProcessById(Long processId);
    
}
