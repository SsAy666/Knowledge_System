package com.knowledge.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 添加知识点树请求参数
 */
@Data
@ApiModel("添加知识点树请求参数")
public class AddKnowledgeTreeVO {
    @ApiModelProperty(value = "分支类型", required = true)
    private Integer branchType;

    @ApiModelProperty(value = "分支名称", required = true)
    private String branchName;

    @ApiModelProperty(value = "父节点ID", required = true)
    private Integer parentId;

}
