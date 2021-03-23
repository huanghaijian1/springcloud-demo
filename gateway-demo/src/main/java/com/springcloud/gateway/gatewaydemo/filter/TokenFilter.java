package com.springcloud.gateway.gatewaydemo.filter;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * 自定义 springcloud geteway GlobalFilter
 *
 * @Author: hhj
 * @Date: Created in  2020/5/13
 */
@Component
public class TokenFilter implements GlobalFilter, Ordered {
    Logger logger = LoggerFactory.getLogger(TokenFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        /**
         * 第一种玩法
         */
//        String token = exchange.getRequest().getQueryParams().getFirst("token");
////        String token = exchange.getRequest().getHeaders().getFirst("token");
//        if (token == null || token.isEmpty()) {
//            logger.info("token 为空，无法进行访问.");
//            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//            return exchange.getResponse().setComplete();
//        }
        return chain.filter(exchange);

        /**
         * 第二种玩法
         */
//        String token = exchange.getRequest().getQueryParams().getFirst("authToken");
//        //返回401状态码和提示信息
//        if (StringUtils.isBlank(token)) {
//            ServerHttpResponse response = exchange.getResponse();
//            JSONObject message = new JSONObject();
//            message.put("status", -1);
//            message.put("data", "鉴权失败");
//            byte[] bits = message.toJSONString().getBytes(StandardCharsets.UTF_8);
//            DataBuffer buffer = response.bufferFactory().wrap(bits);
//            response.setStatusCode(HttpStatus.UNAUTHORIZED);
//            //指定编码，否则在浏览器中会中文乱码
//            response.getHeaders().add("Content-Type", "text/plain;charset=UTF-8");
//            return response.writeWith(Mono.just(buffer));
//        }
//        return chain.filter(exchange);
        /**
         * 第三种玩法
         * 对于浏览器，通常是发现没有权限后跳转到登录页面。响应状态码需要为HttpStatus.SEE_OTHER（303）。
         * 重定向（redirect）会丢失之前请求的参数，对于需要转发到目标URL的参数，需手工添加。
         */
//        String token = exchange.getRequest().getQueryParams().getFirst("token");
//        //重定向(redirect)到登录页面
//        if (StringUtils.isBlank(token)) {
//            String url = "http://想跳转的网址";
//            ServerHttpResponse response = exchange.getResponse();
//            //303状态码表示由于请求对应的资源存在着另一个URI，应使用GET方法定向获取请求的资源
//            response.setStatusCode(HttpStatus.SEE_OTHER);
//            response.getHeaders().set(HttpHeaders.LOCATION, url);
//            return response.setComplete();
//        }
//        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}

