package com.djp.cache.redis.controller;


import com.djp.cache.redis.aop.ReqLimitAop;
import com.djp.cache.redis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private UserService userService;

    @GetMapping("/getUserInfoById")
    public Object getUserInfo(@RequestParam("id") Integer id){
        return userService.getUserInfo(id);
    }


    @GetMapping("/deleteCache")
    public Object deleteCache(){
        userService.deleteCache();
        return "deleteCache success";
    }


    @ReqLimitAop(limitCount = 2,time = 20)
    @GetMapping("/ipLimt")
    public String ipLimitTest(){
        return userService.testIpLimt();
    }

}
