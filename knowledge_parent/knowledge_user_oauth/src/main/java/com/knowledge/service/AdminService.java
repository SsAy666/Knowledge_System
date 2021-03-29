package com.knowledge.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.knowledge.dto.UserRoleDTO;
import com.knowledge.entity.UserEntity;
import com.knowledge.vo.AddUserVO;

import java.util.List;


/**
 * 管理员业务接口
 */
public interface AdminService extends IService<UserEntity> {
    /**
     * 管理员新增用户
     * @param addUserVO 添加用户参数信息VO
     * @return
     */
    void addUser(AddUserVO addUserVO);

    /**
     * 管理员查询用户
     * @param userEntity 用户参数信息
     * @return
     */
    List<UserRoleDTO> queryUser(UserEntity userEntity);
}
