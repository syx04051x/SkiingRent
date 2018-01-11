package com.alphaz.oauth.dao;

import com.alphaz.oauth.pojo.entity.OauthRefreshtokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.dao
 * User: C0dEr
 * Date: 2017/6/19
 * Time: 上午9:58
 * Description:This is a class of com.alphaz.dao
 */
public interface RefreshTokenDAO extends JpaRepository<OauthRefreshtokenEntity, Long> {

    OauthRefreshtokenEntity findByAuthidAndRefreshtoken(String authid, String refreshToken);

    OauthRefreshtokenEntity findByRefreshtoken(String refreshToken);
}
