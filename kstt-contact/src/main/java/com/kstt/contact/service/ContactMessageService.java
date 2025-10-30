package com.kstt.contact.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kstt.contact.entity.ContactMessage;

import java.util.List;

/**
 * 联系消息Service接口
 */
public interface ContactMessageService extends IService<ContactMessage> {
    
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
    ContactMessage selectContactMessageByMessageId(Long messageId);
    
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
    int deleteContactMessageByIds(Long[] messageIds);
    
    /**
     * 删除联系消息信息
     */
    int deleteContactMessageById(Long messageId);
}
