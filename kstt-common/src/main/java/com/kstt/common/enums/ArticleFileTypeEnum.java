package com.kstt.common.enums;

import lombok.Getter;

/**
 * 文件类型枚举
 */
@Getter
public enum ArticleFileTypeEnum {
    
    MAIN_DOCUMENT(1, "Main Document", "主要文档"),
    SUPPLEMENTARY_MATERIAL(2, "Supplementary Material", "补充材料"),
    COVER_LETTER(3, "Cover Letter", "投稿信"),
    DATA_FILE(4, "Data File", "数据文件"),
    FIGURE(5, "Figure", "图片文件"),
    TABLE(6, "Table", "表格文件");

    private final Integer typeId;
    private final String typeName;
    private final String typeDesc;

    ArticleFileTypeEnum(Integer typeId, String typeName, String typeDesc) {
        this.typeId = typeId;
        this.typeName = typeName;
        this.typeDesc = typeDesc;
    }

    /**
     * 根据ID获取枚举
     */
    public static ArticleFileTypeEnum getByTypeId(Integer typeId) {
        for (ArticleFileTypeEnum type : values()) {
            if (type.getTypeId().equals(typeId)) {
                return type;
            }
        }
        return null;
    }
}

