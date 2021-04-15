package com.springcloud.portalserver.portalserverdemo.redis_redission;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Redisson配置类
 */
@Configuration
public class RedissonConfigure {
    @Autowired
    private RedisConfigProperties redisConfigProperties;
    @Bean
    public Redisson redisson() {

        Config config = new Config();

        //看门狗默认值30000ms 也可以在配置文件设置值 锁不设定过期时间才生效？
        //每隔10秒去检查一下锁是否还在，还在就让说的有效时间再加20s
        config.setLockWatchdogTimeout(20000);
        /**         * 单机         */
//        config.useSingleServer().setAddress("redis://127.0.0.1:6379").setPassword(redisConfigProperties.getPassword());
         /**         * 哨兵         */
        // config.useSentinelServers().addSentinelAddress("");
        /**         * 集群         */
        List<String> clusterNodes = new ArrayList<>();
        for (int i = 0; i < redisConfigProperties.getCluster().getNodes().size(); i++) {
            clusterNodes.add("redis://" + redisConfigProperties.getCluster().getNodes().get(i));
        }
        config.useClusterServers().addNodeAddress(clusterNodes.toArray(new String[clusterNodes.size()]));
        return (Redisson) Redisson.create(config);
 }
}

