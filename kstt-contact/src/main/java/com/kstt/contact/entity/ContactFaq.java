package com.kstt.contact.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.kstt.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 常见问题实体类
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("contact_faq")
public class ContactFaq extends BaseEntity {

    /**
     * FAQ ID
     */
    @TableId(type = IdType.AUTO)
    private Long faqId;

    /**
     * 问题
     */
    private String faqQuestion;

    /**
     * 答案
     */
    private String faqAnswer;

    /**
     * 排序
     */
    private Integer faqOrder;

    /**
     * 状态(0：禁用，1：启用)
     */
    private Integer faqStatus;
}