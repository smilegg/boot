package com.djp.boot.controller;


import com.djp.boot.config.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private BatchService batchService;

    @GetMapping("/batch")
    public void batchTest(){
        batchService.batchHandel();
    }

}
