package com.kstt.articles.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kstt.common.entity.BaseEntity;
import com.kstt.sys.entity.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 论文作者关联实体类（只包含论文相关信息）
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("article_author")
public class ArticleAuthor extends BaseEntity {

    /**
     * 作者ID
     */
    @TableId(type = IdType.AUTO)
    private Long authorId;

    /**
     * 关联论文ID
     */
    private Long authorArticleId;

    /**
     * 关联用户ID（必须关联注册用户）
     */
    private Long authorUserId;

    /**
     * 作者顺序（1为第一作者）
     */
    private Integer authorOrder;

    /**
     * 是否为通讯作者(0：否，1：是)
     */
    private Integer authorIsCorresponding;

    /**
     * 作者贡献描述
     */
    private String authorContribution;

    /**
     * 关联的用户信息（不作为数据库字段）
     */
    @TableField(exist = false)
    private SysUser authorUser;

    /**
     * 关联的论文信息（不作为数据库字段）
     */
    @TableField(exist = false)
    @JsonIgnore
    private Article authorArticle;
}
