package com.knowledge.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.knowledge.dao.UserDao;
import com.knowledge.dao.UserRoleDao;
import com.knowledge.dto.UserRoleDTO;
import com.knowledge.entity.UserEntity;
import com.knowledge.entity.UserRoleEntity;
import com.knowledge.exception.RenException;
import com.knowledge.service.AdminService;
import com.knowledge.service.UserService;
import com.knowledge.vo.AddUserVO;
import com.knowledge.vo.UpdateUserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 管理员业务接口实现类
 */
@Slf4j
@Service
public class AdminServiceImpl extends ServiceImpl<UserDao, UserEntity> implements AdminService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 管理员新增用户
     * @param addUserVO 添加用户参数信息VO
     * @return
     */
    @Override
    public void addUser(AddUserVO addUserVO) {
        // 查数据库看用户名是否存在
        UserEntity userDB = userService.queryByName(addUserVO.getUsername());
        if (userDB != null) {
            throw new RenException("该用户名已存在，请重新输入！");
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setCreateTime(new Date());
        BeanUtils.copyProperties(addUserVO,userEntity);
        // 设置默认加密密码为：123456
        String encode = bCryptPasswordEncoder.encode("123456");
        userEntity.setPassword(encode);
        // 设置用户角色信息
        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setRoleId(addUserVO.getRoleId());
        // 保存用户信息和用户角色信息
        if (!this.save(userEntity)) {
            log.info("信息插入用户表失败");
            throw new RenException("添加失败！");
        }
        userRoleEntity.setUserId(userEntity.getId());
        if (userRoleDao.insert(userRoleEntity) == 0) {
            log.info("信息插入用户角色表失败");
            throw new RenException("添加失败！");
        }
    }

    /**
     * 管理员查询用户
     * @param userEntity 用户参数信息
     * @return
     */
    @Override
    public List<UserRoleDTO> queryUser(UserEntity userEntity) {
        List<UserRoleDTO> userRoleDTOS = userDao.queryUser(userEntity.getUsername());
        return userRoleDTOS;
    }

    /**
     * 管理员删除用户
     * @param userId 用户ID
     * @return
     */
    @Override
    public void delUser(Integer userId) {
        // 删除用户表中该用户的信息
        userDao.deleteById(userId);
        // 删除用户-角色表中该用户的信息
        QueryWrapper<UserRoleEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        userRoleDao.delete(wrapper);
    }

    /**
     * 管理员修改用户
     * @param updateUserVO 修改用户参数信息VO
     * @return
     */
    @Override
    public void updateUser(UpdateUserVO updateUserVO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(updateUserVO.getUserId());
        userEntity.setUsername(updateUserVO.getUsername());
        // 设置用户角色信息
        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setRoleId(updateUserVO.getRoleId());
        // 保存用户信息和用户角色信息
        if (!this.updateById(userEntity)) {
            log.info("更新用户表信息失败");
            throw new RenException("更新失败！");
        }
        UpdateWrapper<UserRoleEntity> wrapper = new UpdateWrapper<>();
        wrapper.eq("user_id",userEntity.getId());
        wrapper.set("role_id",updateUserVO.getRoleId());
        if (userRoleDao.update(userRoleEntity,wrapper) == 0) {
            log.info("更新用户角色表信息失败");
            throw new RenException("更新失败！");
        }
    }
}
