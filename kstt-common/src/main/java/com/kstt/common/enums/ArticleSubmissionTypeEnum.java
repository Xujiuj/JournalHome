package com.kstt.common.enums;

import lombok.Getter;

/**
 * 提交类型枚举
 */
@Getter
public enum ArticleSubmissionTypeEnum {
    
    INITIAL_SUBMISSION(1, "Initial Submission", "首次提交"),
    REVISED_SUBMISSION(2, "Revised Submission", "修改后提交"),
    RESUBMISSION(3, "Resubmission", "重新提交");

    private final Integer typeId;
    private final String typeName;
    private final String typeDesc;

    ArticleSubmissionTypeEnum(Integer typeId, String typeName, String typeDesc) {
        this.typeId = typeId;
        this.typeName = typeName;
        this.typeDesc = typeDesc;
    }

    /**
     * 根据ID获取枚举
     */
    public static ArticleSubmissionTypeEnum getByTypeId(Integer typeId) {
        for (ArticleSubmissionTypeEnum type : values()) {
            if (type.getTypeId().equals(typeId)) {
                return type;
            }
        }
        return null;
    }
}

