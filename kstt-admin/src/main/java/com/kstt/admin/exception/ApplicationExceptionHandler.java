package com.kstt.admin.exception;

import com.kstt.application.common.exception.ApplicationException;
import com.kstt.common.core.domain.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 应用层异常处理器
 */
@Slf4j
@Order(1)
@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    public AjaxResult handleApplicationException(ApplicationException e) {
        log.error("应用层异常 [{}]: {}", e.getCode(), e.getMessage());
        
        return AjaxResult.error(e.getCode(), e.getMessage());
    }
}

