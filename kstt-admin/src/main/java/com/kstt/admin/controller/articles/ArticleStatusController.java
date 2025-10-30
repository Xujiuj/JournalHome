package com.kstt.admin.controller.articles;

import com.kstt.admin.controller.articles.model.ArticleStatusChangeCommand;
import com.kstt.application.articles.service.ArticleStatusApplicationService;
import com.kstt.application.articles.status.model.ArticleStatusChangeRequest;
import com.kstt.articles.entity.Article;
import com.kstt.common.core.controller.BaseController;
import com.kstt.common.core.domain.AjaxResult;
import com.kstt.common.enums.ArticleStatusEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "文章状态管理", description = "控制文章生命周期状态的接口")
@RestController
@RequestMapping("/articles/status")
public class ArticleStatusController extends BaseController {

    @Autowired
    private ArticleStatusApplicationService articleStatusApplicationService;

    @PutMapping("/{articleId}/under-review")
    @Operation(summary = "切换为审稿中", description = "将文章状态更新为审稿中，用于初审通过后进入外部评审流程。可选地指定审稿截止时间与备注信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "状态切换成功", content = @Content(schema = @Schema(implementation = Article.class))),
            @ApiResponse(responseCode = "404", description = "文章不存在"),
            @ApiResponse(responseCode = "400", description = "请求参数错误")
    })
    public AjaxResult setUnderReview(
            @Parameter(description = "文章ID", required = true, example = "1001")
            @PathVariable Long articleId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "状态变更参数", required = false,
                    content = @Content(schema = @Schema(implementation = ArticleStatusChangeCommand.class)))
            @RequestBody(required = false) ArticleStatusChangeCommand command) {
        Article article = articleStatusApplicationService.changeStatus(buildRequest(articleId, ArticleStatusEnum.UNDER_REVIEW, command));
        return AjaxResult.success(article);
    }

    @PutMapping("/{articleId}/accept")
    @Operation(summary = "接受文章", description = "当所有评审流程完成后调用该接口，将文章状态设置为已接收")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "状态切换成功", content = @Content(schema = @Schema(implementation = Article.class))),
            @ApiResponse(responseCode = "404", description = "文章不存在"),
            @ApiResponse(responseCode = "400", description = "请求参数错误")
    })
    public AjaxResult acceptArticle(
            @Parameter(description = "文章ID", required = true, example = "1001")
            @PathVariable Long articleId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "状态变更参数", required = false,
                    content = @Content(schema = @Schema(implementation = ArticleStatusChangeCommand.class)))
            @RequestBody(required = false) ArticleStatusChangeCommand command) {
        Article article = articleStatusApplicationService.changeStatus(buildRequest(articleId, ArticleStatusEnum.ACCEPTED, command));
        return AjaxResult.success(article);
    }

    @PutMapping("/{articleId}/reject")
    @Operation(summary = "拒绝文章", description = "当评审结论为拒绝时调用该接口。必须提供备注或决策原因以记录拒稿理由")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "状态切换成功", content = @Content(schema = @Schema(implementation = Article.class))),
            @ApiResponse(responseCode = "404", description = "文章不存在"),
            @ApiResponse(responseCode = "400", description = "请求参数错误")
    })
    public AjaxResult rejectArticle(
            @Parameter(description = "文章ID", required = true, example = "1001")
            @PathVariable Long articleId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "状态变更参数", required = false,
                    content = @Content(schema = @Schema(implementation = ArticleStatusChangeCommand.class)))
            @RequestBody(required = false) ArticleStatusChangeCommand command) {
        Article article = articleStatusApplicationService.changeStatus(buildRequest(articleId, ArticleStatusEnum.REJECTED, command));
        return AjaxResult.success(article);
    }

    @PutMapping("/{articleId}/publish")
    @Operation(summary = "发布文章", description = "文章被接受并完成排版后调用，设置状态为已发表并记录发布时间")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "状态切换成功", content = @Content(schema = @Schema(implementation = Article.class))),
            @ApiResponse(responseCode = "404", description = "文章不存在"),
            @ApiResponse(responseCode = "400", description = "请求参数错误")
    })
    public AjaxResult publishArticle(
            @Parameter(description = "文章ID", required = true, example = "1001")
            @PathVariable Long articleId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "状态变更参数", required = false,
                    content = @Content(schema = @Schema(implementation = ArticleStatusChangeCommand.class)))
            @RequestBody(required = false) ArticleStatusChangeCommand command) {
        Article article = articleStatusApplicationService.changeStatus(buildRequest(articleId, ArticleStatusEnum.PUBLISHED, command));
        return AjaxResult.success(article);
    }

    private ArticleStatusChangeRequest buildRequest(Long articleId,
                                                    ArticleStatusEnum targetStatus,
                                                    ArticleStatusChangeCommand command) {
        ArticleStatusChangeCommand payload = command != null ? command : new ArticleStatusChangeCommand();
        return ArticleStatusChangeRequest.builder()
                .articleId(articleId)
                .targetStatus(targetStatus)
                .operatorId(payload.getOperatorId())
                .operatorName(payload.getOperatorName())
                .remark(payload.getRemark())
                .dueDate(payload.getDueDate())
                .decisionReason(payload.getDecisionReason())
                .build();
    }
}

