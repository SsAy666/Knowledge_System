package com.knowledge.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.knowledge.entity.KnowledgeContentEntity;
import com.knowledge.entity.KnowledgeHistoryRecordEntity;
import com.knowledge.vo.AddKnowledgeContentVO;
import com.knowledge.vo.UpdateKnowledgeContentVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 知识点内容业务接口
 */
public interface KnowledgeContentService extends IService<KnowledgeContentEntity> {

    /**
     * 新增知识点内容
     * @param addKnowledgeContentVO 知识点内容请求参数VO
     * @return
     */
    KnowledgeContentEntity addKnowledgeContent(AddKnowledgeContentVO addKnowledgeContentVO);

    /**
     * 修改知识点内容
     * @param updateKnowledgeContentVO 修改知识点内容请求参数VO
     * @return
     */
    void updateKnowledgeContent(UpdateKnowledgeContentVO updateKnowledgeContentVO);

    /**
     * 查询知识点内容
     * @param knowledgeContentEntity 知识点内容实体类
     * @return
     */
    List<KnowledgeContentEntity> queryKnowledgeContent(KnowledgeContentEntity knowledgeContentEntity);

    /**
     * 删除知识点内容
     * @param id 知识点内容ID
     * @return
     */
    void delKnowledgeContent(Integer id);

    /**
     * 上传文件
     * @param multipartFile   文件
     * @param type           文件类型
     * @return
     */
    String upload(MultipartFile multipartFile, String type);

    /**
     * 插入历史记录管理
     * @param operator      操作人
     * @param operateStyle  操作方式
     */
    void insertHistoryRecord(String operator, Integer operateStyle, String operateBeforeContent, String operateAfterContent);

    /**
     * 根据ID查询知识点内容
     * @param id
     * @return
     */
    KnowledgeContentEntity queryKnowledgeContentById(Integer id);

    /**
     * 查询历史记录
     * @return
     */
    List<KnowledgeHistoryRecordEntity> queryHistoryRecords();

    /**
     * 下载文件
     * @param fileName 文件名称
     */
    void download(String fileName);
}
