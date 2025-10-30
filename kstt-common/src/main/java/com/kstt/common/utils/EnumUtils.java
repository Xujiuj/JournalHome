package com.kstt.common.utils;

import com.kstt.common.enums.*;

/**
 * 枚举工具类
 */
public class EnumUtils {

    /**
     * 根据状态ID获取状态名称
     */
    public static String getStatusName(Integer statusId) {
        ArticleStatusEnum status = ArticleStatusEnum.getByStatusId(statusId);
        return status != null ? status.getStatusName() : "Unknown";
    }

    /**
     * 根据稿件类型ID获取类型名称
     */
    public static String getManuscriptTypeName(Integer typeId) {
        ArticleManuscriptTypeEnum type = ArticleManuscriptTypeEnum.getByTypeId(typeId);
        return type != null ? type.getTypeName() : "Unknown";
    }

    /**
     * 根据提交类型ID获取类型名称
     */
    public static String getSubmissionTypeName(Integer typeId) {
        ArticleSubmissionTypeEnum type = ArticleSubmissionTypeEnum.getByTypeId(typeId);
        return type != null ? type.getTypeName() : "Unknown";
    }

    /**
     * 根据文件类型ID获取类型名称
     */
    public static String getFileTypeName(Integer typeId) {
        ArticleFileTypeEnum type = ArticleFileTypeEnum.getByTypeId(typeId);
        return type != null ? type.getTypeName() : "Unknown";
    }

    /**
     * 根据学位ID获取学位名称
     */
    public static String getDegreeName(Integer degreeId) {
        UserAcademicDegreeEnum degree = UserAcademicDegreeEnum.getByDegreeId(degreeId);
        return degree != null ? degree.getDegreeName() : "Unknown";
    }
}

