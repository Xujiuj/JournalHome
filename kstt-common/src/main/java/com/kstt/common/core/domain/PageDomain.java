package com.kstt.common.core.domain;

/**
 * 分页数据
 * 
 * @author kstt
 * @date 2025-01-01
 */
public class PageDomain {
    
    /** 当前记录起始索引 */
    private Integer pageNum;
    
    /** 每页显示记录数 */
    private Integer pageSize;
    
    /** 排序列 */
    private String orderByColumn;
    
    /** 排序的方向 "desc" 或者 "asc" */
    private String isAsc;
    
    /** 排序字段 */
    private String orderBy;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderByColumn() {
        return orderByColumn;
    }

    public void setOrderByColumn(String orderByColumn) {
        this.orderByColumn = orderByColumn;
    }

    public String getIsAsc() {
        return isAsc;
    }

    public void setIsAsc(String isAsc) {
        this.isAsc = isAsc;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}