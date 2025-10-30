package com.kstt.admin.controller.contact;

import com.kstt.common.core.domain.AjaxResult;
import com.kstt.contact.entity.ContactFaq;
import com.kstt.contact.service.ContactFaqService;
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
 * 常见问题FAQ Controller
 * 
 * @author kstt
 * @date 2025-01-01
 */
@Tag(name = "常见问题管理", description = "管理网站常见问题（FAQ），为用户提供自助服务")
@RestController
@RequestMapping("/api/contact/faq")
public class ContactFaqController extends BaseController {
    
    @Autowired
    private ContactFaqService contactFaqService;

    /**
     * 查询联系FAQ列表
     */
    @GetMapping("/list")
    @Operation(summary = "查询FAQ列表", description = "分页查询常见问题列表，支持按问题内容、状态等条件筛选")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = TableDataInfo.class))),
            @ApiResponse(responseCode = "400", description = "请求参数错误"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public TableDataInfo list(
            @Parameter(description = "查询条件（可按问题、状态等筛选）") ContactFaq contactFaq) {
        startPage();
        List<ContactFaq> list = contactFaqService.selectFaqList(contactFaq);
        return getDataTable(list);
    }


    /**
     * 获取联系FAQ详细信息
     */
    @GetMapping(value = "/{faqId}")
    @Operation(summary = "获取FAQ详细信息", description = "根据FAQ ID获取问题的详细内容和答案")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "获取成功",
                    content = @Content(schema = @Schema(implementation = ContactFaq.class))),
            @ApiResponse(responseCode = "404", description = "FAQ不存在"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public AjaxResult getInfo(
            @Parameter(description = "FAQ ID", required = true, example = "1")
            @PathVariable("faqId") Long faqId) {
        ContactFaq faq = contactFaqService.selectFaqByFaqId(faqId);
        return success(faq);
    }

    /**
     * 新增联系FAQ
     */
    @Log(title = "联系FAQ", businessType = BusinessType.INSERT)
    @PostMapping
    @Operation(summary = "新增FAQ", description = "创建新的常见问题，需要提供问题、答案、排序等信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "新增成功"),
            @ApiResponse(responseCode = "400", description = "请求参数无效"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public AjaxResult add(
            @Parameter(description = "FAQ信息", required = true)
            @RequestBody ContactFaq contactFaq) {
        return toAjax(contactFaqService.insertFaq(contactFaq));
    }

    /**
     * 修改联系FAQ
     */
    @Log(title = "联系FAQ", businessType = BusinessType.UPDATE)
    @PutMapping
    @Operation(summary = "修改FAQ", description = "更新FAQ信息，需要提供完整的FAQ信息，包括FAQ ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "修改成功"),
            @ApiResponse(responseCode = "400", description = "请求参数无效"),
            @ApiResponse(responseCode = "404", description = "FAQ不存在"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public AjaxResult edit(
            @Parameter(description = "FAQ信息（必须包含faqId）", required = true)
            @RequestBody ContactFaq contactFaq) {
        return toAjax(contactFaqService.updateFaq(contactFaq));
    }

    /**
     * 删除联系FAQ
     */
    @Log(title = "联系FAQ", businessType = BusinessType.DELETE)
    @DeleteMapping("/{faqIds}")
    @Operation(summary = "删除FAQ", description = "批量删除常见问题，可以传入单个或多个FAQ ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "删除成功"),
            @ApiResponse(responseCode = "400", description = "请求参数无效"),
            @ApiResponse(responseCode = "404", description = "FAQ不存在"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public AjaxResult remove(
            @Parameter(description = "FAQ ID数组，多个ID用逗号分隔", required = true, example = "1,2,3")
            @PathVariable Long[] faqIds) {
        return toAjax(contactFaqService.deleteFaqByIds(faqIds));
    }
}
