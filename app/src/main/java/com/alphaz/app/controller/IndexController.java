package com.alphaz.app.controller;

import com.alphaz.core.pojo.viewmodel.ResponseModel;
import com.alphaz.core.pojo.viewmodel.user.UserViewModel;
import com.alphaz.core.constant.MenuConst;
import com.alphaz.core.constant.SessionConstant;
import com.alphaz.core.service.PrivilegeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Base64;

/**
 * Created by C0dEr on 2016/12/3.
 */
@Controller
public class IndexController {

    @Resource
    private PrivilegeService privilegeService;

    @GetMapping("")
    public String index(HttpSession session) {
        if (null == session.getAttribute(SessionConstant.CURRENTUSER)) {
            return "redirect:login";
        } else {
            return "redirect:dashboard";
        }
    }

    @GetMapping(value = "login")
    public ModelAndView login(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        ModelAndView mav = new ModelAndView("login");
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("a&p")) {
                    String deap = new String(Base64.getDecoder().decode(c.getValue()));
                    String[] ap = deap.split("\\|%\\|#\\|");
                    mav.addObject("account", ap.length >= 1 ? ap[0] : "");
                    mav.addObject("password", ap.length >= 2 ? ap[1] : "");
                    mav.addObject("rmb", ap.length >= 2);
                    break;
                }
            }
        }
        return mav;
    }

    @GetMapping("sidebarmenu")
    @ResponseBody
    public ResponseModel sidebarmenu(@SessionAttribute(SessionConstant.CURRENTUSER) UserViewModel userViewModel) {
        return privilegeService.getMenuByUserId(userViewModel.getUserid());
    }

    @GetMapping("logout")
    public String logout(HttpSession session) {
        session.removeAttribute(SessionConstant.CURRENTUSER);
        return "redirect:login";
    }

    @GetMapping("dashboard")
    public ModelAndView dashboard() {
        ModelAndView mav = new ModelAndView("dashboard");
        mav.addObject("menu", MenuConst.DASHBOARD);
        return mav;
    }

    @GetMapping("privilege")
    public ModelAndView privilege() {
        ModelAndView mav = new ModelAndView("privilege/privilege");
        mav.addObject("menu", MenuConst.AUTH);
        return mav;
    }

    @GetMapping(value = "customerlist")
    public ModelAndView customerList() {
        ModelAndView mav = new ModelAndView("customer/customerlist");
        mav.addObject("menu", MenuConst.CUSTOMER);
        return mav;
    }

    @GetMapping(value = "userlist")
    public ModelAndView userList() {
        ModelAndView mav = new ModelAndView("user/userlist");
        mav.addObject("menu", MenuConst.USER);
        return mav;
    }

    @GetMapping("productlist")
    public ModelAndView productlist() {
        ModelAndView mav = new ModelAndView("product/productlist");
        mav.addObject("menu", MenuConst.PRODUCT);
        return mav;
    }

}
