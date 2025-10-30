package com.kstt.common.core.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 分页结果封装
 * 
 * @author kstt
 * @date 2025-01-01
 */
public class PageResult<T> implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 总记录数
     */
    private long total;
    
    /**
     * 列表数据
     */
    private List<T> rows;
    
    /**
     * 消息状态码
     */
    private int code;
    
    /**
     * 消息内容
     */
    private String msg;
    
    public PageResult() {
    }
    
    public PageResult(List<T> rows, long total) {
        this.rows = rows;
        this.total = total;
    }
    
    public PageResult(List<T> rows, long total, int code, String msg) {
        this.rows = rows;
        this.total = total;
        this.code = code;
        this.msg = msg;
    }
    
    public long getTotal() {
        return total;
    }
    
    public void setTotal(long total) {
        this.total = total;
    }
    
    public List<T> getRows() {
        return rows;
    }
    
    public void setRows(List<T> rows) {
        this.rows = rows;
    }
    
    public int getCode() {
        return code;
    }
    
    public void setCode(int code) {
        this.code = code;
    }
    
    public String getMsg() {
        return msg;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }
}