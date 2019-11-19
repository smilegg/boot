package com.djp.cache.redis;

import com.djp.cache.redis.enom.LockEnum;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;

@Slf4j
public class LockTest {

    public static int count=0;

    public static void add(){
        Lock writeLock = LockEnum.INSTANCE.readLock();
        try {
            writeLock.lock();
            count=count+1;
            log.info("count值:{}",count);
        }catch (Exception e){
            log.info("业务异常:{}",e.getMessage());
        }finally {
            writeLock.unlock();
        }


    }

    public static void main(String[] args) {
        ExecutorService executorService=new ThreadPoolExecutor(10,1000,60L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10));

        for (int i = 0; i < 1000; i++) {
            Runnable rn=new Runnable() {
                @Override
                public void run() {
                   LockTest.add();
                }
            };
            executorService.execute(rn);
        }
        log.info("count值:{}",count);
        executorService.shutdown();
    }

}
