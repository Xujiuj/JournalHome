package com.kstt.articles.dto;

import lombok.Data;

/**
 * 期刊下文章数量聚合结果
 */
@Data
public class JournalArticleCount {

    private Long journalId;

    private Long articleCount;
}



