package com.knowledge.dao;

import com.knowledge.entity.KnowledgeTreeEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 知识点树DAO
 */
@Mapper
public interface KnowledgeTreeDao extends BaseDao<KnowledgeTreeEntity> {
}
