package com.kstt.common.core.page;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 表格分页数据对象
 * 
 * @author kstt
 * @date 2025-01-01
 */
@Data
public class TableDataInfo implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /** 总记录数 */
    private long total;
    
    /** 列表数据 */
    private List<?> data;
    
    /** 消息状态码 */
    private int code;
    
    /** 消息内容 */
    private String msg;

    public TableDataInfo() {
    }

    public TableDataInfo(List<?> list, int total) {
        this.data = list;
        this.total = total;
    }
}