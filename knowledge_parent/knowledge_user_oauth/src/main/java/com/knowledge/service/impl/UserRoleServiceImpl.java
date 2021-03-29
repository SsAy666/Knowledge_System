package com.knowledge.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.knowledge.dao.UserRoleDao;
import com.knowledge.entity.UserRoleEntity;
import com.knowledge.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户-角色关联业务接口实现类
 */
@Slf4j
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleDao, UserRoleEntity> implements UserRoleService {

    @Resource
    private UserRoleDao userRoleDao;

    @Override
    public List<UserRoleEntity> listByUserId(Integer userId) {
        QueryWrapper<UserRoleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        List<UserRoleEntity> sysUserRoleEntities = userRoleDao.selectList(queryWrapper);
        return sysUserRoleEntities;
    }
}
