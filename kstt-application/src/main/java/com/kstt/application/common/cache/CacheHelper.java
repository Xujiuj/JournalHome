package com.kstt.application.common.cache;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 缓存辅助工具
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CacheHelper {

    private final RedisTemplate<String, Object> redisTemplate;

    /**
     * 批量获取或加载
     */
    public <K, V> Map<K, V> getOrLoad(Collection<K> keys,
                                      Function<K, String> keyMapper,
                                      Function<Collection<K>, Map<K, V>> loader,
                                      Duration ttl) {
        if (keys == null || keys.isEmpty()) {
            return Map.of();
        }

        List<String> cacheKeys = keys.stream()
                .map(keyMapper)
                .collect(Collectors.toList());

        List<Object> cachedValues = redisTemplate.opsForValue().multiGet(cacheKeys);
        
        Map<K, V> result = keys.stream()
                .collect(Collectors.toMap(Function.identity(), k -> null));

        Set<K> missedKeys = keys.stream().collect(Collectors.toSet());

        if (cachedValues != null) {
            int index = 0;
            for (K key : keys) {
                Object cached = cachedValues.get(index++);
                if (cached != null) {
                    result.put(key, (V) cached);
                    missedKeys.remove(key);
                }
            }
        }

        if (!missedKeys.isEmpty()) {
            Map<K, V> loaded = loader.apply(missedKeys);
            loaded.forEach((k, v) -> {
                result.put(k, v);
                String cacheKey = keyMapper.apply(k);
                redisTemplate.opsForValue().set(cacheKey, v, ttl);
            });
        }

        return result;
    }

    /**
     * 设置缓存
     */
    public void set(String key, Object value, Duration ttl) {
        redisTemplate.opsForValue().set(key, value, ttl);
    }

    /**
     * 获取缓存
     */
    public <T> T get(String key, Class<T> type) {
        Object value = redisTemplate.opsForValue().get(key);
        return value != null ? type.cast(value) : null;
    }

    /**
     * 删除缓存
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 批量删除
     */
    public void deletePattern(String pattern) {
        Set<String> keys = redisTemplate.keys(pattern);
        if (keys != null && !keys.isEmpty()) {
            redisTemplate.delete(keys);
        }
    }
}
