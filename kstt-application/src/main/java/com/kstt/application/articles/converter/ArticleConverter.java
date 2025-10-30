package com.kstt.application.articles.converter;

import com.kstt.application.articles.dto.ArticleDetailDTO;
import com.kstt.application.articles.dto.ArticleListDTO;
import com.kstt.application.common.converter.DTOConverter;
import com.kstt.articles.entity.Article;
import com.kstt.articles.entity.ArticleAuthor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 文章转换器
 */
@Component
public class ArticleConverter implements DTOConverter<Article, ArticleListDTO> {

    @Override
    public ArticleListDTO toDTO(Article article) {
        if (article == null) {
            return null;
        }

        return ArticleListDTO.builder()
                .articleId(article.getArticleId())
                .manuscriptId(article.getArticleManuscriptId())
                .title(article.getArticleTitle())
                .statusName(article.getArticleStatus() != null ? 
                    article.getArticleStatus().getStatusName() : null)
                .journalName(article.getJournal() != null ? 
                    "Vol." + article.getJournal().getJournalVolume() + 
                    " No." + article.getJournal().getJournalIssue() : null)
                .firstAuthor(extractFirstAuthorName(article))
                .submitTime(article.getArticleSubmitTime())
                .publishTime(article.getArticlePublishTime())
                .build();
    }

    public ArticleDetailDTO toDetailDTO(Article article) {
        if (article == null) {
            return null;
        }

        ArticleDetailDTO dto = ArticleDetailDTO.builder()
                .articleId(article.getArticleId())
                .manuscriptId(article.getArticleManuscriptId())
                .title(article.getArticleTitle())
                .abstractText(article.getArticleAbstract())
                .keywords(parseKeywords(article.getArticleKeywords()))
                .submitTime(article.getArticleSubmitTime())
                .acceptTime(article.getArticleAcceptTime())
                .publishTime(article.getArticlePublishTime())
                .doi(article.getArticleDoi())
                .wordCount(article.getArticleWordCount())
                .isOpenAccess(article.getArticleIsOpenAccess() != null && article.getArticleIsOpenAccess() == 1)
                .build();

        if (article.getJournal() != null) {
            dto.setJournal(ArticleDetailDTO.JournalInfo.builder()
                    .id(article.getJournal().getJournalId())
                    .name("Vol." + article.getJournal().getJournalVolume() + 
                          " No." + article.getJournal().getJournalIssue())
                    .issn(article.getJournal().getJournalTheme())
                    .build());
        }

        if (article.getArticleStatus() != null) {
            dto.setStatus(ArticleDetailDTO.StatusInfo.builder()
                    .id(article.getArticleStatus().getStatusId())
                    .name(article.getArticleStatus().getStatusName())
                    .description(article.getArticleStatus().getStatusDesc())
                    .build());
        }

        if (article.getArticleAuthors() != null) {
            dto.setAuthors(article.getArticleAuthors().stream()
                    .map(this::toAuthorInfo)
                    .collect(Collectors.toList()));
        }

        dto.setCreateTime(article.getCreateTime());
        dto.setUpdateTime(article.getUpdateTime());

        return dto;
    }

    private String extractFirstAuthorName(Article article) {
        if (article.getArticleAuthors() == null || article.getArticleAuthors().isEmpty()) {
            return null;
        }
        ArticleAuthor firstAuthor = article.getArticleAuthors().stream()
                .min((a1, a2) -> Integer.compare(
                        a1.getAuthorOrder() != null ? a1.getAuthorOrder() : 999,
                        a2.getAuthorOrder() != null ? a2.getAuthorOrder() : 999))
                .orElse(null);
        return firstAuthor != null && firstAuthor.getAuthorUser() != null 
                ? firstAuthor.getAuthorUser().getUserName() 
                : null;
    }

    private List<String> parseKeywords(String keywords) {
        if (keywords == null || keywords.isBlank()) {
            return List.of();
        }
        return Arrays.stream(keywords.split("[;；,，]"))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }

    private ArticleDetailDTO.AuthorInfo toAuthorInfo(ArticleAuthor author) {
        if (author == null || author.getAuthorUser() == null) {
            return null;
        }
        return ArticleDetailDTO.AuthorInfo.builder()
                .userId(author.getAuthorUser().getUserId())
                .name(author.getAuthorUser().getUserName())
                .email(author.getAuthorUser().getUserEmail())
                .affiliation(author.getAuthorUser().getUserAffiliation())
                .isCorresponding(author.getAuthorIsCorresponding() != null && author.getAuthorIsCorresponding() == 1)
                .build();
    }
}
