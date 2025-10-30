package com.kstt.application.common.converter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * DTO 转换器接口
 */
@FunctionalInterface
public interface DTOConverter<E, D> {

    /**
     * 实体转 DTO
     */
    D toDTO(E entity);

    /**
     * 批量转换
     */
    default List<D> toDTOList(List<E> entities) {
        if (entities == null || entities.isEmpty()) {
            return List.of();
        }
        return entities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
