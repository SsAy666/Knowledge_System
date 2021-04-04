package com.knowledge.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户登录接口响应参数
 */
@Data
@ApiModel("用户登录接口响应参数")
public class UserLoginDTO {

    // 用户名
    @ApiModelProperty(value = "用户名")
    private String username;

    // 用户ID
    private Integer userId;

    // 角色名
    List<String> roles = new ArrayList<>();

    // token
    private String token;
}

