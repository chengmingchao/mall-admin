package com.cmc.mall.module.system.dao;

import com.cmc.mall.module.system.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 后台用户表
 * 
 * @author chengmingchao
 * @email 502130705@qq.com
 * @date 2020-08-17 21:05:52
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {

    UserEntity getUserByName(@Param("username") String username);

    List<String> getUserPerms(@Param("userId") Long userId);
}
