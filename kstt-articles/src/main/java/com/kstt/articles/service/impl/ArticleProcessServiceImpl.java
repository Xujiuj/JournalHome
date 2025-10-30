package com.kstt.articles.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kstt.articles.entity.Article;
import com.kstt.articles.entity.ArticleProcess;
import com.kstt.articles.entity.ArticleStatus;
import com.kstt.articles.mapper.ArticleMapper;
import com.kstt.articles.mapper.ArticleProcessMapper;
import com.kstt.articles.mapper.ArticleStatusMapper;
import com.kstt.articles.service.ArticleProcessService;
import com.kstt.sys.entity.SysUser;
import com.kstt.sys.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 论文处理流程Service实现类
 */
@Service
public class ArticleProcessServiceImpl extends ServiceImpl<ArticleProcessMapper, ArticleProcess> implements ArticleProcessService {
    
    @Autowired
    private ArticleProcessMapper articleProcessMapper;
    
    @Autowired
    private ArticleMapper articleMapper;
    
    @Autowired
    private SysUserMapper sysUserMapper;
    
    @Autowired
    private ArticleStatusMapper articleStatusMapper;
    
    /**
     * 填充流程关联对象
     */
    private void fillProcessAssociations(ArticleProcess process) {
        if (process == null) {
            return;
        }
        
        // 填充文章信息
        if (process.getProcessArticleId() != null) {
            Article article = articleMapper.selectById(process.getProcessArticleId());
            process.setProcessArticle(article);
        }
        
        // 填充编辑信息
        if (process.getProcessEditorId() != null) {
            SysUser editor = sysUserMapper.selectById(process.getProcessEditorId());
            process.setProcessEditor(editor);
        }
        
        // 填充当前状态信息
        if (process.getProcessCurrentStatusId() != null) {
            ArticleStatus status = articleStatusMapper.selectById(process.getProcessCurrentStatusId());
            process.setProcessCurrentStatus(status);
        }
        
        // 填充前一状态信息
        if (process.getProcessPreviousStatusId() != null) {
            ArticleStatus status = articleStatusMapper.selectById(process.getProcessPreviousStatusId());
            process.setProcessPreviousStatus(status);
        }
    }
    
    @Override
    public List<ArticleProcess> selectProcessList(ArticleProcess articleProcess) {
        List<ArticleProcess> list = articleProcessMapper.selectProcessList(articleProcess);
        list.forEach(this::fillProcessAssociations);
        return list;
    }
    
    @Override
    public ArticleProcess selectProcessByProcessId(Long processId) {
        ArticleProcess process = articleProcessMapper.selectProcessByProcessId(processId);
        fillProcessAssociations(process);
        return process;
    }
    
    @Override
    public int insertProcess(ArticleProcess articleProcess) {
        return articleProcessMapper.insertProcess(articleProcess);
    }
    
    @Override
    public int updateProcess(ArticleProcess articleProcess) {
        return articleProcessMapper.updateProcess(articleProcess);
    }
    
    @Override
    public int deleteProcessByIds(Long[] processIds) {
        return articleProcessMapper.deleteProcessByIds(processIds);
    }
    
    @Override
    public int deleteProcessById(Long processId) {
        return articleProcessMapper.deleteProcessById(processId);
    }
    
}
