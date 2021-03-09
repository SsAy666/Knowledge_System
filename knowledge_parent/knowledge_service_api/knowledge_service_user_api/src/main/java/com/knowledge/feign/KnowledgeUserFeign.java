package com.knowledge.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 知识点管理系统-人员管理feign接口
 */
@FeignClient(name = "knowledge_user")
@Component
public interface KnowledgeUserFeign {
}
