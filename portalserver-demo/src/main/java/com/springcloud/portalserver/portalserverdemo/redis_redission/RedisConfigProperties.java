package com.springcloud.portalserver.portalserverdemo.redis_redission;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 加载配置类
 */
@Component
@ConfigurationProperties(prefix = "spring.redis")
public class RedisConfigProperties {
    private String password;
    private cluster cluster;
    public static class cluster {
        private List nodes;
        public List getNodes() {
            return nodes;
        }
        public void setNodes(List nodes) {
            this.nodes = nodes;
        }
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public RedisConfigProperties.cluster getCluster() {
        return cluster;
    }
    public void setCluster(RedisConfigProperties.cluster cluster) {
        this.cluster = cluster;
    }
}
