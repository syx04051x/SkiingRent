package com.alphaz.oauth.config;

import com.alphaz.oauth.auth.MutiRealmAuthenticator;
import com.alphaz.oauth.auth.OAuthRealm;
import com.alphaz.oauth.auth.UserRealm;
import com.alphaz.oauth.constant.AuthType;
import com.alphaz.oauth.filter.OAuth2AuthenticationFilter;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
//import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
//import org.crazycake.shiro.RedisCacheManager;
//import org.crazycake.shiro.RedisManager;
//import org.crazycake.shiro.RedisSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.config.auth
 * User: C0dEr
 * Date: 2017/6/14
 * Time: 上午10:26
 * Description:This is a class of com.alphaz.config.auth
 */
@Configuration
public class ShiroConfig {
    private static final Logger logger = LoggerFactory.getLogger(ShiroConfig.class);

    @Bean
    @ConfigurationProperties(prefix = "spring.alphaz.auth.shiro")
    public ShiroList list() {
        return new ShiroList();
    }

    @Bean
    public OAuth2AuthenticationFilter oAuth2AuthenticationFilter() {
        return new OAuth2AuthenticationFilter();
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        logger.info("shiroFilterFactoryBean init", ShiroFilterFactoryBean.class);
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //设置oauth2认证过滤器
        shiroFilterFactoryBean.setFilters(new HashMap<String, Filter>() {{
            put("oauth2", oAuth2AuthenticationFilter());
        }});
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        list().getAuth().stream().forEach(a -> {
            String[] map = a.split("\\|");
            filterChainDefinitionMap.put(map[0].trim(), map[1].trim());
        });
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

//    @Bean
//    public RedisCacheManager ehCacheManager() {
//        RedisCacheManager cacheManager = new RedisCacheManager();
//        return cacheManager;
//    }
    @Bean
    public EhCacheManager ehCacheManager() {
        EhCacheManager cacheManager = new EhCacheManager();
        return cacheManager;
    }
//    @Bean
//    public RedisManager redisManager() {
//        RedisManager redisManager = new RedisManager();
//        return redisManager;
//    }
//
//    @Bean
//    public RedisSessionDAO redisSessionDAO() {
//        RedisSessionDAO dao = new RedisSessionDAO();
//        dao.setRedisManager(redisManager());
//        return dao;
//    }
//
//    @Bean
//    public DefaultWebSessionManager sessionManager() {
//        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
//        sessionManager.setSessionDAO(redisSessionDAO());
//        return sessionManager;
//    }

    @Bean
    public SecurityManager securityManager(
            MutiRealmAuthenticator mutiAuthenticator,
            OAuthRealm oAuthRealm,
            UserRealm userRealm) {
        logger.info("securityManager init", ShiroFilterFactoryBean.class);
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        LinkedHashMap<AuthType, Realm> realms = new LinkedHashMap<>();
        realms.put(AuthType.OAUTH2, oAuthRealm);
        realms.put(AuthType.Default, userRealm);

        ModularRealmAuthorizer modularRealmAuthorizer = new ModularRealmAuthorizer();
        modularRealmAuthorizer.setRealms(realms.values());
        mutiAuthenticator.setDefinedRealms(realms);

        securityManager.setAuthenticator(mutiAuthenticator);

        //这里使用默认的访问控制存在一定问题，因为实现app与api不一定基于同一套角色访问控制，会存在混肴的风险。
        //这里需要手动写入ModularRealmAuthorizer，因为盛放Realm的容器改为了Map，程序不能自动写入。
        securityManager.setAuthorizer(modularRealmAuthorizer);
        securityManager.setCacheManager(ehCacheManager());
//        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

}
