package com.kstt.common.enums;

/**
 * 稿件类型枚举
 */
public enum ArticleManuscriptTypeEnum {
    
    ORIGINAL_RESEARCH(1, "Original Research", "原创研究"),
    REVIEW(2, "Review", "综述文章"),
    LETTER(3, "Letter", "简短通讯"),
    CASE_STUDY(4, "Case Study", "案例研究"),
    EDITORIAL(5, "Editorial", "社论"),
    PERSPECTIVE(6, "Perspective", "观点文章"),
    COMMENT(7, "Comment", "评论");

    private final Integer typeId;
    private final String typeName;
    private final String typeDesc;

    ArticleManuscriptTypeEnum(Integer typeId, String typeName, String typeDesc) {
        this.typeId = typeId;
        this.typeName = typeName;
        this.typeDesc = typeDesc;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    /**
     * 根据ID获取枚举
     */
    public static ArticleManuscriptTypeEnum getByTypeId(Integer typeId) {
        for (ArticleManuscriptTypeEnum type : values()) {
            if (type.getTypeId().equals(typeId)) {
                return type;
            }
        }
        return null;
    }
}

