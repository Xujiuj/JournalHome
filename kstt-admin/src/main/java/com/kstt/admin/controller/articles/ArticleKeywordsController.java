package com.kstt.admin.controller.articles;

import com.kstt.articles.entity.ArticleKeywords;
import com.kstt.articles.service.ArticleKeywordsService;
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
 * 文章关键词Controller
 *
 * @author kstt
 * @date 2025-01-27
 */
@Tag(name = "文章关键词管理", description = "管理文章关键词的增删改查接口")
@RestController
@RequestMapping("/articles/keywords")
public class ArticleKeywordsController extends BaseController {

    @Autowired
    private ArticleKeywordsService keywordService;

    @Operation(summary = "查询关键词列表", description = "分页查询文章关键词列表")
    @GetMapping("/list")
    public TableDataInfo list(
            @Parameter(description = "查询条件") ArticleKeywords keyword) {
        startPage();
        List<ArticleKeywords> list = keywordService.selectKeywordList(keyword);
        return getDataTable(list);
    }

    @Operation(summary = "根据关键词ID获取关键词信息", description = "查询单个关键词的详细信息")
    @GetMapping("/{keywordId}")
    public AjaxResult getInfo(
            @Parameter(description = "关键词ID", required = true) @PathVariable("keywordId") Long keywordId) {
        return success(keywordService.selectKeywordByKeywordId(keywordId));
    }

    @Operation(summary = "根据文章ID获取关键词列表", description = "获取指定文章的所有关键词")
    @GetMapping("/article/{articleId}")
    public AjaxResult getKeywordsByArticleId(
            @Parameter(description = "文章ID", required = true) @PathVariable("articleId") Long articleId) {
        return success(keywordService.selectKeywordsByArticleId(articleId));
    }

    @Operation(summary = "新增关键词", description = "为文章添加新的关键词")
    @PostMapping
    public AjaxResult add(
            @Parameter(description = "关键词信息", required = true) @RequestBody ArticleKeywords keyword) {
        return toAjax(keywordService.insertKeyword(keyword));
    }

    @Operation(summary = "修改关键词", description = "更新关键词信息")
    @PutMapping
    public AjaxResult edit(
            @Parameter(description = "关键词信息", required = true) @RequestBody ArticleKeywords keyword) {
        return toAjax(keywordService.updateKeyword(keyword));
    }

    @Operation(summary = "批量删除关键词", description = "根据关键词ID批量删除")
    @DeleteMapping("/{keywordIds}")
    public AjaxResult remove(
            @Parameter(description = "关键词ID数组", required = true) @PathVariable Long[] keywordIds) {
        return toAjax(keywordService.deleteKeywordByIds(keywordIds));
    }

    @Operation(summary = "根据文章ID删除关键词", description = "删除指定文章的所有关键词")
    @DeleteMapping("/article/{articleId}")
    public AjaxResult removeByArticleId(
            @Parameter(description = "文章ID", required = true) @PathVariable Long articleId) {
        return toAjax(keywordService.deleteKeywordsByArticleId(articleId));
    }
}

