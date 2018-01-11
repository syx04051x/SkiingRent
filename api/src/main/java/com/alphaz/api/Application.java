package com.alphaz.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

import java.net.UnknownHostException;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz
 * User: C0dEr
 * Date: 2017/4/20
 * Time: 下午4:28
 * Description:This is a class of com.alphaz
 */
@SpringBootApplication
@EnableAsync
@EnableAspectJAutoProxy
@EnableCaching
@ComponentScan(basePackages = {"com.alphaz"})
public class Application {
    public static void main(String[] args) throws UnknownHostException {
        SpringApplication.run(Application.class, args);
    }
}
