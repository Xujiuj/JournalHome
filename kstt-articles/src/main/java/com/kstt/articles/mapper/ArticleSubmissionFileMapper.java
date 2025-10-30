package com.kstt.articles.mapper;

import com.kstt.articles.entity.ArticleSubmissionFile;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 提交文件Mapper接口
 */
@Mapper
public interface ArticleSubmissionFileMapper {

    /**
     * 查询提交文件列表
     */
    List<ArticleSubmissionFile> selectFileList(ArticleSubmissionFile file);

    /**
     * 根据文件ID查询文件
     */
    ArticleSubmissionFile selectFileByFileId(Long fileId);

    /**
     * 根据文章ID查询文件列表
     */
    List<ArticleSubmissionFile> selectFilesByArticleId(Long articleId);

    /**
     * 新增提交文件
     */
    int insertFile(ArticleSubmissionFile file);

    /**
     * 修改提交文件
     */
    int updateFile(ArticleSubmissionFile file);

    /**
     * 删除提交文件
     */
    int deleteFileById(Long fileId);

    /**
     * 批量删除提交文件
     */
    int deleteFileByIds(Long[] fileIds);

    /**
     * 根据文章ID删除文件
     */
    int deleteFilesByArticleId(Long articleId);
}

