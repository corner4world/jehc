package jehc.junitmodules.junit.redisunit.lock;

import org.junit.Test;

import jehc.xtmodules.xtcore.cache.jedis.JedisUtil;
/**
 * Redis乐观锁实现
 * @author 邓纯杰
 *
 */
public class JedisTransactionTest{
	@Test
	public void lock() {
		JedisUtil.set("name", "name");
		new ThreadClient3().start(); // 模拟客户端1
		new ThreadClient4().run(); // 模拟客户端2
		new ThreadClient5().run(); // 获取对象
	}
}
