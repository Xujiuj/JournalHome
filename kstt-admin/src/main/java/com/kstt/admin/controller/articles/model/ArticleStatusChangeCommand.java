package com.kstt.admin.controller.articles.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 文章状态更新指令
 */
@Data
@Schema(description = "文章状态更新指令")
public class ArticleStatusChangeCommand {

    @Schema(description = "操作人ID，如果未提供则使用当前登录用户", example = "1001")
    private Long operatorId;

    @Schema(description = "操作人名称", example = "editor_zhang")
    private String operatorName;

    @Schema(description = "状态变更备注", example = "初审完成，进入外审阶段")
    private String remark;

    @Schema(description = "审稿截止时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-11-30 18:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dueDate;

    @Schema(description = "决策原因，可用于拒稿/接受/发布的额外说明", example = "评审意见达到接受标准")
    private String decisionReason;
}
