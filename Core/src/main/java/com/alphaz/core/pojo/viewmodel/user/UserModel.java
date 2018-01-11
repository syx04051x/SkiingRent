package com.alphaz.core.pojo.viewmodel.user;


import javax.validation.constraints.NotNull;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.core.pojo.viewmodel.user
 * User: C0dEr
 * Date: 2017/7/21
 * Time: 上午11:05
 * Description:This is a class of com.alphaz.core.pojo.viewmodel.user
 */
public class UserModel {
    @NotNull(message = "用户名不能为空")
    public String username;
    @NotNull(message = "密码不能为空")
    public String password;
    @NotNull(message = "手机不能为空")
    public String phone;

    public Long teamid;

    public Long getTeamid() {
        return teamid;
    }

    public void setTeamid(Long teamid) {
        this.teamid = teamid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
