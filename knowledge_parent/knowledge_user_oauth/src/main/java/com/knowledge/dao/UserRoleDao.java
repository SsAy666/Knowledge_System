package com.knowledge.dao;

import com.knowledge.entity.UserRoleEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 *  用户-角色关联DAO类
 */
@Mapper
public interface UserRoleDao extends BaseDao<UserRoleEntity> {

}
