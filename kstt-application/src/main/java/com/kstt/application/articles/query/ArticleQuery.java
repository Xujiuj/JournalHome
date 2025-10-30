package com.kstt.application.articles.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文章查询条件
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleQuery {

    private String title;
    private Integer statusId;
    private Long journalId;
    private String manuscriptId;
    private Integer pageNum;
    private Integer pageSize;
    private String orderBy;
    private Boolean desc;
}
