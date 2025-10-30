package com.kstt.application.common.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 分页结果封装
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> implements Serializable {

    private List<T> records;
    private long total;
    private long pageNum;
    private long pageSize;
    private long pages;

    public static <T> PageResult<T> empty() {
        return PageResult.<T>builder()
                .records(Collections.emptyList())
                .total(0)
                .pageNum(1)
                .pageSize(10)
                .pages(0)
                .build();
    }

    public static <T> PageResult<T> of(List<T> records, long total, long pageNum, long pageSize) {
        long pages = total > 0 ? (total + pageSize - 1) / pageSize : 0;
        return PageResult.<T>builder()
                .records(records)
                .total(total)
                .pageNum(pageNum)
                .pageSize(pageSize)
                .pages(pages)
                .build();
    }
}
