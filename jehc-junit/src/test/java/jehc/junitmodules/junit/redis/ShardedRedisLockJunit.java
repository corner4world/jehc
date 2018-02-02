package jehc.junitmodules.junit.redis;

//import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import jehc.junitmodules.junit.base.BaseJunit;
import jehc.xtmodules.xtcore.cache.redis.RedisUtil;
import jehc.xtmodules.xtcore.cache.sharedjedis.SharedRedisLockUtils;

public class ShardedRedisLockJunit extends BaseJunit {
	private static Logger logger = LoggerFactory.getLogger(ShardedRedisLockJunit.class);
	@Autowired
	RedisUtil redisUtil;

	@Test
	public void test() {
		try {
			
//			if(!SharedRedisLockUtils.locksharded("string_test",1000))return;
//			//获取到锁操作
//			logger.info("已获取锁：{}", Thread.currentThread().getName());
//			redisUtil.set("string_test", "string_test_线程1" );
//			// 释放锁
//			SharedRedisLockUtils.unlocksharded("string_test");
			
			/////////////////采用线程池测试并发//////////////////
			ExecutorService pool = Executors.newFixedThreadPool(5);
			// 加入10个任务
			for (int i = 1; i <= 10; i++) {
				final int task = i;
				pool.execute(new Runnable() {
					public void run() {
						System.out.println("现在运行的是第【 " + task + "】任务,任务名称：" + Thread.currentThread().getName());
						// 如果获取不到锁则停止
						if(!SharedRedisLockUtils.locksharded("string_test",0))return;
						//获取到锁操作
//						logger.info("已获取锁：{}", Thread.currentThread().getName());
						redisUtil.set("string_test", "string_test_线程" + task);
						// 释放锁
						SharedRedisLockUtils.unlocksharded("string_test");
						System.out.println("任务 【" + task + "】运行完成");
					}
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
