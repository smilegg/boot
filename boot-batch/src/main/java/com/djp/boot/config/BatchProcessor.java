package com.djp.boot.config;

import com.djp.boot.model.MgdShUserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Scope("prototype")
public class BatchProcessor implements ItemProcessor<MgdShUserModel, MgdShUserModel> {

    private final Logger logger= LoggerFactory.getLogger(this.getClass());

    private Map<String,Object> parameterValues;

    public Map<String, Object> getParameterValues() {
        return parameterValues;
    }

    public void setParameterValues(Map<String, Object> parameterValues) {
        this.parameterValues = parameterValues;
    }


    /**
     * 主要可用于 参数校验
     * @param mgdShUserModel reader分页读取到的对象
     * @return 将读取到的对象进行处理后  返回的对象  可用于后续writer操作
     * @throws Exception
     */
    @Override
    public MgdShUserModel process(MgdShUserModel mgdShUserModel) throws Exception {
        logger.info("currentThreadId:{}",Thread.currentThread().getId());
        logger.info("MgdShUserModel:{}",mgdShUserModel);
        //执行异常测试 当执行到某个点的时候抛出异常 会进行重试 并且跳过 继续往下执行
        if(mgdShUserModel.getId().equals(1906210000400026L)){
            int i=10/0;
        }
        MgdShUserModel su=new MgdShUserModel();
        su.setId(mgdShUserModel.getId());
        su.setLoginStatus(77);
        return su;
    }
}
