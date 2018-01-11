package com.alphaz.core.pojo.dto;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.core.pojo.dto
 * User: C0dEr
 * Date: 2017/6/13
 * Time: 下午4:13
 * Description:This is a class of com.alphaz.core.pojo.dto
 */
public class UserRoleDTO {
    public Long userid;
    public String username;
    public Long roleid;
    public String rolename;
    public String password;

    public UserRoleDTO(Long userid, String username, Long roleid, String rolename, String password) {
        this.userid = userid;
        this.username = username;
        this.roleid = roleid;
        this.rolename = rolename;
        this.password = password;
    }

    public UserRoleDTO() {
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

    public Long getRoleid() {
        return roleid;
    }

    public void setRoleid(Long roleid) {
        this.roleid = roleid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
}
