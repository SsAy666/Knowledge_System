package com.knowledge.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 修改知识点树请求参数
 */
@Data
@ApiModel("修改知识点树请求参数")
public class UpdateKnowledgeTreeVO extends AddKnowledgeTreeVO {
    @ApiModelProperty(value = "id", required = true)
    @NotNull(message = "ID不能为空")
    private Integer id;
}
