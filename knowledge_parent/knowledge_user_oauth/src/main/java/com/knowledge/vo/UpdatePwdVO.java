package com.knowledge.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 用户修改密码接口请求参数
 */
@Data
@ApiModel("用户修改密码接口请求参数")
public class UpdatePwdVO {
    @ApiModelProperty(value = "用户ID", required = true)
    @NotNull(message = "用户ID不能为空")
    private Integer id;

    @ApiModelProperty(value = "用户名", required = true)
    @NotNull(message = "用户名不能为空")
    private String username;

    @ApiModelProperty(value = "原始密码", required = true)
    @NotNull(message = "原始密码不能为空")
    private String oldPassword;

    @ApiModelProperty(value = "新密码", required = true)
    @NotNull(message = "新密码不能为空")
    private String newPassword;
}
