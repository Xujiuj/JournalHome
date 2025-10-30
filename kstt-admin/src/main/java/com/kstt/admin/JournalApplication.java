package com.kstt.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 期刊门户网站主启动类
 */
@SpringBootApplication(scanBasePackages = {
    "com.kstt.common",
    "com.kstt.sys", 
    "com.kstt.journal",
    "com.kstt.articles",
    "com.kstt.notices",
    "com.kstt.contact",
    "com.kstt.about",
    "com.kstt.application",
    "com.kstt.admin"
})
public class JournalApplication {

    public static void main(String[] args) {
        SpringApplication.run(JournalApplication.class, args);
    }
}
