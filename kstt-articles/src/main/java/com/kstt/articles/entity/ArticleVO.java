package com.kstt.articles.entity;

import com.kstt.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 文章视图对象，包含枚举名称
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ArticleVO extends BaseEntity {
    
    private Long articleId;
    private String articleManuscriptId;
    private String articleTitle;
    private String articleAbstract;
    private String articleKeywords;
    private Long articleJournalId;
    private String articlePages;
    private LocalDateTime articleSubmitTime;
    private LocalDateTime articleAcceptTime;
    private LocalDateTime articlePublishTime;
    private String articleDoi;
    private String articlePdf;
    private Integer articleStatusId;
    
    // 枚举名称（用于前端显示）
    private String articleStatusName;
    private String manuscriptTypeName;
    private String submissionTypeName;
    
    private String articleCorrespondingEmail;
    private Integer articleManuscriptTypeId;
    private Integer articleSubmissionTypeId;
    private Integer articleSubjectAreaId;
}

