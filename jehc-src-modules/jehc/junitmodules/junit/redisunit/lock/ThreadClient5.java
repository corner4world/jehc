package jehc.junitmodules.junit.redisunit.lock;

import jehc.xtmodules.xtcore.cache.jedis.JedisUtil;

public class ThreadClient5 extends Thread {
	public void run() {
		try {
			sleep(4000);
			String name = JedisUtil.getJedis().get("name");
			System.out.println("ThreadClient5 获取name 的值为:" + name);
		} catch (InterruptedException e) {
			e.printStackTrace();
			JedisUtil.closeJedis();
		}finally{
			JedisUtil.closeJedis();
		}
	}
}
