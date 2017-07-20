package jehc.xtmodules.xtcore.cache.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.*;

public class RedisUtil {

	private RedisPool redisPool;
	public RedisPool getRedisPool() {
		return redisPool;
	}

	public void setRedisPool(RedisPool redisPool) {
		this.redisPool = redisPool;
	}
	public Jedis getJedis() {
		return redisPool.getInstance();
	}

	public void releaseJedis(Jedis jedis) {
		redisPool.returnResource(jedis);
	}

	public void releaseBrokenJedis(Jedis jedis) {
		redisPool.returnBrokenResource(jedis);
	}

	/**
	 * hash 通过key给field设置指定的值,如果key不存在,则先创建 ,存在会覆盖原来的值
	 * 
	 * @param key
	 * @param field字段
	 * @param value
	 * @return 如果不存在，新建的返回1，存在返回0, 异常返回null
	 * 
	 */
	public Long hset(String key, String field, String value) {
		Jedis jedis = getJedis();
		Long result = jedis.hset(key, field, value);
		releaseJedis(jedis);
		return result;
	}

	/**
	 * Hash 为哈希表 key 中的域 field 的值加上增量 value
	 * 
	 * @param key
	 * @param field
	 * @param value
	 * @return
	 */
	public Long hincrBy(String key, String field, long value) {
		Jedis jedis = getJedis();
		Long result = jedis.hincrBy(key, field, value);
		releaseJedis(jedis);
		return result;
	}

	/**
	 * 
	 * 通过key给field设置指定的值,如果key不存在则先创建,如果field已经存在，操作无效
	 * 
	 * @param key
	 * @param field
	 * @param value
	 * @return 不存在新建返回1，存在返回0
	 */
	public Long hsetnx(String key, String field, String value) {
		Jedis jedis = getJedis();
		Long result = jedis.hsetnx(key, field, value);
		releaseJedis(jedis);
		return result;
	}

	/**
	 * 通过key同时设置 hash的多个field
	 * 
	 * @param key
	 * @param hash
	 * @return 返回OK 异常返回null
	 */
	public String hmset(String key, Map<String, String> hash) {
		Jedis jedis = getJedis();
		String result = jedis.hmset(key, hash);
		releaseJedis(jedis);
		return result;
	}

	/**
	 * 通过key 和 field 获取指定的 value
	 * 
	 * @param key
	 * @param field
	 * @return 没有返回null
	 */
	public String hget(String key, String field) {
		Jedis jedis = getJedis();
		String result = jedis.hget(key, field);
		releaseJedis(jedis);
		return result;
	}

	/**
	 * 通过key 和 fields 获取指定的value 如果没有对应的value则返回null
	 * 
	 * @param key
	 * @param fields可以使 一个String 也可以是 String数组
	 * @return
	 */
	public List<String> hmget(String key, String... fields) {
		Jedis jedis = getJedis();
		List<String> result = jedis.hmget(key, fields);
		releaseJedis(jedis);
		return result;
	}

	/**
	 * 通过key获取所有的field和value
	 * 
	 * @param key
	 * @return
	 */
	public Map<String, String> hgetAll(String key) {
		Jedis jedis = getJedis();
		Map<String, String> result = jedis.hgetAll(key);
		releaseJedis(jedis);
		return result;
	}

	/**
	 * 通过key删除field的value
	 * 
	 * @param key
	 * @return
	 */
	public Long hdel(String key, String field) {
		Jedis jedis = getJedis();
		Long result = jedis.hdel(key, field);
		releaseJedis(jedis);
		return result;
	}

	/**
	 * 返回key为键中存放的field值的个数
	 * 
	 * @param key
	 * @return
	 */
	public Long hlen(String key) {
		Jedis jedis = getJedis();
		Long result = jedis.hlen(key);
		releaseJedis(jedis);
		return result;
	}

	/**
	 * 查看key是否存在指定的field
	 * 
	 * @param key
	 * @return
	 */
	public Boolean hexists(String key, String field) {
		Jedis jedis = getJedis();
		Boolean result = jedis.hexists(key, field);
		releaseJedis(jedis);
		return result;
	}

	/**
	 * 返回key存储的map对象中的所有key
	 * 
	 * @param key
	 * @return
	 */
	public Set<String> hkeys(String key) {
		Jedis jedis = getJedis();
		Set<String> result = jedis.hkeys(key);
		releaseJedis(jedis);
		return result;
	}

