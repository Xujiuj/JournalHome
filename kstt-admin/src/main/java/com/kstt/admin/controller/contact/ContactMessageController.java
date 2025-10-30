package com.kstt.admin.controller.contact;

import com.kstt.common.core.domain.AjaxResult;
import com.kstt.contact.entity.ContactMessage;
import com.kstt.contact.service.ContactMessageService;
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
 * 联系消息Controller
 * 
 * @author kstt
 * @date 2025-01-01
 */
@Tag(name = "联系消息管理", description = "管理用户提交的联系消息，包括消息查看、回复和处理")
@RestController
@RequestMapping("/api/contact/message")
public class ContactMessageController extends BaseController {
    
    @Autowired
    private ContactMessageService contactMessageService;

    /**
     * 查询联系消息列表
     */
    @GetMapping("/list")
    @Operation(summary = "查询联系消息列表", description = "分页查询用户提交的联系消息列表，支持按状态、发送者等条件筛选")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = TableDataInfo.class))),
            @ApiResponse(responseCode = "400", description = "请求参数错误"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public TableDataInfo list(
            @Parameter(description = "查询条件（可按状态、发送者等筛选）") ContactMessage contactMessage) {
        startPage();
        List<ContactMessage> list = contactMessageService.selectContactMessageList(contactMessage);
        return getDataTable(list);
    }


    /**
     * 获取联系消息详细信息
     */
    @GetMapping(value = "/{messageId}")
    @Operation(summary = "获取联系消息详细信息", description = "根据消息ID获取消息的详细内容，包括发送者信息、消息内容、回复等")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "获取成功",
                    content = @Content(schema = @Schema(implementation = ContactMessage.class))),
            @ApiResponse(responseCode = "404", description = "消息不存在"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public AjaxResult getInfo(
            @Parameter(description = "消息ID", required = true, example = "1")
            @PathVariable("messageId") Long messageId) {
        ContactMessage message = contactMessageService.selectContactMessageByMessageId(messageId);
        return success(message);
    }

    /**
     * 新增联系消息
     */
    @Log(title = "联系消息", businessType = BusinessType.INSERT)
    @PostMapping
    @Operation(summary = "新增联系消息", description = "用户提交新的联系消息，需要提供姓名、邮箱、主题、内容等信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "新增成功"),
            @ApiResponse(responseCode = "400", description = "请求参数无效"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public AjaxResult add(
            @Parameter(description = "联系消息信息", required = true)
            @RequestBody ContactMessage contactMessage) {
        return toAjax(contactMessageService.insertContactMessage(contactMessage));
    }

    /**
     * 修改联系消息
     */
    @Log(title = "联系消息", businessType = BusinessType.UPDATE)
    @PutMapping
    @Operation(summary = "修改联系消息", description = "更新消息状态或添加回复，需要提供完整的消息信息，包括消息ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "修改成功"),
            @ApiResponse(responseCode = "400", description = "请求参数无效"),
            @ApiResponse(responseCode = "404", description = "消息不存在"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public AjaxResult edit(
            @Parameter(description = "联系消息信息（必须包含messageId）", required = true)
            @RequestBody ContactMessage contactMessage) {
        return toAjax(contactMessageService.updateContactMessage(contactMessage));
    }

    /**
     * 删除联系消息
     */
    @Log(title = "联系消息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{messageIds}")
    @Operation(summary = "删除联系消息", description = "批量删除联系消息，可以传入单个或多个消息ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "删除成功"),
            @ApiResponse(responseCode = "400", description = "请求参数无效"),
            @ApiResponse(responseCode = "404", description = "消息不存在"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public AjaxResult remove(
            @Parameter(description = "消息ID数组，多个ID用逗号分隔", required = true, example = "1,2,3")
            @PathVariable Long[] messageIds) {
        return toAjax(contactMessageService.deleteContactMessageByIds(messageIds));
    }
}
