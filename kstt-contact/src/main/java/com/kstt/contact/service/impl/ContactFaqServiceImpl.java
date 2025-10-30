package com.kstt.contact.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kstt.contact.entity.ContactFaq;
import com.kstt.contact.mapper.ContactFaqMapper;
import com.kstt.contact.service.ContactFaqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 常见问题Service实现类
 */
@Service
public class ContactFaqServiceImpl extends ServiceImpl<ContactFaqMapper, ContactFaq> implements ContactFaqService {
    
    @Autowired
    private ContactFaqMapper contactFaqMapper;
    
    @Override
    public List<ContactFaq> selectFaqList(ContactFaq contactFaq) {
        return contactFaqMapper.selectFaqList(contactFaq);
    }
    
    @Override
    public ContactFaq selectFaqByFaqId(Long faqId) {
        return contactFaqMapper.selectFaqByFaqId(faqId);
    }
    
    @Override
    public int insertFaq(ContactFaq contactFaq) {
        return contactFaqMapper.insertFaq(contactFaq);
    }
    
    @Override
    public int updateFaq(ContactFaq contactFaq) {
        return contactFaqMapper.updateFaq(contactFaq);
    }
    
    @Override
    public int deleteFaqByIds(Long[] faqIds) {
        return contactFaqMapper.deleteFaqByIds(faqIds);
    }
    
    @Override
    public int deleteFaqById(Long faqId) {
        return contactFaqMapper.deleteFaqById(faqId);
    }
}
