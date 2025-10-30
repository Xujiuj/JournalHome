package com.kstt.journal.model.dto;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Value;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 期刊列表展示 DTO
 */
@Value
@Builder
public class JournalSummaryDTO {

    Long journalId;

    Integer journalVolume;

    Integer journalIssue;

    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate journalPublishDate;

    Integer journalStatus;

    String journalTheme;

    String journalCover;

    Long articleCount;
}


