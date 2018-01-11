package com.alphaz.oauth.token;

import com.alphaz.oauth.token.CustomToken;
import com.alphaz.oauth.constant.AuthType;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.auth
 * User: C0dEr
 * Date: 2017/6/15
 * Time: 下午4:50
 * Description:This is a class of com.alphaz.auth
 */
public class UserToken extends CustomToken {

    public UserToken(String username, String password) {
        this.setUsername(username);
        this.setPassword(password.toCharArray());
        this.setAuthType(AuthType.Default);
    }
}
