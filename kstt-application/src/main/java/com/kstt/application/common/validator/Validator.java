package com.kstt.application.common.validator;

import com.kstt.application.common.exception.ApplicationException;
import com.kstt.application.common.exception.ErrorCode;

import java.util.function.Predicate;

/**
 * 验证器工具类
 */
public class Validator {

    public static <T> T requireNonNull(T obj, String fieldName) {
        if (obj == null) {
            throw new ApplicationException(ErrorCode.PARAM_ERROR, fieldName + "不能为空");
        }
        return obj;
    }

    public static String requireNonBlank(String str, String fieldName) {
        if (str == null || str.isBlank()) {
            throw new ApplicationException(ErrorCode.PARAM_ERROR, fieldName + "不能为空");
        }
        return str;
    }

    public static <T> void require(T value, Predicate<T> condition, String message) {
        if (!condition.test(value)) {
            throw new ApplicationException(ErrorCode.PARAM_ERROR, message);
        }
    }

    public static void requireTrue(boolean condition, String message) {
        if (!condition) {
            throw new ApplicationException(ErrorCode.PARAM_ERROR, message);
        }
    }

    public static void requireRange(Integer value, int min, int max, String fieldName) {
        if (value != null && (value < min || value > max)) {
            throw new ApplicationException(ErrorCode.PARAM_ERROR, 
                    fieldName + "必须在" + min + "到" + max + "之间");
        }
    }

    public static void requirePositive(Long value, String fieldName) {
        if (value != null && value <= 0) {
            throw new ApplicationException(ErrorCode.PARAM_ERROR, fieldName + "必须大于0");
        }
    }

    private Validator() {
    }
}
