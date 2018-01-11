package com.alphaz.oauth.pojo.entity;


import com.alphaz.core.pojo.entity.BaseDO;
import javax.persistence.*;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.entity
 * User: C0dEr
 * Date: 2016/12/30
 * Time: 下午2:10
 * Description:This is a class of com.alphaz.entity
 */
@Entity
@Table(name = "oauth_sysuser", schema = "alphaz", catalog = "")
public class OauthSysuserEntity extends BaseDO {
    private String authid;//uuid唯一键，手动生成
    private String username;//用户名
    private String password;//密码
    private String role;//api角色
    private String scope;
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
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
    @Column(name = "role")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }








    @Basic
    @Column(name = "scope")
    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
