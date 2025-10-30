package com.kstt.common.exception;

/**
 * 业务异常
 * 
 * @author kstt
 * @date 2025-01-01
 */
public class BusinessException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    
    protected String module;
    protected int code;
    protected String[] args;

    public BusinessException(String module, int code, String[] args, String defaultMessage) {
        super(defaultMessage);
        this.module = module;
        this.code = code;
        this.args = args;
    }

    public BusinessException(String module, int code, String[] args) {
        this(module, code, args, null);
    }

    public BusinessException(String module, String defaultMessage) {
        this(module, 500, null, defaultMessage);
    }

    public BusinessException(int code, String[] args) {
        this(null, code, args, null);
    }

    public BusinessException(String defaultMessage) {
        this(null, 500, null, defaultMessage);
    }

    public String getModule() {
        return module;
    }

    public int getCode() {
        return code;
    }

    public String[] getArgs() {
        return args;
    }
}