package com.alphaz.task.config;

import com.alphaz.core.pojo.viewmodel.user.UserViewModel;
import com.alphaz.task.listener.JobListener;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.support.DatabaseType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.task.config
 * User: C0dEr
 * Date: 2017/6/23
 * Time: 下午3:25
 * Description:This is a class of com.alphaz.task.config
 */
@Configuration
@EnableBatchProcessing
public class BatchConfig {

    /**
     * 作业仓库
     *
     * @param dataSource
     * @param transactionManager
     * @return
     * @throws Exception
     */
    @Bean
    public JobRepository jobRepository(DataSource dataSource, PlatformTransactionManager transactionManager) throws Exception {

        JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
        jobRepositoryFactoryBean.setDataSource(dataSource);
        jobRepositoryFactoryBean.setTransactionManager(transactionManager);
        jobRepositoryFactoryBean.setDatabaseType(DatabaseType.MYSQL.name());

        return jobRepositoryFactoryBean.getObject();
    }

    /**
     * 作业调度器
     *
     * @param dataSource
     * @param transactionManager
     * @return
     * @throws Exception
     */
    public SimpleJobLauncher jobLauncher(DataSource dataSource, PlatformTransactionManager transactionManager) throws Exception {

        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(this.jobRepository(dataSource, transactionManager));

        return jobLauncher;
    }

    @Bean
    public Job importJob(JobBuilderFactory jobs, Step step) {
        return jobs.get("importJob")
                .incrementer(new RunIdIncrementer())
                .flow(step)
                .end()
                .build();
    }

    @Bean
    public Step userStep(StepBuilderFactory stepBuilderFactory,
                         ItemReader<UserViewModel> reader, ItemWriter<UserViewModel> writer, ItemProcessor<UserViewModel, UserViewModel> processor) {
        return stepBuilderFactory.get("personStep")
                .<UserViewModel, UserViewModel>chunk(5000)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public JobListener myJobListener() {
        return new JobListener();
    }
}
