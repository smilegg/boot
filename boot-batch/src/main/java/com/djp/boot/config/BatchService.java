package com.djp.boot.config;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.mybatis.spring.batch.MyBatisPagingItemReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BatchService {

    private final Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BatchProcessor batchProcessor;

    @Autowired
    private SimpleJobLauncher simpleJobLauncher;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private MyBatisPagingItemReader itemReader;

    @Autowired
    private MyBatisBatchItemWriter itemWriter;

    @Qualifier("taskExecutor")
    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Autowired
    private BatchJobExecutionListener batchJobExecutionListener;

    @Autowired
    private BatchItemReadAndWriterErrorListener batchItemReadAndWriterErrorListener;


    /**
     * 任务(核心业务) reader-->processor-->writer
     */
    public void batchHandel(){

        //reader
        itemReader.setQueryId("com.djp.boot.mapper.MgdShUserModelMapper.queryAll");
        //queryAll设置参数 暂时没有参数
        Map<String,Object> params= Maps.newHashMap();
        itemReader.setParameterValues(params);


        //writer 读取数据之后 可以进行后续的处理操作
        itemWriter.setStatementId("com.djp.boot.mapper.MgdShUserModelMapper.updateShUser");
        CompositeItemWriter compositeItemWriter=new CompositeItemWriter();
        List delegates= Lists.newArrayList();
        delegates.add(itemWriter);
        compositeItemWriter.setDelegates(delegates);


        //build step
        Step step=stepBuilderFactory
                .get("step1")
                .listener(batchItemReadAndWriterErrorListener)
                .<String,String>chunk(11) //当ItemReader读的数据数量达到该值的时候，这一批次的数据就一起被传到itemWriter，同时transaction被提交。
                .reader(itemReader)
                .processor(batchProcessor)
                .writer(compositeItemWriter)
                .faultTolerant()
                .skipLimit(10)
//                .skip(NullPointerException.class)
//                .skip(ArithmeticException.class)
                .skip(Exception.class)
                .retryLimit(3) //重试机制 允许重试3次
                .retry(RuntimeException.class)  //遇见网络错误等运行时异常 进行重试
                .taskExecutor(taskExecutor)  //使用线程池 多线程处理任务 提高效率
                .build();
        logger.info("step:{}",step);

        //build job
        Job job=jobBuilderFactory.get("job1").incrementer(new RunIdIncrementer()).start(step).listener(batchJobExecutionListener).build();
        //JobParameters parameters = new JobParametersBuilder().toJobParameters();
        JobParameters parameters = new JobParametersBuilder()
                .addLong("runTime", System.currentTimeMillis())
                .toJobParameters();
        logger.info("job:{}",job);
        try {
            //执行构建好的任务 job
            JobExecution jobResult = simpleJobLauncher.run(job, parameters);
            logger.info("jobResult:{}",jobResult.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
