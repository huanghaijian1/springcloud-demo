package com.springcloud.portal.portaldemo.controller;

import com.springcloud.portal.portaldemo.service.FeignClientDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: hhj
 * @Date: Created in  2020/5/11
 */
@RestController
@RequestMapping("/portal-demo")
public class PortalDemo {

//    @Autowired
//    private FeignClientDemo feignClientDemo;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${sayHelloworldurl}")
    private String sayHelloworldurl;

//    @RequestMapping("/sayHelloworld/{name}/{id}")
//    public String sayHelloworld(@PathVariable String name, @PathVariable int id){
//        String message =  feignClientDemo.sayHelloworld(name,id);
//        return message;
//    }

    @RequestMapping("/sayHelloworld2/{name}/{id}")
    public String sayHelloworld2(@PathVariable String name, @PathVariable int id){
        String message = restTemplate.getForObject(sayHelloworldurl ,String.class,name,id);
        return message;
    }
}
