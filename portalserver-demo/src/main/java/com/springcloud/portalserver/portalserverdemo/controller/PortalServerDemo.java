package com.springcloud.portalserver.portalserverdemo.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.catalina.connector.Request;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


/**
 * @Author: hhj
 * @Date: Created in  2020/5/11
 */
@RestController
@RequestMapping("/portal-server")
public class PortalServerDemo {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Autowired
    private Redisson redisson;

    @RequestMapping("/sayHelloworld/{name}/{id}")
    //@HystrixCommand(fallbackMethod = "defaultSay")
    public String sayHelloworld(HttpServletRequest request, @PathVariable String name, @PathVariable int id){
//        try {
//            Thread.sleep(1000*50);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        boolean flag = redisTemplate.expire("abc",60,TimeUnit.SECONDS);
//       redisTemplate.opsForValue().setIfAbsent()
        int a =  1/id;
        /**
         * public boolean tryLock(long waitTime, long leaseTime, TimeUnit unit)
         * 确实是这样，当设置了leaseTime，看门狗机制失效了，设置waitTime不影响看门狗机制
         */
//        RReadWriteLock rReadWriteLock = redisson.getReadWriteLock("abc");
//        RLock rLock= rReadWriteLock.readLock();//读锁
//        RLock wLock= rReadWriteLock.writeLock();//写锁
        new StringBuilder(1000).append("");
        RLock rLock = redisson.getLock("mylock");
        try {
            if(rLock.tryLock()){
                //rLock.tryLock(10,-1L,TimeUnit.SECONDS);第二个参数-1L 看门狗才会生效
            }else{

            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            rLock.unlock();
        }

        return "Hello World" + name + a+"  "+request.getServerPort();
    }

    public String defaultSay(HttpServletRequest request,@PathVariable String name,@PathVariable int id){
        return "Hello World" + name + id+"  "+request.getServerPort();
    }


    @RequestMapping("/sayHelloworld2")
    public String sayHelloworld2(HttpServletRequest request){
        return "Hello World gateway2  "+request.getServerPort();
    }
    @RequestMapping("/sayHelloworld3")
    public String sayHelloworld3(HttpServletRequest request,String name, int id){
        int a =  1/id;
        return "Hello World" + name + a+"  "+request.getServerPort();
    }
}
