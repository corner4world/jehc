package jehc.xtmodules.xtcore.cache.sharedjedis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class ShardedRedisUtil {
	private static final Logger logger = LoggerFactory.getLogger(ShardedRedisUtil.class);
	private static ShardedJedisPool shardedJedisPool;

	public static ShardedJedis getRedisClient() {
		ShardedJedis shardJedis = null;
		try {
			shardJedis = shardedJedisPool.getResource();
			return shardJedis;
		} catch (Exception e) {
			logger.error("[shardedRedisUtil] getRedisClent error:" + e.getMessage());
			if (null != shardJedis)
				shardJedis.close();
		}
		return null;
	}

	public static void returnResource(ShardedJedis shardedJedis) {
		shardedJedis.close();
	}

	@SuppressWarnings("deprecation")
	public static void returnResource(ShardedJedis shardedJedis, boolean broken) {
		if (broken) {
			shardedJedisPool.returnBrokenResource(shardedJedis);
		} else {
			shardedJedis.close();
		}
	}

	public static ShardedJedisPool getShardedJedisPool() {
		return shardedJedisPool;
	}

	public static void setShardedJedisPool(ShardedJedisPool shardedJedisPool) {
		ShardedRedisUtil.shardedJedisPool = shardedJedisPool;
	}
}
