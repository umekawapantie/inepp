package com.inepp.service.residents.config;

import com.inepp.service.residents.schedule.AuthorityQuartz;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfiguration {

    @Bean
    public JobDetail jobDetail(){
        return JobBuilder.newJob(AuthorityQuartz.class)
                .withIdentity("privs-quartz")
                .storeDurably().build();
    }


    @Bean
    public Trigger trigger(){
        return TriggerBuilder.newTrigger().forJob(jobDetail())
                .withIdentity("privs-quartz")
                .withSchedule(
                        CronScheduleBuilder.cronSchedule(
                                "0 36 20 * * ? *"
                        )
                ).build();
    }


}
