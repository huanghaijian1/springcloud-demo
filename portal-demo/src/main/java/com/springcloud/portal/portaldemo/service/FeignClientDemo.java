package com.springcloud.portal.portaldemo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: hhjhhj
 * @Date: Created in  2020/5/11
 */
@FeignClient(name="portalserver-demo",fallback=HFeignClient.class)
public interface FeignClientDemo {

    @RequestMapping("/portal-server/sayHelloworld/{name}/{id}")
    String sayHelloworld(@PathVariable(value="name") String name, @PathVariable(value="id") Integer id);
}
