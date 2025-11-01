package com.kstt.admin.controller.articles;

import com.kstt.admin.service.mail.EmailService;
import com.kstt.articles.entity.Article;
import com.kstt.articles.entity.ArticleAuthor;
import com.kstt.articles.entity.ArticleSuggestedReviewer;
import com.kstt.articles.entity.ArticleOpposedReviewer;
import com.kstt.articles.service.ArticleService;
import com.kstt.articles.service.ArticleAuthorService;
import com.kstt.articles.service.ArticleSuggestedReviewerService;
import com.kstt.articles.service.ArticleOpposedReviewerService;
import com.kstt.common.core.domain.FileUtils;
import com.kstt.common.service.FileUploadService;
import com.kstt.sys.service.SysFileService;
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
import org.springframework.http.MediaType;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    private ArticleAuthorService articleAuthorService;

    @Autowired
    private SysFileService sysFileService;

    @Autowired
    private ArticleSuggestedReviewerService suggestedReviewerService;

    @Autowired
    private ArticleOpposedReviewerService opposedReviewerService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private FileUploadService fileUploadService;

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
     * 创建文章（第一步：基本信息）
     */
    @Log(title = "创建文章", businessType = BusinessType.INSERT)
    @PostMapping("/create")
    @Operation(summary = "创建文章", description = "创建新文章基本信息，返回文章ID供后续文件上传使用")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "创建成功"),
            @ApiResponse(responseCode = "400", description = "请求参数无效"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public AjaxResult createArticle(
            @Parameter(description = "文章基本信息（JSON格式）", required = true)
            @RequestBody Map<String, Object> params) {
        try {
            // 创建文章基本信息
            Article article = new Article();
            article.setArticleTitle((String) params.get("title"));
            article.setArticleAbstract((String) params.get("abstract"));
            article.setArticleKeywords((String) params.get("keywords"));
            article.setArticleCoverLetter((String) params.get("coverLetter"));
            
            // 解析稿件类型
            String manuscriptType = (String) params.get("manuscriptType");
            if (manuscriptType != null) {
                try {
                    ArticleManuscriptTypeEnum typeEnum = ArticleManuscriptTypeEnum.valueOf(manuscriptType);
                    article.setArticleManuscriptTypeId(typeEnum.getTypeId());
                } catch (IllegalArgumentException e) {
                    article.setArticleManuscriptTypeId(ArticleManuscriptTypeEnum.ORIGINAL_RESEARCH.getTypeId());
                }
            } else {
                article.setArticleManuscriptTypeId(ArticleManuscriptTypeEnum.ORIGINAL_RESEARCH.getTypeId());
            }
            
            // 设置状态为草稿（待上传文件）
            article.setArticleStatusId(ArticleStatusEnum.SUBMITTED.getStatusId());
            article.setArticleSubmissionTypeId(ArticleSubmissionTypeEnum.INITIAL_SUBMISSION.getTypeId());
            
            // 设置字数、图表数量等
            if (params.get("wordCount") != null) {
                article.setArticleWordCount(((Number) params.get("wordCount")).intValue());
            }
            if (params.get("figureCount") != null) {
                article.setArticleFigureCount(((Number) params.get("figureCount")).intValue());
            }
            if (params.get("tableCount") != null) {
                article.setArticleTableCount(((Number) params.get("tableCount")).intValue());
            }
            
            // 设置学科领域（支持数组格式，转换为逗号分隔字符串）
            if (params.get("subjectArea") instanceof List) {
                @SuppressWarnings("unchecked")
                List<String> subjectAreas = (List<String>) params.get("subjectArea");
                article.setArticleSubjectAreas(String.join(",", subjectAreas));
            } else if (params.get("subjectArea") != null) {
                article.setArticleSubjectAreas(params.get("subjectArea").toString());
            }

            // 设置提交时间
            article.setArticleSubmitTime(LocalDateTime.now());

            // 保存文章
            int result = articleService.insertArticle(article);
            if (result <= 0) {
                return AjaxResult.error("Failed to create article");
            }

            return AjaxResult.success("Article created successfully", article);

        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("Failed to create article: " + e.getMessage());
        }
    }

    /**
     * 更新文章（第二步：保存文件信息）
     */
    @Log(title = "更新文章", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    @Operation(summary = "更新文章", description = "为已创建的文章添加文件信息，完成投稿")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "更新成功"),
            @ApiResponse(responseCode = "400", description = "请求参数无效"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public AjaxResult updateArticle(
            @Parameter(description = "文章更新信息（JSON格式）", required = true)
            @RequestBody Map<String, Object> params) {
        try {
            // 获取文章ID
            Long manuscriptId = params.get("manuscriptId") != null ? 
                ((Number) params.get("manuscriptId")).longValue() : null;
            
            if (manuscriptId == null) {
                return AjaxResult.error("Article ID is required");
            }

            // 获取文章信息
            Article article = articleService.selectArticleByPaperId(manuscriptId);
            if (article == null) {
                return AjaxResult.error("Article not found");
            }

            // 保存文件信息
            @SuppressWarnings("unchecked")
            Map<String, Object> files = (Map<String, Object>) params.get("files");
            
            if (files != null) {
                // 保存论文文件
                @SuppressWarnings("unchecked")
                Map<String, Object> paperFile = (Map<String, Object>) files.get("paper");
                if (paperFile != null) {
                    FileUtils fileUtils = new FileUtils();
                    fileUtils.setFileName((String) paperFile.get("fileName"));
                    fileUtils.setFilePath((String) paperFile.get("filePath"));
                    fileUtils.setFileType(getFileExtension((String) paperFile.get("fileName")));
                    fileUtils.setFileSize(paperFile.get("fileSize") != null ? 
                        ((Number) paperFile.get("fileSize")).longValue() : null);
                    
                    sysFileService.save(fileUtils);
                }

                // 保存支撑材料文件
                @SuppressWarnings("unchecked")
                Map<String, Object> supportingFile = (Map<String, Object>) files.get("supporting");
                if (supportingFile != null) {
                    FileUtils fileUtils = new FileUtils();
                    fileUtils.setFileName((String) supportingFile.get("fileName"));
                    fileUtils.setFilePath((String) supportingFile.get("filePath"));
                    fileUtils.setFileType(getFileExtension((String) supportingFile.get("fileName")));
                    fileUtils.setFileSize(supportingFile.get("fileSize") != null ? 
                        ((Number) supportingFile.get("fileSize")).longValue() : null);
                    
                    sysFileService.save(fileUtils);
                }
            }

            // 发送投稿确认邮件
            try {
                sendSubmissionEmail(article);
            } catch (Exception e) {
                logger.error("发送投稿确认邮件失败，文章ID: {}", article.getArticleId(), e);
            }

            return AjaxResult.success("Article updated successfully", article);

        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("Failed to update article: " + e.getMessage());
        }
    }

    /**
     * 提交文章
     */
    @Log(title = "文章提交", businessType = BusinessType.INSERT)
    @PostMapping("/submit")
    @Operation(summary = "提交文章", description = "提交新文章，包括文章基本信息、文件路径、建议审稿人和避免审稿人信息。文件已通过统一上传接口上传")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "提交成功"),
            @ApiResponse(responseCode = "400", description = "请求参数无效"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public AjaxResult submitArticle(
            @Parameter(description = "文章信息（JSON格式）", required = true)
            @RequestBody Map<String, Object> params) {
        try {
            // 1. 创建文章基本信息
            Article article = new Article();
            article.setArticleTitle((String) params.get("title"));
            article.setArticleAbstract((String) params.get("abstract"));
            article.setArticleKeywords((String) params.get("keywords"));
            article.setArticleCoverLetter((String) params.get("coverLetter"));
            
            // 解析稿件类型（前端传的是枚举名称，需要转换为ID）
            String manuscriptType = (String) params.get("manuscriptType");
            if (manuscriptType != null) {
                try {
                    ArticleManuscriptTypeEnum typeEnum = ArticleManuscriptTypeEnum.valueOf(manuscriptType);
                    article.setArticleManuscriptTypeId(typeEnum.getTypeId());
                } catch (IllegalArgumentException e) {
                    article.setArticleManuscriptTypeId(ArticleManuscriptTypeEnum.ORIGINAL_RESEARCH.getTypeId());
                }
            } else {
                article.setArticleManuscriptTypeId(ArticleManuscriptTypeEnum.ORIGINAL_RESEARCH.getTypeId());
            }
            
            // 使用枚举类设置状态和提交类型
            article.setArticleStatusId(ArticleStatusEnum.SUBMITTED.getStatusId());
            article.setArticleSubmissionTypeId(ArticleSubmissionTypeEnum.INITIAL_SUBMISSION.getTypeId());
            
            // 设置字数、图表数量等
            if (params.get("wordCount") != null) {
                article.setArticleWordCount(((Number) params.get("wordCount")).intValue());
            }
            if (params.get("figureCount") != null) {
                article.setArticleFigureCount(((Number) params.get("figureCount")).intValue());
            }
            if (params.get("tableCount") != null) {
                article.setArticleTableCount(((Number) params.get("tableCount")).intValue());
            }
            
            // 设置学科领域（支持数组格式，转换为逗号分隔字符串）
            if (params.get("subjectArea") instanceof List) {
                @SuppressWarnings("unchecked")
                List<String> subjectAreas = (List<String>) params.get("subjectArea");
                article.setArticleSubjectAreas(String.join(",", subjectAreas));
            } else if (params.get("subjectArea") != null) {
                article.setArticleSubjectAreas(params.get("subjectArea").toString());
            }

            // 设置提交时间
            article.setArticleSubmitTime(LocalDateTime.now());

            // 2. 保存文章
            int result = articleService.insertArticle(article);
            if (result <= 0) {
                return AjaxResult.error("Failed to save article");
            }

            // 3. 保存文件信息（文件已经通过统一上传接口上传）
            String filePath = (String) params.get("filePath");
            String fileName = (String) params.get("fileName");
            Long fileSize = params.get("fileSize") != null ? ((Number) params.get("fileSize")).longValue() : null;
            
            if (filePath != null && fileName != null) {
                FileUtils fileUtils = new FileUtils();
                fileUtils.setFileName(fileName);
                fileUtils.setFilePath(filePath);
                fileUtils.setFileType(getFileExtension(fileName));
                fileUtils.setFileSize(fileSize);
                
                sysFileService.save(fileUtils);
            }

            // 4. 保存建议审稿人（如果提供）
            if (params.get("suggestedReviewers") instanceof List) {
                @SuppressWarnings("unchecked")
                List<Map<String, Object>> suggestedReviewers = (List<Map<String, Object>>) params.get("suggestedReviewers");
                for (Map<String, Object> reviewerData : suggestedReviewers) {
                    ArticleSuggestedReviewer reviewer = new ArticleSuggestedReviewer();
                    reviewer.setArticleId(article.getArticleId());
                    reviewer.setReviewerUserId(((Number) reviewerData.get("suggestedUserId")).longValue());
                    reviewer.setReason((String) reviewerData.get("suggestedReason"));
                    reviewer.setStatus(0); // 待处理
                    suggestedReviewerService.insertSuggestedReviewer(reviewer);
                }
            }

            // 5. 保存避免审稿人（如果提供）
            if (params.get("opposedReviewers") instanceof List) {
                @SuppressWarnings("unchecked")
                List<Map<String, Object>> opposedReviewers = (List<Map<String, Object>>) params.get("opposedReviewers");
                for (Map<String, Object> reviewerData : opposedReviewers) {
                    ArticleOpposedReviewer reviewer = new ArticleOpposedReviewer();
                    reviewer.setArticleId(article.getArticleId());
                    reviewer.setReviewerUserId(((Number) reviewerData.get("opposedUserId")).longValue());
                    reviewer.setReason((String) reviewerData.get("opposedReason"));
                    opposedReviewerService.insertOpposedReviewer(reviewer);
                }
            }

            // 6. 发送投稿确认邮件（异步发送，不影响主流程）
            try {
                sendSubmissionEmail(article);
            } catch (Exception e) {
                // 邮件发送失败不影响提交结果，只记录日志
                logger.error("发送投稿确认邮件失败，文章ID: {}", article.getArticleId(), e);
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

    /**
     * 发送投稿确认邮件（带附件）
     */
    private void sendSubmissionEmailWithAttachment(Article article, String submitterEmail, String submitterName,
                                                   MultipartFile paperFile, MultipartFile supportingFile) {
        // 构建邮件内容
        String articleTitle = article.getArticleTitle() != null ? article.getArticleTitle() : "未知标题";
        String manuscriptId = article.getArticleManuscriptId() != null 
                ? article.getArticleManuscriptId() 
                : "MS-" + article.getArticleId();
        // 使用传入的投稿人姓名，如果为空则使用默认值
        String finalSubmitterName = (submitterName != null && !submitterName.isEmpty()) 
                ? submitterName 
                : "投稿人";
        String finalSubmitterEmail = submitterEmail != null && !submitterEmail.isEmpty() 
                ? submitterEmail 
                : "";
        String submitTime = article.getArticleSubmitTime() != null
                ? article.getArticleSubmitTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                : LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        // 准备附件列表
        List<MultipartFile> attachments = new java.util.ArrayList<>();
        if (paperFile != null && !paperFile.isEmpty()) {
            attachments.add(paperFile);
        }
        if (supportingFile != null && !supportingFile.isEmpty()) {
            attachments.add(supportingFile);
        }

        // 发送带附件的邮件
        emailService.sendArticleSubmissionEmailWithAttachment(
                articleTitle, manuscriptId, finalSubmitterName, finalSubmitterEmail, submitTime, attachments);
    }

    /**
     * 发送投稿确认邮件（保留原方法用于兼容）
     */
    private void sendSubmissionEmail(Article article) {
        // 获取第一作者信息
        ArticleAuthor firstAuthor = null;
        List<ArticleAuthor> authors = articleAuthorService.getAuthorsByArticleId(article.getArticleId());
        if (authors != null && !authors.isEmpty()) {
            firstAuthor = authors.stream()
                    .min((a1, a2) -> Integer.compare(
                            a1.getAuthorOrder() != null ? a1.getAuthorOrder() : 999,
                            a2.getAuthorOrder() != null ? a2.getAuthorOrder() : 999))
                    .orElse(authors.get(0));
        }

        // 构建邮件内容
        String articleTitle = article.getArticleTitle() != null ? article.getArticleTitle() : "未知标题";
        String manuscriptId = article.getArticleManuscriptId() != null 
                ? article.getArticleManuscriptId() 
                : "MS-" + article.getArticleId();
        String submitterName = firstAuthor != null && firstAuthor.getAuthorUser() != null
                ? firstAuthor.getAuthorUser().getUserRealName()
                : "投稿人";
        String submitterEmail = firstAuthor != null && firstAuthor.getAuthorUser() != null
                ? firstAuthor.getAuthorUser().getUserEmail()
                : "";
        String submitTime = article.getArticleSubmitTime() != null
                ? article.getArticleSubmitTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                : LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        // 发送邮件
        emailService.sendArticleSubmissionEmail(
                articleTitle, manuscriptId, submitterName, submitterEmail, submitTime);
    }

    /**
     * 最终提交文章（包含文件上传）
     */
    @Log(title = "文章最终提交", businessType = BusinessType.INSERT)
    @PostMapping(value = "/submit-final", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "最终提交文章", description = "上传文件、保存文件信息到sys_file、创建文章记录、发送邮件")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "提交成功"),
            @ApiResponse(responseCode = "400", description = "请求参数无效"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public AjaxResult submitFinalArticle(
            @RequestParam("title") String title,
            @RequestParam("abstract") String abstractText,
            @RequestParam("keywords") String keywords,
            @RequestParam("manuscriptType") String manuscriptType,
            @RequestParam("subjectArea") String subjectAreaJson,
            @RequestParam("wordCount") Integer wordCount,
            @RequestParam(value = "figureCount", required = false, defaultValue = "0") Integer figureCount,
            @RequestParam(value = "tableCount", required = false, defaultValue = "0") Integer tableCount,
            @RequestParam(value = "coverLetter", required = false) String coverLetter,
            @RequestParam(value = "submitterEmail", required = false) String submitterEmail,
            @RequestParam(value = "submitterName", required = false) String submitterName,
            @RequestParam("paperFile") MultipartFile paperFile,
            @RequestParam(value = "supportingFile", required = false) MultipartFile supportingFile) {
        try {
            // 1. 上传论文文件到服务器
            if (paperFile == null || paperFile.isEmpty()) {
                return AjaxResult.error("Paper file is required");
            }
            Article article = new Article();
            article.setArticleManuscriptId(articleService.generateManuscriptId());
            // 上传文件并获取文件信息
            Map<String, Object> paperFileInfo = fileUploadService.uploadFileToSubDir(paperFile, "articles", article.getArticleManuscriptId());
            
            // 2. 将文件信息写入sys_file表
            FileUtils paperFileUtils = new FileUtils();
            paperFileUtils.setFileName((String) paperFileInfo.get("fileName"));
            paperFileUtils.setFilePath((String) paperFileInfo.get("filePath"));
            paperFileUtils.setFileType(getFileExtension((String) paperFileInfo.get("fileName")));
            paperFileUtils.setFileSize(paperFileInfo.get("fileSize") != null ? 
                ((Number) paperFileInfo.get("fileSize")).longValue() : null);
            
            boolean paperFileSaved = sysFileService.save(paperFileUtils);
            if (!paperFileSaved) {
                return AjaxResult.error("Failed to save paper file information");
            }
            
            Long paperFileId = paperFileUtils.getFileId();
            
            // 处理支撑材料文件（可选）
            if (supportingFile != null && !supportingFile.isEmpty()) {
                Map<String, Object> supportingFileInfo = fileUploadService.uploadFileToSubDir(supportingFile, "articles", article.getArticleManuscriptId());
                
                FileUtils supportingFileUtils = new FileUtils();
                supportingFileUtils.setFileName((String) supportingFileInfo.get("fileName"));
                supportingFileUtils.setFilePath((String) supportingFileInfo.get("filePath"));
                supportingFileUtils.setFileType(getFileExtension((String) supportingFileInfo.get("fileName")));
                supportingFileUtils.setFileSize(supportingFileInfo.get("fileSize") != null ? 
                    ((Number) supportingFileInfo.get("fileSize")).longValue() : null);
                
                sysFileService.save(supportingFileUtils);
            }
            
            // 3. 创建Article记录

            article.setArticleTitle(title);
            article.setArticleAbstract(abstractText);
            article.setArticleKeywords(keywords);
            article.setArticleCoverLetter(coverLetter != null ? coverLetter : "");
            
            // 解析稿件类型
            try {
                ArticleManuscriptTypeEnum typeEnum = ArticleManuscriptTypeEnum.valueOf(manuscriptType);
                article.setArticleManuscriptTypeId(typeEnum.getTypeId());
            } catch (IllegalArgumentException e) {
                article.setArticleManuscriptTypeId(ArticleManuscriptTypeEnum.ORIGINAL_RESEARCH.getTypeId());
            }
            
            // 设置状态和提交类型
            article.setArticleStatusId(ArticleStatusEnum.SUBMITTED.getStatusId());
            article.setArticleSubmissionTypeId(ArticleSubmissionTypeEnum.INITIAL_SUBMISSION.getTypeId());
            
            // 设置字数、图表数量等
            article.setArticleWordCount(wordCount != null ? wordCount : 0);
            article.setArticleFigureCount(figureCount != null ? figureCount : 0);
            article.setArticleTableCount(tableCount != null ? tableCount : 0);
            
            // 设置学科领域
            try {
                @SuppressWarnings("unchecked")
                List<String> subjectAreas = new com.fasterxml.jackson.databind.ObjectMapper()
                    .readValue(subjectAreaJson, List.class);
                article.setArticleSubjectAreas(String.join(",", subjectAreas));
            } catch (Exception e) {
                article.setArticleSubjectAreas(subjectAreaJson);
            }
            
            // 设置提交时间
            article.setArticleSubmitTime(LocalDateTime.now());
            
            // 将file_id存储到article_pdf字段（使用论文文件的fileId）
            article.setArticlePdf(String.valueOf(paperFileId));
            
            // 保存文章
            int result = articleService.insertArticle(article);
            if (result <= 0) {
                return AjaxResult.error("Failed to create article");
            }
            
            // 4. 发送投稿确认邮件（带附件）
            try {
                sendSubmissionEmailWithAttachment(article, submitterEmail, submitterName, paperFile, supportingFile);
            } catch (Exception e) {
                logger.error("发送投稿确认邮件失败，文章ID: {}", article.getArticleId(), e);
            }
            
            return AjaxResult.success("Article submitted successfully", article);
            
        } catch (IOException e) {
            logger.error("File upload error", e);
            return AjaxResult.error("Failed to upload file: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Error submitting article", e);
            return AjaxResult.error("Failed to submit article: " + e.getMessage());
        }
    }

    /**
     * 获取文件扩展名
     */
    private String getFileExtension(String filename) {
        if (filename == null || filename.isEmpty()) {
            return "";
        }
        int lastDotIndex = filename.lastIndexOf('.');
        if (lastDotIndex == -1 || lastDotIndex == filename.length() - 1) {
            return "";
        }
        return filename.substring(lastDotIndex + 1);
    }

}
