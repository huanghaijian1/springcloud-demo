package com.springcloud.configclientdemo.configclientdemo.controller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: hhj
 * @Date: Created in  2020/5/14
 */

@RestController
@RequestMapping("/cfg")
public class ConfigController {
    @Value("${foo}")
    String foo;

    @Value("${bar}")
    String bar;

    @RequestMapping(value = "/foo")
    public String foo(){
        return foo + "——" + bar;
    }
}

