package com.kstt.articles.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kstt.articles.entity.ArticleStatus;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文章状态Mapper接口
 */
@Mapper
public interface ArticleStatusMapper extends BaseMapper<ArticleStatus> {
}