	/**
	 * 返回key存储的map对象中的所有键的values值
	 * 
	 * @param key
	 * @return
	 */
	public List<String> hvals(String key) {
		Jedis jedis = getJedis();
		List<String> result = jedis.hvals(key);
		releaseJedis(jedis);
		return result;
	}

	/**
	 * 判断key是否存在
	 * 
	 * @param key
	 * @return true OR false
	 */
	public boolean exists(String key) {
		Jedis jedis = null;
		boolean result = false;
		try {
			jedis = getJedis();
			result = jedis.exists(key);
		} catch (Exception e) {
			releaseBrokenJedis(jedis);
			e.printStackTrace();
		} finally {
			releaseJedis(jedis);
		}

		return result;
	}

	/**
	 * 删除指定的key,也可以传入一个包含key的数组
	 * 
	 * @param keys
	 * @return 返回删除成功的个数
	 */
	public Long del(String... keys) {
		Jedis jedis = null;
		Long result = 0L;
		try {
			jedis = getJedis();
			result = jedis.del(keys);
		} catch (Exception e) {
			releaseBrokenJedis(jedis);
			e.printStackTrace();
			result = 0L;
		} finally {
			releaseJedis(jedis);
		}
		return result;
	}

	/**
	 * 对key的对应value值排序
	 * 
	 * @return
	 */
	public List<String> sort(String key) {
		Jedis jedis = getJedis();
		List<String> result = jedis.sort(key);
		releaseJedis(jedis);
		return result;
	}

	/**
	 * 将当前数据库的 ke移动到给定的数据库 db 当中
	 * 
	 * @return
	 */
	public Long move(String key, int dbIndex) {
		Jedis jedis = getJedis();
		Long result = jedis.move(key, dbIndex);
		releaseJedis(jedis);
		return result;
	}

	/**
	 * 返回某个key元素的数据类型 ( none:不存在,string:字符,list,set,zset,hash)
	 * 
	 * @return
	 */
	public String type(String key) {
		Jedis jedis = getJedis();
		String result = jedis.type(key);
		releaseJedis(jedis);
		return result;
	}

	/**
	 * 返回当前数据库的key的总数
	 * 
	 * @return
	 */
	public Long dbsize() {
		Jedis jedis = getJedis();
		Long result = jedis.dbSize();
		releaseJedis(jedis);
		return result;
	}

	/**
	 * 设置某个key的过期时间(秒),(EXPIRE bruce
	 * 1000：设置bruce这个key1000秒后系统自动删除)注意：如果在还没有过期的时候，对值进行了改变，那么那个值会被清除。
	 * 
	 * @return
	 */
	public Long expire(String key, int seconds) {
		Jedis jedis = getJedis();
		Long result = jedis.expire(key, seconds);
		releaseJedis(jedis);
		return result;
	}

	/**
	 * EXPIREAT 的作用和 EXPIRE 类似，都用于为 key 设置生存时间。 不同在于 EXPIREAT 命令接受的时间参数是 UNIX
	 * 时间戳(unix timestamp)。
	 * 
	 * @return
	 */

	public Long expireAt(String key, Long unixTime) {
		Jedis jedis = getJedis();
		Long result = jedis.expireAt(key, unixTime);
		releaseJedis(jedis);
		return result;
	}

	/**
	 * List 通过key在list头部添加值
	 * 
	 * @param key
	 * @param value
	 * @return 在 push 操作后的 list 长度。
	 */
	public Long lpush(String key, String value) {
		Jedis jedis = getJedis();
		Long result = jedis.lpush(key, value);
		releaseJedis(jedis);
		return result;
	}

	/**
	 * List 向存于 key 的列表的尾部插入所有指定的值。如果 key 不存在，那么会创建一个空的列表然后再进行 push 操作。 当 key
	 * 保存的不是一个列表，那么会返回一个错误。
	 * 
	 * @param key
	 * @param value
	 * @return 在 push 操作后的列表长度
	 */
	public Long rpush(String key, String value) {
		Jedis jedis = getJedis();
		Long result = jedis.rpush(key, value);
		releaseJedis(jedis);
		return result;
	}

