package com.knowledge.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.knowledge.dao.KnowledgeContentDao;
import com.knowledge.dao.KnowledgeContentTreeDao;
import com.knowledge.dao.KnowledgeHistoryRecordDao;
import com.knowledge.entity.KnowledgeContentEntity;
import com.knowledge.entity.KnowledgeContentTreeEntity;
import com.knowledge.entity.KnowledgeHistoryRecordEntity;
import com.knowledge.exception.RenException;
import com.knowledge.service.KnowledgeContentService;
import com.knowledge.utils.IDUtil;
import com.knowledge.vo.AddKnowledgeContentVO;
import com.knowledge.vo.UpdateKnowledgeContentVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

/**
 * 知识点内容业务接口实现类
 */
@Slf4j
@Service
public class KnowledgeContentServiceImpl extends ServiceImpl<KnowledgeContentDao, KnowledgeContentEntity> implements KnowledgeContentService {

    //配置文件中文件上传路径
    @Value("${prop.upload-folder}")
    private String UPLOAD_FOLDER_PATH;

    @Autowired
    private KnowledgeContentDao knowledgeContentDao;

    @Autowired
    private KnowledgeContentTreeDao knowledgeContentTreeDao;

    @Autowired
    private KnowledgeHistoryRecordDao knowledgeHistoryRecordDao;

    /**
     * 新增知识点内容
     * @param addKnowledgeContentVO 知识点内容请求参数VO
     * @return
     */
    @Override
    public KnowledgeContentEntity addKnowledgeContent(AddKnowledgeContentVO addKnowledgeContentVO) {
        KnowledgeContentEntity knowledgeContentEntity = new KnowledgeContentEntity();
        BeanUtils.copyProperties(addKnowledgeContentVO,knowledgeContentEntity);
        // 保存知识点内容
        if (!this.save(knowledgeContentEntity)) {
            log.info("插入知识点内容信息失败");
            throw new RenException("新增知识点内容失败！");
        }
        // 保存知识点内容-知识点树
        KnowledgeContentTreeEntity knowledgeContentTreeEntity = new KnowledgeContentTreeEntity();
        knowledgeContentTreeEntity.setKnowledgeTreeId(addKnowledgeContentVO.getTreeId());
        knowledgeContentTreeEntity.setKnowledgeContentId(knowledgeContentEntity.getId());
        if (knowledgeContentTreeDao.insert(knowledgeContentTreeEntity) == 0) {
            log.info("插入知识点内容-知识点树信息失败");
            throw new RenException("新增知识点内容！");
        }
        return knowledgeContentEntity;
    }

    /**
     * 修改知识点内容
     * @param updateKnowledgeContentVO 修改知识点内容请求参数VO
     * @return
     */
    @Override
    public void updateKnowledgeContent(UpdateKnowledgeContentVO updateKnowledgeContentVO) {
        KnowledgeContentEntity knowledgeContentEntity = new KnowledgeContentEntity();
        BeanUtils.copyProperties(updateKnowledgeContentVO,knowledgeContentEntity);
        if (!this.updateById(knowledgeContentEntity)) {
            log.info("更新知识点内容失败");
            throw new RenException("更新知识点内容失败！");
        }
    }

    /**
     * 查询知识点内容
     * @param knowledgeContentEntity 知识点内容实体类
     * @return
     */
    @Override
    public List<KnowledgeContentEntity> queryKnowledgeContent(KnowledgeContentEntity knowledgeContentEntity) {
        QueryWrapper<KnowledgeContentEntity> wrapper = new QueryWrapper<>();
        List<KnowledgeContentEntity> knowledgeContentEntities = this.list(wrapper);
        return knowledgeContentEntities;
    }

    /**
     * 删除知识点内容
     * @param id 知识点内容ID
     * @return
     */
    @Override
    public void delKnowledgeContent(Integer id) {
        // 删除知识点内容
        this.removeById(id);
        // 删除知识点内容-知识点树
        QueryWrapper<KnowledgeContentTreeEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("knowledge_content_id",id);
        knowledgeContentTreeDao.delete(wrapper);
    }

