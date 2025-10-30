package com.kstt.contact.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kstt.contact.entity.ContactMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 联系消息Mapper接口
 */
@Mapper
public interface ContactMessageMapper extends BaseMapper<ContactMessage> {
    
    /**
     * 查询联系消息列表
     */
    List<ContactMessage> selectContactMessageList(ContactMessage contactMessage);
    
    /**
     * 根据消息ID查询联系消息信息
     * 
     * @param messageId 消息ID
     * @return 联系消息信息
     */
    ContactMessage selectContactMessageByMessageId(@Param("messageId") Long messageId);
    
    /**
     * 新增联系消息
     */
    int insertContactMessage(ContactMessage contactMessage);
    
    /**
     * 修改联系消息
     */
    int updateContactMessage(ContactMessage contactMessage);
    
    /**
     * 批量删除联系消息
     */
    int deleteContactMessageByIds(@Param("messageIds") Long[] messageIds);
    
    /**
     * 删除联系消息信息
     */
    int deleteContactMessageById(@Param("messageId") Long messageId);
}
