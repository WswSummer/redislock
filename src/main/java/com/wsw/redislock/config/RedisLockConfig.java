package com.wsw.redislock.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.integration.redis.util.RedisLockRegistry;

/**
 * @Author WangSongWen
 * @Date: Created in 14:33 2020/9/24
 * @Description:
 */
@Configuration
public class RedisLockConfig {
    // 分布式锁组名称
    private static final String integrationGroupKey = "redis_integration";
    // 分布式锁持有时间---避免获取后处理业务异常，锁不释放
    private static final long expireAfter = 10L * 1000L;

    @Bean
    public RedisLockRegistry redisLockRegistry(RedisConnectionFactory factory){
        return new RedisLockRegistry(factory, integrationGroupKey, expireAfter);
    }

    // 使用Redisson
    @Bean
    public RedissonClient redissonClient(){
        // 1. Create config object
        Config config = new Config();
        config.useSingleServer().setAddress("redis://39.107.80.231:6379");
        /*config.useClusterServers()
                // use "rediss://" for SSL connection
                .addNodeAddress("redis://127.0.0.1:7181");*/

        RedissonClient redisson = Redisson.create(config);
        return redisson;
    }
}
