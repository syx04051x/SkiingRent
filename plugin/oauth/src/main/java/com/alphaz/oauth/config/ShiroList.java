package com.alphaz.oauth.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.config
 * User: C0dEr
 * Date: 2017/6/19
 * Time: 下午3:52
 * Description:This is a class of com.alphaz.config
 */
@ConfigurationProperties(prefix = "spring.alphaz.auth.shiro")
public class ShiroList {

    private List<String> auth = new ArrayList<>();

    public List<String> getAuth() {
        return auth;
    }

    public void setAuth(List<String> auth) {
        this.auth = auth;
    }
}
