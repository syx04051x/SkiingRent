package com.alphaz.oauth.token;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.token
 * User: C0dEr
 * Date: 2017/6/19
 * Time: 下午3:11
 * Description:This is a class of com.alphaz.token
 */
public class Auth2Token extends CustomToken {
    public String accesstoken;

    public String getAccesstoken() {
        return accesstoken;
    }

    public void setAccesstoken(String accesstoken) {
        this.accesstoken = accesstoken;
    }
}
