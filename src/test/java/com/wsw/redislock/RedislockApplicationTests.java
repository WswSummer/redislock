package com.wsw.redislock;

import com.wsw.redislock.service.RedisLockService;
import org.junit.jupiter.api.Test;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RedislockApplicationTests {
    @Autowired
    private RedisLockService redisLockService;
    @Autowired
    private RedissonClient redissonClient;

    @Test
    void contextLoads() {
        redisLockService.testUnLock();
    }

}
