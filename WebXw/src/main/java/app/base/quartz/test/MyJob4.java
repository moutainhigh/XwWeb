package app.base.quartz.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class MyJob4 implements Job {
    private Log log = LogFactory.getLog(MyJob4.class);

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        log.info(Thread.currentThread().getName());
    }

}