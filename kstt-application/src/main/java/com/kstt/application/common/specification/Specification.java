package com.kstt.application.common.specification;

/**
 * 规范接口
 */
@FunctionalInterface
public interface Specification<T> {

    /**
     * 判断对象是否满足规范
     */
    boolean isSatisfiedBy(T candidate);

    /**
     * 与运算
     */
    default Specification<T> and(Specification<T> other) {
        return candidate -> this.isSatisfiedBy(candidate) && other.isSatisfiedBy(candidate);
    }

    /**
     * 或运算
     */
    default Specification<T> or(Specification<T> other) {
        return candidate -> this.isSatisfiedBy(candidate) || other.isSatisfiedBy(candidate);
    }

    /**
     * 非运算
     */
    default Specification<T> not() {
        return candidate -> !this.isSatisfiedBy(candidate);
    }
}
