package com.kstt.admin.controller.notices;

import com.kstt.common.core.domain.AjaxResult;
import com.kstt.notices.entity.Notice;
import com.kstt.notices.service.NoticeService;
import com.kstt.common.annotation.Log;
import com.kstt.common.core.controller.BaseController;
import com.kstt.common.core.page.TableDataInfo;
import com.kstt.common.enums.BusinessType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 通知Controller
 * 
 * @author kstt
 * @date 2025-01-01
 */
@Tag(name = "通知管理", description = "管理系统通知公告，包括通知的发布、修改和删除")
@RestController
@RequestMapping("/api/notices")
public class NoticeController extends BaseController {
    
    @Autowired
    private NoticeService noticeService;

    /**
     * 查询通知列表
     */
    @GetMapping("/list")
    @Operation(summary = "查询通知列表", description = "分页查询通知列表，支持按标题、状态等条件筛选")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = TableDataInfo.class))),
            @ApiResponse(responseCode = "400", description = "请求参数错误"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public TableDataInfo list(
            @Parameter(description = "查询条件（可按标题、状态等筛选）") Notice notice) {
        startPage();
        List<Notice> list = noticeService.selectNoticeList(notice);
        return getDataTable(list);
    }


    /**
     * 获取通知详细信息
     */
    @GetMapping(value = "/{noticeId}")
    @Operation(summary = "获取通知详细信息", description = "根据通知ID获取通知的详细内容")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "获取成功",
                    content = @Content(schema = @Schema(implementation = Notice.class))),
            @ApiResponse(responseCode = "404", description = "通知不存在"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public AjaxResult getInfo(
            @Parameter(description = "通知ID", required = true, example = "1")
            @PathVariable("noticeId") Long noticeId) {
        Notice notice = noticeService.selectNoticeByNoticeId(noticeId);
        return success(notice);
    }

    /**
     * 新增通知
     */
    @Log(title = "通知", businessType = BusinessType.INSERT)
    @PostMapping
    @Operation(summary = "新增通知", description = "发布新的通知公告")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "新增成功"),
            @ApiResponse(responseCode = "400", description = "请求参数无效"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public AjaxResult add(
            @Parameter(description = "通知信息", required = true)
            @RequestBody Notice notice) {
        return toAjax(noticeService.insertNotice(notice));
    }

    /**
     * 修改通知
     */
    @Log(title = "通知", businessType = BusinessType.UPDATE)
    @PutMapping
    @Operation(summary = "修改通知", description = "更新通知信息，需要提供完整的通知信息，包括通知ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "修改成功"),
            @ApiResponse(responseCode = "400", description = "请求参数无效"),
            @ApiResponse(responseCode = "404", description = "通知不存在"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public AjaxResult edit(
            @Parameter(description = "通知信息（必须包含noticeId）", required = true)
            @RequestBody Notice notice) {
        return toAjax(noticeService.updateNotice(notice));
    }

    /**
     * 删除通知
     */
    @Log(title = "通知", businessType = BusinessType.DELETE)
    @DeleteMapping("/{noticeIds}")
    @Operation(summary = "删除通知", description = "批量删除通知，可以传入单个或多个通知ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "删除成功"),
            @ApiResponse(responseCode = "400", description = "请求参数无效"),
            @ApiResponse(responseCode = "404", description = "通知不存在"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public AjaxResult remove(
            @Parameter(description = "通知ID数组，多个ID用逗号分隔", required = true, example = "1,2,3")
            @PathVariable Long[] noticeIds) {
        return toAjax(noticeService.deleteNoticeByIds(noticeIds));
    }
}
