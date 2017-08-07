package jehc.junitmodules.junit.redis;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import jehc.xtmodules.xtcore.base.BaseJunit;
import jehc.xtmodules.xtcore.cache.RedisCache;
import jehc.xtmodules.xtcore.cache.RedisLock;
import jehc.xtmodules.xtcore.cache.redis.RedisUtil;

public class RedisLockJunit extends BaseJunit {
	@Autowired
	private RedisTemplate redisTemplate;
	@Autowired
	RedisUtil redisUtil;
	@Test
	public void reidsTest(){
		for(int i = 0; i < 100; i++){
			RedisLock lock = new RedisLock(redisTemplate, "string_test", 10000, 20000);
			try {
				if (lock.lock()) {
					 redisUtil.set("string_test", "string_test"+i);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				// 为了让分布式锁的算法更稳键些，持有锁的客户端在解锁之前应该再检查一次自己的锁是否已经超时，再去做DEL操作，因为可能客户端因为某个耗时的操作而挂起，
				// 操作完的时候锁因为超时已经被别人获得，这时就不必解锁了。 ————这里没有做
				lock.unlock();
			}
		}
	}
}
