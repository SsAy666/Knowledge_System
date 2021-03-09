package com.knowledge.service.impl;

import com.knowledge.dao.KnowledgeTreeDao;
import com.knowledge.entity.KnowledgeTreeEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.knowledge.service.KnowledgeTreeService;

/**
 * 知识点树接口实现类
 */
@Slf4j
@Transactional
@Service
public class KnowledgeTreeServiceImpl extends BaseServiceImpl<KnowledgeTreeDao, KnowledgeTreeEntity> implements KnowledgeTreeService {

}

