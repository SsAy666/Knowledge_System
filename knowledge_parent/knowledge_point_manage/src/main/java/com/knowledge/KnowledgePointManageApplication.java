package com.knowledge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 知识点树微服务启动类
 */
@SpringBootApplication
@EnableEurekaClient
//@MapperScan(basePackages = {"com.knowledge.dao"})
//@EnableFeignClients(basePackages = {"com.knowledge.feign"})
public class KnowledgePointManageApplication {
    public static void main(String[] args) {
        SpringApplication.run( KnowledgePointManageApplication.class);
    }
}
