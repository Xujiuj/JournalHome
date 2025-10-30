package com.kstt.application.common.exception;

import lombok.Getter;

/**
 * 错误码枚举
 */
@Getter
public enum ErrorCode {

    // 通用错误 1xxx
    SUCCESS(200, "操作成功"),
    PARAM_ERROR(400, "参数错误"),
    UNAUTHORIZED(401, "未授权"),
    FORBIDDEN(403, "无权限"),
    NOT_FOUND(404, "资源不存在"),
    SYSTEM_ERROR(500, "系统内部错误"),

    // 业务错误 2xxx
    BUSINESS_ERROR(2000, "业务处理失败"),
    
    // 文章相关 21xx
    ARTICLE_NOT_FOUND(2101, "文章不存在"),
    ARTICLE_STATUS_INVALID(2102, "文章状态不允许此操作"),
    ARTICLE_ALREADY_EXISTS(2103, "文章已存在"),
    MANUSCRIPT_ID_DUPLICATE(2104, "稿件编号重复"),
    
    // 用户相关 22xx
    USER_NOT_FOUND(2201, "用户不存在"),
    USER_ALREADY_EXISTS(2202, "用户已存在"),
    USER_DISABLED(2203, "用户已被禁用"),
    PASSWORD_ERROR(2204, "密码错误"),
    EMAIL_ALREADY_EXISTS(2205, "邮箱已被使用"),
    
    // 审稿相关 23xx
    REVIEW_NOT_FOUND(2301, "审稿记录不存在"),
    REVIEWER_CONFLICT(2302, "审稿人存在利益冲突"),
    REVIEW_ALREADY_SUBMITTED(2303, "审稿意见已提交"),
    
    // 期刊相关 24xx
    JOURNAL_NOT_FOUND(2401, "期刊不存在"),
    JOURNAL_FULL(2402, "期刊已满"),
    
    // 数据库相关 3xxx
    DB_ERROR(3000, "数据库操作失败"),
    DB_CONSTRAINT_VIOLATION(3001, "数据完整性约束冲突"),
    
    // 缓存相关 4xxx
    CACHE_ERROR(4000, "缓存操作失败"),
    
    // 消息队列相关 5xxx
    MQ_ERROR(5000, "消息队列操作失败");

    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
