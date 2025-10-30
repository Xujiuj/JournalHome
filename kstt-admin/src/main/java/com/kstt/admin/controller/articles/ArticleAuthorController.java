package com.kstt.admin.controller.articles;

import com.kstt.articles.entity.ArticleAuthor;
import com.kstt.articles.service.ArticleAuthorService;
import com.kstt.common.annotation.Log;
import com.kstt.common.core.controller.BaseController;
import com.kstt.common.core.page.TableDataInfo;
import com.kstt.common.core.domain.AjaxResult;
import com.kstt.common.enums.BusinessType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 文章作者Controller
 * 
 * @author kstt
 * @date 2025-01-01
 */
@Tag(name = "文章作者管理")
@RestController
@RequestMapping("/api/articles/author")
public class ArticleAuthorController extends BaseController {
    
    @Autowired
    private ArticleAuthorService articleAuthorService;

    /**
     * 查询文章作者列表
     */
    @GetMapping("/list")
    @Operation(summary = "查询文章作者列表")
    public TableDataInfo list(ArticleAuthor articleAuthor) {
        startPage();
        List<ArticleAuthor> list = articleAuthorService.selectAuthorList(articleAuthor);
        return getDataTable(list);
    }


    /**
     * 获取文章作者详细信息
     */
    @GetMapping(value = "/{authorId}")
    @Operation(summary = "获取文章作者详细信息")
    public AjaxResult getInfo(@PathVariable("authorId") Long authorId) {
        ArticleAuthor author = articleAuthorService.selectAuthorByAuthorId(authorId);
        return success(author);
    }

    /**
     * 新增文章作者
     */
    @Log(title = "文章作者", businessType = BusinessType.INSERT)
    @PostMapping
    @Operation(summary = "新增文章作者")
    public AjaxResult add(@RequestBody ArticleAuthor articleAuthor) {
        return toAjax(articleAuthorService.insertAuthor(articleAuthor));
    }

    /**
     * 修改文章作者
     */
    @Log(title = "文章作者", businessType = BusinessType.UPDATE)
    @PutMapping
    @Operation(summary = "修改文章作者")
    public AjaxResult edit(@RequestBody ArticleAuthor articleAuthor) {
        return toAjax(articleAuthorService.updateAuthor(articleAuthor));
    }

    /**
     * 删除文章作者
     */
    @Log(title = "文章作者", businessType = BusinessType.DELETE)
    @DeleteMapping("/{authorIds}")
    @Operation(summary = "删除文章作者")
    public AjaxResult remove(@PathVariable Long[] authorIds) {
        return toAjax(articleAuthorService.deleteAuthorByIds(authorIds));
    }
}