package com.kstt.admin.controller.sys;

import com.kstt.common.core.domain.AjaxResult;
import com.kstt.sys.entity.SysInfo;
import com.kstt.sys.service.SysInfoService;
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
 * 系统信息Controller（期刊基本信息）
 * 
 * @author kstt
 * @date 2025-01-01
 */
@Tag(name = "系统信息管理", description = "管理期刊系统的基本信息，如期刊名称、ISSN号、影响因子等")
@RestController
@RequestMapping("/api/sys/info")
public class SysInfoController extends BaseController {
    
    @Autowired
    private SysInfoService sysInfoService;

    /**
     * 查询系统信息列表
     */
    @GetMapping("/list")
    @Operation(summary = "查询系统信息列表", description = "分页查询系统信息列表，用于获取期刊的基本信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = TableDataInfo.class))),
            @ApiResponse(responseCode = "400", description = "请求参数错误"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public TableDataInfo list(
            @Parameter(description = "查询条件") SysInfo sysInfo) {
        startPage();
        List<SysInfo> list = sysInfoService.selectSysInfoList(sysInfo);
        return getDataTable(list);
    }


    /**
     * 获取系统信息详细信息
     */
    @GetMapping(value = "/{infoId}")
    @Operation(summary = "获取系统信息详细信息", description = "根据ID获取期刊系统的详细信息，包括ISSN、影响因子、官网等")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "获取成功",
                    content = @Content(schema = @Schema(implementation = SysInfo.class))),
            @ApiResponse(responseCode = "404", description = "信息不存在"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public AjaxResult getInfo(
            @Parameter(description = "系统信息ID", required = true, example = "1")
            @PathVariable("infoId") Long infoId) {
        SysInfo info = sysInfoService.selectSysInfoById(infoId);
        return success(info);
    }

    /**
     * 新增系统信息
     */
    @Log(title = "系统信息", businessType = BusinessType.INSERT)
    @PostMapping
    @Operation(summary = "新增系统信息", description = "创建新的系统信息记录，用于配置期刊的基本信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "新增成功"),
            @ApiResponse(responseCode = "400", description = "请求参数无效"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public AjaxResult add(
            @Parameter(description = "系统信息", required = true)
            @RequestBody SysInfo sysInfo) {
        return toAjax(sysInfoService.insertSysInfo(sysInfo));
    }

    /**
     * 修改系统信息
     */
    @Log(title = "系统信息", businessType = BusinessType.UPDATE)
    @PutMapping
    @Operation(summary = "修改系统信息", description = "更新系统信息，需要提供完整的信息，包括信息ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "修改成功"),
            @ApiResponse(responseCode = "400", description = "请求参数无效"),
            @ApiResponse(responseCode = "404", description = "信息不存在"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public AjaxResult edit(
            @Parameter(description = "系统信息（必须包含infoId）", required = true)
            @RequestBody SysInfo sysInfo) {
        return toAjax(sysInfoService.updateSysInfo(sysInfo));
    }

    /**
     * 删除系统信息
     */
    @Log(title = "系统信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{infoIds}")
    @Operation(summary = "删除系统信息", description = "批量删除系统信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "删除成功"),
            @ApiResponse(responseCode = "400", description = "请求参数无效"),
            @ApiResponse(responseCode = "404", description = "信息不存在"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public AjaxResult remove(
            @Parameter(description = "系统信息ID数组，多个ID用逗号分隔", required = true, example = "1,2,3")
            @PathVariable Long[] infoIds) {
        return toAjax(sysInfoService.deleteSysInfoByIds(infoIds));
    }
}
