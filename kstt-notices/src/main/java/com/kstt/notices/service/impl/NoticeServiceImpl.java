package com.kstt.notices.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kstt.notices.entity.Notice;
import com.kstt.notices.mapper.NoticeMapper;
import com.kstt.notices.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 通知Service实现类
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {
    
    @Autowired
    private NoticeMapper noticeMapper;
    
    @Override
    public List<Notice> selectNoticeList(Notice notice) {
        return noticeMapper.selectNoticeList(notice);
    }
    
    @Override
    public Notice selectNoticeByNoticeId(Long noticeId) {
        return noticeMapper.selectNoticeByNoticeId(noticeId);
    }
    
    @Override
    public int insertNotice(Notice notice) {
        return noticeMapper.insertNotice(notice);
    }
    
    @Override
    public int updateNotice(Notice notice) {
        return noticeMapper.updateNotice(notice);
    }
    
    @Override
    public int deleteNoticeByIds(Long[] noticeIds) {
        return noticeMapper.deleteNoticeByIds(noticeIds);
    }
    
    @Override
    public int deleteNoticeById(Long noticeId) {
        return noticeMapper.deleteNoticeById(noticeId);
    }
}
