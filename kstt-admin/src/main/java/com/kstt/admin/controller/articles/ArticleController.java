package com.kstt.admin.controller.articles;

import com.kstt.articles.entity.Article;
import com.kstt.articles.entity.ArticleSuggestedReviewer;
import com.kstt.articles.entity.ArticleOpposedReviewer;
import com.kstt.articles.service.ArticleService;
import com.kstt.articles.service.ArticleSuggestedReviewerService;
import com.kstt.articles.service.ArticleOpposedReviewerService;
import com.kstt.common.annotation.Log;
import com.kstt.common.core.controller.BaseController;
import com.kstt.common.core.domain.AjaxResult;
import com.kstt.common.core.page.TableDataInfo;
import com.kstt.common.enums.ArticleStatusEnum;
import com.kstt.common.enums.ArticleManuscriptTypeEnum;
import com.kstt.common.enums.ArticleSubmissionTypeEnum;
import com.kstt.common.enums.BusinessType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 文章Controller
 *
 * @author kstt
 * @date 2025-01-27
 */
@Tag(name = "文章管理", description = "提供文章的增删改查、提交、审稿人管理等接口")
@RestController
@RequestMapping("/api/articles")
public class ArticleController extends BaseController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleSuggestedReviewerService suggestedReviewerService;

    @Autowired
    private ArticleOpposedReviewerService opposedReviewerService;

    /**
     * 查询文章列表
     */
    @GetMapping("/list")
    @Operation(summary = "查询文章列表", description = "分页查询文章列表，支持按标题、状态、作者、期刊等条件筛选，返回包含关联信息的完整文章数据")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = TableDataInfo.class))),
            @ApiResponse(responseCode = "400", description = "请求参数错误"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public TableDataInfo list(
            @Parameter(description = "查询条件（可按文章标题、状态、作者、期刊等筛选）") Article article) {
        startPage();
        List<Article> list = articleService.selectArticleList(article);
        return getDataTable(list);
    }

    @GetMapping("/lastest/{limits}")
    @Operation(summary = "获取最新文章", description = "获取最近提交的文章，返回包含关联信息的完整文章数据")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = TableDataInfo.class))),
            @ApiResponse(responseCode = "400", description = "请求参数错误"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public TableDataInfo lastest(
            @Parameter(description = "查询近几篇文章")
            @PathVariable("limits")
            @Min(value = 1, message = "查询数量不能小于1")
            @Max(value = 100, message = "查询数量不能大于100") Integer limits
    ) {
//        startPage();
        List<Article> list = articleService.selectArticleLastList(limits);
        return getDataTable(list);
    }

    /**
     * 获取文章详情
     */
    @GetMapping(value = "/{articleId}")
    @Operation(summary = "根据文章ID获取文章详情", description = "根据文章ID获取完整的文章信息，包括标题、摘要、关键词、状态、作者、关联期刊等详细信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "获取成功",
                    content = @Content(schema = @Schema(implementation = Article.class))),
            @ApiResponse(responseCode = "404", description = "文章不存在"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public AjaxResult getInfo(
            @Parameter(description = "文章ID", required = true, example = "1")
            @PathVariable("articleId") Long articleId) {
        Article article = articleService.selectArticleByPaperId(articleId);
        return AjaxResult.success(article);
    }

    /**
     * 获取文章详情（通过稿件编号）
     */
    @GetMapping(value = "/detail/{manuscriptId}")
    @Operation(summary = "根据稿件编号获取文章详情", description = "根据唯一的稿件编号（manuscriptId）获取文章完整信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "获取成功",
                    content = @Content(schema = @Schema(implementation = Article.class))),
            @ApiResponse(responseCode = "404", description = "文章不存在"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public AjaxResult getInfoByManuscriptId(
            @Parameter(description = "稿件编号", required = true, example = "MS-2025-001")
            @PathVariable("manuscriptId") String manuscriptId) {
        Article article = articleService.selectArticleByManuscriptId(manuscriptId);
        return AjaxResult.success(article);
    }

    /**
     * 新增文章
     */
    @Log(title = "文章", businessType = BusinessType.INSERT)
    @PostMapping
    @Operation(summary = "新增文章", description = "创建新的文章记录，需要提供文章标题、摘要、关键词、作者等基本信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "新增成功"),
            @ApiResponse(responseCode = "400", description = "请求参数无效"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public AjaxResult add(
            @Parameter(description = "文章信息", required = true)
            @RequestBody Article article) {
        return toAjax(articleService.insertArticle(article));
    }

    /**
     * 修改文章
     */
    @Log(title = "文章", businessType = BusinessType.UPDATE)
    @PutMapping
    @Operation(summary = "修改文章", description = "更新文章信息，需要提供完整的文章信息，包括文章ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "修改成功"),
            @ApiResponse(responseCode = "400", description = "请求参数无效"),
            @ApiResponse(responseCode = "404", description = "文章不存在"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public AjaxResult edit(
            @Parameter(description = "文章信息（必须包含articleId）", required = true)
            @RequestBody Article article) {
        return toAjax(articleService.updateArticle(article));
    }

    /**
     * 删除文章
     */
    @Log(title = "文章", businessType = BusinessType.DELETE)
    @DeleteMapping("/{articleIds}")
    @Operation(summary = "删除文章", description = "批量删除文章，可以传入单个或多个文章ID，多个ID使用逗号分隔")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "删除成功"),
            @ApiResponse(responseCode = "400", description = "请求参数无效"),
            @ApiResponse(responseCode = "404", description = "文章不存在"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public AjaxResult remove(
            @Parameter(description = "文章ID数组，多个ID用逗号分隔，如：1,2,3", required = true, example = "1,2,3")
            @PathVariable Long[] articleIds) {
        return toAjax(articleService.deleteArticleByIds(articleIds));
    }

    /**
     * 提交文章
     */
    @Log(title = "文章提交", businessType = BusinessType.INSERT)
    @PostMapping("/submit")
    @Operation(summary = "提交文章", description = "提交新文章，包括文章基本信息、文件上传、建议审稿人和避免审稿人信息。文件为multipart/form-data格式")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "提交成功"),
            @ApiResponse(responseCode = "400", description = "请求参数无效或文件上传失败"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public AjaxResult submitArticle(
            @Parameter(description = "文章参数（title:标题, abstract:摘要, keywords:关键词, coverLetter:投稿信, suggestedReviewers:建议审稿人JSON, opposedReviewers:避免审稿人JSON）")
            @RequestParam Map<String, String> params,
            @Parameter(description = "文章文件（PDF或Word文档）", required = true)
            @RequestParam("file") MultipartFile file) {
        try {
            // 1. 创建文章基本信息
            Article article = new Article();
            article.setArticleTitle(params.get("title"));
            article.setArticleAbstract(params.get("abstract"));
            article.setArticleKeywords(params.get("keywords"));
            // 使用枚举类设置ID
            article.setArticleManuscriptTypeId(ArticleManuscriptTypeEnum.ORIGINAL_RESEARCH.getTypeId());
            article.setArticleStatusId(ArticleStatusEnum.SUBMITTED.getStatusId());
            article.setArticleSubmissionTypeId(ArticleSubmissionTypeEnum.INITIAL_SUBMISSION.getTypeId());
            article.setArticleCoverLetter(params.get("coverLetter"));

            // 2. 保存文章
            int result = articleService.insertArticle(article);
            if (result <= 0) {
                return AjaxResult.error("Failed to save article");
            }

            // 3. 保存文件信息
            if (file != null && !file.isEmpty()) {
                // TODO: 实现文件保存逻辑
                // saveFile(article.getArticleId(), file);
            }

            return AjaxResult.success("Article submitted successfully", article);

        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("Failed to submit article: " + e.getMessage());
        }
    }

    /**
     * 获取文章的建议审稿人
     */
    @GetMapping("/{articleId}/suggested-reviewers")
    @Operation(summary = "获取文章的建议审稿人列表", description = "获取指定文章的所有建议审稿人信息，包括审稿人用户信息、推荐理由、状态等")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功"),
            @ApiResponse(responseCode = "404", description = "文章不存在"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public AjaxResult getSuggestedReviewers(
            @Parameter(description = "文章ID", required = true, example = "1")
            @PathVariable Long articleId) {
        List<ArticleSuggestedReviewer> reviewers = suggestedReviewerService.selectByArticleId(articleId);
        return AjaxResult.success(reviewers);
    }

    /**
     * 获取文章的避免审稿人
     */
    @GetMapping("/{articleId}/opposed-reviewers")
    @Operation(summary = "获取文章的避免审稿人列表", description = "获取指定文章的所有避免审稿人信息，包括审稿人用户信息、避免理由、冲突类型等")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功"),
            @ApiResponse(responseCode = "404", description = "文章不存在"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public AjaxResult getOpposedReviewers(
            @Parameter(description = "文章ID", required = true, example = "1")
            @PathVariable Long articleId) {
        List<ArticleOpposedReviewer> reviewers = opposedReviewerService.selectByArticleId(articleId);
        return AjaxResult.success(reviewers);
    }

    /**
     * 添加建议审稿人
     */
    @Log(title = "建议审稿人", businessType = BusinessType.INSERT)
    @PostMapping("/{articleId}/suggested-reviewers")
    @Operation(summary = "添加建议审稿人", description = "为指定文章添加一个建议审稿人，需要提供审稿人用户ID、推荐理由等信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "添加成功"),
            @ApiResponse(responseCode = "400", description = "请求参数无效"),
            @ApiResponse(responseCode = "404", description = "文章或用户不存在"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public AjaxResult addSuggestedReviewer(
            @Parameter(description = "文章ID", required = true, example = "1")
            @PathVariable Long articleId,
            @Parameter(description = "建议审稿人信息（必须包含suggestedUserId和suggestedReason）", required = true)
            @RequestBody ArticleSuggestedReviewer reviewer) {
        reviewer.setArticleId(articleId);
        int result = suggestedReviewerService.insertSuggestedReviewer(reviewer);
        return toAjax(result);
    }

    /**
     * 添加避免审稿人
     */
    @Log(title = "避免审稿人", businessType = BusinessType.INSERT)
    @PostMapping("/{articleId}/opposed-reviewers")
    @Operation(summary = "添加避免审稿人", description = "为指定文章添加一个避免审稿人，需要提供审稿人用户ID、避免理由、冲突类型等信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "添加成功"),
            @ApiResponse(responseCode = "400", description = "请求参数无效"),
            @ApiResponse(responseCode = "404", description = "文章或用户不存在"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public AjaxResult addOpposedReviewer(
            @Parameter(description = "文章ID", required = true, example = "1")
            @PathVariable Long articleId,
            @Parameter(description = "避免审稿人信息（必须包含opposedUserId和opposedReason）", required = true)
            @RequestBody ArticleOpposedReviewer reviewer) {
        reviewer.setArticleId(articleId);
        int result = opposedReviewerService.insertOpposedReviewer(reviewer);
        return toAjax(result);
    }

}
