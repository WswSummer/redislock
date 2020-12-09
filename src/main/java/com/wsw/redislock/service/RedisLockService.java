package com.wsw.redislock.service;

import com.wsw.redislock.entity.RedisLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @Author WangSongWen
 * @Date: Created in 14:42 2020/9/24
 * @Description:
 */
@Service
@Slf4j
public class RedisLockService {
    private static final String REDIS_LOCK_PRE = "testLock:";
    private static final boolean REDIS_ENABLE = true;

    @Autowired
    private RedisLockRegistry redisLockRegistry;
    @Autowired
    private StringRedisTemplate redisTemplate;

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

    public void lockAndChangeData(String tableId, String tableName, Map<String, Object> dataMap){
        RedisLock redisLock = null;
        if (REDIS_ENABLE) {
            try {
                redisLock = new RedisLock(redisTemplate, REDIS_LOCK_PRE + tableName, 5 * 60, 500);
                redisLock.tryLock();
            } catch (Exception e) {
                log.error("获取分布式锁异常: " + e.getMessage());
            }
        }

        try {
            // 业务执行
            System.out.println(tableId + ", " + tableName + ", " + dataMap);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (REDIS_ENABLE && null != redisLock){
                redisLock.unlock();
            }
        }
    }
}
