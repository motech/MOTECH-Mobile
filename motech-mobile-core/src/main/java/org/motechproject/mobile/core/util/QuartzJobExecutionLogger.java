/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.motechproject.mobile.core.util;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

/**
 *
 * @author Kweku
 */
public class QuartzJobExecutionLogger implements JobListener {
    private static final Logger logger = Logger.getLogger(QuartzJobExecutionLogger.class);
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void jobToBeExecuted(JobExecutionContext context) {
        
    }

    public void jobExecutionVetoed(JobExecutionContext context) {
        
    }

    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        String log = "\nName: " + context.getJobDetail().getName();
        log       += "\nDuration: " + (context.getJobRunTime() / 1000) + " seconds";
        log       += "\nTime started: " + context.getFireTime();
        logger.info("Logging Job Execution Time:" + log);
    }

}
