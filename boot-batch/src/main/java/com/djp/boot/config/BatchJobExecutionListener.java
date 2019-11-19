package com.djp.boot.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

/**
 * 监听job运行状态
 */
@Component("batchJobExecutionListener")
public class BatchJobExecutionListener implements JobExecutionListener {

    private final Logger logger= LoggerFactory.getLogger(this.getClass());

    /**
     *
     * @param jobExecution job执行结果
     */
    @Override
    public void beforeJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            logger.info("Job beforeJob completed: " + jobExecution.getJobId());
        } else if (jobExecution.getStatus() == BatchStatus.FAILED) {
            logger.info("Job beforeJob failed: " + jobExecution.getJobId());
        }
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            logger.info("Job afterJob completed: " + jobExecution.getJobId());
        } else if (jobExecution.getStatus() == BatchStatus.FAILED) {
            logger.info("Job afterJob failed: " + jobExecution.getJobId());
        }
    }
}
