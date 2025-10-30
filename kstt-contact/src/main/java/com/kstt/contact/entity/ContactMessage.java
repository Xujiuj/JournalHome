package com.kstt.contact.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.kstt.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 联系消息实体类
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("contact_message")
public class ContactMessage extends BaseEntity {

    /**
     * 消息ID
     */
    @TableId(type = IdType.AUTO)
    private Long messageId;

    /**
     * 发送者姓名
     */
    private String messageName;

    /**
     * 发送者邮箱
     */
    private String messageEmail;

    /**
     * 消息主题
     */
    private String messageSubject;

    /**
     * 消息内容
     */
    private String messageContent;

    /**
     * 状态(0：未处理，1：已处理)
     */
    private Integer messageStatus;

    /**
     * 回复内容
     */
    private String messageReply;
}