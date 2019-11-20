package com.djp.bootxxljob.job;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@JobHandler(value = "firstJobHandler")
@Slf4j
public class FirstJob extends IJobHandler {

    @Override
    public ReturnT<String> execute(String s) throws Exception {

        log.info("--------------------job start------------------------");
        //自己的业务
        log.info("--------------------------execute FirstJob----------------------");

        log.info("--------------------job end------------------------");
        return new ReturnT("execute success");
    }
}
