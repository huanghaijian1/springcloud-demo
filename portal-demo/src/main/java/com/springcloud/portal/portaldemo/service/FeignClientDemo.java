package com.springcloud.portal.portaldemo.service;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: hhjhhj
 * @Date: Created in  2020/5/11
 */
//@FeignClient(value="portalserver-demo",fallback=HFeignClient.class)
public interface FeignClientDemo {

    @RequestMapping("/portal-server/sayHelloworld/{name}/{id}")
    String sayHelloworld(@PathVariable String name, @PathVariable Integer id);
}
