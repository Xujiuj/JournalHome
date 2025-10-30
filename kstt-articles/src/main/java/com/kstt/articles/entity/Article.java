package com.kstt.articles.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.kstt.common.entity.BaseEntity;
import com.kstt.journal.entity.Journal;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 论文实体类
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("article")
public class Article extends BaseEntity {
    
    /**
     * 论文ID
     */
    @TableId(type = IdType.AUTO)
    private Long articleId;
    
    /**
     * 稿件编号
     */
    private String articleManuscriptId;
    
    /**
     * 论文标题
     */
    private String articleTitle;
    
    /**
     * 摘要
     */
    private String articleAbstract;
    
    /**
     * 关键词（多个用分号分隔）
     */
    private String articleKeywords;
    
    /**
     * 关联的卷次信息
     */
    private Long articleJournalId;
    
    /**
     * 页码
     */
    private String articlePages;
    
    /**
     * 首次提交时间
     */
    private LocalDateTime articleSubmitTime;
    
    /**
     * 论文接收时间
     */
    private LocalDateTime articleAcceptTime;
    
    /**
     * 论文发表时间
     */
    private LocalDateTime articlePublishTime;
    
    /**
     * DOI号
     */
    private String articleDoi;
    
    /**
     * 论文PDF文件
     */
    private String articlePdf;
    
    /**
     * 状态ID
     */
    private Integer articleStatusId;

    /**
     * 稿件类型ID
     */
    private Integer articleManuscriptTypeId;

    /**
     * 字数
     */
    private Integer articleWordCount;

    /**
     * 图片数量
     */
    private Integer articleFigureCount;

    /**
     * 表格数量
     */
    private Integer articleTableCount;

    /**
     * 提交类型ID
     */
    private Integer articleSubmissionTypeId;

    /**
     * 在线发表时间
     */
    private LocalDateTime articleOnlineTime;

    /**
     * 投稿信
     */
    private String articleCoverLetter;

    /**
     * 文章类型ID
     */
    private Integer articleTypeId;

    /**
     * 是否开放获取(0：否，1：是)
     */
    private Integer articleIsOpenAccess;

    /**
     * 学科领域IDs（多个用逗号分隔）
     */
    private String articleSubjectAreas;

    /**
     * 补充材料说明
     */
    private String articleSupplementary;

    /**
     * 补充材料文件
     */
    private String articleSupplementaryFile;

    /**
     * 学科领域ID（别名字段，用于兼容前端参数）
     */
    @TableField(exist = false)
    private String articleSubjectAreaId;


    /**
     * 作者列表
     */
    @TableField(exist = false)
    private List<ArticleAuthor> articleAuthors;

    /**
     * 关联的期刊信息（不作为数据库字段）
     */
    @TableField(exist = false)
    private Journal journal;
    
    /**
     * 关联的状态信息（不作为数据库字段）
     */
    @TableField(exist = false)
    private ArticleStatus articleStatus;

    /**
     * 关联的稿件类型信息（不作为数据库字段）
     */
    @TableField(exist = false)
    private ArticleManuscriptType articleManuscriptType;

    /**
     * 关联的提交类型信息（不作为数据库字段）
     */
    @TableField(exist = false)
    private ArticleSubmissionType articleSubmissionType;

    /**
     * 获取学科领域ID（兼容方法）
     */
    public String getArticleSubjectAreaId() {
        return this.articleSubjectAreas;
    }

    /**
     * 设置学科领域ID（兼容方法）
     */
    public void setArticleSubjectAreaId(String articleSubjectAreaId) {
        this.articleSubjectAreas = articleSubjectAreaId;
    }
}
