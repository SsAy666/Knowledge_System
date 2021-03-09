package com.knowledge;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 知识点树微服务启动类
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = {"com.knowledge.dao"})
@EnableFeignClients(basePackages = {"com.knowledge.feign"})
public class KnowledgeTreeApplication {
    public static void main(String[] args) {
        SpringApplication.run( KnowledgeTreeApplication.class);
    }
}
