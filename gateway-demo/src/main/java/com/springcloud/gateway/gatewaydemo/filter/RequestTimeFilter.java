package com.springcloud.gateway.gatewaydemo.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 自定义Gatewayfilter
 * @Author: hhj
 * @Date: Created in  2020/5/13
 */
public class RequestTimeFilter implements GatewayFilter, Ordered {
    private static final Log log = LogFactory.getLog(GatewayFilter.class);
    private static final String REQUEST_TIME_BEGIN = "requestTimeBegin";
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        exchange.getAttributes().put(REQUEST_TIME_BEGIN, System.currentTimeMillis());
//        如何区分“pre”和“post”？
//        pre就是chain.filter(exchange)部分.
//        post就是then()部分.
        return chain.filter(exchange).then(
                Mono.fromRunnable(() -> {
                    Long startTime = exchange.getAttribute(REQUEST_TIME_BEGIN);
                    if (startTime != null) {
                        log.debug("请求路径："+exchange.getRequest().getURI().getRawPath() + "消耗时间: " + (System.currentTimeMillis() - startTime) + "ms");
                    }
                })
        );
    }

    //上述代码中定义了自己实现的过滤器。Ordered的int getOrder（）方法是来给过滤器定优先级的，值越大优先级越低。
    @Override
    public int getOrder() {
        return 0;
    }
}

