package com.alphaz.oauth.service;

import com.alphaz.oauth.pojo.entity.OauthSysuserEntity;
import com.alphaz.core.pojo.viewmodel.ResponseModel;
import com.alphaz.oauth.pojo.viewmodel.AuthTokenModel;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.service
 * User: C0dEr
 * Date: 2017/6/15
 * Time: 下午4:06
 * Description:This is a class of com.alphaz.service
 */
public interface AuthService {

    ResponseModel<AuthTokenModel> usernameAndPasswordAuth(String username, String password);

    ResponseModel<AuthTokenModel> refresh(String grantType, String scope, String refreshToken);

    boolean validToken(String token);

    OauthSysuserEntity getUserByAccesstoken(String accesstoken);
}
