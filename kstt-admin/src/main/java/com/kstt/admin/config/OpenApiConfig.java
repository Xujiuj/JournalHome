package com.kstt.admin.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Journal Management Admin API")
                        .version("v1.0.0")
                        .description("期刊管理系统后台接口文档。包含用户、文章、期刊、通知、联系等模块。")
                        .contact(new Contact().name("KSTT").email("support@kstt.com"))
                        .license(new License().name("Apache 2.0")))
                .components(new Components())
                .servers(List.of(
                        new Server().url("/"    ).description("默认服务器"),
                        new Server().url("http://localhost:8080/").description("本地开发")
                ));
    }

    @Bean
    public GroupedOpenApi journalApi() {
        return GroupedOpenApi.builder()
                .group("journal")
                .packagesToScan("com.kstt.admin.controller.journal")
                .build();
    }

    @Bean
    public GroupedOpenApi articleApi() {
        return GroupedOpenApi.builder()
                .group("articles")
                .packagesToScan("com.kstt.admin.controller.articles")
                .build();
    }

    @Bean
    public GroupedOpenApi systemApi() {
        return GroupedOpenApi.builder()
                .group("system")
                .packagesToScan("com.kstt.admin.controller.sys")
                .build();
    }

    @Bean
    public GroupedOpenApi othersApi() {
        return GroupedOpenApi.builder()
                .group("others")
                .packagesToScan(
                        "com.kstt.admin.controller.notices",
                        "com.kstt.admin.controller.contact",
                        "com.kstt.admin.controller.about",
                        "com.kstt.admin.controller.common"
                )
                .build();
    }
}
