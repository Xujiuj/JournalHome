package com.kstt.admin.controller.articles;

import com.kstt.articles.entity.ArticleProcess;
import com.kstt.articles.service.ArticleProcessService;
import com.kstt.common.annotation.Log;
import com.kstt.common.core.controller.BaseController;
import com.kstt.common.core.page.TableDataInfo;
import com.kstt.common.enums.ArticleStatusEnum;
import com.kstt.common.enums.BusinessType;
import com.kstt.common.core.domain.AjaxResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 文章流程Controller
 * 
 * @author kstt
 * @date 2025-01-01
 */
@Tag(name = "文章流程管理")
@RestController
@RequestMapping("/api/articles/process")
public class ArticleProcessController extends BaseController {
    
    @Autowired
    private ArticleProcessService articleProcessService;

    /**
     * 查询文章流程列表
     */
    @GetMapping("/list")
    @Operation(summary = "查询文章流程列表")
    public TableDataInfo list(ArticleProcess articleProcess) {
        startPage();
        List<ArticleProcess> list = articleProcessService.selectProcessList(articleProcess);
        return getDataTable(list);
    }


    /**
     * 获取文章流程详细信息
     */
    @GetMapping(value = "/{processId}")
    @Operation(summary = "获取文章流程详细信息")
    public AjaxResult getInfo(@PathVariable("processId") Long processId) {
        ArticleProcess process = articleProcessService.selectProcessByProcessId(processId);
        return success(process);
    }

    /**
     * 新增文章流程
     */
    @Log(title = "文章流程", businessType = BusinessType.INSERT)
    @PostMapping
    @Operation(summary = "新增文章流程")
    public AjaxResult add(@RequestBody ArticleProcess articleProcess) {
        return toAjax(articleProcessService.insertProcess(articleProcess));
    }

    /**
     * 修改文章流程
     */
    @Log(title = "文章流程", businessType = BusinessType.UPDATE)
    @PutMapping
    @Operation(summary = "修改文章流程")
    public AjaxResult edit(@RequestBody ArticleProcess articleProcess) {
        return toAjax(articleProcessService.updateProcess(articleProcess));
    }

    /**
     * 删除文章流程
     */
    @Log(title = "文章流程", businessType = BusinessType.DELETE)
    @DeleteMapping("/{processIds}")
    @Operation(summary = "删除文章流程")
    public AjaxResult remove(@PathVariable Long[] processIds) {
        return toAjax(articleProcessService.deleteProcessByIds(processIds));
    }
}