package com.kstt.admin.controller.sys;

import com.kstt.common.core.domain.AjaxResult;
import com.kstt.common.core.page.TableDataInfo;
import com.kstt.sys.entity.SysUserAcademicDegree;
import com.kstt.sys.service.SysUserAcademicDegreeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户学术学位信息Controller
 */
@Tag(name = "学术学位管理", description = "用户学术学位信息管理相关接口")
@RestController
@RequestMapping("/api/sys/academic-degree")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5173", "http://localhost:5174", "http://localhost:8000"})
public class SysUserAcademicDegreeController {

    private final SysUserAcademicDegreeService sysUserAcademicDegreeService;

    /**
     * 查询学位列表
     */
    @Operation(summary = "查询学位列表", description = "分页查询学术学位信息列表")
    @GetMapping("/list")
    public AjaxResult list(
            @Parameter(description = "学位名称") @RequestParam(required = false) String degreeName,
            @Parameter(description = "学位级别") @RequestParam(required = false) String degreeLevel,
            @Parameter(description = "页码") @RequestParam(defaultValue = "1") Integer pageNum,
            @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") Integer pageSize) {

        SysUserAcademicDegree degree = new SysUserAcademicDegree();
        degree.setDegreeName(degreeName);
        degree.setDegreeLevel(degreeLevel);

        List<SysUserAcademicDegree> list = sysUserAcademicDegreeService.selectDegreeList(degree);
        TableDataInfo rspData = new TableDataInfo();
        rspData.setData(list);
        rspData.setTotal(list.size());
        return AjaxResult.success(rspData);
    }

    /**
     * 获取学位详细信息
     */
    @Operation(summary = "获取学位详细信息", description = "根据学位ID获取学位详细信息")
    @GetMapping("/{degreeId}")
    public AjaxResult getInfo(@Parameter(description = "学位ID", required = true) @PathVariable Integer degreeId) {
        return AjaxResult.success(sysUserAcademicDegreeService.selectDegreeByDegreeId(degreeId));
    }

    /**
     * 获取所有学位列表
     */
    @Operation(summary = "获取所有学位列表", description = "获取所有学术学位列表，用于下拉框等")
    @GetMapping("/all")
    public AjaxResult getAllDegrees() {
        List<SysUserAcademicDegree> list = sysUserAcademicDegreeService.selectDegreeList(new SysUserAcademicDegree());
        return AjaxResult.success(list);
    }
}

