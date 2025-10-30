package com.kstt.articles.service.impl;

import com.kstt.articles.entity.Article;
import com.kstt.articles.entity.ArticleFileType;
import com.kstt.articles.entity.ArticleSubmissionFile;
import com.kstt.articles.mapper.ArticleFileTypeMapper;
import com.kstt.articles.mapper.ArticleMapper;
import com.kstt.articles.mapper.ArticleSubmissionFileMapper;
import com.kstt.articles.service.ArticleSubmissionFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 提交文件Service业务层处理
 */
@Service
public class ArticleSubmissionFileServiceImpl implements ArticleSubmissionFileService {

    @Autowired
    private ArticleSubmissionFileMapper fileMapper;
    
    @Autowired
    private ArticleMapper articleMapper;
    
    @Autowired
    private ArticleFileTypeMapper articleFileTypeMapper;
    
    /**
     * 填充文件关联对象
     */
    private void fillFileAssociations(ArticleSubmissionFile file) {
        if (file == null) {
            return;
        }
        
        // 填充文章信息
        if (file.getFileArticleId() != null) {
            Article article = articleMapper.selectById(file.getFileArticleId());
            file.setFileArticle(article);
        }
        
        // 填充文件类型信息
        if (file.getFileTypeId() != null) {
            ArticleFileType fileType = articleFileTypeMapper.selectById(file.getFileTypeId());
            file.setFileType(fileType);
        }
    }

    /**
     * 查询提交文件列表
     */
    @Override
    public List<ArticleSubmissionFile> selectFileList(ArticleSubmissionFile file) {
        List<ArticleSubmissionFile> list = fileMapper.selectFileList(file);
        list.forEach(this::fillFileAssociations);
        return list;
    }

    /**
     * 根据文件ID查询文件
     */
    @Override
    public ArticleSubmissionFile selectFileByFileId(Long fileId) {
        ArticleSubmissionFile file = fileMapper.selectFileByFileId(fileId);
        fillFileAssociations(file);
        return file;
    }

    /**
     * 根据文章ID查询文件列表
     */
    @Override
    public List<ArticleSubmissionFile> selectFilesByArticleId(Long articleId) {
        List<ArticleSubmissionFile> list = fileMapper.selectFilesByArticleId(articleId);
        list.forEach(this::fillFileAssociations);
        return list;
    }

    /**
     * 新增提交文件
     */
    @Override
    public int insertFile(ArticleSubmissionFile file) {
        return fileMapper.insertFile(file);
    }

    /**
     * 修改提交文件
     */
    @Override
    public int updateFile(ArticleSubmissionFile file) {
        return fileMapper.updateFile(file);
    }

    /**
     * 删除提交文件
     */
    @Override
    public int deleteFileById(Long fileId) {
        return fileMapper.deleteFileById(fileId);
    }

    /**
     * 批量删除提交文件
     */
    @Override
    public int deleteFileByIds(Long[] fileIds) {
        return fileMapper.deleteFileByIds(fileIds);
    }

    /**
     * 根据文章ID删除文件
     */
    @Override
    public int deleteFilesByArticleId(Long articleId) {
        return fileMapper.deleteFilesByArticleId(articleId);
    }
}

