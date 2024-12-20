package com.example.springdemo.demos.web;

import org.redisson.Redisson;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redissonClient() {
        Config config = new Config();
        // 单机模式
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        // 集群模式
        // config.useClusterServers().addNodeAddress("redis://127.0.0.1:7000", "redis://127.0.0.1:7001", "redis://127.0.0.1:7002");
        return Redisson.create(config);
    }

    @Bean
    public RBlockingQueue<String> blockingQueue(RedissonClient redissonClient) {
        return redissonClient.getBlockingQueue("TOKEN-RENEWAL");
    }

    @Bean
    public RDelayedQueue<String> delayedQueue(RBlockingQueue<String> blockingQueue,
                                              RedissonClient redissonClient) {
        return redissonClient.getDelayedQueue(blockingQueue);
    }
}