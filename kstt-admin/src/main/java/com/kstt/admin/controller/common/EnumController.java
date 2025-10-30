package com.kstt.admin.controller.common;

import com.kstt.common.core.controller.BaseController;
import com.kstt.common.core.domain.AjaxResult;
import com.kstt.common.dto.EnumOptionDTO;
import com.kstt.common.service.EnumService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 枚举数据Controller
 */
@Tag(name = "枚举数据", description = "提供系统各类枚举选项的查询接口，用于前端下拉框等组件")
@RestController
@RequestMapping("/api/common/enums")
public class EnumController extends BaseController {

    @Operation(summary = "获取文章状态选项", description = "返回所有文章状态枚举（草稿、已提交、审稿中等）")
    @GetMapping("/article-status")
    public AjaxResult getArticleStatusOptions() {
        List<EnumOptionDTO> options = EnumService.getArticleStatusOptions();
        return success(options);
    }

    @Operation(summary = "获取稿件类型选项", description = "返回所有稿件类型枚举（原创研究、综述等）")
    @GetMapping("/manuscript-type")
    public AjaxResult getManuscriptTypeOptions() {
        List<EnumOptionDTO> options = EnumService.getManuscriptTypeOptions();
        return success(options);
    }

    @Operation(summary = "获取提交类型选项", description = "返回所有提交类型枚举（首次投稿、修订稿等）")
    @GetMapping("/submission-type")
    public AjaxResult getSubmissionTypeOptions() {
        List<EnumOptionDTO> options = EnumService.getSubmissionTypeOptions();
        return success(options);
    }

    @Operation(summary = "获取文件类型选项", description = "返回所有文件类型枚举（PDF、Word等）")
    @GetMapping("/file-type")
    public AjaxResult getFileTypeOptions() {
        List<EnumOptionDTO> options = EnumService.getFileTypeOptions();
        return success(options);
    }

    @Operation(summary = "获取学位选项", description = "返回所有学位枚举（学士、硕士、博士等）")
    @GetMapping("/degree")
    public AjaxResult getDegreeOptions() {
        List<EnumOptionDTO> options = EnumService.getDegreeOptions();
        return success(options);
    }
}

