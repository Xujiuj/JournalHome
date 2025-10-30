package com.kstt.common.service;

import com.kstt.common.dto.EnumOptionDTO;
import com.kstt.common.enums.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 枚举服务类，提供枚举数据给前端
 */
public class EnumService {

    /**
     * 获取所有文章状态选项
     */
    public static List<EnumOptionDTO> getArticleStatusOptions() {
        List<EnumOptionDTO> options = new ArrayList<>();
        for (ArticleStatusEnum status : ArticleStatusEnum.values()) {
            options.add(new EnumOptionDTO(status.getStatusId(), status.getStatusName(), status.getStatusDesc()));
        }
        return options;
    }

    /**
     * 获取所有稿件类型选项
     */
    public static List<EnumOptionDTO> getManuscriptTypeOptions() {
        List<EnumOptionDTO> options = new ArrayList<>();
        for (ArticleManuscriptTypeEnum type : ArticleManuscriptTypeEnum.values()) {
            options.add(new EnumOptionDTO(type.getTypeId(), type.getTypeName(), type.getTypeDesc()));
        }
        return options;
    }

    /**
     * 获取所有提交类型选项
     */
    public static List<EnumOptionDTO> getSubmissionTypeOptions() {
        List<EnumOptionDTO> options = new ArrayList<>();
        for (ArticleSubmissionTypeEnum type : ArticleSubmissionTypeEnum.values()) {
            options.add(new EnumOptionDTO(type.getTypeId(), type.getTypeName(), type.getTypeDesc()));
        }
        return options;
    }

    /**
     * 获取所有文件类型选项
     */
    public static List<EnumOptionDTO> getFileTypeOptions() {
        List<EnumOptionDTO> options = new ArrayList<>();
        for (ArticleFileTypeEnum type : ArticleFileTypeEnum.values()) {
            options.add(new EnumOptionDTO(type.getTypeId(), type.getTypeName(), type.getTypeDesc()));
        }
        return options;
    }

    /**
     * 获取所有学位选项
     */
    public static List<EnumOptionDTO> getDegreeOptions() {
        List<EnumOptionDTO> options = new ArrayList<>();
        for (UserAcademicDegreeEnum degree : UserAcademicDegreeEnum.values()) {
            options.add(new EnumOptionDTO(degree.getDegreeId(), degree.getDegreeName(), degree.getDegreeLevel()));
        }
        return options;
    }
}

