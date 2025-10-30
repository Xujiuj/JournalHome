package com.kstt.admin.controller.sys;

import com.kstt.common.core.domain.AjaxResult;
import com.kstt.sys.entity.SysMenu;
import com.kstt.sys.service.SysMenuService;
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
 * 系统菜单Controller
 * 
 * @author kstt
 * @date 2025-01-01
 */
@Tag(name = "系统菜单管理", description = "管理系统菜单权限，支持树形结构的菜单配置")
@RestController
@RequestMapping("/api/sys/menu")
public class SysMenuController extends BaseController {
    
    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 查询系统菜单列表
     */
    @GetMapping("/list")
    @Operation(summary = "查询系统菜单列表", description = "分页查询系统菜单列表，支持按菜单名称、类型、状态等条件筛选")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = TableDataInfo.class))),
            @ApiResponse(responseCode = "400", description = "请求参数错误"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public TableDataInfo list(
            @Parameter(description = "查询条件（可按菜单名称、类型等筛选）") SysMenu menu) {
        startPage();
        List<SysMenu> list = sysMenuService.selectMenuList(menu);
        return getDataTable(list);
    }


    /**
     * 获取系统菜单详细信息
     */
    @GetMapping(value = "/{menuId}")
    @Operation(summary = "获取系统菜单详细信息", description = "根据菜单ID获取菜单的详细信息，包括父级菜单、权限标识等")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "获取成功",
                    content = @Content(schema = @Schema(implementation = SysMenu.class))),
            @ApiResponse(responseCode = "404", description = "菜单不存在"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public AjaxResult getInfo(
            @Parameter(description = "菜单ID", required = true, example = "1")
            @PathVariable("menuId") Long menuId) {
        SysMenu menu = sysMenuService.selectMenuByMenuId(menuId);
        return success(menu);
    }

    /**
     * 新增系统菜单
     */
    @Log(title = "系统菜单", businessType = BusinessType.INSERT)
    @PostMapping
    @Operation(summary = "新增系统菜单", description = "创建新的系统菜单，需要提供菜单名称、类型、路由、权限标识等信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "新增成功"),
            @ApiResponse(responseCode = "400", description = "请求参数无效或菜单路由已存在"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public AjaxResult add(
            @Parameter(description = "菜单信息", required = true)
            @RequestBody SysMenu menu) {
        return toAjax(sysMenuService.insertMenu(menu));
    }

    /**
     * 修改系统菜单
     */
    @Log(title = "系统菜单", businessType = BusinessType.UPDATE)
    @PutMapping
    @Operation(summary = "修改系统菜单", description = "更新菜单信息，需要提供完整的菜单信息，包括菜单ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "修改成功"),
            @ApiResponse(responseCode = "400", description = "请求参数无效"),
            @ApiResponse(responseCode = "404", description = "菜单不存在"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public AjaxResult edit(
            @Parameter(description = "菜单信息（必须包含menuId）", required = true)
            @RequestBody SysMenu menu) {
        return toAjax(sysMenuService.updateMenu(menu));
    }

    /**
     * 删除系统菜单
     */
    @Log(title = "系统菜单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{menuIds}")
    @Operation(summary = "删除系统菜单", description = "批量删除系统菜单，可以传入单个或多个菜单ID。注意：删除父菜单会同时删除子菜单")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "删除成功"),
            @ApiResponse(responseCode = "400", description = "请求参数无效或存在子菜单"),
            @ApiResponse(responseCode = "404", description = "菜单不存在"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public AjaxResult remove(
            @Parameter(description = "菜单ID数组，多个ID用逗号分隔", required = true, example = "1,2,3")
            @PathVariable Long[] menuIds) {
        return toAjax(sysMenuService.deleteMenuByMenuIds(menuIds));
    }
}
