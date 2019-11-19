package com.djp.cache.redis.service;


import com.djp.cache.redis.entity.User;
import com.djp.cache.redis.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class UserService {

    private static Map<Integer,String> map;

    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    static {
        map=new HashMap<Integer,String>();
        map.put(1,"test1");
        map.put(2,"test2");
    }

    @Cacheable(value = "testCache")
    public Object getUserInfo(Integer id) {
        log.info("没有走缓存，访问的数据库");

        Integer v1 = (Integer) redisTemplate.opsForValue().get("test");
        log.info("没有走缓存，访问的数据库v1:{}",v1);
        log.info("没有走缓存，访问的数据库redisTemplate.opsForValue().get:{}",redisTemplate.opsForValue().get("testCache"));

        return map.get(id);
    }

    @CacheEvict(value = "testCache",allEntries = true,beforeInvocation = true)
    public void deleteCache() {
        log.info("删除testCache");
    }


    public static void main(String[] args) {

        Optional<Object> opt = Optional.ofNullable("null");
        if(opt.isPresent()){
            System.err.println("测试ofNullable");
        }

       // Optional<Object> opt2 = Optional.of(null);

        if((10*1000)>100){
            System.err.println("进来了");
        }


        User userEt=new User();
        userEt.setId(1).setName("Optional");
        List<User> userList=new ArrayList<User>();
        userList.add(userEt);
        //userEt=null;
        Optional<List<User>> userOp=Optional.ofNullable(userList);
        userOp.filter(user->{
            if(Objects.isNull(userList)||userList.size()<=0){
                throw new BusinessException("不能为空1");
            }
            return user.get(0).getId().intValue()!=0?true:false;
        })
              .ifPresent(user->{
                  log.info("Optional 代码测试user:{}",user);
        });


        User userEt2=new User();
        Optional<User> userOp2=Optional.empty();


//        userOp2.orElseThrow(()->{ throw new BusinessException("不能为空2"); }); //这个不好使

        User userElse2 = userOp2.orElse(userEt2.setId(1).setName("orElse"));
        log.info("Optional 代码测试orElse:{}",userElse2);

        //userEt2=null; 测试为空是否抛出异常
        Optional.ofNullable(userEt2).orElseGet(() -> {
            throw new BusinessException("不能为空3");
        });
        log.info("Optional 代码测试orElseGet:{}",userEt2);
    }


    public String testIpLimt(){
        return "ip limit";
    }
}
