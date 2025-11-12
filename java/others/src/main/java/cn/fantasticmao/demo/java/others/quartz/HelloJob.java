package cn.fantasticmao.demo.java.others.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * HelloJob
 *
 * @author fantasticmao
 * @since 2021-04-07
 */
@Slf4j
public class HelloJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("thread id: {} - Hello Quartz!", Thread.currentThread().threadId());
    }
}
