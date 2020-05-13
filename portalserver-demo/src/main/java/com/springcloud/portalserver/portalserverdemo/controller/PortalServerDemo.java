package com.springcloud.portalserver.portalserverdemo.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.catalina.connector.Request;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


/**
 * @Author: hhj
 * @Date: Created in  2020/5/11
 */
@RestController
@RequestMapping("/portal-server")
public class PortalServerDemo {


    @RequestMapping("/sayHelloworld/{name}/{id}")
    @HystrixCommand(fallbackMethod = "defaultSay")
    public String sayHelloworld(HttpServletRequest request, @PathVariable String name, @PathVariable int id){


        int a =  1/id;
        return "Hello World" + name + a+"  "+request.getServerPort();
    }

    public String defaultSay(HttpServletRequest request,@PathVariable String name,@PathVariable int id){
        return "Hello World" + name + id+"  "+request.getServerPort();
    }


    @RequestMapping("/sayHelloworld2")
    public String sayHelloworld2(HttpServletRequest request){
        return "Hello World gateway  "+request.getServerPort();
    }
    @RequestMapping("/sayHelloworld3")
    public String sayHelloworld3(HttpServletRequest request,String name, int id){
        int a =  1/id;
        return "Hello World" + name + a+"  "+request.getServerPort();
    }
}
