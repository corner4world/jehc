package jehc.xtmodules.xtcore.cache.sharedjedis;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.ShardedJedis;

public class SharedRedisLockUtils {
	private static Logger logger = LoggerFactory.getLogger(SharedRedisLockUtils.class);
	// 加锁标志
	public static final String LOCKED_TIME = SimpleDateFormat.getDateTimeInstance().format(new Date());
	// 1毫秒=1000 000纳秒
	public static final long ONE_MILLI_NANOS = 1000000L;
	// 默认超时时间（毫秒）
	public static final long DEFAULT_TIME_OUT = 1 * 1000;
	public static final Random r = new Random();
	// 锁的超时时间（秒），过期删除
	public static final int EXPIRE = 5 * 60;

	private static ShardedRedisUtil shardedRedisUtil;
	private static RedisTemplate redisTemplate;
	

	public static RedisTemplate getRedisTemplate() {
		return redisTemplate;
	}

	public static void setRedisTemplate(RedisTemplate redisTemplate) {
		SharedRedisLockUtils.redisTemplate = redisTemplate;
	}

	public static ShardedRedisUtil getShardedRedisUtil() {
		return shardedRedisUtil;
	}

	public static void setShardedRedisUtil(ShardedRedisUtil shardedRedisUtil) {
		SharedRedisLockUtils.shardedRedisUtil = shardedRedisUtil;
	}

	///////////////////////////////通过sharded方式分布式锁开始//////////////////////////
	/**
	 * 分布式锁
	 * @param key
	 * @param timeout
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static boolean locksharded(String lockKey, long timeout) {
		boolean lockSuccess = false;
		ShardedJedis shardedJedis = shardedRedisUtil.getRedisClient();
		try {
			long start = System.currentTimeMillis();
			lockKey = "lock_" + lockKey;
			do {
				//设置过期时间为1秒
				int expired = 1000;
		        long locked_time = System.currentTimeMillis() + expired + 1; 
				long result = shardedJedis.setnx(lockKey,String.valueOf(locked_time));
				if (result == 1) {
					//成功返回true
					lockSuccess = true;
					break;
				} else {
					//判断是否死锁
					//获取原来值
					String lockTimeStr = shardedJedis.get(lockKey);
					if (StringUtils.isNumeric(lockTimeStr)) {
						//如果key存在，锁存在
						long lockTime = Long.valueOf(lockTimeStr);
						//与当前时间比较，如果小于当前时间代表已超时
						if (lockTime < System.currentTimeMillis()) {
							//锁已过期 重新设置过期时间
							String beforeStr = shardedJedis.getSet(lockKey,String.valueOf(locked_time));
							if (StringUtils.isNoneBlank(beforeStr) && beforeStr.equals(lockTimeStr)) {
								//表明锁由该线程获得
								lockSuccess = true;
								break;
							}
						}
					}
				}
				// 如果不等待，则直接返回
				if (timeout == 0) {
					break;
				}
				// 等待300ms继续加锁
				Thread.sleep(300);
				logger.debug("-----------------1：",System.currentTimeMillis() - start);
				logger.debug("-----------------2：",(System.currentTimeMillis() - start)<timeout);
			} while ((System.currentTimeMillis() - start) < timeout);
		} catch (Exception e) {
			shardedRedisUtil.returnResource(shardedJedis, true);
			e.printStackTrace();
		} finally {
			shardedRedisUtil.returnResource(shardedJedis);
		}
		return lockSuccess;
	}
	
	/**
	 * 释放分布式锁
	 * @param lockedKey 建议业务名称+业务主键
	 */
	@SuppressWarnings("static-access")
	public static void unlocksharded(String lockedKey) {
		lockedKey = "lock_"+lockedKey; 	
		ShardedJedis shardedJedis = shardedRedisUtil.getRedisClient();
		try {
			Long del = shardedJedis.del(lockedKey);
			logger.info("shardedRedisUtil.unlock-->lockedKey=‘{}’ ,del lock={}", lockedKey, del);
		} catch (Exception e) {
			shardedRedisUtil.returnResource(shardedJedis, true);
		} finally {
			shardedRedisUtil.returnResource(shardedJedis);
		}
	}
	
	///////////////////////////////通过sharded方式分布式锁结束//////////////////////////
	
	
	
	
	
	///////////////////////////////通过redisTemplate方式分布式锁开始//////////////////////////
	/**
	 * 设置时间
	 * @param key
	 * @param jsonString
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Boolean setNX(final String key, final String jsonString){
		Boolean result =  (Boolean) this.redisTemplate.execute(new RedisCallback<Boolean>(){
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                StringRedisConnection stringRedisConnection = (StringRedisConnection)connection;
                stringRedisConnection.select(3);
                Boolean result = stringRedisConnection.setNX(key, jsonString);
                return result;
            }
        });
        return result;
    }

	/**
	 * 设置值
	 * @param key
	 * @param jsonString
	 * @return
	 */
    @SuppressWarnings("unchecked")
	public String getSet(final String key, final String jsonString){
        String result =  (String) this.redisTemplate.execute(new RedisCallback<String>(){
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                StringRedisConnection stringRedisConnection = (StringRedisConnection)connection;
                stringRedisConnection.select(3);
                String result = stringRedisConnection.getSet(key, jsonString);
                return result;
            }
        });
        return result;
    }
    
    @SuppressWarnings({ "unchecked", "unused" })
	private String get(final String key) {
		Object obj = null;
		try {
			obj = redisTemplate.execute(new RedisCallback<Object>() {
				public Object doInRedis(RedisConnection connection) throws DataAccessException {
					StringRedisSerializer serializer = new StringRedisSerializer();
					byte[] data = connection.get(serializer.serialize(key));
					connection.close();
					if (data == null) {
						return null;
					}
					return serializer.deserialize(data);
				}
			});
		} catch (Exception e) {
			logger.error("get redis error, key : {}", key);
		}
		return obj != null ? obj.toString() : null;
	}
    
    public void lock(String lock) {
        // 设置过期时间为1秒
        int expired = 1000;
        long value = System.currentTimeMillis() + expired + 1; 
        boolean result = this.setNX(lock, String.valueOf(value));
        
        // true代表获得锁成功
        if(result) {
            // TODO 实现具体业务逻辑
        } else {
            // 不成功则判断是否死锁
            Long oldValue  =  Long.valueOf(this.get(lock));
            // 于当前时间比较，如果小于当前时间代表已超时
            if(oldValue < System.currentTimeMillis()) {
                // 设置并获取旧的值
                String getValue = this.getSet(lock, String.valueOf(value));
                if(Long.valueOf(getValue) == oldValue) {
                    // 获取锁成功
                } else {
                    // 已被其他进程获取锁
                }
            }
        }
    }
    public void unlock(String lockKey) {
    	redisTemplate.delete(lockKey);
	}
    ///////////////////////////////通过redisTemplate方式分布式锁结束//////////////////////////
}
