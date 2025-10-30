package com.kstt.admin.controller.sys;

import com.kstt.common.core.domain.AjaxResult;
import com.kstt.sys.entity.SysUser;
import com.kstt.sys.service.SysUserService;
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
 * 系统用户Controller
 * 
 * @author kstt
 * @date 2025-01-01
 */
@Tag(name = "系统用户管理", description = "提供系统用户的增删改查、密码重置、状态管理等接口")
@RestController
@RequestMapping("/api/sys/user")
public class SysUserController extends BaseController {
    
    @Autowired
    private SysUserService sysUserService;

    /**
     * 查询系统用户列表
     */
    @GetMapping("/list")
    @Operation(summary = "查询系统用户列表", description = "分页查询系统用户列表，支持按用户名、邮箱、角色等条件筛选，返回包含角色信息的完整用户数据")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = TableDataInfo.class))),
            @ApiResponse(responseCode = "400", description = "请求参数错误"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public TableDataInfo list(
            @Parameter(description = "查询条件（可按用户名、邮箱、角色ID等筛选）") SysUser user) {
        startPage();
        List<SysUser> list = sysUserService.selectUserList(user);
        return getDataTable(list);
    }


    /**
     * 获取系统用户详细信息
     */
    @GetMapping(value = "/{userId}")
    @Operation(summary = "获取系统用户详细信息", description = "根据用户ID获取完整的用户信息，包括基本信息、角色信息等（密码字段不会返回）")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "获取成功",
                    content = @Content(schema = @Schema(implementation = SysUser.class))),
            @ApiResponse(responseCode = "404", description = "用户不存在"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public AjaxResult getInfo(
            @Parameter(description = "用户ID", required = true, example = "1")
            @PathVariable("userId") Long userId) {
        SysUser user = sysUserService.selectUserByUserId(userId);
        return success(user);
    }

    /**
     * 新增系统用户
     */
    @Log(title = "系统用户", businessType = BusinessType.INSERT)
    @PostMapping
    @Operation(summary = "新增系统用户", description = "创建新的系统用户，需要提供用户名、密码、邮箱等基本信息。密码会自动进行加密处理")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "新增成功"),
            @ApiResponse(responseCode = "400", description = "请求参数无效或用户名/邮箱已存在"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public AjaxResult add(
            @Parameter(description = "用户信息（必须包含用户名、密码、邮箱等）", required = true)
            @RequestBody SysUser user) {
        return toAjax(sysUserService.insertUser(user));
    }

    /**
     * 修改系统用户
     */
    @Log(title = "系统用户", businessType = BusinessType.UPDATE)
    @PutMapping
    @Operation(summary = "修改系统用户", description = "更新用户信息，需要提供完整的用户信息，包括用户ID。如果提供新密码，会自动进行加密处理")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "修改成功"),
            @ApiResponse(responseCode = "400", description = "请求参数无效"),
            @ApiResponse(responseCode = "404", description = "用户不存在"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public AjaxResult edit(
            @Parameter(description = "用户信息（必须包含userId）", required = true)
            @RequestBody SysUser user) {
        return toAjax(sysUserService.updateUser(user));
    }

    /**
     * 删除系统用户
     */
    @Log(title = "系统用户", businessType = BusinessType.DELETE)
    @DeleteMapping("/{userIds}")
    @Operation(summary = "删除系统用户", description = "批量删除系统用户，可以传入单个或多个用户ID，多个ID使用逗号分隔")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "删除成功"),
            @ApiResponse(responseCode = "400", description = "请求参数无效"),
            @ApiResponse(responseCode = "404", description = "用户不存在"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public AjaxResult remove(
            @Parameter(description = "用户ID数组，多个ID用逗号分隔，如：1,2,3", required = true, example = "1,2,3")
            @PathVariable Long[] userIds) {
        return toAjax(sysUserService.deleteUserByUserIds(userIds));
    }
}