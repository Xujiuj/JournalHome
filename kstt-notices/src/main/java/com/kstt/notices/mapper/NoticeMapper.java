package com.kstt.notices.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kstt.notices.entity.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 通知Mapper接口
 */
@Mapper
public interface NoticeMapper extends BaseMapper<Notice> {
    
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
    int deleteNoticeByIds(@Param("noticeIds") Long[] noticeIds);
    
    /**
     * 删除通知信息
     */
    int deleteNoticeById(@Param("noticeId") Long noticeId);
}
