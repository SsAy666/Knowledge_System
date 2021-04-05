package com.knowledge.controller;

import com.knowledge.service.KnowledgeContentService;
import com.knowledge.utils.Result;
import com.knowledge.vo.KnowledgeContentVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
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
     * 新增知识点内容
     * @param knowledgeContentVO 加知识点内容请求参数VO
     * @return
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "新增知识点内容接口", notes = "新增知识点内容接口")
    @PostMapping("/addKnowledgeContent")
    public Result addKnowledgeContent(@RequestBody KnowledgeContentVO knowledgeContentVO){
        knowledgeContentService.addKnowledgeContent(knowledgeContentVO);
        return new Result().success("新增知识点内容成功！");
    }

    //Value注解里面是虚拟路径
    @Value("D:\\images")
    private String uploadAbsolutePath;

    @RequestMapping(value = "/api/user/upload" ,method = RequestMethod.POST)
    public Result updload(@RequestParam("userHeaderPicture") MultipartFile multipartFile){
        Result jsonResult = null;
        if(!multipartFile.isEmpty()){
            try{
                multipartFile.transferTo(new File(uploadAbsolutePath +"\\"+ multipartFile.getOriginalFilename()));
                jsonResult = new Result().ok(multipartFile.getOriginalFilename());
            }catch(Exception e){
                e.printStackTrace();
                jsonResult = new Result().error("网络异常");
            }
        }else{
            jsonResult = new Result().error("上传失败");
        }
        return jsonResult;
    }

}
