package com.kstt.admin.controller.about;

import com.kstt.about.entity.AboutEditorialTeam;
import com.kstt.about.service.AboutEditorialTeamService;
import com.kstt.common.annotation.Log;
import com.kstt.common.core.controller.BaseController;
import com.kstt.common.core.page.TableDataInfo;
import com.kstt.common.core.domain.AjaxResult;
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
 * 编辑团队Controller
 * 
 * @author kstt
 * @date 2025-01-01
 */
@Tag(name = "编辑团队管理", description = "管理期刊编辑团队成员信息，包括编辑、审稿人等")
@RestController
@RequestMapping("/api/team")
public class AboutEditorialTeamController extends BaseController {
    
    @Autowired
    private AboutEditorialTeamService aboutEditorialTeamService;

    /**
     * 查询编辑团队列表
     */
    @GetMapping("/list")
    @Operation(summary = "查询编辑团队列表", description = "分页查询编辑团队成员列表，支持按姓名、职位、状态等条件筛选")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = TableDataInfo.class))),
            @ApiResponse(responseCode = "400", description = "请求参数错误"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public TableDataInfo list(
            @Parameter(description = "查询条件（可按姓名、职位等筛选）") AboutEditorialTeam aboutEditorialTeam) {
        startPage();
        List<AboutEditorialTeam> list = aboutEditorialTeamService.selectAboutEditorialTeamList(aboutEditorialTeam);
        return getDataTable(list);
    }



    /**
     * 获取编辑团队详细信息
     */
    @GetMapping(value = "/{teamId}")
    @Operation(summary = "获取编辑团队成员详细信息", description = "根据团队成员ID获取详细信息，包括姓名、职位、简介等")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "获取成功",
                    content = @Content(schema = @Schema(implementation = AboutEditorialTeam.class))),
            @ApiResponse(responseCode = "404", description = "团队成员不存在"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public AjaxResult getInfo(
            @Parameter(description = "团队成员ID", required = true, example = "1")
            @PathVariable("teamId") Long teamId) {
        AboutEditorialTeam team = aboutEditorialTeamService.selectAboutEditorialTeamByTeamId(teamId);
        return success(team);
    }

    /**
     * 新增编辑团队
     */
    @Log(title = "编辑团队", businessType = BusinessType.INSERT)
    @PostMapping
    @Operation(summary = "新增编辑团队成员", description = "添加新的编辑团队成员，需要提供姓名、职位、简介等信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "新增成功"),
            @ApiResponse(responseCode = "400", description = "请求参数无效"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public AjaxResult add(
            @Parameter(description = "团队成员信息", required = true)
            @RequestBody AboutEditorialTeam aboutEditorialTeam) {
        return toAjax(aboutEditorialTeamService.insertAboutEditorialTeam(aboutEditorialTeam));
    }

    /**
     * 修改编辑团队
     */
    @Log(title = "编辑团队", businessType = BusinessType.UPDATE)
    @PutMapping
    @Operation(summary = "修改编辑团队成员", description = "更新团队成员信息，需要提供完整的成员信息，包括成员ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "修改成功"),
            @ApiResponse(responseCode = "400", description = "请求参数无效"),
            @ApiResponse(responseCode = "404", description = "团队成员不存在"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public AjaxResult edit(
            @Parameter(description = "团队成员信息（必须包含teamId）", required = true)
            @RequestBody AboutEditorialTeam aboutEditorialTeam) {
        return toAjax(aboutEditorialTeamService.updateAboutEditorialTeam(aboutEditorialTeam));
    }

    /**
     * 删除编辑团队
     */
    @Log(title = "编辑团队", businessType = BusinessType.DELETE)
    @DeleteMapping("/{teamIds}")
    @Operation(summary = "删除编辑团队成员", description = "批量删除团队成员，可以传入单个或多个成员ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "删除成功"),
            @ApiResponse(responseCode = "400", description = "请求参数无效"),
            @ApiResponse(responseCode = "404", description = "团队成员不存在"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public AjaxResult remove(
            @Parameter(description = "团队成员ID数组，多个ID用逗号分隔", required = true, example = "1,2,3")
            @PathVariable Long[] teamIds) {
        return toAjax(aboutEditorialTeamService.deleteAboutEditorialTeamByTeamIds(teamIds));
    }
}
