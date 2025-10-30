package com.kstt.common.enums;

/**
 * 文章状态枚举
 */
public enum ArticleStatusEnum {
    
    DRAFT(0, "Draft", "草稿状态"),
    SUBMITTED(1, "Submitted", "已提交"),
    UNDER_REVIEW(2, "Under Review", "审稿中"),
    REVISION_REQUESTED(3, "Revision Requested", "修改中"),
    ACCEPTED(4, "Accepted", "已接收"),
    PUBLISHED(5, "Published", "已发表"),
    REJECTED(6, "Rejected", "已拒绝"),
    WITHDRAWN(7, "Withdrawn", "已撤回");

    private final Integer statusId;
    private final String statusName;
    private final String statusDesc;

    ArticleStatusEnum(Integer statusId, String statusName, String statusDesc) {
        this.statusId = statusId;
        this.statusName = statusName;
        this.statusDesc = statusDesc;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    /**
     * 根据ID获取枚举
     */
    public static ArticleStatusEnum getByStatusId(Integer statusId) {
        for (ArticleStatusEnum status : values()) {
            if (status.getStatusId().equals(statusId)) {
                return status;
            }
        }
        return null;
    }
}

