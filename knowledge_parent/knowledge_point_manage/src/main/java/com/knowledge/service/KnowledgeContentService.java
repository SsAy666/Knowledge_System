package com.knowledge.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.knowledge.entity.KnowledgeContentEntity;
import com.knowledge.vo.KnowledgeContentVO;
import org.springframework.web.multipart.MultipartFile;

/**
 * 知识点内容业务接口
 */
public interface KnowledgeContentService extends IService<KnowledgeContentEntity> {

    /**
     * 新增知识点内容
     * @param knowledgeContentVO 知识点内容请求参数VO
     * @return
     */
    void addKnowledgeContent(KnowledgeContentVO knowledgeContentVO);

    /**
     * 上传文件
     * @param multipartFile   文件
     * @param type           文件类型
     * @return
     */
    String upload(MultipartFile multipartFile, String type);
}
