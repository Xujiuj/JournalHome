package com.kstt.admin.controller.sys;

import com.kstt.common.core.domain.AjaxResult;
import com.kstt.common.core.page.TableDataInfo;
import com.kstt.sys.entity.SysSubject;
import com.kstt.sys.service.SysSubjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 学科信息Controller
 */
@Tag(name = "学科管理", description = "学科信息管理相关接口")
@RestController
@RequestMapping("/api/sys/subject")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5173", "http://localhost:5174", "http://localhost:8000"})
public class SysSubjectController {

    private final SysSubjectService sysSubjectService;

    /**
     * 查询学科列表（公开接口，无需权限）
     */
    @Operation(summary = "查询学科列表", description = "查询所有学科信息列表")
    @GetMapping("/list")
    public AjaxResult list(
            @Parameter(description = "学科名称") @RequestParam(required = false) String subjectName) {

        SysSubject subject = new SysSubject();
        subject.setSubjectName(subjectName);

        List<SysSubject> list = sysSubjectService.selectSubjectList(subject);
        TableDataInfo rspData = new TableDataInfo();
        rspData.setData(list);
        rspData.setTotal(list.size());
        return AjaxResult.success(rspData);
    }

    /**
     * 获取学科详细信息
     */
    @Operation(summary = "获取学科详细信息", description = "根据学科ID获取学科详细信息")
    @PreAuthorize("@ss.hasPermi('system:subject:query')")
    @GetMapping("/{subjectId}")
    public AjaxResult getInfo(@Parameter(description = "学科ID", required = true) @PathVariable Integer subjectId) {
        return AjaxResult.success(sysSubjectService.selectSubjectBySubjectId(subjectId));
    }

    /**
     * 新增学科
     */
    @Operation(summary = "新增学科", description = "新增学科信息")
    @PreAuthorize("@ss.hasPermi('system:subject:add')")
    @PostMapping
    public AjaxResult add(@RequestBody SysSubject subject) {
        int result = sysSubjectService.insertSubject(subject);
        return result > 0 ? AjaxResult.success("新增成功") : AjaxResult.error("新增失败");
    }

    /**
     * 修改学科
     */
    @Operation(summary = "修改学科", description = "修改学科信息")
    @PreAuthorize("@ss.hasPermi('system:subject:edit')")
    @PutMapping
    public AjaxResult edit(@RequestBody SysSubject subject) {
        int result = sysSubjectService.updateSubject(subject);
        return result > 0 ? AjaxResult.success("修改成功") : AjaxResult.error("修改失败");
    }

    /**
     * 删除学科
     */
    @Operation(summary = "删除学科", description = "根据学科ID删除学科信息")
    @PreAuthorize("@ss.hasPermi('system:subject:remove')")
    @DeleteMapping("/{subjectId}")
    public AjaxResult remove(@Parameter(description = "学科ID", required = true) @PathVariable Integer subjectId) {
        int result = sysSubjectService.deleteSubjectBySubjectId(subjectId);
        return result > 0 ? AjaxResult.success("删除成功") : AjaxResult.error("删除失败");
    }

    /**
     * 批量删除学科
     */
    @Operation(summary = "批量删除学科", description = "批量删除学科信息")
    @PreAuthorize("@ss.hasPermi('system:subject:remove')")
    @DeleteMapping("/batch")
    public AjaxResult batchRemove(@RequestBody Integer[] subjectIds) {
        int result = sysSubjectService.deleteSubjectBySubjectIds(subjectIds);
        return result > 0 ? AjaxResult.success("删除成功") : AjaxResult.error("删除失败");
    }
}

