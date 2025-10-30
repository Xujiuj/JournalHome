package com.kstt.admin.controller.sys;

import com.kstt.common.core.domain.AjaxResult;
import com.kstt.sys.entity.SysRole;
import com.kstt.sys.service.SysRoleService;
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
 * 系统角色Controller
 * 
 * @author kstt
 * @date 2025-01-01
 */
@Tag(name = "系统角色管理", description = "管理系统角色，包括角色的增删改查操作")
@RestController
@RequestMapping("/api/sys/role")
public class SysRoleController extends BaseController {
    
    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 查询系统角色列表
     */
    @GetMapping("/list")
    @Operation(summary = "查询系统角色列表", description = "分页查询系统角色列表，支持按角色名称、角色键等条件筛选")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = TableDataInfo.class))),
            @ApiResponse(responseCode = "400", description = "请求参数错误"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public TableDataInfo list(
            @Parameter(description = "查询条件（可按角色名称、角色键等筛选）") SysRole role) {
        startPage();
        List<SysRole> list = sysRoleService.selectRoleList(role);
        return getDataTable(list);
    }


    /**
     * 获取系统角色详细信息
     */
    @GetMapping(value = "/{roleId}")
    @Operation(summary = "获取系统角色详细信息", description = "根据角色ID获取角色的详细信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "获取成功",
                    content = @Content(schema = @Schema(implementation = SysRole.class))),
            @ApiResponse(responseCode = "404", description = "角色不存在"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public AjaxResult getInfo(
            @Parameter(description = "角色ID", required = true, example = "1")
            @PathVariable("roleId") Long roleId) {
        SysRole role = sysRoleService.selectRoleByRoleId(roleId);
        return success(role);
    }

    /**
     * 新增系统角色
     */
    @Log(title = "系统角色", businessType = BusinessType.INSERT)
    @PostMapping
    @Operation(summary = "新增系统角色", description = "创建新的系统角色，需要提供角色名称、角色键、权限等信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "新增成功"),
            @ApiResponse(responseCode = "400", description = "请求参数无效或角色键已存在"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public AjaxResult add(
            @Parameter(description = "角色信息", required = true)
            @RequestBody SysRole role) {
        return toAjax(sysRoleService.insertRole(role));
    }

    /**
     * 修改系统角色
     */
    @Log(title = "系统角色", businessType = BusinessType.UPDATE)
    @PutMapping
    @Operation(summary = "修改系统角色", description = "更新角色信息，需要提供完整的角色信息，包括角色ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "修改成功"),
            @ApiResponse(responseCode = "400", description = "请求参数无效"),
            @ApiResponse(responseCode = "404", description = "角色不存在"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public AjaxResult edit(
            @Parameter(description = "角色信息（必须包含roleId）", required = true)
            @RequestBody SysRole role) {
        return toAjax(sysRoleService.updateRole(role));
    }

    /**
     * 删除系统角色
     */
    @Log(title = "系统角色", businessType = BusinessType.DELETE)
    @DeleteMapping("/{roleIds}")
    @Operation(summary = "删除系统角色", description = "批量删除系统角色，可以传入单个或多个角色ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "删除成功"),
            @ApiResponse(responseCode = "400", description = "请求参数无效"),
            @ApiResponse(responseCode = "404", description = "角色不存在"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public AjaxResult remove(
            @Parameter(description = "角色ID数组，多个ID用逗号分隔", required = true, example = "1,2,3")
            @PathVariable Long[] roleIds) {
        return toAjax(sysRoleService.deleteRoleByRoleIds(roleIds));
    }
}