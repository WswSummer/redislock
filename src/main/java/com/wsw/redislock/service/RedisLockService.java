package com.wsw.redislock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @Author WangSongWen
 * @Date: Created in 14:42 2020/9/24
 * @Description:
 */
@Service
public class RedisLockService {
    @Autowired
    private RedisLockRegistry redisLockRegistry;

    private int num = 20;

    // 不加锁
    public void testUnLock(){
        String s = Thread.currentThread().getName();
        if (num > 0){
            System.out.println(s + "=>排号成功,号码是: " + num);
            num--;
        }else {
            System.out.println(s + "=>排号失败,号码已被抢光!");
        }
    }

    // 加锁
    public void testLock() throws InterruptedException {
        Lock lock = redisLockRegistry.obtain("lock");

        boolean isLock = lock.tryLock(5, TimeUnit.SECONDS); // 等待获取锁的时间，5秒还没拿到就放弃
        String s = Thread.currentThread().getName();
        if (num > 0 && isLock){
            System.out.println(s + "=>排号成功,号码是: " + num);
            num--;
            lock.unlock();
        }else {
            System.out.println(s + "=>排号失败,号码已被抢光!");
        }
    }
}
