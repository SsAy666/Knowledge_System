package com.knowledge.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 修改知识点内容请求参数
 */
@Data
@ApiModel("修改知识点内容请求参数")
public class UpdateKnowledgeContentVO extends AddKnowledgeContentVO {
    @ApiModelProperty(value = "id", required = true)
    @NotNull(message = "ID不能为空")
    private Integer id;

    @ApiModelProperty(value = "createTime", required = true)
    @NotNull(message = "创建时间不能为空")
    private Date createTime;
}
