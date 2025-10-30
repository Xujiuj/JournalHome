package com.kstt.admin.controller.journal;

import com.kstt.application.journal.service.JournalApplicationService;
import com.kstt.journal.model.dto.JournalDetailDTO;
import com.kstt.journal.model.dto.JournalListResult;
import com.kstt.journal.model.query.JournalQuery;
import com.kstt.common.annotation.Log;
import com.kstt.common.constant.HttpStatus;
import com.kstt.common.core.controller.BaseController;
import com.kstt.common.core.domain.AjaxResult;
import com.kstt.common.core.page.TableDataInfo;
import com.kstt.common.enums.BusinessType;
import com.kstt.journal.entity.Journal;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

/**
 * 期刊Controller
 * 
 * @author kstt
 * @date 2025-01-01
 */
@Tag(name = "期刊管理")
@RestController
@RequestMapping("/api/journal")
@RequiredArgsConstructor
public class JournalController extends BaseController {

    private final JournalApplicationService journalApplicationService;

    /**
     * 查询期刊列表
     */
    @GetMapping("/list")
    @Operation(summary = "查询期刊列表", description = "分页查询期刊列表，支持按卷次、期号、状态、主题等条件筛选，返回包含文章数量的期刊概要信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = TableDataInfo.class))),
            @ApiResponse(responseCode = "400", description = "请求参数错误"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public TableDataInfo list(
            @Parameter(description = "查询条件") JournalQuery query) {
        startPage();
        JournalListResult result = journalApplicationService.list(query);
        TableDataInfo table = new TableDataInfo();
        table.setCode(HttpStatus.SUCCESS);
        table.setMsg("查询成功");
        table.setTotal(result.getTotal());
        table.setData(result.getRecords());
        return table;
    }


    /**
     * 获取期刊详细信息
     */
    @GetMapping(value = "/{journalId}")
    @Operation(summary = "获取期刊详细信息", description = "根据期刊ID获取完整的期刊信息，包括卷次、期号、出版日期、主题、简介、封面、PDF文件等详细信息以及关联的文章数量")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "获取成功",
                    content = @Content(schema = @Schema(implementation = JournalDetailDTO.class))),
            @ApiResponse(responseCode = "404", description = "期刊不存在"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public AjaxResult getInfo(
            @Parameter(description = "期刊ID", required = true, example = "1")
            @PathVariable("journalId") Long journalId) {
        JournalDetailDTO detail = journalApplicationService.detail(journalId);
        return success(detail);
    }

    /**
     * 新增期刊
     */
    @Log(title = "期刊", businessType = BusinessType.INSERT)
    @PostMapping
    @Operation(summary = "新增期刊", description = "创建新的期刊记录，需要提供卷次、期号、出版日期、状态、主题、简介等信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "新增成功"),
            @ApiResponse(responseCode = "400", description = "请求参数无效"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public AjaxResult add(
            @Parameter(description = "期刊信息", required = true)
            @RequestBody Journal journal) {
        return toAjax(journalApplicationService.create(journal));
    }

    /**
     * 修改期刊
     */
    @Log(title = "期刊", businessType = BusinessType.UPDATE)
    @PutMapping
    @Operation(summary = "修改期刊", description = "更新期刊信息，需要提供完整的期刊信息，包括期刊ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "修改成功"),
            @ApiResponse(responseCode = "400", description = "请求参数无效"),
            @ApiResponse(responseCode = "404", description = "期刊不存在"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public AjaxResult edit(
            @Parameter(description = "期刊信息（必须包含journalId）", required = true)
            @RequestBody Journal journal) {
        return toAjax(journalApplicationService.modify(journal));
    }

    /**
     * 删除期刊
     */
    @Log(title = "期刊", businessType = BusinessType.DELETE)
    @DeleteMapping("/{journalIds}")
    @Operation(summary = "删除期刊", description = "批量删除期刊，可以传入单个或多个期刊ID，多个ID使用逗号分隔")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "删除成功"),
            @ApiResponse(responseCode = "400", description = "请求参数无效"),
            @ApiResponse(responseCode = "404", description = "期刊不存在"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public AjaxResult remove(
            @Parameter(description = "期刊ID数组，多个ID用逗号分隔，如：1,2,3", required = true, example = "1,2,3")
            @PathVariable Long[] journalIds) {
        return toAjax(journalApplicationService.deleteByIds(journalIds));
    }
}