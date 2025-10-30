package com.kstt.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.kstt.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 系统信息实体类（期刊信息）
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_info")
public class SysInfo extends BaseEntity {
    
    /**
     * 信息ID
     */
    @TableId(type = IdType.AUTO)
    private Long infoId;
    
    /**
     * 期刊名称
     */
    private String infoName;
    
    /**
     * 期刊简称
     */
    private String infoSimpleName;
    
    /**
     * ISSN号
     */
    private String infoIssn;
    
    /**
     * 期刊描述
     */
    private String infoComment;
    
    /**
     * 期刊使命
     */
    private String infoMission;
    
    /**
     * 影响因子
     */
    private BigDecimal infoImpactFactor;
    
    /**
     * 创刊年份
     */
    private Integer infoFoundedYear;
    
    /**
     * 出版社
     */
    private String infoPublisher;
    
    /**
     * 官网
     */
    private String infoWebsite;
    
    /**
     * 联系邮箱
     */
    private String infoEmail;
    
    /**
     * 联系电话
     */
    private String infoPhone;
    
    /**
     * 地址
     */
    private String infoAddress;

    /**
     * 期刊Logo
     */
    private String infoLogo;

    /**
     * 出版商Logo
     */
    private String infoPublisherLogo;

}

