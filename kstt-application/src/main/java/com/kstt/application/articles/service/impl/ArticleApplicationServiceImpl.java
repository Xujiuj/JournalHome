package com.kstt.application.articles.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kstt.application.articles.converter.ArticleConverter;
import com.kstt.application.articles.dto.ArticleDetailDTO;
import com.kstt.application.articles.dto.ArticleListDTO;
import com.kstt.application.articles.query.ArticleQuery;
import com.kstt.application.articles.query.ArticleQueryBuilder;
import com.kstt.application.articles.service.ArticleApplicationService;
import com.kstt.application.common.exception.ApplicationException;
import com.kstt.application.common.exception.ErrorCode;
import com.kstt.application.common.result.PageResult;
import com.kstt.articles.entity.Article;
import com.kstt.articles.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 文章应用服务实现
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ArticleApplicationServiceImpl implements ArticleApplicationService {

    private final ArticleService articleService;
    private final ArticleConverter articleConverter;
    private final ArticleQueryBuilder queryBuilder;

    @Override
    @Transactional(readOnly = true)
    public PageResult<ArticleListDTO> queryArticleList(ArticleQuery query) {
        if (query == null) {
            query = ArticleQuery.builder().pageNum(1).pageSize(10).build();
        }

        queryBuilder.validate(query);
        Article condition = queryBuilder.build(query);
        
        PageHelper.startPage(
                query.getPageNum() != null ? query.getPageNum() : 1,
                query.getPageSize() != null ? query.getPageSize() : 10
        );

        if (query.getOrderBy() != null) {
            String orderBy = query.getOrderBy() + (Boolean.TRUE.equals(query.getDesc()) ? " DESC" : " ASC");
            PageHelper.orderBy(orderBy);
        }

        List<Article> articles = articleService.selectArticleList(condition);
        PageInfo<Article> pageInfo = new PageInfo<>(articles);

        List<ArticleListDTO> dtoList = articleConverter.toDTOList(articles);

        return PageResult.of(dtoList, pageInfo.getTotal(), 
                pageInfo.getPageNum(), pageInfo.getPageSize());
    }

    @Override
    @Transactional(readOnly = true)
    public ArticleDetailDTO getArticleDetail(Long articleId) {
        if (articleId == null) {
            throw new ApplicationException(ErrorCode.PARAM_ERROR, "文章ID不能为空");
        }

        Article article = articleService.selectArticleByPaperId(articleId);
        if (article == null) {
            throw new ApplicationException(ErrorCode.ARTICLE_NOT_FOUND);
        }

        return articleConverter.toDetailDTO(article);
    }

    @Override
    @Transactional(readOnly = true)
    public ArticleDetailDTO getArticleDetailByManuscriptId(String manuscriptId) {
        if (manuscriptId == null || manuscriptId.isBlank()) {
            throw new ApplicationException(ErrorCode.PARAM_ERROR, "稿件编号不能为空");
        }

        Article article = articleService.selectArticleByManuscriptId(manuscriptId);
        if (article == null) {
            throw new ApplicationException(ErrorCode.ARTICLE_NOT_FOUND);
        }

        return articleConverter.toDetailDTO(article);
    }
}
