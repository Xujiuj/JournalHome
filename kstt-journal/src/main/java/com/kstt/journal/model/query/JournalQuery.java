package com.kstt.journal.model.query;

import lombok.Data;

/**
 * 期刊列表查询条件
 */
@Data
public class JournalQuery {

    /**
     * 期刊卷次
     */
    private Integer journalVolume;

    /**
     * 期刊期号
     */
    private Integer journalIssue;

    /**
     * 状态(0：待发布，1：已发布)
     */
    private Integer journalStatus;

    /**
     * 主题关键词
     */
    private String journalTheme;

    /**
     * 简介关键词
     */
    private String journalDescription;
}


