package cn.fantasticmao.demo.java.others.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * HelloJob
 *
 * @author fantasticmao
 * @since 2021-04-07
 */
public class HelloJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.printf("%s - Hello Quartz!", Thread.currentThread().getName());
    }
}