    /**
     * 上传文件
     * @param multipartFile   文件
     * @param type           文件类型
     * @return
     */
    @Override
    public String upload(MultipartFile multipartFile, String type) {
        // 文件为空不能上传
        if (multipartFile.isEmpty()) {
            throw new RenException("文件为空，上传失败！");
        }
        // 获取文件后缀
        String suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1);
        if ("pic".equals(type) && !"jpg,jpeg,gif,png".toUpperCase().contains(suffix.toUpperCase())) {
            throw new RenException("请选择jpg,jpeg,gif,png格式的图片");
        }
        // 文件目录位置
        String saveMkdir = UPLOAD_FOLDER_PATH + type;
        // 设置文件存储路径
        File savePathFile = new File(saveMkdir);
        if (!savePathFile.exists()) {
            // 若不存在该目录，则创建目录
            savePathFile.mkdirs();
        }
        // 通过UUID生成唯一文件名
        String filename = IDUtil.randomId() + "." + suffix;
        // 将图片保存指定目录
        String savePic = saveMkdir + "\\"+ filename;
        //数据库保存位置
        String saveDB = type + "-" + filename;
        // 上传文件
        try {
            multipartFile.transferTo(new File(savePic));
        } catch(Exception e) {
            e.printStackTrace();
            throw new RenException("上传失败！");
        }
        return saveDB;
    }

    /**
     * 插入历史记录
     * @param operator      操作人
     * @param operateStyle  操作方式
     */
    @Override
    public void insertHistoryRecord(String operator, Integer operateStyle, String operateBeforeContent, String operateAfterContent) {
        KnowledgeHistoryRecordEntity historyRecordEntity = new KnowledgeHistoryRecordEntity();
        historyRecordEntity.setOperateTime(new Date());
        historyRecordEntity.setOperator(operator);
        historyRecordEntity.setOperateStyle(operateStyle);
        historyRecordEntity.setOperateBeforeContent(operateBeforeContent);
        historyRecordEntity.setOperateAfterContent(operateAfterContent);
        // 插入历史记录
        if (knowledgeHistoryRecordDao.insert(historyRecordEntity) != 1) {
            log.info("插入历史记录失败");
            throw new RenException("插入历史记录失败！");
        }
    }

    /**
     * 根据ID查询知识点内容
     * @param id
     * @return
     */
    @Override
    public KnowledgeContentEntity queryKnowledgeContentById(Integer id) {
        return this.getById(id);
    }

    /**
     * 查询历史记录
     * @return
     */
    @Override
    public List<KnowledgeHistoryRecordEntity> queryHistoryRecords() {
        QueryWrapper<KnowledgeHistoryRecordEntity> wrapper = new QueryWrapper<>();
        return knowledgeHistoryRecordDao.selectList(wrapper);
    }

    /**
     * 下载文件
     * @param fileName 文件名称
     */
    @Override
    public void download(String fileName) {
        String[] fileArr = fileName.split("-");
        String pathName = UPLOAD_FOLDER_PATH + fileArr[0] + fileArr[1];
        try {
            // 设置图片存储路径
            String savePath = "D:\\download\\file\\";
            File savePathFile = new File(savePath);
            if (!savePathFile.exists()) {
                //若不存在该目录，则创建目录
                savePathFile.mkdirs();
            }
            // 将图片读入代码块
            FileInputStream fis = new FileInputStream(pathName);
            // 将图片从代码块中写出到硬盘
            String saveName = savePath + fileName;
            FileOutputStream fos = new FileOutputStream(saveName);
            // 长度多长,一次就读多少个
            byte[] bytes = new byte[1024];
            // 定义变量
            int len;
            // 读数据
            while((len=fis.read(bytes))!=-1) {
                // 写数据
                fos.write(bytes,0,len);
            }
            // 关流
            fos.close();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
