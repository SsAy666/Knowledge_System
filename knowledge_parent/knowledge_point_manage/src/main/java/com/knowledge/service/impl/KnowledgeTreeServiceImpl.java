package com.knowledge.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.knowledge.dao.KnowledgeContentDao;
import com.knowledge.dao.KnowledgeContentTreeDao;
import com.knowledge.dao.KnowledgeTreeDao;
import com.knowledge.dto.KnowledgeTreeDTO;
import com.knowledge.entity.KnowledgeContentTreeEntity;
import com.knowledge.entity.KnowledgeTreeEntity;
import com.knowledge.exception.RenException;
import com.knowledge.vo.AddKnowledgeTreeVO;
import com.knowledge.vo.UpdateKnowledgeTreeVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.knowledge.service.KnowledgeTreeService;

import java.util.List;

/**
 * 知识点树接口实现类
 */
@Slf4j
@Transactional
@Service
public class KnowledgeTreeServiceImpl extends ServiceImpl<KnowledgeTreeDao, KnowledgeTreeEntity> implements KnowledgeTreeService {

    @Autowired
    private KnowledgeTreeDao knowledgeTreeDao;

    @Autowired
    private KnowledgeContentTreeDao knowledgeContentTreeDao;

    @Autowired
    private KnowledgeContentDao knowledgeContentDao;

    /**
     * 新增知识点树
     * @param addKnowledgeTreeVO 添加知识点树请求参数
     * @return
     */
    @Override
    public void addKnowledgeTree(AddKnowledgeTreeVO addKnowledgeTreeVO) {
        KnowledgeTreeEntity knowledgeTreeEntity = new KnowledgeTreeEntity();
        BeanUtils.copyProperties(addKnowledgeTreeVO,knowledgeTreeEntity);
        // 获取当前相同父节点中最大的顺序号
        Integer maxOrderNum = knowledgeTreeDao.getMaxOrderNum(addKnowledgeTreeVO.getParentId());
        if (maxOrderNum != null) {
            // 设置当前相同父节点的顺序号
            knowledgeTreeEntity.setOrderNum(maxOrderNum+1);
        } else {
            // 设置当前相同父节点的顺序号为 1
            knowledgeTreeEntity.setOrderNum(1);
        }
        // 保存知识点树
        if (!this.save(knowledgeTreeEntity)) {
            log.info("插入知识点树信息失败");
            throw new RenException("新增知识点树失败！");
        }
    }

    /**
     * 修改知识点树
     * @param updateKnowledgeTreeVO 修改知识点树请求参数VO
     * @return
     */
    @Override
    public void updateKnowledgeTree(UpdateKnowledgeTreeVO updateKnowledgeTreeVO) {
        KnowledgeTreeEntity knowledgeTreeEntity = new KnowledgeTreeEntity();
        BeanUtils.copyProperties(updateKnowledgeTreeVO,knowledgeTreeEntity);
        if (!this.updateById(knowledgeTreeEntity)) {
            log.info("更新知识点树失败");
            throw new RenException("更新知识点树失败！");
        }
    }

    /**
     * 查询知识点树
     * @return
     */
    @Override
    public List<KnowledgeTreeDTO> queryKnowledgeTree() {
        List<KnowledgeTreeDTO> knowledgeTreeDTOS = knowledgeTreeDao.getKnowledgeTreeList();
        return knowledgeTreeDTOS;
    }

    /**
     * 删除知识点树
     * @param id 知识点树ID
     * @return
     */
    @Override
    public void delKnowledgeTree(Integer id) {
        // 删除知识点树表数据
        this.removeById(id);
        // 查询知识点树对应的知识点内容
        QueryWrapper<KnowledgeContentTreeEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("knowledge_tree_id",id);
        List<KnowledgeContentTreeEntity> knowledgeContentTreeEntities = knowledgeContentTreeDao.selectList(wrapper);
        for (KnowledgeContentTreeEntity knowledgeContentTreeEntity : knowledgeContentTreeEntities) {
            // 删除知识点内容
            knowledgeContentDao.deleteById(knowledgeContentTreeEntity.getKnowledgeContentId());
        }
        // 删除知识点内容-知识点树表数据
        knowledgeContentTreeDao.delete(wrapper);
    }
}