	/**
	 * List 获取list的长度
	 * 
	 * @param key
	 * @return
	 */
	public Long llen(String key) {
		Jedis jedis = getJedis();
		Long result = jedis.llen(key);
		releaseJedis(jedis);
		return result;
	}

	/**
	 * List 返回存储在 key 的列表里指定范围内的元素
	 * 
	 * @param key
	 * @parm start 开始位置
	 * @param end
	 *            结束位置 -1表示最后一个
	 * @return
	 */
	public List<String> lrange(String key, long start, long end) {
		Jedis jedis = getJedis();
		List<String> result = jedis.lrange(key, start, end);
		releaseJedis(jedis);
		return result;
	}

	/**
	 * List 截取(trim)一个已存在的 list，这样 list 就会只包含指定范围的指定元素
	 * 
	 * @param key
	 * @parm start 开始位置
	 * @param end
	 *            结束位置 -1表示最后一个
	 * @return
	 */

	public String ltrim(String key, long start, long end) {
		Jedis jedis = getJedis();
		String result = jedis.ltrim(key, start, end);
		releaseJedis(jedis);
		return result;
	}

	/**
	 * List 通过key在list头部添加值 只有当 key 已经存在并且存着一个 list 的时候，在这个 key 下面的 list 的头部插入
	 * value。 与 LPUSH 相反，当 key 不存在的时候不会进行任何操作。
	 * 
	 * @param key
	 * @param value
	 * @return 在 push 操作后的 list 长度。
	 */

	public Long lpushx(String key, String value) {
		Jedis jedis = getJedis();
		Long result = jedis.lpushx(key, value);
		releaseJedis(jedis);
		return result;
	}

	public Long rpushx(String key, String value) {
		Jedis jedis = getJedis();
		Long result = jedis.rpushx(key, value);
		releaseJedis(jedis);
		return result;
	}

	/**
	 * 弹出 List 的第一个元素
	 * 
	 * @param key
	 * @return
	 */
	public String lpop(String key) {
		Jedis jedis = getJedis();
		String result = jedis.lpop(key);
		releaseJedis(jedis);
		return result;
	}

	public String rpop(String key) {
		Jedis jedis = getJedis();
		String result = jedis.rpop(key);
		releaseJedis(jedis);
		return result;
	}

	/**
	 * 根据参数 COUNT 的值，移除列表中与参数 VALUE 相等的元素。 count > 0 : 从表头开始向表尾搜索，移除与 VALUE
	 * 相等的元素，数量为 COUNT 。 count < 0 : 从表尾开始向表头搜索，移除与 VALUE 相等的元素，数量为 COUNT 的绝对值。
	 * count = 0 : 移除表中所有与 VALUE 相等的值。
	 */

	public Long lrem(String key, long count, String value) {
		Jedis jedis = getJedis();
		Long result = jedis.lrem(key, count, value);
		releaseJedis(jedis);
		return result;
	}

	/**
	 * 设置 index 位置的list元素的值为 value
	 * 
	 * @param key
	 * @param index
	 * @param value
	 * @return
	 */
	public String lset(String key, long index, String value) {
		Jedis jedis = getJedis();
		String result = jedis.lset(key, index, value);
		releaseJedis(jedis);
		return result;
	}

	/**
	 * 返回列表 key 中，下标为 index 的元素。
	 * 
	 * @param key
	 * @param index
	 * @return
	 */
	public String lindex(String key, long index) {
		Jedis jedis = getJedis();
		String result = jedis.lindex(key, index);
		releaseJedis(jedis);
		return result;
	}

	/**
	 * 命令 RPOPLPUSH 在一个原子时间内，执行以下两个动作： 将列表 source 中的最后一个元素(尾元素)弹出，并返回给客户端。 将
	 * source 弹出的元素插入到列表 destination ，作为 destination 列表的的头元素。 举个例子，你有两个列表 source
	 * 和 destination ， source 列表有元素 a, b, c ， destination 列表有元素 x, y, z ， 执行
	 * RPOPLPUSH source destination 之后， source 列表包含元素 a, b ， destination 列表包含元素
	 * c, x, y, z ， 并且元素 c 会被返回给客户端。
	 * 
	 * @param srcKey
	 * @param dstKey
	 * @return
	 */
	public String rpoplpush(String srcKey, String dstKey) {
		Jedis jedis = getJedis();
		String result = jedis.rpoplpush(srcKey, dstKey);
		releaseJedis(jedis);
		return result;
	}

