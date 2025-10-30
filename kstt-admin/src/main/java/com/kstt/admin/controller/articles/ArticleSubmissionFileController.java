package com.kstt.admin.controller.articles;

import com.kstt.articles.entity.ArticleSubmissionFile;
import com.kstt.articles.service.ArticleSubmissionFileService;
import com.kstt.common.core.controller.BaseController;
import com.kstt.common.core.domain.AjaxResult;
import com.kstt.common.core.page.TableDataInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 提交文件Controller
 *
 * @author kstt
 * @date 2025-01-27
 */
@Tag(name = "文章提交文件管理", description = "管理文章提交文件的增删改查接口")
@RestController
@RequestMapping("/articles/files")
public class ArticleSubmissionFileController extends BaseController {

    @Autowired
    private ArticleSubmissionFileService fileService;

    @Operation(summary = "查询提交文件列表", description = "分页查询文章提交文件列表")
    @GetMapping("/list")
    public TableDataInfo list(
            @Parameter(description = "查询条件") ArticleSubmissionFile file) {
        startPage();
        List<ArticleSubmissionFile> list = fileService.selectFileList(file);
        return getDataTable(list);
    }

    @Operation(summary = "根据文件ID获取文件信息", description = "查询单个文件的详细信息")
    @GetMapping("/{fileId}")
    public AjaxResult getInfo(
            @Parameter(description = "文件ID", required = true) @PathVariable("fileId") Long fileId) {
        return success(fileService.selectFileByFileId(fileId));
    }

    @Operation(summary = "根据文章ID获取文件列表", description = "获取指定文章的所有提交文件")
    @GetMapping("/article/{articleId}")
    public AjaxResult getFilesByArticleId(
            @Parameter(description = "文章ID", required = true) @PathVariable("articleId") Long articleId) {
        return success(fileService.selectFilesByArticleId(articleId));
    }

    @Operation(summary = "新增提交文件", description = "为文章添加新的提交文件记录")
    @PostMapping
    public AjaxResult add(
            @Parameter(description = "文件信息", required = true) @RequestBody ArticleSubmissionFile file) {
        return toAjax(fileService.insertFile(file));
    }

    @Operation(summary = "修改提交文件", description = "更新文件信息")
    @PutMapping
    public AjaxResult edit(
            @Parameter(description = "文件信息", required = true) @RequestBody ArticleSubmissionFile file) {
        return toAjax(fileService.updateFile(file));
    }

    @Operation(summary = "批量删除提交文件", description = "根据文件ID批量删除")
    @DeleteMapping("/{fileIds}")
    public AjaxResult remove(
            @Parameter(description = "文件ID数组", required = true) @PathVariable Long[] fileIds) {
        return toAjax(fileService.deleteFileByIds(fileIds));
    }

    @Operation(summary = "根据文章ID删除文件", description = "删除指定文章的所有提交文件")
    @DeleteMapping("/article/{articleId}")
    public AjaxResult removeByArticleId(
            @Parameter(description = "文章ID", required = true) @PathVariable Long articleId) {
        return toAjax(fileService.deleteFilesByArticleId(articleId));
    }
}

