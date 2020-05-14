package com.springcloud.gateway.gatewaydemo;

import com.springcloud.gateway.gatewaydemo.filter.RequestTimeFilter;
import com.springcloud.gateway.gatewaydemo.filter.TokenFilter;
import com.springcloud.gateway.gatewaydemo.resolver.UriKeyResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.GatewayFilterSpec;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.gateway.route.builder.UriSpec;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;

@SpringBootApplication
@EnableEurekaClient
public class GatewayDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayDemoApplication.class, args);
    }

    /**
     * 自定义Gatewayfilter
     * @Author: hhj
     * @Date: Created in  2020/5/13
     */
//@Bean
//public RouteLocator getRouteLocator(RouteLocatorBuilder builder) {
//    return builder.routes().
//            route(new Function<PredicateSpec, Route.AsyncBuilder>() {
//                @Override
//                public Route.AsyncBuilder apply(PredicateSpec predicateSpec) {
//                    return predicateSpec
//                            .path("/portal-demo/sayHelloworld2")
//                            .filters(new Function<GatewayFilterSpec, UriSpec>() {
//                                @Override
//                                public UriSpec apply(GatewayFilterSpec gatewayFilterSpec) {
//                                    return gatewayFilterSpec.stripPrefix(0).filter(new RequestTimeFilter());
//                                }
//                            })
//                            .uri("lb://portal-demo")
//                            .id("consumer9090)");
//
//                }
//            }).build();
//}



//    @Bean
//    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
//        return builder.routes().build();
//    }


//@Bean
//public RouteLocator myRoutes(RouteLocatorBuilder builder) {
//    return builder.routes()
//            .route(p -> p
//                    .path("/get")
//                    .filters(f -> f.addRequestHeader("Hello", "World"))
//                    .uri("http://httpbin.org:80"))
//            .route(p -> p
//                    .host("*.hystrix.com")
//                    .filters(f -> f.hystrix(config -> config.setName("mycmd")))
//                    .uri("http://httpbin.org:80")).build();
//}

}
