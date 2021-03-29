package com.knowledge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 用户管理微服务启动类
 */
@SpringBootApplication
@EnableEurekaClient
//@EnableFeignClients(basePackages = {"com.knowledge.feign"})
public class KnowledgeUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(KnowledgeUserApplication.class);
    }
}
