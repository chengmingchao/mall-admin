package com.cmc.mall.module.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cmc.mall.module.system.entity.UserTokenEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统用户Token
 * 
 * @author chengmingchao
 * @email 502130705@qq.com
 * @date 2020-08-27 22:27:55
 */
@Mapper
public interface UserTokenDao extends BaseMapper<UserTokenEntity> {

    UserTokenEntity getBytoken(String token);
}
