package com.kstt.application.journal.service;

import com.kstt.journal.entity.Journal;
import com.kstt.journal.model.dto.JournalDetailDTO;
import com.kstt.journal.model.dto.JournalListResult;
import com.kstt.journal.model.query.JournalQuery;

/**
 * 期刊应用服务接口（应用层）
 */
public interface JournalApplicationService {

    JournalListResult list(JournalQuery query);

    JournalDetailDTO detail(Long journalId);

    int create(Journal journal);

    int modify(Journal journal);

    int deleteByIds(Long[] journalIds);

    int deleteById(Long journalId);
}
