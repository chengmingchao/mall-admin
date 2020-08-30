package com.cmc.mall.module.system.service.impl;

import com.cmc.common.utils.R;
import com.cmc.mall.module.system.entity.UserTokenEntity;
import com.cmc.mall.module.system.oauth2.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cmc.common.utils.PageUtils;
import com.cmc.common.utils.Query;

import com.cmc.mall.module.system.dao.UserDao;
import com.cmc.mall.module.system.entity.UserEntity;
import com.cmc.mall.module.system.service.UserService;


@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UserEntity> page = this.page(
                new Query<UserEntity>().getPage(params),
                new QueryWrapper<UserEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public UserEntity getUserByName(String username) {
        return userDao.getUserByName(username);
    }

    @Override
    public List<String> getUserPerms(Long userId) {
        return userDao.getUserPerms(userId);
    }


}