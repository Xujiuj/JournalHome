package com.kstt.application.articles.service;

import com.kstt.application.articles.dto.ArticleDetailDTO;
import com.kstt.application.articles.dto.ArticleListDTO;
import com.kstt.application.articles.query.ArticleQuery;
import com.kstt.application.common.result.PageResult;

/**
 * 文章应用服务
 */
public interface ArticleApplicationService {

    /**
     * 分页查询文章列表（精简版）
     */
    PageResult<ArticleListDTO> queryArticleList(ArticleQuery query);

    /**
     * 获取文章详情
     */
    ArticleDetailDTO getArticleDetail(Long articleId);

    /**
     * 根据稿件编号获取文章详情
     */
    ArticleDetailDTO getArticleDetailByManuscriptId(String manuscriptId);
}
