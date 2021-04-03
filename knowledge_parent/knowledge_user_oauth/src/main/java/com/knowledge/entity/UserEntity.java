package com.knowledge.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *  用户实体类
 */
@Data
@TableName("tb_user")
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type= IdType.AUTO)
    private Integer id;

    // 用户名
    private String username;

    // 密码
    private String password;

    // 昵称
    private String nickname;

    // 头像
    private String avatar;

    // 简介
    private String intro;

    // 描述
    private String description;

    // 邮箱
    private String email;

    // 手机号
    private String phone;

    // 0:可用  1：逻辑删除
    private Integer deleted;

    // 创建时间
    private Date createTime;

    // 更新时间
    @TableField(update = "now()")
    private Date updateTime;
}
