package com.cmc.mall.module.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cmc.common.utils.Query;
import com.cmc.common.utils.R;
import com.cmc.mall.module.system.dao.UserTokenDao;
import com.cmc.mall.module.system.entity.UserTokenEntity;
import com.cmc.mall.module.system.oauth2.TokenGenerator;
import com.cmc.mall.module.system.service.UserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import com.cmc.common.utils.PageUtils;



@Service("userTokenService")
public class UserTokenServiceImpl extends ServiceImpl<UserTokenDao, UserTokenEntity> implements UserTokenService {


    @Autowired
    private UserTokenDao userTokenDao;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UserTokenEntity> page = this.page(
                new Query<UserTokenEntity>().getPage(params),
                new QueryWrapper<UserTokenEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public UserTokenEntity getBytoken(String token) {
        return userTokenDao.getBytoken(token);
    }


    @Override
    public String createToken(Long userId,int EXPIRE) {
        //生成一个token
        String token = TokenGenerator.generateValue();

        //当前时间
        Date now = new Date();
        //过期时间
        Date expireTime = new Date(now.getTime() + EXPIRE * 1000);

        //判断是否生成过token
        UserTokenEntity tokenEntity = this.getById(userId);
        if (tokenEntity == null) {
            tokenEntity = new UserTokenEntity();
            tokenEntity.setUserId(userId);
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);

            //保存token
            this.save(tokenEntity);
        } else {
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);

            //更新token
            this.updateById(tokenEntity);
        }

        return token;
    }
}