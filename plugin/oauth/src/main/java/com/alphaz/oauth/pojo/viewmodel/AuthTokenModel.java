package com.alphaz.oauth.pojo.viewmodel;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.pojo.viewmodel
 * User: C0dEr
 * Date: 2017/6/16
 * Time: 下午6:04
 * Description:This is a class of com.alphaz.pojo.viewmodel
 */
@ApiModel("token model")
public class AuthTokenModel {
    @ApiModelProperty("accesstoken")
    public String accesstoken;
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    @ApiModelProperty("accesstoken过期日期")
    public Date accesstoken_expireuntil;
    @ApiModelProperty("refreshtoken")
    public String refreshtoken;
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    @ApiModelProperty("refreshtoken过期日期")
    public Date refreshtoken_expireuntil;
    @ApiModelProperty("授权类型")
    public String grantType;
    @ApiModelProperty("token类型")
    public String tokenType;

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getAccesstoken() {
        return accesstoken;
    }

    public void setAccesstoken(String accesstoken) {
        this.accesstoken = accesstoken;
    }

    public Date getAccesstoken_expireuntil() {
        return accesstoken_expireuntil;
    }

    public void setAccesstoken_expireuntil(Date accesstoken_expireuntil) {
        this.accesstoken_expireuntil = accesstoken_expireuntil;
    }

    public String getRefreshtoken() {
        return refreshtoken;
    }

    public void setRefreshtoken(String refreshtoken) {
        this.refreshtoken = refreshtoken;
    }

    public Date getRefreshtoken_expireuntil() {
        return refreshtoken_expireuntil;
    }

    public void setRefreshtoken_expireuntil(Date refreshtoken_expireuntil) {
        this.refreshtoken_expireuntil = refreshtoken_expireuntil;
    }
}
