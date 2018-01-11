package com.alphaz.oauth.token;

import com.alphaz.oauth.constant.AuthType;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.pojo.dto
 * User: C0dEr
 * Date: 2017/6/15
 * Time: 下午4:36
 * Description:This is a class of com.alphaz.pojo.dto
 */
public class CustomToken extends UsernamePasswordToken {

    public AuthType authType;

    public CustomToken() {
    }

    public CustomToken(String username, String password) {
        super(username, password);
    }

    public AuthType getAuthType() {
        return authType;
    }

    public void setAuthType(AuthType authType) {
        this.authType = authType;
    }
}
