package com.kstt.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 枚举选项DTO，用于前端下拉框
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnumOptionDTO {
    
    /**
     * 值（ID）
     */
    private Integer value;
    
    /**
     * 标签（名称）
     */
    private String label;
    
    /**
     * 描述
     */
    private String description;
}

