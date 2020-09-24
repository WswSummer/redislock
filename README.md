## redis 分布式锁

1. setnx key value（key若存在，set命令会改变value，但setnx却不会），只有key不存在才会设置成功

   在集群中，若使用这个命令，获取到了锁，但是挂掉了，没有释放锁，则其他的集群就获取不到锁

2. 设置过期时间 expire px

* 但是这两个操作，不是原子性操作

3. lua脚本    lua = setnx + expire
4. Reddisson
5. Spring integration