	/**
	 * BRPOPLPUSH 是 RPOPLPUSH 的阻塞版本，当给定列表 source 不为空时， BRPOPLPUSH 的表现和 RPOPLPUSH
	 * 一样。 当列表 source 为空时， BRPOPLPUSH 命令将阻塞连接，直到等待超时，或有另一个客户端对 source 执行 LPUSH 或
	 * RPUSH 命令为止。 超时参数 timeout 接受一个以秒为单位的数字作为值。超时参数设为 0 表示阻塞时间可以无限期延长(block
	 * indefinitely) 。
	 * 
	 * @param source
	 * @param destination
	 * @param timeout
	 * @return
	 */
	public String brpoplpush(String source, String destination, int timeout) {
		Jedis jedis = getJedis();
		String result = jedis.brpoplpush(source, destination, timeout);
		releaseJedis(jedis);
		return result;
	}

	/**
	 * 将一个或多个 member 元素加入到集合 key 当中，已经存在于集合的 member 元素将被忽略(set不重复)。 假如 key
	 * 不存在，则创建一个只包含 member 元素作成员的集合。
	 * 
	 * @param key
	 * @param member
	 * @return
	 */
	public Long sadd(String key, String member) {
		Jedis jedis = getJedis();
		Long result = jedis.sadd(key, member);
		releaseJedis(jedis);
		return result;
	}

	/**
	 * 移除集合 key 中的一个 member 元素，不存在的 member 元素会被忽略。
	 * 
	 * @param key
	 * @param member
	 * @return
	 */
	public Long srem(String key, String member) {
		Jedis jedis = getJedis();
		Long result = jedis.srem(key, member);
		releaseJedis(jedis);
		return result;
	}

	/**
	 * 返回集合 key 中的所有成员。
	 * 
	 * @param key
	 * @return
	 */
	public Set<String> smembers(String key) {
		Jedis jedis = getJedis();
		Set<String> result = jedis.smembers(key);
		releaseJedis(jedis);
		return result;
	}

	/**
	 * 判断 member 元素是否集合 key 的成员。
	 * 
	 * @param key
	 * @param member
	 * @return
	 */
	public Boolean sismember(String key, String member) {
		Jedis jedis = getJedis();
		Boolean result = jedis.sismember(key, member);
		releaseJedis(jedis);
		return result;
	}

	/**
	 * 返回集合 key集合中元素的数量)。
	 * 
	 * @param key
	 * @return
	 */
	public Long scard(String key) {
		Jedis jedis = getJedis();
		Long result = jedis.scard(key);
		releaseJedis(jedis);
		return result;
	}

	/**
	 * 将 member 元素从 source 集合移动到 destination 集合。 SMOVE 是原子性操作。 如果 source
	 * 集合不存在或不包含指定的 member 元素，则 SMOVE 命令不执行任何操作，仅返回 0 。否则， member 元素从 source
	 * 集合中被移除，并添加到 destination 集合中去。 当 destination 集合已经包含 member 元素时， SMOVE
	 * 命令只是简单地将 source 集合中的 member 元素删除。 当 source 或 destination 不是集合类型时，返回一个错误。
	 * 
	 * @param srckey
	 * @param dstkey
	 * @param member
	 * @return
	 */
	public Long smove(String srckey, String dstkey, String member) {
		Jedis jedis = getJedis();
		Long result = jedis.smove(srckey, dstkey, member);
		releaseJedis(jedis);
		return result;
	}

	/**
	 * 移除并返回集合中的一个随机元素。
	 * 
	 * @param key
	 * @return
	 */
	public String spop(String key) {
		Jedis jedis = getJedis();
		String result = jedis.spop(key);
		releaseJedis(jedis);
		return result;
	}

	/**
	 * 返回集合中的一个随机元素
	 * 
	 * @param key
	 * @return
	 */
	public String srandmember(String key) {
		Jedis jedis = getJedis();
		String result = jedis.srandmember(key);
		releaseJedis(jedis);
		return result;
	}

	/**
	 * 返回给定集合的交集。
	 * 
	 * @param keys
	 * @return
	 */
	public Set<String> sinter(String... keys) {
		Jedis jedis = getJedis();
		Set<String> result = jedis.sinter(keys);
		releaseJedis(jedis);
		return result;
	}

