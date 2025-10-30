package com.kstt.admin.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试控制器
 */
@Tag(name = "测试接口", description = "用于测试API是否正常工作的接口")
@RestController
@RequestMapping("/api/test")
public class TestController {

    @Operation(summary = "健康检查", description = "检查API服务是否正常运行")
    @GetMapping("/health")
    public Map<String, Object> health() {
        Map<String, Object> result = new HashMap<>();
        result.put("status", "UP");
        result.put("message", "API服务正常运行");
        result.put("timestamp", System.currentTimeMillis());
        return result;
    }

    @Operation(summary = "获取服务器信息", description = "获取服务器基本信息")
    @GetMapping("/info")
    public Map<String, Object> info() {
        Map<String, Object> result = new HashMap<>();
        result.put("application", "Journal Management System");
        result.put("version", "1.0.0");
        result.put("port", 10086);
        result.put("java.version", System.getProperty("java.version"));
        result.put("os.name", System.getProperty("os.name"));
        return result;
    }
}
