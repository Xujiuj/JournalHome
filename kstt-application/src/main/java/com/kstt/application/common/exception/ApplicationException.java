package com.kstt.application.common.exception;

import lombok.Getter;

/**
 * 应用层异常
 */
@Getter
public class ApplicationException extends RuntimeException {

    private final ErrorCode errorCode;
    private final Object[] args;

    public ApplicationException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.args = null;
    }

    public ApplicationException(ErrorCode errorCode, String customMessage) {
        super(customMessage);
        this.errorCode = errorCode;
        this.args = null;
    }

    public ApplicationException(ErrorCode errorCode, Object... args) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.args = args;
    }

    public int getCode() {
        return errorCode.getCode();
    }
}
