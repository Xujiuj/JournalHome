package com.kstt.common.core.page;

import com.kstt.common.core.domain.PageDomain;
import com.kstt.common.utils.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 表格数据处理
 * 
 * @author kstt
 * @date 2025-01-01
 */
public class TableSupport {
    
    /**
     * 当前记录起始索引
     */
    public static final String PAGE_NUM = "pageNum";
    
    /**
     * 每页显示记录数
     */
    public static final String PAGE_SIZE = "pageSize";
    
    /**
     * 排序列
     */
    public static final String ORDER_BY_COLUMN = "orderByColumn";
    
    /**
     * 排序的方向 "desc" 或者 "asc"
     */
    public static final String IS_ASC = "isAsc";
    
    /**
     * 分页参数合理化
     */
    private static final int DEFAULT_PAGE_NUM = 1;
    private static final int DEFAULT_PAGE_SIZE = 10;
    private static final int MAX_PAGE_SIZE = 100;
    
    /**
     * 封装分页对象
     */
    public static PageDomain getPageDomain() {
        PageDomain pageDomain = new PageDomain();
        pageDomain.setPageNum(getPageNum());
        pageDomain.setPageSize(getPageSize());
        pageDomain.setOrderByColumn(getOrderByColumn());
        pageDomain.setIsAsc(getIsAsc());
        pageDomain.setOrderBy(getOrderBy());
        return pageDomain;
    }
    
    /**
     * 构建分页请求
     */
    public static PageDomain buildPageRequest() {
        return getPageDomain();
    }
    
    /**
     * 获取请求
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attributes != null ? attributes.getRequest() : null;
    }
    
    /**
     * 获取页码
     */
    public static Integer getPageNum() {
        HttpServletRequest request = getRequest();
        if (request != null) {
            String pageNum = request.getParameter(PAGE_NUM);
            if (StringUtils.isNotEmpty(pageNum)) {
                return Integer.parseInt(pageNum);
            }
        }
        return DEFAULT_PAGE_NUM;
    }
    
    /**
     * 获取每页大小
     */
    public static Integer getPageSize() {
        HttpServletRequest request = getRequest();
        if (request != null) {
            String pageSize = request.getParameter(PAGE_SIZE);
            if (StringUtils.isNotEmpty(pageSize)) {
                int size = Integer.parseInt(pageSize);
                return size > MAX_PAGE_SIZE ? MAX_PAGE_SIZE : size;
            }
        }
        return DEFAULT_PAGE_SIZE;
    }
    
    /**
     * 获取排序列
     */
    public static String getOrderByColumn() {
        HttpServletRequest request = getRequest();
        if (request != null) {
            return request.getParameter(ORDER_BY_COLUMN);
        }
        return null;
    }
    
    /**
     * 获取排序方向
     */
    public static String getIsAsc() {
        HttpServletRequest request = getRequest();
        if (request != null) {
            return request.getParameter(IS_ASC);
        }
        return null;
    }
    
    /**
     * 获取排序字段
     */
    public static String getOrderBy() {
        String orderByColumn = getOrderByColumn();
        String isAsc = getIsAsc();
        if (StringUtils.isNotEmpty(orderByColumn)) {
            return orderByColumn + " " + (StringUtils.isNotEmpty(isAsc) ? isAsc : "asc");
        }
        return null;
    }
}