package com.knowledge.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.knowledge.entity.UserEntity;
import com.knowledge.vo.RegisterUserVO;

/**
 * 用户业务接口
 */
public interface UserService extends IService<UserEntity> {
    /**
     * 根据ID查询用户
     * @param id 用户ID
     * @return
     */
    UserEntity queryById(Integer id);

    /**
     * 根据用户名查询用户
     * @param name 用户名
     * @return
     */
    UserEntity queryByName(String name);

    /**
     * 注册用户
     * @param registerUserVO 注册信息VO
     * @return
     */
    void registerUser(RegisterUserVO registerUserVO);
}
