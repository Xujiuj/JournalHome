package com.kstt.journal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.kstt.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * 期刊卷期实体类
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("journal")
public class Journal extends BaseEntity {

    /**
     * 期刊卷期ID
     */
    @TableId(type = IdType.AUTO)
    private Long journalId;

    /**
     * 期刊卷次
     */
    private Integer journalVolume;

    /**
     * 期号
     */
    private Integer journalIssue;

    /**
     * 出版日期
     */
    private LocalDate journalPublishDate;

    /**
     * 状态(0：待发布，1：已发布)
     */
    private Integer journalStatus;

    /**
     * 本期主题
     */
    private String journalTheme;

    /**
     * 本期简介
     */
    private String journalDescription;

    /**
     * 封面图片
     */
    private String journalCover;

    /**
     * 整期PDF文件
     */
    private String journalPdf;
}
