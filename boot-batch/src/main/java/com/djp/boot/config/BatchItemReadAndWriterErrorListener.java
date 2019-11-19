package com.djp.boot.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.listener.ItemListenerSupport;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * 监听job是read出错还是writer出错
 */
@Component("batchItemReadAndWriterErrorListener")
public class BatchItemReadAndWriterErrorListener extends ItemListenerSupport<Object, Object> {

    private final Logger logger= LoggerFactory.getLogger(this.getClass());

   /* @Override
    public void afterRead(Object item) {
        super.afterRead(item);
    }

    @Override
    public void beforeRead() {
        super.beforeRead();
    }

    @Override
    public void afterProcess(Object item, Object result) {
        super.afterProcess(item, result);
    }

    @Override
    public void beforeProcess(Object item) {
        super.beforeProcess(item);
    }

    @Override
    public void afterWrite(List<?> item) {
        super.afterWrite(item);
    }

    @Override
    public void beforeWrite(List<?> item) {
        super.beforeWrite(item);
    }*/

    @Override
    public void onReadError(Exception ex) {
        logger.info("batch handle read error:{}",ex.getMessage());
    }

    @Override
    public void onProcessError(Object item, Exception e) {
        logger.info("item:{}",item);
        logger.info("batch handle proccss error:{}",e.getMessage());
    }

    @Override
    public void onWriteError(Exception ex, List<?> item) {
        logger.info("batch handle write error:{}",ex.getMessage());
    }

}
