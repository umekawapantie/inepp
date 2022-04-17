package com.inepp.service.admin.schedule;

import com.inepp.service.admin.service.IAdminService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class AuthorityAdminQuartz extends QuartzJobBean {

    @Autowired
    private IAdminService ias;


    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        ias.preLoadAuthority();
    }
}
