package com.kstt.journal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kstt.journal.entity.Journal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 期刊Mapper接口
 */
@Mapper
public interface JournalMapper extends BaseMapper<Journal> {

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
    int deleteJournalByIds(@Param("journalIds") Long[] journalIds);

    /**
     * 删除期刊信息
     */
    int deleteJournalById(@Param("journalId") Long journalId);
    
}
