package com.knowledge.controller;

import com.knowledge.dto.KnowledgeTreeDTO;
import com.knowledge.service.KnowledgeTreeService;
import com.knowledge.utils.Result;
import com.knowledge.vo.AddKnowledgeTreeVO;
import com.knowledge.vo.UpdateKnowledgeTreeVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 知识点树controller
 */
@RestController
@RequestMapping("/knowledge/tree")
@Slf4j
@Api(tags = "知识树点管理接口")
public class KnowledgeTreeController {

    @Autowired
    private KnowledgeTreeService knowledgeTreeService;

    /**
     * 新增知识点树
     * @param addKnowledgeTreeVO 添加知识点树请求参数
     * @return
     */
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "新增知识点树接口", notes = "新增知识点树接口")
    @PostMapping("/addKnowledgeTree")
    public Result addKnowledgeTree(@RequestBody AddKnowledgeTreeVO addKnowledgeTreeVO){
        knowledgeTreeService.addKnowledgeTree(addKnowledgeTreeVO);
        return new Result().success("新增知识点树成功！");
    }

    /**
     * 修改知识点树内容
     * @param updateKnowledgeTreeVO 修改知识点树请求参数VO
     * @return
     */
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "修改知识点树接口", notes = "修改知识点树接口")
    @PostMapping("/updateKnowledgeTree")
    public Result updateKnowledgeTree(@RequestBody UpdateKnowledgeTreeVO updateKnowledgeTreeVO){
        knowledgeTreeService.updateKnowledgeTree(updateKnowledgeTreeVO);
        return new Result().success("修改知识点树成功！");
    }

    /**
     * 查询知识点树
     * @return
     */
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "查询知识点树接口", notes = "查询知识点树接口")
    @GetMapping("/queryKnowledgeTree")
    public Result queryKnowledgeTree(){
        List<KnowledgeTreeDTO> knowledgeContentEntities = knowledgeTreeService.queryKnowledgeTree();
        return new Result().ok(knowledgeContentEntities);
    }

    /**
     * 删除知识点树
     * @param id 知识点树ID
     * @return
     */
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "删除知识点树接口", notes = "删除知识点树接口")
    @GetMapping("/delKnowledgeTree")
    public Result delKnowledgeTree(@ApiParam(value = "知识点树ID", required = true) @RequestParam("id") Integer id){
        knowledgeTreeService.delKnowledgeTree(id);
        return new Result().success("删除知识点树成功！");
    }

}
