package com.alphaz.api.controller;


import com.alphaz.core.pojo.viewmodel.ResponseModel;
import com.alphaz.core.constant.DataState;
import com.alphaz.core.constant.SessionConstant;
import com.alphaz.core.service.impl.UserServiceImpl;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Base64;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.controller
 * UserEntity: C0dEr
 * Date: 2016-10-31
 * Time: 15:31
 * Description:
 */
@RestController
@RequestMapping("/")
@Api(value = "/", description = "用户相关")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    UserServiceImpl userService;
    @Autowired
    JavaMailSender mailSender;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private ApplicationContext context;
    @Autowired
    private RequestMappingHandlerMapping RequestMappings;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(notes = "用户登陆", httpMethod = "POST", value = "用户登陆", produces = "application/json", consumes = "application/json")
    public ResponseModel login(@RequestParam @ApiParam(required = true, example = "admin") String username
            , @RequestParam @ApiParam(required = true, example = "111111") String password,
                               HttpSession session,
                               @RequestParam(required = true) @ApiParam(required = true, example = "4r3e") String captcha,
                               @RequestParam(required = false) @ApiParam(required = false) boolean rmbPwd,
                               HttpServletResponse response,
                               HttpServletRequest request) {
        ResponseModel model = null;
        LocaleContextHolder.getLocale();
        logger.info(messageSource.getMessage("errorTips", new String[]{"a"}, LocaleContextHolder.getLocale()));


        session.setAttribute(SessionConstant.CURRENTUSER, model.data);
        if (model.state == DataState.Ava && rmbPwd) {
            Cookie cookie = new Cookie("a&p", new String(Base64.getEncoder().encode((username + "|%|#|" + password).getBytes())));
            cookie.setMaxAge(30 * 24 * 60 * 60);
            cookie.setPath("/login");
            response.addCookie(cookie);
        }
        if (model.state == DataState.Ava && !rmbPwd) {
            Cookie cookie = new Cookie("a&p", new String(Base64.getEncoder().encode(username.getBytes())));
            cookie.setMaxAge(30 * 24 * 60 * 60);
            cookie.setPath("/login");
            response.addCookie(cookie);
        }
        return model;
    }
}
