package com.wsw.redislock.controller;

import com.wsw.redislock.service.RedisLockService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Author WangSongWen
 * @Date: Created in 14:39 2020/9/24
 * @Description:
 */
@RestController
@RequestMapping("/redis")
public class RedisLockController {
    @Autowired
    private RedisLockService redisLockService;

    @Autowired
    private RedissonClient redissonClient;

    @GetMapping("/testUnLock")
    public void testUnLock(){
        redisLockService.testUnLock();
    }

    @GetMapping("/testLock")
    public void testLock() throws InterruptedException {
        redisLockService.testLock();
    }

    @GetMapping("/testRedisson")
    public Object testRedisson(Integer id){
        // 4. Get Redis based implementation of java.util.concurrent.locks.Lock
        RLock lock = redissonClient.getLock(id.toString());
        lock.lock(30, TimeUnit.SECONDS);
        try {
            redisLockService.testUnLock();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return "ok";
    }
}
