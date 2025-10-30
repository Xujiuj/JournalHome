package com.kstt.notices.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kstt.notices.entity.Notice;

import java.util.List;

/**
 * 通知Service接口
 */
public interface NoticeService extends IService<Notice> {
    
    /**
     * 查询通知列表
     */
    List<Notice> selectNoticeList(Notice notice);
    
    /**
     * 根据通知ID查询通知信息
     * 
     * @param noticeId 通知ID
     * @return 通知信息
     */
    Notice selectNoticeByNoticeId(Long noticeId);
    
    /**
     * 新增通知
     */
    int insertNotice(Notice notice);
    
    /**
     * 修改通知
     */
    int updateNotice(Notice notice);
    
    /**
     * 批量删除通知
     */
    int deleteNoticeByIds(Long[] noticeIds);
    
    /**
     * 删除通知信息
     */
    int deleteNoticeById(Long noticeId);
}
