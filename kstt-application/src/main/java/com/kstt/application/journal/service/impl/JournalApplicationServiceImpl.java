package com.kstt.application.journal.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageInfo;
import com.kstt.articles.service.ArticleService;
import com.kstt.application.journal.service.JournalApplicationService;
import com.kstt.journal.entity.Journal;
import com.kstt.journal.model.converter.JournalConverter;
import com.kstt.journal.model.dto.JournalDetailDTO;
import com.kstt.journal.model.dto.JournalListResult;
import com.kstt.journal.model.dto.JournalSummaryDTO;
import com.kstt.journal.model.query.JournalQuery;
import com.kstt.journal.service.JournalService;

import lombok.RequiredArgsConstructor;

/**
 * 期刊应用服务实现类（应用层）
 */
@Service
@RequiredArgsConstructor
public class JournalApplicationServiceImpl implements JournalApplicationService {

    private final JournalService journalService;
    private final ArticleService articleService;

    @Override
    public JournalListResult list(JournalQuery query) {
        Journal criteria = buildCriteria(query);
        List<Journal> journals = journalService.selectJournalList(criteria);
        if (CollectionUtils.isEmpty(journals)) {
            return new JournalListResult(Collections.emptyList(), 0L);
        }
        Map<Long, Long> articleCountMap = fetchArticleCountMap(journals);
        List<JournalSummaryDTO> summaries = journals.stream()
                .map(journal -> JournalConverter.toSummary(journal,
                        articleCountMap.getOrDefault(journal.getJournalId(), 0L)))
                .collect(Collectors.toList());
        long total = new PageInfo<>(journals).getTotal();
        return new JournalListResult(summaries, total);
    }

    @Override
    public JournalDetailDTO detail(Long journalId) {
        if (journalId == null) {
            return null;
        }
        Journal journal = journalService.selectJournalByJournalId(journalId);
        if (journal == null) {
            return null;
        }
        long articleCount = articleService.countByJournalIds(Collections.singletonList(journalId))
                .getOrDefault(journalId, 0L);
        return JournalConverter.toDetail(journal, articleCount);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int create(Journal journal) {
        return journalService.insertJournal(journal);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int modify(Journal journal) {
        return journalService.updateJournal(journal);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByIds(Long[] journalIds) {
        return journalService.deleteJournalByIds(journalIds);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteById(Long journalId) {
        return journalService.deleteJournalById(journalId);
    }

    private Journal buildCriteria(JournalQuery query) {
        if (query == null) {
            return new Journal();
        }
        Journal journal = new Journal();
        journal.setJournalVolume(query.getJournalVolume());
        journal.setJournalIssue(query.getJournalIssue());
        journal.setJournalStatus(query.getJournalStatus());
        if (StringUtils.hasText(query.getJournalTheme())) {
            journal.setJournalTheme(query.getJournalTheme());
        }
        if (StringUtils.hasText(query.getJournalDescription())) {
            journal.setJournalDescription(query.getJournalDescription());
        }
        return journal;
    }

    private Map<Long, Long> fetchArticleCountMap(List<Journal> journals) {
        Set<Long> journalIds = journals.stream()
                .map(Journal::getJournalId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
        if (CollectionUtils.isEmpty(journalIds)) {
            return Collections.emptyMap();
        }
        return articleService.countByJournalIds(journalIds.stream().collect(Collectors.toList()));
    }
}
