package com.alphaz.oauth.service.impl;

import com.alphaz.core.pojo.viewmodel.ResponseModel;
import com.alphaz.oauth.pojo.entity.OauthAccesstokenEntity;
import com.alphaz.oauth.pojo.entity.OauthRefreshtokenEntity;
import com.alphaz.oauth.pojo.entity.OauthSysuserEntity;
import com.alphaz.oauth.pojo.viewmodel.AuthTokenModel;
import com.alphaz.oauth.dao.OauthUserDAO;
import com.alphaz.oauth.dao.RefreshTokenDAO;
import com.alphaz.oauth.dao.TokenDAO;
import com.alphaz.oauth.service.AuthService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.service.impl
 * User: C0dEr
 * Date: 2017/6/15
 * Time: 下午4:07
 * Description:This is a class of com.alphaz.service.impl
 */
@Service
public class AuthServiceImpl implements AuthService {
    @Resource
    private OauthUserDAO oauthUserDAO;
    @Resource
    private TokenDAO tokenDAO;
    @Resource
    private RefreshTokenDAO refreshTokenDAO;

    @Value("${spring.alphaz.auth.accesstoken_expiresin:86400}")
    private Long accesstokenExpiredin;
    @Value("${spring.alphaz.auth.refreshtoken_expiresin:2592000}")
    private Long refreshtokenExpiredin;

    public OauthSysuserEntity getUser(String username, String password) {
        return oauthUserDAO.findByUsernameAndPassword(username, password);
    }

    @Override
    @Transactional
    @CachePut(key = "#username")
    public ResponseModel<AuthTokenModel> usernameAndPasswordAuth(String username, String password) {
        ResponseModel<AuthTokenModel> model = new ResponseModel<>();
        model.httpStatus = "401";
        OauthSysuserEntity user = this.getUser(username, password);
        if (user == null) {
            model.message = "用户未授权";
            return model;
        }
        OauthAccesstokenEntity token = this.tokenDAO.findByAuthid(user.getAuthid());
        OauthRefreshtokenEntity refresh = null;
        if (token == null) {
            token = new OauthAccesstokenEntity();
            refresh = new OauthRefreshtokenEntity();

            refresh.setAuthid(user.getAuthid());
            refresh.setExpiretime(new Timestamp(Date.from(Instant.now().plusSeconds(refreshtokenExpiredin)).getTime()));
            refresh.setGranttype("password");
            refresh.setRefreshtoken(UUID.randomUUID().toString());
            refreshTokenDAO.save(refresh);

            token.setAccesstoken(UUID.randomUUID().toString());
            token.setRefreshtokenid(refresh.getId());
            token.setAuthid(user.getAuthid());
            token.setTokentype("bearer");
            token.setExpiretime(new Timestamp(Date.from(Instant.now().plusSeconds(accesstokenExpiredin)).getTime()));
            tokenDAO.save(token);
        } else {
            refresh = this.refreshTokenDAO.findOne(token.getRefreshtokenid());
        }
        if (token.getExpiretime().before(new Timestamp(new java.util.Date().getTime()))) {
            token.setExpiretime(new Timestamp(Date.from(Instant.now().plusSeconds(accesstokenExpiredin)).getTime()));
            token.setAccesstoken(UUID.randomUUID().toString());
            tokenDAO.save(token);
        }
        if (refresh.getExpiretime().before(new Timestamp(new java.util.Date().getTime()))) {
            refresh.setExpiretime(new Timestamp(Date.from(Instant.now().plusSeconds(refreshtokenExpiredin)).getTime()));
            refreshTokenDAO.save(refresh);
        }
        AuthTokenModel tokenModel = new AuthTokenModel();
        tokenModel.accesstoken = token.getAccesstoken();
        token.setTokentype("bearer");
        tokenModel.accesstoken_expireuntil = new Date(token.getExpiretime().getTime());
        tokenModel.refreshtoken = refresh.getRefreshtoken();
        tokenModel.refreshtoken_expireuntil = new Date(refresh.getExpiretime().getTime());
        tokenModel.grantType = refresh.getGranttype();
        tokenModel.tokenType = token.getTokentype();
        model.data = tokenModel;
        model.message = "授权成功";
        model.httpStatus = "200";
        return model;
    }

    @Override
    public ResponseModel<AuthTokenModel> refresh(String grantType, String scope, String refreshToken) {
        ResponseModel<AuthTokenModel> model = new ResponseModel<>();
        model.httpStatus = "401";
        OauthSysuserEntity user = this.oauthUserDAO.findByRefreshToken(refreshToken);
        if (user == null) {
            model.message = "用户未授权";
            return model;
        }
        OauthRefreshtokenEntity refresh = this.refreshTokenDAO.findByRefreshtoken(refreshToken);
        if (refresh.getExpiretime().before(new Timestamp(new java.util.Date().getTime()))) {
            model.message = "授权过期";
            return model;
        }
        OauthAccesstokenEntity entity = refreshToken(user.getAuthid(), refresh.getId());
        AuthTokenModel tokenModel = new AuthTokenModel();
        tokenModel.accesstoken = entity.getAccesstoken();
        tokenModel.accesstoken_expireuntil = new Date(entity.getExpiretime().getTime());
        tokenModel.refreshtoken = refresh.getRefreshtoken();
        tokenModel.refreshtoken_expireuntil = new Date(refresh.getExpiretime().getTime());
        tokenModel.grantType = refresh.getGranttype();
        tokenModel.tokenType = entity.getTokentype();
        model.data = tokenModel;
        model.message = "刷新成功";
        model.httpStatus = "200";
        return model;
    }

    @Override
    public boolean validToken(String token) {
        return this.tokenDAO.findByAccesstokenAndExpiretimeGreaterThan(token, new Date()) != null;
    }

    @Override
    public OauthSysuserEntity getUserByAccesstoken(String accesstoken) {
        return this.oauthUserDAO.findByAccessToken(accesstoken);
    }

    public OauthAccesstokenEntity refreshToken(String authid, Long refreshTokenid) {
        OauthAccesstokenEntity refreshtokenEntity = this.tokenDAO.findByAuthidAndRefreshtokenid(authid, refreshTokenid);
        refreshtokenEntity.setAccesstoken(UUID.randomUUID().toString());
        tokenDAO.save(refreshtokenEntity);
        return refreshtokenEntity;
    }
}
