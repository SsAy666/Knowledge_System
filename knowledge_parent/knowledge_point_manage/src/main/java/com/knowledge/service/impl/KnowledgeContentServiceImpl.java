package com.knowledge.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.knowledge.dao.KnowledgeContentDao;
import com.knowledge.entity.KnowledgeContentEntity;
import com.knowledge.service.KnowledgeContentService;
import com.knowledge.vo.KnowledgeContentVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 知识点内容业务接口实现类
 */
@Slf4j
@Service
public class KnowledgeContentServiceImpl extends ServiceImpl<KnowledgeContentDao, KnowledgeContentEntity> implements KnowledgeContentService {

    @Autowired
    private KnowledgeContentDao knowledgeContentDao;

    /**
     * 新增知识点内容
     * @param knowledgeContentVO 加知识点内容请求参数VO
     * @return
     */
    @Override
    public void addKnowledgeContent(KnowledgeContentVO knowledgeContentVO) {

    }
}
