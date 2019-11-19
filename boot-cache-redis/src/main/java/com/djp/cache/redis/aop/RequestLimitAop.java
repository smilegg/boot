package com.djp.cache.redis.aop;

import com.djp.cache.redis.exception.BusinessException;
import com.djp.cache.redis.utils.SysUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Component
@Aspect
@Slf4j
public class RequestLimitAop {

    private HttpServletRequest request;
    @Autowired
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    private RedisTemplate<Object,Object> redisTemplate;
    @Autowired
    public void setRedisTemplate(RedisTemplate<Object, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Before("@annotation(reqLimitAop)")
    public void requestLimit(JoinPoint joinPoint,ReqLimitAop reqLimitAop){
        Object[] args = joinPoint.getArgs();
        //log.info("requestLimit args:{}",args);

        //String ip=this.getIpAddress(request);
        //log.info("requestLimit ip:{}",ip);
        String ip= SysUtil.getIpAddress(request);
        log.info("requestLimit ip:{}",ip);

        String reqUrl = request.getRequestURL().toString();

        String ipKey="req_limit_".concat(reqUrl).concat("_").concat(ip);
        log.info("requestLimit ipKey:{}",ipKey);

        if(this.checkIpLimit(reqLimitAop,ipKey)){
            log.info("[requestLimit]-->" + "[用户ip:{}],[访问地址:{}]超过了限定的次数[{}]次", ip, reqUrl, reqLimitAop.limitCount());
            throw new BusinessException("请求过于频繁,访问受限");
        }

    }

    /**
     * 检查ip是否在限制的范围内
     * @param reqLimitAop 注解参数
     * @param ipKey ipkey
     * @return 是否
     */
    private boolean checkIpLimit(ReqLimitAop reqLimitAop,String ipKey){

        Integer ipReqCount = (Integer) redisTemplate.opsForValue().get(ipKey);
        log.info("ip value start:{}",ipReqCount);
        //如果ip为空 就把ip为key放入redis中
        if(Objects.isNull(ipReqCount)){
            //设置key 为多少秒过期
            redisTemplate.opsForValue().set(ipKey,1,reqLimitAop.time(), TimeUnit.SECONDS);
        }else if(ipReqCount==reqLimitAop.limitCount()){
            return Boolean.TRUE;
            //throw new BusinessException("请求过于频繁,访问受限");
        }else{
            redisTemplate.opsForValue().increment(ipKey,1);
        }
        log.info("ip value end:{}",redisTemplate.opsForValue().get(ipKey));
        return Boolean.FALSE;
    }

    /**
     * 返回用户真实ip
     * @param request 请求
     * @return ip
     */
    private String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        log.info("x-forwarded-for ip: " + ip);
        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if(ip.contains(",")){
                ip = ip.split(",")[0];
            }
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
            log.info("Proxy-Client-IP ip: " + ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
            log.info("WL-Proxy-Client-IP ip: " + ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
            log.info("HTTP_CLIENT_IP ip: " + ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            log.info("HTTP_X_FORWARDED_FOR ip: " + ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
            log.info("X-Real-IP ip: " + ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            log.info("getRemoteAddr ip: " + ip);
        }
        log.info("获取客户端ip: " + ip);
        return ip;
    }

}
