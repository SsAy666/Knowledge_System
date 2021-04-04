package com.knowledge.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 用户角色接口响应参数
 */
@Data
@ApiModel("用户角色接口响应参数")
public class UserRoleDTO {

    // 用户名
    private String username;

    // 角色ID
    private Integer roleId;

    // 用户ID
    private Integer userId;

    // 角色名
    private String roleName;

}

