package com.kstt.journal.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 期刊分页结果
 */
@Data
@AllArgsConstructor
public class JournalListResult {

    private List<JournalSummaryDTO> records;

    private long total;
}

