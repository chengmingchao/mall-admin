package com.cmc.mall.module.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cmc.common.utils.PageUtils;
import com.cmc.common.utils.R;
import com.cmc.mall.module.system.entity.UserEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 后台用户表
 *
 * @author chengmingchao
 * @email 502130705@qq.com
 * @date 2020-08-17 21:05:52
 */
public interface UserService extends IService<UserEntity> {

    PageUtils queryPage(Map<String, Object> params);

    UserEntity getUserByName(String username);

    List<String> getUserPerms(Long userId);
}

