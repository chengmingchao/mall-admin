package com.cmc.mall.module.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cmc.common.utils.PageUtils;
import com.cmc.mall.module.system.entity.UserLoginLogEntity;

import java.util.Map;

/**
 * 后台用户登录日志表
 *
 * @author chengmingchao
 * @email 502130705@qq.com
 * @date 2020-08-17 21:05:52
 */
public interface UserLoginLogService extends IService<UserLoginLogEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

