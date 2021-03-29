package com.knowledge.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 注册参数实体
 */
@Data
public class RegisterUserVO {

    @NotNull(message = "人员名称不能为空")
    private String name;

    @NotNull(message = "密码不能为空")
    private String password;

    @NotNull(message = "角色ID不能为空")
    private Integer roleId;

}
