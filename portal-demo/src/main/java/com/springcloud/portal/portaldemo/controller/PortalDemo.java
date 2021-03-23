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

    @Autowired
    private FeignClientDemo feignClientDemo;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${sayHelloworldurl}")
    private String sayHelloworldurl;
    @Value("${sayHelloworldurl2}")
    private String sayHelloworldurl2;
    @Value("${sayHelloworldurl3}")
    private String sayHelloworldurl3;

    @RequestMapping("/sayHello/{name}/{id}")
    public String sayHello(@PathVariable String name, @PathVariable int id){
        int a = 1/0;
        String message =  feignClientDemo.sayHelloworld(name,id);
        return message;
    }

    @RequestMapping("/sayHelloworld/{name}/{id}")
    public String sayHelloworld(@PathVariable String name, @PathVariable int id){
        String message = restTemplate.getForObject(sayHelloworldurl ,String.class,name,id);
        return message;
    }
    @RequestMapping("/sayHelloworld2")
    public String sayHelloworld2(){
        String message = restTemplate.getForObject(sayHelloworldurl2 ,String.class);
        return message;
    }

    @RequestMapping("/sayHelloworld3")
    public String sayHelloworld3(String name, int id){
        String message = restTemplate.getForObject(sayHelloworldurl ,String.class,name,id);
        return message;
    }
}
