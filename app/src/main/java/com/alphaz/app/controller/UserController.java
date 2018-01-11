package com.alphaz.app.controller;


import com.alphaz.core.pojo.viewmodel.DataTableModel;
import com.alphaz.core.pojo.viewmodel.ResponseModel;
import com.alphaz.core.pojo.viewmodel.user.UserModel;
import com.alphaz.core.pojo.viewmodel.user.UserSearchModel;
import com.alphaz.core.pojo.viewmodel.user.UserUpdateModel;
import com.alphaz.core.pojo.viewmodel.user.UserViewModel;
import com.alphaz.core.constant.SessionConstant;
import com.alphaz.core.constant.DataState;
import com.alphaz.core.service.PrivilegeService;
import com.alphaz.core.service.UserService;
import com.alphaz.oauth.token.UserToken;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * Created by C0dEr on 2016/12/3.
 */
@RequestMapping("/user")
@RestController
public class UserController {
    @Resource
    UserService userService;
    @Resource
    PrivilegeService privilegeService;

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseModel login(String name, String password, Boolean remember, HttpServletResponse response) {
        if (remember) {
            String namepassword = name.concat("|%|#|").concat(password);
            String namepassword64 = null;
            try {
                namepassword64 = Base64.getEncoder().encodeToString(namepassword.getBytes("utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            Cookie cookie = new Cookie("a&p", namepassword64);
            cookie.setPath("/");
            cookie.setMaxAge(60 * 60 * 24 * 30);
            response.addCookie(cookie);
        }
        if (!remember) {
            Cookie cookie = new Cookie("a&p", null);
            cookie.setPath("/");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
        ResponseModel<UserViewModel> model = userService.login(name, password);
        if (model.state == DataState.Ava) {
            UserToken token = new UserToken(model.data.getUsername(), model.data.getPassword());
            SecurityUtils.getSubject().login(token);
            SecurityUtils.getSubject().getSession().setAttribute(SessionConstant.CURRENTUSER, model.data);
        }
        return model;
    }


    @GetMapping("/{username}/role")
    public ResponseModel getRoleByUser(@PathVariable String username) {
        return this.privilegeService.getUserRoleByUsername(username);
    }

    @GetMapping("/{id}/menu/op")
    public ResponseModel getMOByUser(@PathVariable Long id) {
        return this.privilegeService.getMOByUserid(id);
    }

    @PostMapping("/new")
    public ResponseModel addUser(@Valid UserModel model) {
        return userService.addUser(model);
    }
    @PostMapping("/forgetpassword")
    public ResponseModel forgetPassword(String username,String password){
        return userService.changePassword(username,password);
    }

    @PatchMapping("/{userid}")
    public ResponseModel updateUser(@PathVariable Long userid, @Valid UserUpdateModel model) {
        return userService.updateUser(userid, model);
    }

    @DeleteMapping("/{userid}")
    public ResponseModel deleteUser(@PathVariable Long userid) {
        return this.userService.deleteUser(userid);
    }

//    @GetMapping("/search")
//    public DataTableModel findUser(@Valid UserSearchModel model) {
//        return this.userService.findUser(model);
//    }
}