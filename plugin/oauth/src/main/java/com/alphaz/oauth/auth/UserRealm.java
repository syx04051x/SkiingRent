package com.alphaz.oauth.auth;

import com.alphaz.core.pojo.viewmodel.user.UserViewModel;
import com.alphaz.core.pojo.viewmodel.privilege.MenuOperationModel;
import com.alphaz.core.service.PrivilegeService;
import com.alphaz.util.valid.ValideHelper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.config.auth
 * User: C0dEr
 * Date: 2017/6/14
 * Time: 上午10:49
 * Description:This is a class of com.alphaz.config.auth
 */
@Component
public class UserRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(UserRealm.class);

    @Autowired
    private PrivilegeService privilegeService;

    /**
     * 获取授权信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String currentLoginName = (String) principals.getPrimaryPrincipal();
        Set<String> userRoles = new HashSet<>();
        Set<String> userPermissions = new HashSet<>();
        UserViewModel userInfo = privilegeService.getUserRole(currentLoginName);
        if (null != userInfo && !ValideHelper.isNullOrEmpty(userInfo.getRoles())) {
            userRoles.addAll(userInfo.getRoles().values());
            MenuOperationModel ro = privilegeService.getMenuOperationByUserid(userInfo.getUserid());
            userPermissions.addAll(ro.getNamePair().entrySet().stream().map(a -> a.getKey() + ":" + a.getValue().stream().collect(Collectors.joining(","))).collect(Collectors.toList()));
        } else {
            throw new AuthorizationException();
        }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRoles(userRoles);
        authorizationInfo.addStringPermissions(userPermissions);
        logger.info("###[SessionId] => {}", SecurityUtils.getSubject().getSession().getId());
        return authorizationInfo;
    }

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        UserViewModel user = privilegeService.getUserRole(token.getUsername());
        if (user != null) {
            return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
        }
        return null;
    }

}