	/**
	 * 类似于 SINTER 命令，但它将结果保存到 destination 集合，而不是简单地返回结果集
	 * 
	 * @param dstkey
	 * @param keys
	 * @return
	 */
	public Long sinterstore(String dstkey, String... keys) {
		Jedis jedis = getJedis();
		Long result = jedis.sinterstore(dstkey, keys);
		releaseJedis(jedis);
		return result;
	}

	/**
	 * 返回所有给定集合的并集
	 * 
	 * @param keys
	 * @return
	 */
	public Set<String> sunion(String... keys) {
		Jedis jedis = getJedis();
		Set<String> result = jedis.sunion(keys);
		releaseJedis(jedis);
		return result;
	}

	public Long sunionstore(String dstkey, String... keys) {
		Jedis jedis = getJedis();
		Long result = jedis.sunionstore(dstkey, keys);
		releaseJedis(jedis);
		return result;
	}

	/**
	 * 返回所有给定集合之间的差集。
	 * 
	 * @param keys
	 * @return
	 */
	public Set<String> sdiff(String... keys) {
		Jedis jedis = getJedis();
		Set<String> result = jedis.sdiff(keys);
		releaseJedis(jedis);
		return result;
	}

	public Long sdiffstore(String dstkey, String... keys) {
		Jedis jedis = getJedis();
		Long result = jedis.sdiffstore(dstkey, keys);
		releaseJedis(jedis);
		return result;
	}

	/**
	 * server 清空整个 Redis 服务器的数据(删除所有数据库的所有 key )。
	 * 
	 * @return
	 */
	public String flushAll() {
		Jedis jedis = getJedis();
		String result = jedis.flushAll();
		releaseJedis(jedis);
		return result;
	}

	/**
	 * 清空当前数据库中的所有 key。
	 * 
	 * @return
	 */
	public String flushDB() {
		Jedis jedis = getJedis();
		String result = jedis.flushDB();
		releaseJedis(jedis);
		return result;
	}

	/**
	 * 停止所有客户端 如果有至少一个保存点在等待，执行 SAVE 命令 如果 AOF 选项被打开，更新 AOF 文件 关闭 redis
	 * 服务器(server)
	 * 
	 * @return
	 */

	public String shutdown() {
		Jedis jedis = getJedis();
		String result = jedis.shutdown();
		releaseJedis(jedis);
		return result;
	}

	/**
	 * sorted set 将一个 member 元素及其 score值加入到有序集 key 当中。 如果某个 member
	 * 已经是有序集的成员，那么更新这个 member 的 score 值， 并通过重新插入这个 member 元素，来保证该 member
	 * 在正确的位置上。
	 * 
	 * @param key
	 * @param score
	 * @param member
	 * @return
	 */
	public Long zadd(String key, double score, String member) {
		Jedis jedis = getJedis();
		Long result = jedis.zadd(key, score, member);
		releaseJedis(jedis);
		return result;
	}

	/**
	 * sorted set 移除有序集 key 中的一成员member，不存在的成员将被忽略。
	 * 
	 * @param key
	 * @param member
	 * @return
	 */
	public Long zrem(String key, String member) {
		Jedis jedis = getJedis();
		Long result = jedis.zrem(key, member);
		releaseJedis(jedis);
		return result;
	}

	/**
	 * sorted set 返回集合 key集合中元素的数量
	 * 
	 * @param key
	 * @return
	 */
	public Long zcard(String key) {
		Jedis jedis = getJedis();
		Long result = jedis.zcard(key);
		releaseJedis(jedis);
		return result;
	}

	/**
	 * 返回有序集 key 中， score 值在 min 和 max 之间(默认包括 score 值等于 min 或 max )的成员的数量。
	 * 
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 */
	public Long zcount(String key, double min, double max) {
		Jedis jedis = getJedis();
		Long result = jedis.zcount(key, min, max);
		releaseJedis(jedis);
		return result;
	}

	/**
	 * 返回有序集 key 中，成员 member 的 score 值。
	 * 
	 * @param key
	 * @param member
	 * @return
	 */
	public Double zscore(String key, String member) {
		Jedis jedis = getJedis();
		Double result = jedis.zscore(key, member);
		releaseJedis(jedis);
		return result;
	}

