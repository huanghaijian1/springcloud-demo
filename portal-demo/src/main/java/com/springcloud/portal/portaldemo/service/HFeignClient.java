package com.springcloud.portal.portaldemo.service;

import org.springframework.stereotype.Component;

/**
 * @Author: hhj
 * @Date: Created in  2020/5/11
 */
@Component
public class HFeignClient implements FeignClientDemo{
    @Override
    public String sayHelloworld(String name, Integer id) {
        System.out.println(name+id+"---------------------------------");
        System.out.println(name+id+"---------------------------------");
        System.out.println(name+id+"---------------------------------");
        System.out.println(name+id+"---------------------------------");
        System.out.println(name+id+"---------------------------------");
        System.out.println(name+id+"---------------------------------");
        System.out.println(name+id+"---------------------------------");


        return "portalserver-demo 参数：name = "+name+" id = "+id;
    }
}
