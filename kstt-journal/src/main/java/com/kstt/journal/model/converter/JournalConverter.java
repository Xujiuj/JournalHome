package com.kstt.journal.model.converter;

import com.kstt.journal.entity.Journal;
import com.kstt.journal.model.dto.JournalDetailDTO;
import com.kstt.journal.model.dto.JournalSummaryDTO;

/**
 * 期刊实体与 DTO 转换工具
 */
public final class JournalConverter {

    private JournalConverter() {
    }

    public static JournalSummaryDTO toSummary(Journal journal, long articleCount) {
        if (journal == null) {
            return null;
        }
        return JournalSummaryDTO.builder()
                .journalId(journal.getJournalId())
                .journalVolume(journal.getJournalVolume())
                .journalIssue(journal.getJournalIssue())
                .journalPublishDate(journal.getJournalPublishDate())
                .journalStatus(journal.getJournalStatus())
                .journalTheme(journal.getJournalTheme())
                .journalCover(journal.getJournalCover())
                .articleCount(articleCount)
                .build();
    }

    public static JournalDetailDTO toDetail(Journal journal, long articleCount) {
        if (journal == null) {
            return null;
        }
        return JournalDetailDTO.builder()
                .journalId(journal.getJournalId())
                .journalVolume(journal.getJournalVolume())
                .journalIssue(journal.getJournalIssue())
                .journalPublishDate(journal.getJournalPublishDate())
                .journalStatus(journal.getJournalStatus())
                .journalTheme(journal.getJournalTheme())
                .journalDescription(journal.getJournalDescription())
                .journalCover(journal.getJournalCover())
                .journalPdf(journal.getJournalPdf())
                .articleCount(articleCount)
                .build();
    }
}

