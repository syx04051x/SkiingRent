package com.alphaz.task.component;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * ProjectName: IndustrialBank
 * PackageName: com.alphaz.task.task
 * User: C0dEr
 * Date: 2017/2/15
 * Time: 下午5:03
 * Description:This is a class of com.alphaz.task.task
 */
@Component
public class test {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job importJob;

    public JobParameters jobParameters;

    //    @Scheduled(cron = "*/5 * * * * *")
    @Scheduled(fixedRate = 5000)
    public void excute() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters();
        jobLauncher.run(importJob, jobParameters);
        System.out.println("this is a task");
    }


}
