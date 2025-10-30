package com.kstt.application.articles.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 提交文章命令
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubmitArticleCommand {

    private String title;
    private String abstractText;
    private String keywords;
    private String coverLetter;
    private Long journalId;
    private Integer manuscriptTypeId;
    private Long submitterId;
    private List<AuthorInfo> authors;
    private List<String> suggestedReviewers;
    private List<String> opposedReviewers;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AuthorInfo {
        private Long userId;
        private String affiliation;
        private String contribution;
        private Integer sequence;
        private Boolean isCorresponding;
    }
}
