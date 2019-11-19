package com.djp.boot.config;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.util.List;

public class BatchReader implements ItemReader<List<String>> {

    @Override
    public List<String> read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return null;
    }
}
