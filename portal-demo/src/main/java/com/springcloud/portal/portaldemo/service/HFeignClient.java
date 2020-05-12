package com.springcloud.portal.portaldemo.service;

/**
 * @Author: hhj
 * @Date: Created in  2020/5/11
 */
public class HFeignClient implements FeignClientDemo{
    @Override
    public String sayHelloworld(String name, Integer id) {
        return "portalserver-demo 参数：name = "+name+" id = "+id;
    }
}
