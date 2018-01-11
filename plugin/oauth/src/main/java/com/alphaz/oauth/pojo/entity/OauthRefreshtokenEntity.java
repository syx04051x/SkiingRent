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
@Table(name = "oauth_refreshtoken", schema = "alphaz", catalog = "")
public class OauthRefreshtokenEntity extends BaseDO {
    private String authid;
    private String refreshtoken;
    private Timestamp expiretime;
    private String granttype;



    @Basic
    @Column(name = "authid")
    public String getAuthid() {
        return authid;
    }

    public void setAuthid(String authid) {
        this.authid = authid;
    }


    @Basic
    @Column(name = "granttype")
    public String getGranttype() {
        return granttype;
    }

    public void setGranttype(String granttype) {
        this.granttype = granttype;
    }

    @Basic
    @Column(name = "refreshtoken")
    public String getRefreshtoken() {
        return refreshtoken;
    }

    public void setRefreshtoken(String refreshtoken) {
        this.refreshtoken = refreshtoken;
    }


    @Basic
    @Column(name = "expiretime")
    public Timestamp getExpiretime() {
        return expiretime;
    }

    public void setExpiretime(Timestamp expiretime) {
        this.expiretime = expiretime;
    }


}
