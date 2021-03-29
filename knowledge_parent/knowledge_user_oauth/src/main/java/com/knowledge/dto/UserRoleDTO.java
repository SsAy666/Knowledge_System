package com.knowledge.dto;

import lombok.Data;

@Data
public class UserRoleDTO {

    // 用户名
    private String name;

    // 角色ID
    private Integer roleId;

    // 用户ID
    private Integer userId;

    // 角色名
    private String roleName;

}

