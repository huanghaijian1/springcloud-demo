package com.springcloud.gateway.gatewaydemo.filter;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

/**
 * 自定义GatewayfilterFactory
 * @Author: hhj
 * @Date: Created in  2020/5/14
 */
@Component
@Slf4j
public class RequestTimeGatewayFilterFactory extends AbstractGatewayFilterFactory<RequestTimeGatewayFilterFactory.Config> {

    /**
     * 定义可以再yaml中声明的属性变量
     */
    private static final String TYPE = "type";
    private static final String OP = "op";
    private static final String REQUEST_TIME_BEGIN = "requestTimeBean";
    private static final String REQUEST_PATH_BEGIN = "REQUEST_PATH_BEGIN";

    /**
     * constructor
     */
    public RequestTimeGatewayFilterFactory(){
        // 这里需要将自定义的config传过去，否则会报告ClassCastException
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(TYPE, OP);
    }



    /**
     * 注意我们通过这种工厂创建出来的过滤器是没有指定order的，会被默认设置为是0，配置在yml文件中，则按照它书写的顺序来执行
     * 如果想要在代码中设置好它的顺序，工厂的apply方法需要做一些修改，如下（数字越小优先级越高）
     */
//    @Override
//    public GatewayFilter apply(Config config) {
//        return ((exchange, chain) -> {
//            System.out.println("  pre 自定义过滤器工厂 AAAA (前置) " + this.getClass().getSimpleName());
//            exchange.getAttributes().put(REQUEST_TIME_BEGIN, System.currentTimeMillis());
//            exchange.getAttributes().put(REQUEST_PATH_BEGIN,exchange.getRequest().getURI().getRawPath());
//            boolean root = "root".equals(config.getOp());
//            if (root){
//                log.info("-----------GatewayFilter root----------");
//            }
//            else {
//                log.info("-----------GatewayFilter customer-----------");
//            }
//            // 在then方法里的，相当于aop中的后置通知
//            return chain.filter(exchange).then(Mono.fromRunnable(()->{
//                // do something
//                System.out.println("  post 自定义过滤器工厂 AAAA (后置) " + this.getClass().getSimpleName());
//                Long startTime = exchange.getAttribute(REQUEST_TIME_BEGIN);
//                if (startTime != null) {
//                    String requestPath = exchange.getAttribute(REQUEST_PATH_BEGIN);
//                    log.info("请求路径："+requestPath + "  消耗时间: " + (System.currentTimeMillis() - startTime) + "ms");
//                }
//            }));
//        });
//    }

    /**
     *
     * @param config
     * @return
     */
    @Override
    public GatewayFilter apply(Config config) {
        return new InnerFilter(config);
    }

    /**
     * 创建一个内部类，来实现2个接口，指定顺序
     */
    private class InnerFilter implements GatewayFilter, Ordered {

        private Config config;

        InnerFilter(Config config) {
            this.config = config;
        }

        @Override
        public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
            System.out.println("  pre 自定义过滤器工厂 AAAA (前置) " + this.getClass().getSimpleName());
            exchange.getAttributes().put(REQUEST_TIME_BEGIN, System.currentTimeMillis());
            exchange.getAttributes().put(REQUEST_PATH_BEGIN,exchange.getRequest().getURI().getRawPath());
            boolean root = "root".equals(config.getOp());
            if (root){
                log.info("-----------GatewayFilter root----------");
            }
            else {
                log.info("-----------GatewayFilter customer-----------");
            }
            // 在then方法里的，相当于aop中的后置通知
            return chain.filter(exchange).then(Mono.fromRunnable(()->{
                // do something
                System.out.println("  post 自定义过滤器工厂 AAAA (后置) " + this.getClass().getSimpleName());
                Long startTime = exchange.getAttribute(REQUEST_TIME_BEGIN);
                if (startTime != null) {
                    String requestPath = exchange.getAttribute(REQUEST_PATH_BEGIN);
                    log.info("请求路径："+requestPath + "  消耗时间: " + (System.currentTimeMillis() - startTime) + "ms");
                }
            }));
        }

        /**
         * （数字越小优先级越高）
         * @return
         */
        @Override
        public int getOrder() {
            return -100;
        }
    }


    /**
     * 自定义的config类，用来设置传入的参数
     */
    @Setter
    @Getter
    public static class Config {

        /**
         * 过滤类型
         */
        private String type;

        /**
         * 操作
         */
        private String op;


    }
}
