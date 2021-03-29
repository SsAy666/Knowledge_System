package com.knowledge.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.knowledge.entity.UserRoleEntity;

import java.util.List;

/**
 * 用户-角色关联业务接口
 */
public interface UserRoleService extends IService<UserRoleEntity> {
    /**
     * 根据用户ID查询用户权限
     * @param userId 用户ID
     * @return
     */
    List<UserRoleEntity> listByUserId(Integer userId);
}
