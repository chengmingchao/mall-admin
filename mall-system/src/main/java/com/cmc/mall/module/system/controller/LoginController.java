package com.cmc.mall.module.system.controller;

import com.cmc.common.utils.R;
import com.cmc.common.utils.ResultCode;
import com.cmc.mall.module.system.entity.UserEntity;
import com.cmc.mall.module.system.service.UserService;
import com.cmc.mall.module.system.service.UserTokenService;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chengmingchao
 * @version 1.0
 * @date 2020/8/27 8:56 下午
 * 登陆接口
 */
@RestController
@RequestMapping("system")
public class LoginController {
    //12小时后过期
    private final static int EXPIRE = 3600 * 12;
    @Autowired
    private UserService userService;

    @Autowired
    private UserTokenService userTokenService;
    /**
     * 登陆接口
     *
     * @param user
     * @return
     */
    @PostMapping("login")
    public R login(@RequestBody UserEntity user) {
        //用户信息
        UserEntity userEntity = userService.getUserByName(user.getUsername());

        //账号不存在、密码错误
        if (userEntity == null || !userEntity.getPassword().equals(new Sha256Hash(user.getPassword(), userEntity.getSalt()).toHex())) {
            return R.error("账号或密码不正确");
        }

        //账号锁定
        if (userEntity.getStatus() == 0) {
            return R.error("账号已被锁定,请联系管理员");
        }
        //生成token，并保存到数据库
        String token = userTokenService.createToken(user.getUserId(),EXPIRE);
        return R.ok().put("token", token).put("expire", EXPIRE);
    }
}
