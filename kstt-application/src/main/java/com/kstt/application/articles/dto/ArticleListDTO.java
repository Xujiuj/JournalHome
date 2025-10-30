package com.kstt.application.articles.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kstt.application.common.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 文章列表 DTO - 精简版
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ArticleListDTO extends BaseDTO {

    private Long articleId;
    private String manuscriptId;
    private String title;
    private String statusName;
    private String journalName;
    private String firstAuthor;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime submitTime;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime publishTime;
}
