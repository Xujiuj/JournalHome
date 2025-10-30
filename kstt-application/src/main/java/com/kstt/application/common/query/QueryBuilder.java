package com.kstt.application.common.query;

/**
 * 查询构建器接口
 */
public interface QueryBuilder<T, Q> {

    /**
     * 从查询对象构建实体条件
     */
    T build(Q query);

    /**
     * 验证查询参数
     */
    default void validate(Q query) {
        // 子类可覆盖
    }
}
