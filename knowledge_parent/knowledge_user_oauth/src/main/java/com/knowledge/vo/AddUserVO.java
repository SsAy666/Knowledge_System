package com.knowledge.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 添加用户参数实体
 */
@Data
public class AddUserVO {

    @NotNull(message = "人员名称不能为空")
    private String username;

    @NotNull(message = "角色ID不能为空")
    private Integer roleId;

}
