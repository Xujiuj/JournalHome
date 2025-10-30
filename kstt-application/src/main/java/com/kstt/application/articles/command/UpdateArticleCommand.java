package com.kstt.application.articles.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 更新文章命令
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateArticleCommand {

    private Long articleId;
    private String title;
    private String abstractText;
    private String keywords;
    private String coverLetter;
    private Integer wordCount;
    private Integer figureCount;
    private Integer tableCount;
    private List<SubmitArticleCommand.AuthorInfo> authors;
}
