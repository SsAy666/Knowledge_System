package com.knowledge.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.knowledge.dao.RoleDao;
import com.knowledge.entity.RoleEntity;
import com.knowledge.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 角色业务接口实现类
 */
@Slf4j
@Service
public class RoleServiceImpl extends ServiceImpl<RoleDao, RoleEntity> implements RoleService {

    @Resource
    private RoleDao roleDao;

    @Override
    public RoleEntity queryById(Integer id) {
        RoleEntity roleEntity = getById(id);
        return roleEntity;
    }
}
