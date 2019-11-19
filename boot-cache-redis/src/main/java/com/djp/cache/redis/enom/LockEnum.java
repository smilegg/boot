package com.djp.cache.redis.enom;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public enum LockEnum {

    INSTANCE;

    private static final ReentrantReadWriteLock rwLock=new ReentrantReadWriteLock();

    public Lock writeLock(){
        return rwLock.writeLock();
    }

    public Lock readLock(){
        return rwLock.readLock();
    }

}
