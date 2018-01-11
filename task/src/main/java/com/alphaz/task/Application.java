package com.alphaz.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.net.UnknownHostException;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.task
 * User: C0dEr
 * Date: 2017/4/20
 * Time: 下午4:28
 * Description:This is a class of com.alphaz.task
 */
@SpringBootApplication
@EnableScheduling
public class Application {
    public static void main(String[] args) throws UnknownHostException {
        SpringApplication.run(Application.class, args);
    }
}
