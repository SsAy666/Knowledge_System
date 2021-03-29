package com.knowledge.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 *  用户-角色关联实体类
 */
@Data
@TableName("tb_user_role")
public class UserRoleEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type= IdType.AUTO)
    private Integer id;

    // 用户ID
    private Integer userId;

    // 角色ID
    private Integer roleId;
}