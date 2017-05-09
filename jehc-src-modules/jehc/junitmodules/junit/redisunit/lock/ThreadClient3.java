package jehc.junitmodules.junit.redisunit.lock;

import jehc.xtmodules.xtcore.cache.jedis.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class ThreadClient3 extends Thread {
	public void run() {
		try {
			Jedis jedis = JedisUtil.getJedis();
			if ("OK".equals(jedis.watch("name"))) {
				System.out.println("key:name 被监视");
			}
			sleep(3000);
			Transaction t = jedis.multi();
			t.set("name", "client3");
			if (t.exec() == null) {
				System.out.println("数据库中的name 已经被修改,ThreadClient3无法set  name");
			}
			jedis.unwatch();
		} catch (InterruptedException e) {
			e.printStackTrace();
			JedisUtil.closeJedis();
		}finally{
			JedisUtil.closeJedis();
		}
	}
}
