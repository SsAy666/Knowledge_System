package com.knowledge.dao;

import com.knowledge.entity.KnowledgeContentEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 *  知识点内容DAO类
 */
@Mapper
public interface KnowledgeContentDao extends BaseDao<KnowledgeContentEntity> {

}
