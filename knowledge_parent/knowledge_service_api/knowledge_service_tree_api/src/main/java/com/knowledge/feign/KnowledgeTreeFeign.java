package com.knowledge.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 知识点树feign接口
 */
@FeignClient(name = "knowledge-tree") //指明微服务名称
@Component
public interface KnowledgeTreeFeign {

}
