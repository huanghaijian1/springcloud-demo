package com.springcloud.gateway.gatewaydemo.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: hhj
 * @Date: Created in  2020/5/12
 */
@RestController
public class GatewayDemoController {

    @RequestMapping("/hello")
    public String hello(){
        return "hello geteway";
    }
}
