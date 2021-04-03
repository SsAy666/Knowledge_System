package com.knowledge.dao;

import com.knowledge.dto.UserRoleDTO;
import com.knowledge.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *  用户DAO类
 */
@Mapper
public interface UserDao extends BaseDao<UserEntity> {

    /**
     * 管理员查询用户
     * @param username 用户名
     * @return
     */
    List<UserRoleDTO> queryUser(@Param("username") String username);
}
