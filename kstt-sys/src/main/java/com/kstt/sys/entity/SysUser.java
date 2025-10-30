package com.kstt.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.kstt.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 用户实体类
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_user")
public class SysUser extends BaseEntity {
    
    /**
     * 用户ID
     */
    @TableId(type = IdType.AUTO)
    private Long userId;
    
    /**
     * 用户名
     */
    private String userName;
    
    /**
     * 邮箱
     */
    private String userEmail;
    
    /**
     * 密码
     */
    private String userPassword;
    
    /**
     * 真实姓名
     */
    private String userRealName;
    
    /**
     * 所属机构
     */
    private String userAffiliation;
    
    /**
     * 所属学院/研究所
     */
    private String userDepartment;
    
    /**
     * 职称
     */
    private String userTitle;
    
    /**
     * 联系电话
     */
    private String userPhone;
    
    /**
     * 通讯地址
     */
    private String userAddress;
    
    /**
     * 角色ID
     */
    private Long userRoleId;
    
    /**
     * 头像
     */
    private String userAvatar;
    
    /**
     * 状态(0：禁用，1：启用)
     */
    private Integer userStatus;
    
    /**
     * 最后登录时间
     */
    private LocalDateTime userLastLogin;

    /**
     * ORCID标识符
     */
    private String userOrcid;

    /**
     * 职位
     */
    private String userPosition;


    /**
     * 国家
     */
    private String userCountry;

    /**
     * 州/省
     */
    private String userState;

    /**
     * 城市
     */
    private String userCity;

    /**
     * 邮政编码
     */
    private String userPostalCode;

    /**
     * 邮箱验证状态(0：未验证，1：已验证)
     */
    private Integer userEmailVerified;

    /**
     * 个人简介
     */
    private String userBiography;

    /**
     * 研究领域
     */
    private String userClassification;

    /**
     * 个人研究方向关键词
     */
    private String userKeywords;

    /**
     * 用户描述
     */
    private String userDetail;

    /**
     * 关联的角色信息（不作为数据库字段）
     */
    @TableField(exist = false)
    private SysRole userRole;
}
