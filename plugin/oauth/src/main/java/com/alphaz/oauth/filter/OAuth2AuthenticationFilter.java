package com.alphaz.oauth.filter;

import com.alphaz.oauth.constant.AuthType;
import com.alphaz.core.pojo.viewmodel.ResponseModel;
import com.alphaz.oauth.service.AuthService;
import com.alphaz.oauth.token.Auth2Token;
import com.alphaz.util.valid.ValideHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.http.HttpStatus;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz
 * User: C0dEr
 * Date: 2017/6/19
 * Time: 下午1:09
 * Description:This is a class of com.alphaz
 */
public class OAuth2AuthenticationFilter extends AuthenticatingFilter {
    //username password mode
    private final static String authorization = "Authorization";
    private final static String authURL = "/auth/**";

    @Resource
    AuthService authService;


    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
        Auth2Token token = new Auth2Token();
        token.authType = AuthType.OAUTH2;
        token.setAccesstoken(((HttpServletRequest) request).getHeader(authorization).replace("bearer ", "").trim());
        return token;
    }

    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        String token = ((HttpServletRequest) request).getHeader(authorization);
        if (!ValideHelper.isNullOrEmpty(token)) {
            if (authService.validToken(((HttpServletRequest) request).getHeader(authorization).replace("bearer ", "").trim())) {
                return true;
            }
        }
        return isAuthUrl(request);
    }


    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ResponseModel model = new ResponseModel();
        model.httpStatus = "401";
        model.message = "用户未授权";
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        ((HttpServletResponse) response).setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write(mapper.writeValueAsString(model));
        return false;
    }

    private boolean isAuthUrl(ServletRequest request) {
        return pathsMatch(authURL, request);
    }
}
