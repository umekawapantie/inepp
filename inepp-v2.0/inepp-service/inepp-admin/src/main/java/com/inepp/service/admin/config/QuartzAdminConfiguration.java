package com.inepp.service.admin.config;

import com.inepp.service.admin.schedule.AuthorityAdminQuartz;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzAdminConfiguration {

    @Bean
    public JobDetail jobDetail(){
        return JobBuilder.newJob(AuthorityAdminQuartz.class)
                .withIdentity("admin-quartz")
                .storeDurably().build();
    }


    @Bean
    public Trigger trigger(){
        return TriggerBuilder.newTrigger().forJob(jobDetail())
                .withIdentity("admin-quartz")
                .withSchedule(
                        CronScheduleBuilder.cronSchedule(
                                "0 50 10 * * ? *"
                        )
                ).build();
    }


}
