package com.knowledge.dao;

import com.knowledge.dto.KnowledgeTreeDTO;
import com.knowledge.entity.KnowledgeTreeEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 知识点树DAO
 */
@Mapper
public interface KnowledgeTreeDao extends BaseDao<KnowledgeTreeEntity> {
    /**
     * 获取当前相同父节点中最大的顺序号
     * @param parentId 父节点ID
     * @return
     */
    Integer getMaxOrderNum(@Param("parentId") Integer parentId);

    /**
     * 查询知识点树
     * @return
     */
    List<KnowledgeTreeDTO> getKnowledgeTreeList();

    /**
     * 查询知识点树
     * @return
     */
    List<KnowledgeTreeDTO> getKnowledgeTreeChildren();
}
