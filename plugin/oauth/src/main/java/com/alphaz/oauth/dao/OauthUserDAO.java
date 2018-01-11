package com.alphaz.oauth.dao;

import com.alphaz.oauth.pojo.entity.OauthSysuserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.dao
 * User: C0dEr
 * Date: 2017/6/16
 * Time: 下午5:23
 * Description:This is a class of com.alphaz.dao
 */
public interface OauthUserDAO extends JpaRepository<OauthSysuserEntity, Long> {
    OauthSysuserEntity findByUsernameAndPassword(String username, String password);

    @Query("select a from OauthSysuserEntity a,OauthAccesstokenEntity b where a.authid=b.authid and b.accesstoken=?1")
    OauthSysuserEntity findByAccessToken(String accesstoken);

    @Query("select a from OauthSysuserEntity a,OauthRefreshtokenEntity b where a.authid=b.authid and b.refreshtoken=?1")
    OauthSysuserEntity findByRefreshToken(String refreshToken);
}
