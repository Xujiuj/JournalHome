package com.kstt.admin.controller.articles;

import com.kstt.articles.entity.ArticleReviewInvitation;
import com.kstt.articles.service.ArticleReviewInvitationService;
import com.kstt.common.annotation.Log;
import com.kstt.common.core.controller.BaseController;
import com.kstt.common.core.page.TableDataInfo;
import com.kstt.common.enums.BusinessType;
import com.kstt.common.core.domain.AjaxResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 文章审稿邀请Controller
 * 
 * @author kstt
 * @date 2025-01-01
 */
@Tag(name = "文章审稿邀请管理")
@RestController
@RequestMapping("/api/articles/review")
public class ArticleReviewInvitationController extends BaseController {
    
    @Autowired
    private ArticleReviewInvitationService articleReviewInvitationService;

    /**
     * 查询文章审稿邀请列表
     */
    @GetMapping("/list")
    @Operation(summary = "查询文章审稿邀请列表")
    public TableDataInfo list(ArticleReviewInvitation articleReviewInvitation) {
        startPage();
        List<ArticleReviewInvitation> list = articleReviewInvitationService.selectInvitationList(articleReviewInvitation);
        return getDataTable(list);
    }


    /**
     * 获取文章审稿邀请详细信息
     */
    @GetMapping(value = "/{invitationId}")
    @Operation(summary = "获取文章审稿邀请详细信息")
    public AjaxResult getInfo(@PathVariable("invitationId") Long invitationId) {
        ArticleReviewInvitation invitation = articleReviewInvitationService.selectInvitationByInvitationId(invitationId);
        return success(invitation);
    }

    /**
     * 新增文章审稿邀请
     */
    @Log(title = "文章审稿邀请", businessType = BusinessType.INSERT)
    @PostMapping
    @Operation(summary = "新增文章审稿邀请")
    public AjaxResult add(@RequestBody ArticleReviewInvitation articleReviewInvitation) {
        return toAjax(articleReviewInvitationService.insertInvitation(articleReviewInvitation));
    }

    /**
     * 修改文章审稿邀请
     */
    @Log(title = "文章审稿邀请", businessType = BusinessType.UPDATE)
    @PutMapping
    @Operation(summary = "修改文章审稿邀请")
    public AjaxResult edit(@RequestBody ArticleReviewInvitation articleReviewInvitation) {
        return toAjax(articleReviewInvitationService.updateInvitation(articleReviewInvitation));
    }

    /**
     * 删除文章审稿邀请
     */
    @Log(title = "文章审稿邀请", businessType = BusinessType.DELETE)
    @DeleteMapping("/{invitationIds}")
    @Operation(summary = "删除文章审稿邀请")
    public AjaxResult remove(@PathVariable Long[] invitationIds) {
        return toAjax(articleReviewInvitationService.deleteInvitationByIds(invitationIds));
    }
}