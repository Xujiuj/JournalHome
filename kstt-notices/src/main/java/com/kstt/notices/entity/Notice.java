package com.kstt.notices.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.kstt.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * 通知公告实体类
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("notice")
public class Notice extends BaseEntity {
    
    /**
     * 通知ID
     */
    @TableId(type = IdType.AUTO)
    private Long noticeId;
    
    /**
     * 通知标题
     */
    private String noticeTitle;
    
    /**
     * 通知内容
     */
    private String noticeContent;
    
    /**
     * 通知类型
     */
    private String noticeType;
    
    /**
     * 状态(0：禁用，1：启用)
     */
    private Integer noticeStatus;
    
    /**
     * 是否置顶(0：否，1：是)
     */
    private Integer noticeTop;
    
    /**
     * 排序
     */
    private Integer noticeOrder;
}
