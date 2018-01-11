package com.alphaz.oauth.pojo.entity;

import com.alphaz.core.pojo.entity.BaseDO;

import javax.persistence.*;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.pojo.entity
 * User: C0dEr
 * Date: 2017/6/16
 * Time: 下午5:06
 * Description:This is a class of com.alphaz.pojo.entity
 */
@Entity
@Table(name = "oauth_clientdetail", schema = "alphaz", catalog = "")
public class OauthClientdetailEntity extends BaseDO {
    private String clientid;
    private String authid;
    private String scope;
    private String clientsecret;
    private String redirecturi;
    private String responsetype;



    @Basic
    @Column(name = "authid")
    public String getAuthid() {
        return authid;
    }

    public void setAuthid(String authid) {
        this.authid = authid;
    }
    @Basic
    @Column(name = "clientid")
    public String getClientid() {
        return clientid;
    }

    public void setClientid(String clientid) {
        this.clientid = clientid;
    }

    @Basic
    @Column(name = "scope")
    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    @Basic
    @Column(name = "clientsecret")
    public String getClientsecret() {
        return clientsecret;
    }

    public void setClientsecret(String clientsecret) {
        this.clientsecret = clientsecret;
    }



    @Basic
    @Column(name = "redirecturi")
    public String getRedirecturi() {
        return redirecturi;
    }

    public void setRedirecturi(String redirecturi) {
        this.redirecturi = redirecturi;
    }

    @Basic
    @Column(name = "responsetype")
    public String getResponsetype() {
        return responsetype;
    }

    public void setResponsetype(String responsetype) {
        this.responsetype = responsetype;
    }


}
