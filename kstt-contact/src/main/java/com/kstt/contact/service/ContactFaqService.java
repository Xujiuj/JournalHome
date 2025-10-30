package com.kstt.contact.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kstt.contact.entity.ContactFaq;

import java.util.List;

/**
 * 常见问题Service接口
 */
public interface ContactFaqService extends IService<ContactFaq> {

    /**
     * 查询常见问题列表
     */
    List<ContactFaq> selectFaqList(ContactFaq contactFaq);
    
    /**
     * 根据FAQ ID查询常见问题信息
     * 
     * @param faqId FAQ ID
     * @return 常见问题信息
     */
    ContactFaq selectFaqByFaqId(Long faqId);

    /**
     * 新增常见问题
     */
    int insertFaq(ContactFaq contactFaq);

    /**
     * 修改常见问题
     */
    int updateFaq(ContactFaq contactFaq);

    /**
     * 批量删除常见问题
     */
    int deleteFaqByIds(Long[] faqIds);

    /**
     * 删除常见问题信息
     */
    int deleteFaqById(Long faqId);
}
