package com.knowledge.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.knowledge.dto.KnowledgeTreeDTO;
import com.knowledge.entity.KnowledgeTreeEntity;
import com.knowledge.vo.AddKnowledgeTreeVO;
import com.knowledge.vo.UpdateKnowledgeTreeVO;

import java.util.List;

/**
 * 知识点树接口
 */
public interface KnowledgeTreeService extends IService<KnowledgeTreeEntity> {

    /**
     * 新增知识点树
     * @param addKnowledgeTreeVO 添加知识点树请求参数
     * @return
     */
    void addKnowledgeTree(AddKnowledgeTreeVO addKnowledgeTreeVO);

    /**
     * 修改知识点树内容
     * @param updateKnowledgeTreeVO 修改知识点树请求参数VO
     * @return
     */
    void updateKnowledgeTree(UpdateKnowledgeTreeVO updateKnowledgeTreeVO);

    /**
     * 查询知识点树
     * @return
     */
    List<KnowledgeTreeDTO> queryKnowledgeTree();

    /**
     * 删除知识点树
     * @param id 知识点树ID
     * @return
     */
    void delKnowledgeTree(Integer id);
}
