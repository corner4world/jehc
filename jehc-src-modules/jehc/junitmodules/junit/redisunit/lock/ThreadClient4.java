package jehc.junitmodules.junit.redisunit.lock;

import jehc.xtmodules.xtcore.cache.jedis.JedisUtil;
import redis.clients.jedis.Jedis;

public class ThreadClient4 extends Thread {

	public void run() {
		try {
			sleep(1000);
			Jedis jedis = JedisUtil.getJedis();
			if ("OK".equals(jedis.set("name", "client4"))) {
				System.out.println("ThreadClient4 set name 成功");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			JedisUtil.closeJedis();
		}
	}
}
