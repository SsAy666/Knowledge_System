package com.knowledge.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.knowledge.dao.UserDao;
import com.knowledge.dao.UserRoleDao;
import com.knowledge.entity.UserEntity;
import com.knowledge.entity.UserRoleEntity;
import com.knowledge.exception.RenException;
import com.knowledge.service.UserService;
import com.knowledge.vo.AddUserVO;
import com.knowledge.vo.RegisterUserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


/**
 * 用户业务接口实现类
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserEntity queryById(Integer id) {
        UserEntity userEntity = getById(id);
        return userEntity;
    }

    @Override
    public UserEntity queryByName(String name) {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", name);
        UserEntity userEntity = userDao.selectOne(queryWrapper);
        return userEntity;
    }

    @Override
    public void registerUser(RegisterUserVO registerUserVO) {
        // 查数据库看用户名是否存在
        UserEntity userDB = queryByName(registerUserVO.getName());
        if (userDB != null) {
            throw new RenException("注册失败，该用户名已存在！");
        }
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(registerUserVO,userEntity);
        // 加密密码
        String encode = bCryptPasswordEncoder.encode(registerUserVO.getPassword());
        userEntity.setPassword(encode);
        // 设置用户角色信息
        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setRoleId(registerUserVO.getRoleId());
        // 保存用户信息和用户角色信息
        if (!this.save(userEntity)) {
            throw new RenException("添加失败，请联系管理员！");
        }
        userRoleEntity.setUserId(userEntity.getId());
        if (userRoleDao.insert(userRoleEntity) == 0) {
            throw new RenException("注册失败，请联系管理员！");
        }
    }
}
