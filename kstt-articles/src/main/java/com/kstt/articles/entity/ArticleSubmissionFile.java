package com.kstt.articles.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kstt.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 提交文件实体类
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("article_submission_file")
public class ArticleSubmissionFile extends BaseEntity {

    /**
     * 文件ID
     */
    @TableId(type = IdType.AUTO)
    private Long fileId;

    /**
     * 关联论文ID
     */
    private Long fileArticleId;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 原始文件名
     */
    private String fileOriginalName;

    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 文件大小(字节)
     */
    private Long fileSize;

    /**
     * 文件类型ID
     */
    private Integer fileTypeId;

    /**
     * MIME类型
     */
    private String fileMimeType;

    /**
     * 文件描述
     */
    private String fileDescription;

    /**
     * 上传时间
     */
    private LocalDateTime fileUploadTime;

    /**
     * 文件版本
     */
    private Integer fileVersion;

    /**
     * 是否为主文件(0：否，1：是)
     */
    private Integer fileIsPrimary;

    /**
     * 关联的论文信息（不作为数据库字段）
     */
    @TableField(exist = false)
    @JsonIgnore
    private Article fileArticle;

    /**
     * 关联的文件类型信息（不作为数据库字段）
     */
    @TableField(exist = false)
    private ArticleFileType fileType;
}

