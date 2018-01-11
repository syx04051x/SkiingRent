package com.alphaz.oauth.dao;

import com.alphaz.oauth.pojo.entity.OauthAccesstokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.dao
 * User: C0dEr
 * Date: 2017/6/16
 * Time: 下午5:40
 * Description:This is a class of com.alphaz.dao
 */

public interface TokenDAO extends JpaRepository<OauthAccesstokenEntity, Long> {
    OauthAccesstokenEntity findByAuthid(String authid);

    OauthAccesstokenEntity findByAccesstokenAndExpiretimeGreaterThan(String token, Date expire);

    OauthAccesstokenEntity findByAuthidAndRefreshtokenid(String authid, Long id);
}
