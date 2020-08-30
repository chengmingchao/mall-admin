package com.cmc.mall.utils;

import com.cmc.mall.module.system.entity.UserEntity;
import org.apache.shiro.SecurityUtils;

/**
 * @author chengmingchao
 * @version 1.0
 * @date 2020/8/27 11:30 下午
 */
public class UserUtil {

    public static UserEntity getuser(){
        UserEntity user = (UserEntity) SecurityUtils.getSubject().getPrincipal();
        return user;
    }
}