	/**
	 * 为有序集 key 的成员 member 的 score 值加上增量"score"
	 * 
	 * @param key
	 * @param score
	 * @param member
	 * @return
	 */
	public Double zincrby(String key, double score, String member) {
		Jedis jedis = getJedis();
		Double result = jedis.zincrby(key, score, member);
		releaseJedis(jedis);
		return result;
	}

	/**
	 * 返回有序集 key 中，指定区间内的成员。其中成员的位置按 score 值递增(从小到大)来排序。
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public Set<String> zrange(String key, int start, int end) {
		Jedis jedis = getJedis();
		Set<String> result = jedis.zrange(key, start, end);
		releaseJedis(jedis);
		return result;
	}

	/**
	 * 其中成员的位置按 score 值递减(从大到小)来排列。
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public Set<String> zrevrange(String key, int start, int end) {
		Jedis jedis = getJedis();
		Set<String> result = jedis.zrevrange(key, start, end);
		releaseJedis(jedis);
		return result;
	}

	/**
	 * 返回有序集 key 中， score 值介于 max 和 min 之间(默认包括等于 max 或 min )的所有的成员。 有序集成员按
	 * score 值递减(从大到小)的次序排列。
	 * 
	 * @param key
	 * @param max
	 * @param min
	 * @param offset
	 * @param count
	 * @return
	 */
	public Set<String> zrevrangeByScore(String key, double max, double min, int offset, int count) {
		Jedis jedis = getJedis();
		try {
			Set<String> result = jedis.zrevrangeByScore(key, max, min, offset, count);
			return result;
		} finally {
			releaseJedis(jedis);
		}
	}

	public Set<String> zrevrangeByScore(String key, double max, double min) {
		Jedis jedis = getJedis();
		try {
			Set<String> result = jedis.zrevrangeByScore(key, max, min);
			return result;
		} finally {
			releaseJedis(jedis);
		}
	}

	/**
	 * 返回有序集 key 中，所有 score 值介于 min 和 max 之间(包括等于 min 或 max )的成员。 有序集成员按 score
	 * 值递增(从小到大)次序排列。
	 * 
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 */
	public Set<String> zrangeByScore(String key, double min, double max) {
		Jedis jedis = getJedis();
		try {
			Set<String> result = jedis.zrangeByScore(key, min, max);
			return result;
		} finally {
			releaseJedis(jedis);
		}
	}

	/**
	 * 返回有序集 key 中成员 member 的排名。其中有序集成员按 score 值递增(从小到大)顺序排列。 排名以 0 为底，也就是说，
	 * score 值最小的成员排名为 0 。
	 */

	public Long zrank(String key, String member) {
		Jedis jedis = getJedis();
		Long result = jedis.zrank(key, member);
		releaseJedis(jedis);
		return result;
	}

	/**
	 * 返回有序集 key 中成员 member 的排名。其中有序集成员按 score 值递减(从大到小)排序。 排名以 0 为底，也就是说， score
	 * 值最大的成员排名为 0 。
	 * 
	 * @param key
	 * @param member
	 * @return
	 */
	public Long zrevrank(String key, String member) {
		Jedis jedis = getJedis();
		Long result = jedis.zrevrank(key, member);
		releaseJedis(jedis);
		return result;
	}

	/**
	 * 移除有序集 key 中，指定排名(rank)区间内的所有成员。
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public Long zremrangeByRank(String key, int start, int end) {
		Jedis jedis = getJedis();
		Long result = jedis.zremrangeByRank(key, start, end);
		releaseJedis(jedis);
		return result;
	}

	/**
	 * 移除有序集 key 中，所有 score 值介于 min 和 max 之间(包括等于 min 或 max )的成员。
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */

	public Long zremrangeByScore(String key, double start, double end) {
		Jedis jedis = getJedis();
		Long result = jedis.zremrangeByScore(key, start, end);
		releaseJedis(jedis);
		return result;
	}

	/**
	 * 计算给定的一个或多个有序集的交集，并将该交集(结果集)储存到 destination 。 默认情况下，结果集中某个成员的 score
	 * 值是所有给定集下该成员 score 值之和.
	 * 
	 * @param dstkey
	 * @param sets
	 * @return
	 */
	public Long zinterstore(String dstkey, String... sets) {
		Jedis jedis = getJedis();
		Long result = jedis.zinterstore(dstkey, sets);
		releaseJedis(jedis);
		return result;
	}

