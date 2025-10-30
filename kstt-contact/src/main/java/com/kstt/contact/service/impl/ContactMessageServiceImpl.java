package com.kstt.contact.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kstt.contact.entity.ContactMessage;
import com.kstt.contact.mapper.ContactMessageMapper;
import com.kstt.contact.service.ContactMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 联系消息Service实现类
 */
@Service
public class ContactMessageServiceImpl extends ServiceImpl<ContactMessageMapper, ContactMessage> implements ContactMessageService {
    
    @Autowired
    private ContactMessageMapper contactMessageMapper;
    
    @Override
    public List<ContactMessage> selectContactMessageList(ContactMessage contactMessage) {
        return contactMessageMapper.selectContactMessageList(contactMessage);
    }
    
    @Override
    public ContactMessage selectContactMessageByMessageId(Long messageId) {
        return contactMessageMapper.selectContactMessageByMessageId(messageId);
    }
    
    @Override
    public int insertContactMessage(ContactMessage contactMessage) {
        return contactMessageMapper.insertContactMessage(contactMessage);
    }
    
    @Override
    public int updateContactMessage(ContactMessage contactMessage) {
        return contactMessageMapper.updateContactMessage(contactMessage);
    }
    
    @Override
    public int deleteContactMessageByIds(Long[] messageIds) {
        return contactMessageMapper.deleteContactMessageByIds(messageIds);
    }
    
    @Override
    public int deleteContactMessageById(Long messageId) {
        return contactMessageMapper.deleteContactMessageById(messageId);
    }
}
