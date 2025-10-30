package com.kstt.admin.controller.sys;

import com.kstt.common.core.domain.AjaxResult;
import com.kstt.common.core.page.TableDataInfo;
import com.kstt.sys.entity.SysArea;
import com.kstt.sys.service.SysAreaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 学科领域信息Controller
 */
@Tag(name = "学科领域管理", description = "学科领域信息管理相关接口")
@RestController
@RequestMapping("/api/sys/area")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5173", "http://localhost:5174", "http://localhost:8000"})
public class SysAreaController {

    private final SysAreaService sysAreaService;

    /**
     * 查询领域列表（公开接口，无需权限）
     */
    @Operation(summary = "查询领域列表", description = "查询学科领域信息列表")
    @GetMapping("/list")
    public AjaxResult list(
            @Parameter(description = "领域名称") @RequestParam(required = false) String areaName,
            @Parameter(description = "学科ID") @RequestParam(required = false) Integer areaSubjectId) {

        SysArea area = new SysArea();
        area.setAreaName(areaName);
        area.setAreaSubjectId(areaSubjectId);

        List<SysArea> list = sysAreaService.selectAreaList(area);
        TableDataInfo rspData = new TableDataInfo();
        rspData.setData(list);
        rspData.setTotal(list.size());
        return AjaxResult.success(rspData);
    }

    /**
     * 根据学科ID查询领域列表（公开接口）
     */
    @Operation(summary = "根据学科ID查询领域列表", description = "根据学科ID查询该学科下的所有领域")
    @GetMapping("/subject/{subjectId}")
    public AjaxResult getAreasBySubjectId(@Parameter(description = "学科ID", required = true) @PathVariable Integer subjectId) {
        List<SysArea> list = sysAreaService.selectAreasBySubjectId(subjectId);
        return AjaxResult.success(list);
    }

    /**
     * 获取领域详细信息
     */
    @Operation(summary = "获取领域详细信息", description = "根据领域ID获取领域详细信息")
    @PreAuthorize("@ss.hasPermi('system:area:query')")
    @GetMapping("/{areaId}")
    public AjaxResult getInfo(@Parameter(description = "领域ID", required = true) @PathVariable Integer areaId) {
        return AjaxResult.success(sysAreaService.selectAreaByAreaId(areaId));
    }

    /**
     * 新增领域
     */
    @Operation(summary = "新增领域", description = "新增学科领域信息")
    @PreAuthorize("@ss.hasPermi('system:area:add')")
    @PostMapping
    public AjaxResult add(@RequestBody SysArea area) {
        int result = sysAreaService.insertArea(area);
        return result > 0 ? AjaxResult.success("新增成功") : AjaxResult.error("新增失败");
    }

    /**
     * 修改领域
     */
    @Operation(summary = "修改领域", description = "修改学科领域信息")
    @PreAuthorize("@ss.hasPermi('system:area:edit')")
    @PutMapping
    public AjaxResult edit(@RequestBody SysArea area) {
        int result = sysAreaService.updateArea(area);
        return result > 0 ? AjaxResult.success("修改成功") : AjaxResult.error("修改失败");
    }

    /**
     * 删除领域
     */
    @Operation(summary = "删除领域", description = "根据领域ID删除学科领域信息")
    @PreAuthorize("@ss.hasPermi('system:area:remove')")
    @DeleteMapping("/{areaId}")
    public AjaxResult remove(@Parameter(description = "领域ID", required = true) @PathVariable Integer areaId) {
        int result = sysAreaService.deleteAreaByAreaId(areaId);
        return result > 0 ? AjaxResult.success("删除成功") : AjaxResult.error("删除失败");
    }

    /**
     * 批量删除领域
     */
    @Operation(summary = "批量删除领域", description = "批量删除学科领域信息")
    @PreAuthorize("@ss.hasPermi('system:area:remove')")
    @DeleteMapping("/batch")
    public AjaxResult batchRemove(@RequestBody Integer[] areaIds) {
        int result = sysAreaService.deleteAreaByAreaIds(areaIds);
        return result > 0 ? AjaxResult.success("删除成功") : AjaxResult.error("删除失败");
    }
}

