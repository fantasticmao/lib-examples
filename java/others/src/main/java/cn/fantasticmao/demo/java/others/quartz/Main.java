package cn.fantasticmao.demo.java.others.quartz;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Main
 *
 * @author fantasticmao
 * @since 2021-04-07
 */
public class Main {

    public static void main(String[] args) throws SchedulerException, InterruptedException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        scheduler.start();

        JobDetail job = JobBuilder.newJob(HelloJob.class)
            .withIdentity("helloJob", "group1")
            .build();

        Trigger trigger = TriggerBuilder.newTrigger()
            .withIdentity("helloTrigger", "group1")
            .build();
        scheduler.scheduleJob(job, trigger);

        TimeUnit.SECONDS.sleep(3);
        scheduler.shutdown();
    }
}
