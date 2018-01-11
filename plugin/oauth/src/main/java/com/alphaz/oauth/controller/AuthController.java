package com.alphaz.oauth.controller;


import com.alphaz.core.pojo.viewmodel.ResponseModel;
import com.alphaz.oauth.pojo.viewmodel.AuthTokenModel;
import com.alphaz.oauth.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.controller
 * UserEntity: C0dEr
 * Date: 2016-10-31
 * Time: 15:31
 * Description:
 */
@RestController
@RequestMapping("/auth")
@Api(value = "/auth", description = "OAuth")
public class AuthController {
    @Resource
    AuthService authService;

    @GetMapping("")
    @ApiOperation(value = "获取accesstoken", produces = "application/json")
    public ResponseModel<AuthTokenModel> auth(@RequestParam @ApiParam(required = true, example = "your username", value = "用户名") String username
            , @RequestParam @ApiParam(required = true, example = "your password", value = "密码") String password,
                                              @RequestParam @ApiParam(required = true, example = "password", value = "授权类型") String grantType,
                                              @RequestParam(required = false) @ApiParam(required = false, value = "权限范围") String scope) {
        return authService.usernameAndPasswordAuth(username, password);
    }

    @GetMapping("refresh")
    @ApiOperation(value = "刷新accesstoken", produces = "application/json")
    public ResponseModel<AuthTokenModel> refresh(@RequestParam @ApiParam(required = true, example = "password", value = "授权类型") String grantType,
                                                 @RequestParam @ApiParam(required = true, example = "password", value = "更新令牌") String refreshToken,
                                                 @RequestParam @ApiParam(required = true, value = "权限范围") String scope) {
        return authService.refresh(grantType, scope, refreshToken);
    }
}
