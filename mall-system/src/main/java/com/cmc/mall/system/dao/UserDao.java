package com.cmc.mall.system.dao;

import com.cmc.mall.system.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 后台用户表
 * 
 * @author chengmingchao
 * @email 502130705@qq.com
 * @date 2020-08-17 21:05:52
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {
	
}
