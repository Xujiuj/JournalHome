package com.kstt.journal.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kstt.journal.entity.Journal;
import com.kstt.journal.mapper.JournalMapper;
import com.kstt.journal.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 期刊Service实现类
 */
@Service
public class JournalServiceImpl extends ServiceImpl<JournalMapper, Journal> implements JournalService {

    @Autowired
    private JournalMapper journalMapper;

    @Override
    public List<Journal> selectJournalList(Journal journal) {
        return journalMapper.selectJournalList(journal);
    }
    
    @Override
    public Journal selectJournalByJournalId(Long journalId) {
        return journalMapper.selectJournalByJournalId(journalId);
    }


    @Override
    public int insertJournal(Journal journal) {
        return journalMapper.insertJournal(journal);
    }

    @Override
    public int updateJournal(Journal journal) {
        return journalMapper.updateJournal(journal);
    }

    @Override
    public int deleteJournalByIds(Long[] journalIds) {
        return journalMapper.deleteJournalByIds(journalIds);
    }

    @Override
    public int deleteJournalById(Long journalId) {
        return journalMapper.deleteJournalById(journalId);
    }
    
}
