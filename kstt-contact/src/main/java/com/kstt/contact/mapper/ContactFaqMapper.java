package com.kstt.contact.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kstt.contact.entity.ContactFaq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 常见问题Mapper接口
 */
@Mapper
public interface ContactFaqMapper extends BaseMapper<ContactFaq> {
    
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
    ContactFaq selectFaqByFaqId(@Param("faqId") Long faqId);
    
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
    int deleteFaqByIds(@Param("faqIds") Long[] faqIds);
    
    /**
     * 删除常见问题信息
     */
    int deleteFaqById(@Param("faqId") Long faqId);
}
