package com.djp.boot.config;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.mybatis.spring.batch.MyBatisPagingItemReader;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * spring batch 配置类
 * @author djp
 */

@Configuration
public class BatchConfig {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @Autowired
    private DataSource dataSource;


   /* @Bean
    public ResourcelessTransactionManager transactionManager(){
        return new ResourcelessTransactionManager();
    }*/


    /**
     * 这里需要覆盖 原来的sqlSessionTemplate  加入ExecutorType.BATCH操作
     * @return
     */
    @Bean
    public SqlSessionTemplate sqlSessionTemplate() {
        SqlSessionTemplate sqlSessionTemplate=new SqlSessionTemplate(sqlSessionFactory, ExecutorType.BATCH);
        return sqlSessionTemplate;
    }

    @Bean
    public JobRepository jobRepository() throws Exception {
        JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
        jobRepositoryFactoryBean.setDataSource(dataSource);
        jobRepositoryFactoryBean.setDatabaseType("MySQL");
        jobRepositoryFactoryBean.setTransactionManager(platformTransactionManager);
        return jobRepositoryFactoryBean.getObject();
    }

    @Bean
    public SimpleJobLauncher simpleJobLauncher() throws Exception {
        SimpleJobLauncher simpleJobLauncher = new SimpleJobLauncher();
        simpleJobLauncher.setJobRepository(this.jobRepository());
        return simpleJobLauncher;
    }

    /**
     * MyBatisPagingItemReader 用于分页读取数据
     * @return
     */
    @Bean
    public MyBatisPagingItemReader myBatisPagingItemReader() {
        MyBatisPagingItemReader myBatisPagingItemReader = new MyBatisPagingItemReader();
        myBatisPagingItemReader.setSqlSessionFactory(sqlSessionFactory);
        myBatisPagingItemReader.setPageSize(10);
        myBatisPagingItemReader.setQueryId("");
        return myBatisPagingItemReader;
    }

    /**
     * MyBatisBatchItemWriter 用户写数据 更新数据库操作
     * @param sqlSessionTemplate
     * @return
     */
    @Bean
    public MyBatisBatchItemWriter myBatisBatchItemWriter(SqlSessionTemplate sqlSessionTemplate) {
        MyBatisBatchItemWriter myBatisBatchItemWriter = new MyBatisBatchItemWriter();
        myBatisBatchItemWriter.setSqlSessionFactory(sqlSessionFactory);
        myBatisBatchItemWriter.setSqlSessionTemplate(sqlSessionTemplate);
        myBatisBatchItemWriter.setStatementId("");
        return myBatisBatchItemWriter;
    }

    /**
     * JobBuilderFactory 负责创建job任务
     * @return
     * @throws Exception
     */
    @Bean
    public JobBuilderFactory jobBuilderFactory() throws Exception {
        JobBuilderFactory jobBuilderFactory = new JobBuilderFactory(this.jobRepository());
        return jobBuilderFactory;
    }


    /**
     * StepBuilderFactory
     * @return
     * @throws Exception
     */
    @Bean
    public StepBuilderFactory stepBuilderFactory() throws Exception {
        return new StepBuilderFactory(this.jobRepository(), platformTransactionManager);
    }

    /**
     * ThreadPoolTaskExecutor 使用线程池处理任务
     * @return
     */
    @Bean(name = "taskExecutor")
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(5);
        taskExecutor.setMaxPoolSize(10);
        taskExecutor.setQueueCapacity(30);
        return taskExecutor;
    }

}
