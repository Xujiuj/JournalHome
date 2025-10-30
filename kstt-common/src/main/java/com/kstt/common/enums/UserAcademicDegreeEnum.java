package com.kstt.common.enums;

/**
 * 学术学位枚举
 */
public enum UserAcademicDegreeEnum {
    
    BACHELOR(1, "学士", "Bachelor"),
    MASTER(2, "硕士", "Master"),
    DOCTOR(3, "博士", "Doctor"),
    NONE(4, "无", "None");

    private final Integer degreeId;
    private final String degreeName;
    private final String degreeLevel;

    UserAcademicDegreeEnum(Integer degreeId, String degreeName, String degreeLevel) {
        this.degreeId = degreeId;
        this.degreeName = degreeName;
        this.degreeLevel = degreeLevel;
    }

    public Integer getDegreeId() {
        return degreeId;
    }

    public String getDegreeName() {
        return degreeName;
    }

    public String getDegreeLevel() {
        return degreeLevel;
    }

    /**
     * 根据ID获取枚举
     */
    public static UserAcademicDegreeEnum getByDegreeId(Integer degreeId) {
        for (UserAcademicDegreeEnum degree : values()) {
            if (degree.getDegreeId().equals(degreeId)) {
                return degree;
            }
        }
        return null;
    }
}

