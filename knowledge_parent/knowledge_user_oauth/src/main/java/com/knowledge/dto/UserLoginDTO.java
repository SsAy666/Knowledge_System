package com.knowledge.dto;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class UserLoginDTO {

    // 用户名
    private String username;

    // 用户ID
    private Integer userId;

    // 角色名
    List<String> roles = new ArrayList<>();

    // token
    private String token;
}

