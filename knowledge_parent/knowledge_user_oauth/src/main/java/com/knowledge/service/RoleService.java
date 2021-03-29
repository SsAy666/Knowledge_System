package com.knowledge.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.knowledge.entity.RoleEntity;

/**
 * 角色业务接口
 */
public interface RoleService extends IService<RoleEntity> {
    /**
     * 根据ID查询角色
     * @param id 角色ID
     * @return
     */
    RoleEntity queryById(Integer id);
}
