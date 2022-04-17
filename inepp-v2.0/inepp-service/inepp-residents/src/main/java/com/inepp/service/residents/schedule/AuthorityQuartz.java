package com.inepp.service.residents.schedule;

import com.inepp.service.residents.service.IResidentsService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class AuthorityQuartz extends QuartzJobBean {

    @Autowired
    private IResidentsService irs;


    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        irs.loadAllUsernames();
    }
}
