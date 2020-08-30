package com.cmc.mall.module.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cmc.common.utils.PageUtils;
import com.cmc.common.utils.R;
import com.cmc.mall.module.system.entity.UserTokenEntity;

import java.util.Map;

/**
 * 系统用户Token
 *
 * @author chengmingchao
 * @email 502130705@qq.com
 * @date 2020-08-27 22:27:55
 */
public interface UserTokenService extends IService<UserTokenEntity> {

    PageUtils queryPage(Map<String, Object> params);

    UserTokenEntity getBytoken(String token);

    String createToken(Long userId,int e);
}

