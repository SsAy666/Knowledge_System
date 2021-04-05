package com.knowledge.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel("修改用户接口请求参数")
public class UpdateUserVO {

    @ApiModelProperty(value = "用户ID", required = true)
    @NotNull(message = "用户ID不能为空")
    private Integer userId;

    @ApiModelProperty(value = "人员名称", required = true)
    @NotNull(message = "人员名称不能为空")
    private String username;

    @ApiModelProperty(value = "角色ID", required = true)
    @NotNull(message = "角色ID不能为空")
    private Integer roleId;

}

