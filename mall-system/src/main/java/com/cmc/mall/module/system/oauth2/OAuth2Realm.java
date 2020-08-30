package com.cmc.mall.module.system.oauth2;

import com.cmc.common.utils.RRException;
import com.cmc.mall.module.system.entity.UserEntity;
import com.cmc.mall.module.system.entity.UserTokenEntity;
import com.cmc.mall.module.system.service.UserService;
import com.cmc.mall.module.system.service.UserTokenService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author chengmingchao
 * @version 1.0
 * @date 2020/8/27 8:58 下午
 */
public class OAuth2Realm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private UserTokenService userTokenService;
    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String accessToken = (String) authenticationToken.getPrincipal();
        UserTokenEntity tokenEntity = userTokenService.getBytoken(accessToken);
        if (tokenEntity==null){
            throw new RRException("token失效");//token失效
        }
        UsernamePasswordToken token= (UsernamePasswordToken) authenticationToken;
        UserEntity user = userService.getUserByName(token.getUsername());
        if (user==null){
            throw new UnknownAccountException();//账户不存在
        }
        if (user.getStatus()==0){
            throw new LockedAccountException();//账户锁定
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo=new SimpleAuthenticationInfo(user,user.getPassword(),getName());
        return simpleAuthenticationInfo;
    }
}
