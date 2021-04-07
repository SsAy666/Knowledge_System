package com.knowledge.controller;

import com.knowledge.service.KnowledgeContentService;
import com.knowledge.utils.Result;
import com.knowledge.vo.KnowledgeContentVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * 管理员controller
 */
@RestController
@RequestMapping("/knowledge/content")
@Slf4j
@Api(tags = "知识点内容管理接口")
public class KnowledgeContentController {

    @Autowired
    private KnowledgeContentService knowledgeContentService;

    /**
     * 修改知识点内容
     * @param knowledgeContentVO 修改知识点内容请求参数VO
     * @return
     */
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "修改知识点内容接口", notes = "修改知识点内容接口")
    @PostMapping("/updateKnowledgeContent")
    public Result updateKnowledgeContent(@RequestBody KnowledgeContentVO knowledgeContentVO){
        knowledgeContentService.addKnowledgeContent(knowledgeContentVO);
        return new Result().success("修改知识点内容成功！");
    }

    /**
     * 新增知识点内容
     * @param knowledgeContentVO 知识点内容请求参数VO
     * @return
     */
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "新增知识点内容接口", notes = "新增知识点内容接口")
    @PostMapping("/addKnowledgeContent")
    public Result addKnowledgeContent(@RequestBody KnowledgeContentVO knowledgeContentVO){
        knowledgeContentService.addKnowledgeContent(knowledgeContentVO);
        return new Result().success("新增知识点内容成功！");
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

}