	/**
	 * 计算给定的一个或多个有序集的并集，并将该并集(结果集)储存到 destination 。 默认情况下，结果集中某个成员的 score
	 * 值是所有给定集下该成员 score 值之 和 。
	 * 
	 * @param dstkey
	 * @param sets
	 * @return
	 */
	public Long zunionstore(String dstkey, String... sets) {
		Jedis jedis = getJedis();
		Long result = jedis.zunionstore(dstkey, sets);
		releaseJedis(jedis);
		return result;
	}

	/**
	 * String 通过key获取储存在redis中的value 并释放连接
	 * 
	 * @param key
	 * @return 成功返回value 失败返回null
	 */
	public String get(String key) {
		Jedis jedis = null;
		String result = null;
		try {
			jedis = getJedis();
			result = jedis.get(key);
		} catch (Exception e) {
			releaseBrokenJedis(jedis);
			e.printStackTrace();
		} finally {
			releaseJedis(jedis);
		}
		return result;
	}

	/**
	 * string 向redis存入key和value,并释放连接资源 如果key已经存在 则覆盖
	 * 
	 * @param key
	 * @param value
	 * @return 成功 返回OK 失败返回 0
	 */
	public String set(String key, String value) {
		Jedis jedis = null;
		String result = null;
		try {
			jedis = getJedis();
			result = jedis.set(key, value);
		} catch (Exception e) {
			releaseBrokenJedis(jedis);
			e.printStackTrace();
			result = "0";
		} finally {
			releaseJedis(jedis);
		}
		return result;
	}

	/**
	 * <p>
	 * 设置key value,如果key已经存在则返回0,nx==> not exist
	 * 
	 * @param key
	 * @param value
	 * @return 成功返回1 如果存在 和 发生异常 返回 0
	 */
	public Long setnx(String key, String value) {
		Jedis jedis = null;
		Long result = 0L;
		try {
			jedis = getJedis();
			result = jedis.setnx(key, value);
		} catch (Exception e) {
			releaseBrokenJedis(jedis);
			e.printStackTrace();
		} finally {
			releaseJedis(jedis);
		}

		return result;
	}

	/**
	 * string 将给定 key 的值设为 value ，并返回 key 的旧值(old value)。
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public String getSet(String key, String value) {
		Jedis jedis = getJedis();
		String result = jedis.getSet(key, value);
		releaseJedis(jedis);
		return result;
	}

	/**
	 * 返回所有(一个或多个)给定 key 的值。 如果给定的 key 里面，有某个 key 不存在，那么这个 key 返回特殊值 nil
	 * 。因此，该命令永不失败。
	 * 
	 * @param keys
	 * @return
	 */
	public List<String> mget(String[] keys) {
		Jedis jedis = getJedis();
		List<String> result = jedis.mget(keys);
		releaseJedis(jedis);
		return result;
	}

	/**
	 * 同时设置一个或多个 key-value 对。 有会覆盖
	 * 
	 * @param keysvalues
	 */
	public void mset(String... keysvalues) {
		Jedis jedis = getJedis();
		jedis.mset(keysvalues);
		releaseJedis(jedis);
	}

	/**
	 * key不存在时才插入
	 * 
	 * @param keysvalues
	 */
	public void msetnx(String... keysvalues) {
		Jedis jedis = getJedis();
		jedis.msetnx(keysvalues);
		releaseJedis(jedis);
	}

	/**
	 * 将 key 所储存的值加上增量 increment 。 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 INCRBY
	 * 命令。
	 * 
	 * @param key
	 * @param integer
	 * @return
	 */
	public Long incrBy(String key, Integer integer) {
		Jedis jedis = getJedis();
		Long result = jedis.incrBy(key, integer);
		releaseJedis(jedis);
		return result;
	}

	/**
	 * 返回 key 所储存的字符串值的长度。
	 * 
	 * @param key
	 * @return
	 */
	public Long strlen(String key) {
		Jedis jedis = getJedis();
		Long result = jedis.strlen(key);
		releaseJedis(jedis);
		return result;
	}

	/**
	 * 通过key 对value进行加值+1操作,当value不是int类型时会返回错误,当key不存在是则value为1
	 * 
	 * @param key
	 * @return 加值后的结果
	 */
	public Long incr(String key) {
		Jedis jedis = getJedis();
		Long result = jedis.incr(key);
		releaseJedis(jedis);
		return result;
	}

