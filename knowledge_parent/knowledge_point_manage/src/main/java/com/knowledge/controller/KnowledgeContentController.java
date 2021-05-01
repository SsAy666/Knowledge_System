package com.knowledge.controller;

import com.alibaba.fastjson.JSON;
import com.knowledge.entity.KnowledgeContentEntity;
import com.knowledge.entity.KnowledgeHistoryRecordEntity;
import com.knowledge.enums.OperateStyleEnum;
import com.knowledge.service.KnowledgeContentService;
import com.knowledge.utils.HttpContextUtils;
import com.knowledge.utils.Result;
import com.knowledge.vo.AddKnowledgeContentVO;
import com.knowledge.vo.UpdateKnowledgeContentVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 知识点内容controller
 */
@RestController
@RequestMapping("/knowledge/content")
@Slf4j
@Api(tags = "知识点内容管理接口")
public class KnowledgeContentController {

    @Autowired
    private KnowledgeContentService knowledgeContentService;

    /**
     * 新增知识点内容
     * @param addKnowledgeContentVO 知识点内容请求参数VO
     * @return
     */
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "新增知识点内容接口", notes = "新增知识点内容接口")
    @PostMapping("/addKnowledgeContent")
    public Result addKnowledgeContent(@RequestBody AddKnowledgeContentVO addKnowledgeContentVO){
        // 进行添加操作
        KnowledgeContentEntity knowledgeContentEntity = knowledgeContentService.addKnowledgeContent(addKnowledgeContentVO);
        // 将操作插入历史记录表
        knowledgeContentService.insertHistoryRecord(HttpContextUtils.getUsername(), OperateStyleEnum.ADD.getCode(), null, JSON.toJSONString(knowledgeContentEntity));
        return new Result().success("新增知识点内容成功！");
    }

    /**
     * 修改知识点内容
     * @param updateKnowledgeContentVO 修改知识点内容请求参数VO
     * @return
     */
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "修改知识点内容接口", notes = "修改知识点内容接口")
    @PostMapping("/updateKnowledgeContent")
    public Result updateKnowledgeContent(@RequestBody UpdateKnowledgeContentVO updateKnowledgeContentVO){
        // 根据ID查询更新操作前的记录
        KnowledgeContentEntity updateBeforeInfo = knowledgeContentService.queryKnowledgeContentById(updateKnowledgeContentVO.getId());
        // 进行更新操作
        knowledgeContentService.updateKnowledgeContent(updateKnowledgeContentVO);
        // 根据ID查询更新操作前的记录
        KnowledgeContentEntity updateAfterInfo = knowledgeContentService.queryKnowledgeContentById(updateKnowledgeContentVO.getId());
        // 将操作插入历史记录表
        knowledgeContentService.insertHistoryRecord(HttpContextUtils.getUsername(), OperateStyleEnum.EDIT.getCode(), JSON.toJSONString(updateBeforeInfo), JSON.toJSONString(updateAfterInfo));
        return new Result().success("修改知识点内容成功！");
    }

    /**
     * 查询知识点内容
     * @param knowledgeContentEntity 知识点内容实体类
     * @return
     */
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "查询知识点内容接口", notes = "查询知识点内容接口")
    @PostMapping("/queryKnowledgeContent")
    public Result queryKnowledgeContent(@RequestBody KnowledgeContentEntity knowledgeContentEntity){
        List<KnowledgeContentEntity> knowledgeContentEntities = knowledgeContentService.queryKnowledgeContent(knowledgeContentEntity);
        return new Result().ok(knowledgeContentEntities);
    }

    /**
     * 删除知识点内容
     * @param id 知识点内容ID
     * @return
     */
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "删除知识点内容接口", notes = "删除知识点内容接口")
    @GetMapping("/delKnowledgeContent")
    public Result delKnowledgeContent(@ApiParam(value = "知识点内容ID", required = true) @RequestParam("id") Integer id){
        // 根据ID查询删除操作前的记录
        KnowledgeContentEntity knowledgeContentEntity = knowledgeContentService.queryKnowledgeContentById(id);
        // 进行删除操作
        knowledgeContentService.delKnowledgeContent(id);
        // 将操作插入历史记录表
        knowledgeContentService.insertHistoryRecord(HttpContextUtils.getUsername(), OperateStyleEnum.DELETE.getCode(), JSON.toJSONString(knowledgeContentEntity), null);
        return new Result().success("删除知识点内容成功！");
    }

    /**
     * 查询历史记录
     * @return
     */
    @ApiOperation(value = "查询历史记录接口", notes = "查询历史记录接口")
    @GetMapping("/queryHistoryRecords")
    public Result queryHistoryRecords(){
        List<KnowledgeHistoryRecordEntity> knowledgeHistoryRecords = knowledgeContentService.queryHistoryRecords();
        return new Result().ok(knowledgeHistoryRecords);
    }

    /**
     * 上传图片
     * @param multipartFile
     * @return
     */
    @PostMapping(value = "/upload/pic")
    public Result updloadPic(@RequestParam("picFile") MultipartFile multipartFile){
        String fileName = knowledgeContentService.upload(multipartFile,"pic");
        return new Result().ok(fileName);
    }

    /**
     * 上传视频
     * @param multipartFile
     * @return
     */
    @PostMapping(value = "/upload/video")
    public Result updloadVideo(@RequestParam("videoFile") MultipartFile multipartFile){
        String fileName = knowledgeContentService.upload(multipartFile,"video");
        return new Result().ok(fileName);
    }

    /**
     * 上传音频
     * @param multipartFile
     * @return
     */
    @PostMapping(value = "/upload/mp3")
    public Result updloadMp3(@RequestParam("mp3File") MultipartFile multipartFile){
        String fileName = knowledgeContentService.upload(multipartFile,"mp3");
        return new Result().ok(fileName);
    }

    /**
     * 下载文件
     * @param map
     * @return
     */
    @PostMapping("/download")
    public Result download(@RequestBody Map<String,String> map) {
        String fileName = map.get("fileName");
        knowledgeContentService.download(fileName);
        return new Result().ok("下载成功");
    }

}
