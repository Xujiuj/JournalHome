package com.kstt.application.articles.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kstt.application.common.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 文章详情 DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ArticleDetailDTO extends BaseDTO {

    private Long articleId;
    private String manuscriptId;
    private String title;
    private String abstractText;
    private List<String> keywords;
    
    private JournalInfo journal;
    private StatusInfo status;
    private List<AuthorInfo> authors;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime submitTime;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime acceptTime;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime publishTime;
    
    private String doi;
    private Integer wordCount;
    private Boolean isOpenAccess;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JournalInfo {
        private Long id;
        private String name;
        private String issn;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StatusInfo {
        private Integer id;
        private String name;
        private String description;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AuthorInfo {
        private Long userId;
        private String name;
        private String email;
        private String affiliation;
        private Boolean isCorresponding;
    }
}