	/**
	 * 对key的值做减减操作,如果key不存在,则设置key为-1
	 * 
	 * @param key
	 * @return
	 */
	public Long decr(String key) {
		Jedis jedis = getJedis();
		Long result = jedis.decr(key);
		releaseJedis(jedis);
		return result;
	}

	/**
	 * 减去指定的值
	 * 
	 * @param key
	 * @param integer
	 * @return
	 */
	public Long decrBy(String key, Integer integer) {
		Jedis jedis = getJedis();
		Long result = jedis.decrBy(key, integer);
		releaseJedis(jedis);
		return result;
	}

	/**
	 * 通过key向指定的value值追加值
	 * 
	 * @param key
	 * @param str
	 * @return 成功返回 添加后value的长度 失败 返回 添加的 value 的长度 异常返回0L
	 */
	public Long append(String key, String str) {
		Jedis jedis = null;
		Long res = null;
		try {
			jedis = getJedis();
			res = jedis.append(key, str);
		} catch (Exception e) {
			releaseBrokenJedis(jedis);
			e.printStackTrace();
			res = 0L;
		} finally {
			releaseJedis(jedis);
		}
		return res;
	}

	public String subStr(String key, int Start, int end) {
		Jedis jedis = getJedis();
		String result = jedis.substr(key, Start, end);
		releaseJedis(jedis);
		return result;
	}

	/**
	 * 设置key value并制定这个键值的有效期
	 * 
	 * @param key
	 * @param value
	 * @param seconds
	 *            单位:秒
	 * @return 成功返回OK 失败和异常返回null
	 */
	public String setex(String key, String value, int seconds) {
		Jedis jedis = null;
		String res = null;
		try {
			jedis = getJedis();
			res = jedis.setex(key, seconds, value);
		} catch (Exception e) {
			releaseBrokenJedis(jedis);
			e.printStackTrace();
		} finally {
			releaseJedis(jedis);
		}
		return res;
	}

	/**
	 * 用 value 参数覆写(overwrite)给定 key 所储存的字符串值，从偏移量 offset 开始。
	 * 
	 * @param key
	 * @param offset
	 * @param value
	 * @return
	 */
	public Long setRange(String key, long offset, String value) {
		Jedis jedis = getJedis();
		Long result = jedis.setrange(key, offset, value);
		releaseJedis(jedis);
		return result;
	}

	public String getRange(String key, long StartOffset, long endOffset) {
		Jedis jedis = getJedis();
		String result = jedis.getrange(key, StartOffset, endOffset);
		releaseJedis(jedis);
		return result;
	}

	/**
	 * 查找所有符合给定模式 pattern 的 key 。
	 * 
	 * @param key
	 * @return
	 */
	public Set<String> keys(String key) {
		Jedis jedis = getJedis();
		Set<String> keys = jedis.keys(key);
		releaseJedis(jedis);
		return keys;
	}

	public List<String> sort(String key, SortingParams params) {
		Jedis jedis = getJedis();
		List<String> sortedResult = jedis.sort(key, params);
		releaseJedis(jedis);
		return sortedResult;
	}

	/**
	 * 检测给定key的剩余生存时间，单位秒
	 * 
	 * @param key
	 * @return returns -2 if the key does not exist.returns -1 if the key exists
	 *         but has no associated expire.
	 */
	public long ttl(String key) {
		Long result = -1L;
		Jedis jedis = null;
		try {
			jedis = getJedis();
			result = jedis.ttl(key);
		} catch (Exception e) {
			if (jedis != null) {
				redisPool.returnBrokenResource(jedis);
			}
		} finally {
			redisPool.returnResource(jedis);
		}

		return result;
	}

	/**
	 * 检测给定key的剩余生存时间，单位毫秒
	 * 
	 * @param key
	 * @return returns -2 if the key does not exist.returns -1 if the key exists
	 *         but has no associated expire.
	 */
	public long pttl(String key) {
		long result = -2L;
		Jedis jedis = null;
		try {
			jedis = getJedis();
			result = jedis.pttl(key);
		} catch (Exception e) {
			if (jedis != null) {
				redisPool.returnBrokenResource(jedis);
			}
		} finally {
			redisPool.returnResource(jedis);
		}
		return result;
	}
}
