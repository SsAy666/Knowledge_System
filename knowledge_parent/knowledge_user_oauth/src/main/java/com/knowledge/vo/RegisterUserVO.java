package com.knowledge.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 注册用户接口请求参数
 */
@Data
@ApiModel("注册用户接口请求参数")
public class RegisterUserVO {

    @ApiModelProperty(value = "人员名称", required = true)
    @NotNull(message = "人员名称不能为空")
    private String username;

    @ApiModelProperty(value = "密码", required = true)
    @NotNull(message = "密码不能为空")
    private String password;

    @ApiModelProperty(value = "角色ID", required = true)
    @NotNull(message = "角色ID不能为空")
    private Integer roleId;

}
