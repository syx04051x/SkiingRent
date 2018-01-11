package com.alphaz.oauth.pojo.entity;

import com.alphaz.core.pojo.entity.BaseDO;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.pojo.entity
 * User: C0dEr
 * Date: 2017/6/16
 * Time: 下午5:06
 * Description:This is a class of com.alphaz.pojo.entity
 */
@Entity
@Table(name = "oauth_accesstoken", schema = "alphaz", catalog = "")
public class OauthAccesstokenEntity extends BaseDO {
    private Long refreshtokenid;
    private String accesstoken;
    private Timestamp expiretime;
    private String authid;
    private String tokentype;



    @Basic
    @Column(name = "authid")
    public String getAuthid() {
        return authid;
    }

    public void setAuthid(String authid) {
        this.authid = authid;
    }
    @Basic
    @Column(name = "tokentype")
    public String getTokentype() {
        return tokentype;
    }

    public void setTokentype(String tokentype) {
        this.tokentype = tokentype;
    }

    @Basic
    @Column(name = "accesstoken")
    public String getAccesstoken() {
        return accesstoken;
    }

    public void setAccesstoken(String accesstoken) {
        this.accesstoken = accesstoken;
    }


    @Basic
    @Column(name = "expiretime")
    public Timestamp getExpiretime() {
        return expiretime;
    }

    public void setExpiretime(Timestamp expiretime) {
        this.expiretime = expiretime;
    }

    @Basic
    @Column(name = "refreshtokenid")
    public Long getRefreshtokenid() {
        return refreshtokenid;
    }

    public void setRefreshtokenid(Long refreshtokenid) {
        this.refreshtokenid = refreshtokenid;
    }


}
