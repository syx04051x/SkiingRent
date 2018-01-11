package com.alphaz.core.config;

import com.alphaz.core.dao.base.BaseRepoImpl;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.core.config
 * User: C0dEr
 * Date: 2017/7/20
 * Time: 下午4:13
 * Description:This is a class of com.alphaz.core.config
 */
@Configuration
@EnableJpaRepositories(value = "com.alphaz", repositoryBaseClass = BaseRepoImpl.class)
@EntityScan(basePackages = "com.alphaz")
public class CoreJPAConfig {


}
