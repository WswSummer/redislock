package com.wsw.redislock;

import com.wsw.redislock.service.RedisLockService;
import org.junit.jupiter.api.Test;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class RedislockApplicationTests {
    @Autowired
    private RedisLockService redisLockService;

    @Test
    void test1() {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("1", "wsw");
        dataMap.put("2", "www");
        redisLockService.lockAndChangeData("1", "table1", dataMap);
    }

}
