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
@Table(name = "oauth_code", schema = "alphaz", catalog = "")
public class OauthCodeEntity extends BaseDO {
    private String clientid;
    private String code;
    private Timestamp expiretime;



    @Basic
    @Column(name = "clientid")
    public String getClientid() {
        return clientid;
    }

    public void setClientid(String clientid) {
        this.clientid = clientid;
    }

    @Basic
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
