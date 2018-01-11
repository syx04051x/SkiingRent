package com.alphaz.flow.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.flow.config
 * User: C0dEr
 * Date: 2017/7/24
 * Time: 下午9:53
 * Description:This is a class of com.alphaz.flow.config
 */
@Configuration
public class TaskConfig {

    @Primary
    @Bean
    public TaskExecutor primaryTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        return executor;
    }
}