package com.alphaz.core.pojo.viewmodel.user;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.core.viewmodel
 * User: C0dEr
 * Date: 2017/1/3
 * Time: 下午2:41
 * Description:This is a class of com.alphaz.core.viewmodel
 */
public class UserViewModel implements Serializable {
    private Long userid;
    private String username;
    private Long avatar;
    private Map<Long, String> roles;
    @JsonIgnore
    private String password;
    private Date registertime;
    private String phone;
    private Date birthday;

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getRegistertime() {
        return registertime;
    }

    public void setRegistertime(Date registertime) {
        this.registertime = registertime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    private Map<String, List<String>> auth;

    public Map<String, List<String>> getAuth() {
        return auth;
    }

    public void setAuth(Map<String, List<String>> auth) {
        this.auth = auth;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getAvatar() {
        return avatar;
    }

    public void setAvatar(Long avatar) {
        this.avatar = avatar;
    }

    public Map<Long, String> getRoles() {
        return roles;
    }

    public void setRoles(Map<Long, String> roles) {
        this.roles = roles;
    }
}
