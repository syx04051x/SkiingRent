package com.alphaz.oauth.auth;

import com.alphaz.oauth.pojo.entity.OauthSysuserEntity;
import com.alphaz.oauth.service.AuthService;
import com.alphaz.oauth.token.Auth2Token;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.config.auth
 * User: C0dEr
 * Date: 2017/6/14
 * Time: 上午10:49
 * Description:This is a class of com.alphaz.config.auth
 */
@Component
public class OAuthRealm extends AuthorizingRealm {

    @Autowired
    private AuthService authService;

    /**
     * 获取授权信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        return authorizationInfo;
    }

    /**
     *
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        Auth2Token token = (Auth2Token) authenticationToken;
        OauthSysuserEntity oauthSysuserEntity = this.authService.getUserByAccesstoken(token.accesstoken);
        if (oauthSysuserEntity == null) {
            return null;
        }
        return new SimpleAuthenticationInfo(oauthSysuserEntity.getUsername(), oauthSysuserEntity.getPassword(), getName());
    }

}