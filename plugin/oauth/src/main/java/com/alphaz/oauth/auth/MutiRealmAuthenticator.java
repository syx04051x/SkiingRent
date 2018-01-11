package com.alphaz.oauth.auth;

import com.alphaz.oauth.token.CustomToken;
import com.alphaz.oauth.constant.AuthType;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.util.Assert;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.config.auth
 * User: C0dEr
 * Date: 2017/6/15
 * Time: 下午4:30
 * Description:This is a class of com.alphaz.config.auth
 */
@Component
public class MutiRealmAuthenticator extends ModularRealmAuthenticator {
    private Map<AuthType, Realm> definedRealms;

    /**
     * 多个realm实现
     */
    @Override
    protected AuthenticationInfo doMultiRealmAuthentication(
            Collection<Realm> realms, AuthenticationToken token) {
        return super.doMultiRealmAuthentication(realms, token);
    }

    /**
     * 调用单个realm执行操作
     */
    @Override
    protected AuthenticationInfo doSingleRealmAuthentication(Realm realm,
                                                             AuthenticationToken token) {

        if (!realm.supports(token)) {
            throw new ShiroException("token错误!");
        }
        AuthenticationInfo info = null;
        try {
            info = realm.getAuthenticationInfo(token);

            if (info == null) {
                throw new ShiroException("token不存在!");
            }
        } catch (Exception e) {
            throw new ShiroException("用户名或者密码错误!");
        }
        return info;
    }


    @Override
    protected AuthenticationInfo doAuthenticate(
            AuthenticationToken authenticationToken)
            throws AuthenticationException {
        this.assertRealmsConfigured();

        Realm realm = null;

        CustomToken token = (CustomToken) authenticationToken;
        realm = (Realm) this.definedRealms.get(token.authType);
        Assert.notNull(realm);

        return this.doSingleRealmAuthentication(realm, authenticationToken);
    }

    /**
     * 判断realm是否为空
     */
    @Override
    protected void assertRealmsConfigured() throws IllegalStateException {
        this.definedRealms = this.getDefinedRealms();
        if (CollectionUtils.isEmpty(this.definedRealms)) {
            throw new ShiroException("值传递错误!");
        }
    }

    public Map<AuthType, Realm> getDefinedRealms() {
        return this.definedRealms;
    }

    public void setDefinedRealms(Map<AuthType, Realm> definedRealms) {
        this.definedRealms = definedRealms;
    }

}
