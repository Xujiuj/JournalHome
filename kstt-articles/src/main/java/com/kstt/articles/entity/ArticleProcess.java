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

import java.time.LocalDateTime;

/**
 * 论文处理流程实体类
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("article_process")
public class ArticleProcess extends BaseEntity {

    /**
     * 流程ID
     */
    @TableId(type = IdType.AUTO)
    private Long processId;

    /**
     * 关联的article_id
     */
    private Long processArticleId;

    /**
     * 当前状态ID
     */
    private Integer processCurrentStatusId;

    /**
     * 前一状态ID
     */
    private Integer processPreviousStatusId;

    /**
     * 负责编辑ID
     */
    private Long processEditorId;

    /**
     * 分配时间
     */
    private LocalDateTime processAssignTime;

    /**
     * 截止日期
     */
    private LocalDateTime processDueDate;

    /**
     * 优先级(1：低，2：中，3：高)
     */
    private Integer processPriority;

    /**
     * 处理备注
     */
    private String processNotes;

    /**
     * 最终决定(accept, reject, revise)
     */
    private String processDecision;

    /**
     * 决定原因
     */
    private String processDecisionReason;

    /**
     * 决定时间
     */
    private LocalDateTime processDecisionTime;

    /**
     * 关联的论文信息（不作为数据库字段）
     */
    @TableField(exist = false)
    @JsonIgnore
    private Article processArticle;

    /**
     * 关联的编辑信息（不作为数据库字段）
     */
    @TableField(exist = false)
    private SysUser processEditor;

    /**
     * 关联的当前状态信息（不作为数据库字段）
     */
    @TableField(exist = false)
    private ArticleStatus processCurrentStatus;

    /**
     * 关联的前一状态信息（不作为数据库字段）
     */
    @TableField(exist = false)
    private ArticleStatus processPreviousStatus;
}
