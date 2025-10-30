package com.kstt.journal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kstt.journal.entity.Journal;

import java.util.List;

/**
 * 期刊Service接口
 */
public interface JournalService extends IService<Journal> {

    /**
     * 查询期刊列表
     */
    List<Journal> selectJournalList(Journal journal);
    
    /**
     * 根据期刊ID查询期刊信息
     * 
     * @param journalId 期刊ID
     * @return 期刊信息
     */
    Journal selectJournalByJournalId(Long journalId);


    /**
     * 新增期刊
     */
    int insertJournal(Journal journal);

    /**
     * 修改期刊
     */
    int updateJournal(Journal journal);

    /**
     * 批量删除期刊
     */
    int deleteJournalByIds(Long[] journalIds);

    /**
     * 删除期刊信息
     */
    int deleteJournalById(Long journalId);
    
}
