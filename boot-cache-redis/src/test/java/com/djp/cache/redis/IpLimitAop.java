package com.djp.cache.redis;

import com.djp.cache.redis.exception.BusinessException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

@SpringBootTest
public class IpLimitAop {

    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;


    @Test
    public void ipLimitTest(){

        for (int i=0;i<500;i++){
            Runnable rn= () -> {

                System.err.println("当前线程:"+Thread.currentThread().getId()+"-"+Thread.currentThread().getName());

                String ip="127.0.0.1";

                Integer ipReqCount = (Integer) redisTemplate.opsForValue().get(ip);
                System.err.println("ip value start:"+ipReqCount);
                //如果ip为空 就把ip为key放入redis中
                if(ipReqCount==null){
                    //设置key 为5秒过期
                    redisTemplate.opsForValue().set(ip,1,20, TimeUnit.SECONDS);
                }else if(ipReqCount==20){
                    throw new BusinessException("请求过于频繁,访问受限");
                }else{
                    redisTemplate.opsForValue().increment(ip,1);
                }

                System.err.println("ip value end:"+redisTemplate.opsForValue().get(ip));
            };
            rn.run();
        }
    }

}
