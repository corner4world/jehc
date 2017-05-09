package jehc.xtmodules.xtcore.cache.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 单机版
 *
 */
public class JedisUtil {
	private static JedisPool jedisPool; 
	static{
		PropertiesLoader pl =  new PropertiesLoader("classpath:/jehc/xtmodules/xtcore/sources/redis/redis.properties"); 
		JedisPoolConfig config = new JedisPoolConfig();
		//连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
		config.setBlockWhenExhausted(true);
		//设置的逐出策略类名, 默认DefaultEvictionPolicy(当连接超过最大空闲时间,或连接数超过最大空闲连接数)
		config.setEvictionPolicyClassName("org.apache.commons.pool2.impl.DefaultEvictionPolicy");
		//是否启用pool的jmx管理功能, 默认true
		config.setJmxEnabled(true);
		//MBean ObjectName = new ObjectName("org.apache.commons.pool2:type=GenericObjectPool,name=" + "pool" + i); 默 认为"pool", JMX不熟,具体不知道是干啥的...默认就好.
		config.setJmxNamePrefix("pool");
		//是否启用后进先出, 默认true
		config.setLifo(true);
		//最大空闲连接数, 默认8个
		config.setMaxIdle(new Integer(pl.getProperty("pool.maxIdle")));
		//最大连接数, 默认8个
		config.setMaxTotal(new Integer(pl.getProperty("pool.maxTotal")));
		//获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1
		config.setMaxWaitMillis(new Integer(pl.getProperty("pool.maxWaitMillis")));
		//逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
		config.setMinEvictableIdleTimeMillis(new Integer(pl.getProperty("pool.minEvictableIdleTimeMillis")));
		//最小空闲连接数, 默认0pool.minIdle
		config.setMinIdle(new Integer(pl.getProperty("pool.minIdle")));
		//每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3
		config.setNumTestsPerEvictionRun(new Integer(pl.getProperty("pool.numTestsPerEvictionRun")));
		//对象空闲多久后逐出, 当空闲时间>该值 且 空闲连接>最大空闲数 时直接逐出,不再根据MinEvictableIdleTimeMillis判断  (默认逐出策略)   
		config.setSoftMinEvictableIdleTimeMillis(new Integer(pl.getProperty("pool.softMinEvictableIdleTimeMillis")));
		//在获取连接的时候检查有效性, 默认false
		config.setTestOnBorrow(false);
		//在空闲时检查有效性, 默认false
		config.setTestWhileIdle(false);
		//逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1
		config.setTimeBetweenEvictionRunsMillis(new Integer(pl.getProperty("pool.timeBetweenEvictionRunsMillis")));
		jedisPool = new JedisPool(config, pl.getProperty("redis.host"), new Integer(pl.getProperty("redis.port")));
	}
	/** 
     *类级的内部类，也就是静态的成员式内部类，该内部类的实例与外部类的实例 
     *没有绑定关系，而且只有被调用到时才会装载，从而实现了延迟加载。 
     */  
    private static class RedisUtilHolder{  
	    /** 
	     * 静态初始化器，由JVM来保证线程安全 
	     */  
	    private static JedisUtil instance = new JedisUtil();  
    } 
    /** 
     *当getInstance方法第一次被调用的时候，它第一次读取 
     *RedisUtilHolder.instance，导致RedisUtilHolder类得到初始化；而这个类在装载并被初始化的时候，会初始化它的静 
     *态域，从而创建RedisUtil的实例，由于是静态的域，因此只会在虚拟机装载类的时候初始化一次，并由虚拟机来保证它的线程安全性。 
     *这个模式的优势在于，getInstance方法并没有被同步，并且只是执行一个域的访问，因此延迟初始化并没有增加任何访问成本。 
     */  
    public static JedisUtil getInstance() {  
        return RedisUtilHolder.instance;  
    }  
	/**
	 * 获取Pool
	 * @return
	 */
	public static JedisPool getPool(){
		PropertiesLoader pl =  new PropertiesLoader("classpath:/jehc/xtmodules/xtcore/sources/redis/redis.properties"); 
		JedisPoolConfig config = new JedisPoolConfig();
		//连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
		config.setBlockWhenExhausted(true);
		//设置的逐出策略类名, 默认DefaultEvictionPolicy(当连接超过最大空闲时间,或连接数超过最大空闲连接数)
		config.setEvictionPolicyClassName("org.apache.commons.pool2.impl.DefaultEvictionPolicy");
		//是否启用pool的jmx管理功能, 默认true
		config.setJmxEnabled(true);
		//MBean ObjectName = new ObjectName("org.apache.commons.pool2:type=GenericObjectPool,name=" + "pool" + i); 默 认为"pool", JMX不熟,具体不知道是干啥的...默认就好.
		config.setJmxNamePrefix("pool");
		//是否启用后进先出, 默认true
		config.setLifo(true);
		//最大空闲连接数, 默认8个
		config.setMaxIdle(new Integer(pl.getProperty("pool.maxIdle")));
		//最大连接数, 默认8个
		config.setMaxTotal(new Integer(pl.getProperty("pool.maxTotal")));
		//获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1
		config.setMaxWaitMillis(new Integer(pl.getProperty("pool.maxWaitMillis")));
		//逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
		config.setMinEvictableIdleTimeMillis(new Integer(pl.getProperty("pool.minEvictableIdleTimeMillis")));
		//最小空闲连接数, 默认0
		config.setMinIdle(new Integer(pl.getProperty("pool.minIdle")));
		//每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3
		config.setNumTestsPerEvictionRun(new Integer(pl.getProperty("pool.numTestsPerEvictionRun")));
		//对象空闲多久后逐出, 当空闲时间>该值 且 空闲连接>最大空闲数 时直接逐出,不再根据MinEvictableIdleTimeMillis判断  (默认逐出策略)   
		config.setSoftMinEvictableIdleTimeMillis(new Integer(pl.getProperty("pool.softMinEvictableIdleTimeMillis")));
		//在获取连接的时候检查有效性, 默认false
		config.setTestOnBorrow(false);
		//在空闲时检查有效性, 默认false
		config.setTestWhileIdle(false);
		//逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1
		config.setTimeBetweenEvictionRunsMillis(new Integer(pl.getProperty("pool.timeBetweenEvictionRunsMillis")));
		jedisPool = new JedisPool(config, pl.getProperty("redis.host"), new Integer(pl.getProperty("redis.port")));
		return jedisPool;
	}
	/** 
     * 获取Redis实例. 
     * @return Redis工具类实例 
     */  
    public static Jedis getJedis() {  
    	Jedis jedis = null;
    	try {
    		jedis = getPool().getResource(); 
		} catch (Exception e) {
			closeJedis();
		}
        return jedis;  
    } 
	 /** 
     * 释放redis实例到连接池. 
     * @param jedis redis实例 
     */  
    public static void closeJedis() {  
    	Jedis jedis = getPool().getResource();
    	 if(jedis != null) { 
    		 jedis.close();   
    	 }
    }  
    /** 
     * 成功,"OK" 
     */  
    private static final String SUCCESS_OK = "OK";  
    /** 
     * 成功,1L 
     */  
    private static final Long SUCCESS_STATUS_LONG = 1L;  
    /** 
     * 只用key不存在时才设置。Only set the key if it does not already exist 
     */  
    private static final String NX = "NX";  
    /** 
     * XX -- 只有key存在时才设置。和NX相反。Only set the key if it already exist. 
     */  
    private static final String XX = "XX";  
    /** 
     * EX|PX, 时间单位，EX是秒，PX是毫秒。expire time units: EX = seconds; PX = milliseconds 
     */  
    private static final String EX = "EX";  
    /** 
     * EX|PX, 时间单位，EX是秒，PX是毫秒。expire time units: EX = seconds; PX = milliseconds 
     */  
    //private static final String PX = "PX";  
      
    /** 
     * 成功返回true 
     * @param key 
     * @param value 
     * @return 
     */  
    public static boolean set(String key, String value){  
        Jedis jedis = jedisPool.getResource();  
        String statusCode = jedis.set(key, value);  
        jedis.close();  
        if(SUCCESS_OK.equalsIgnoreCase(statusCode)){  
            return true;  
        }  
        return false;  
    }  
      
    /** 
     * 返回值 
     * @param key 
     * @return 
     */  
    public static String get(String key){  
        Jedis jedis = jedisPool.getResource();  
        String value = jedis.get(key);  
        jedis.close();  
        return value;  
    }  
      
    /** 
     * 设置key值和过期时间 
     * @param key 
     * @param value 
     * @param seconds 秒数，不能小于0 
     * @return 
     */  
    public static boolean setByTime(String key, String value, int seconds){  
        if(seconds < 0){  
            return false;  
        }  
        Jedis jedis = jedisPool.getResource();  
        String statusCode = jedis.setex(key, seconds, value);  
        if(SUCCESS_OK.equalsIgnoreCase(statusCode)){  
            return true;  
        }  
        return false;  
    }  
      
    /** 
     *  
     * @param key 
     * @param value 
     * @param nxxx NX|XX  是否存在 
     * <li>NX -- Only set the key if it does not already exist.</li> 
     * <li>XX -- Only set the key if it already exist.</li> 
     * @param expx EX|PX, expire time units ，时间单位格式，秒或毫秒 
     * <li>EX = seconds;</li> 
     * <li>PX = milliseconds</li> 
     * @param time expire time in the units of expx，时间（long型），不能小于0 
     * @return 
     */  
    public static boolean set(String key, String value,   
            String nxxx, String expx, long time){  
        Jedis jedis = jedisPool.getResource();  
        //Status code reply  
        if(time < 0){  
            return false;  
        }  
        String statusCode = jedis.set(key, value, nxxx, expx, time);  
        jedis.close();  
        if(SUCCESS_OK.equalsIgnoreCase(statusCode)){  
            return true;  
        }  
        return false;  
    }  
      
    /** 
     * 设置key 
     * @param key 
     * @param value 
     * @param nxxx NX|XX 是否需要存在 
     * <li>NX -- Only set the key if it does not already exist.</li>  
     * <li>XX -- Only set the key if it already exist.</li>  
     * @return 
     */  
    public static boolean set(String key, String value,   
            String nxxx){  
        Jedis jedis = jedisPool.getResource();  
        String statusCode = jedis.set(key, value, nxxx);  
        jedis.close();  
        if(SUCCESS_OK.equalsIgnoreCase(statusCode)){  
            return true;  
        }  
        return false;  
    }  
      
    /** 
     * 当key不存在时，设置值，成功返回true 
     * @param key 
     * @param value 
     * @return 
     */  
    public static boolean setIfNotExists(String key, String value){  
        Jedis jedis = jedisPool.getResource();  
        //1 if the key was set, 0 if the key was not set  
        Long statusCode = jedis.setnx(key, value);  
        jedis.close();  
        if(SUCCESS_STATUS_LONG == statusCode){  
            return true;  
        }  
        return false;  
    }  
      
    /** 
     * 当key不存在时，设置值，成功返回true，同setIfNotExists 
     * @param key 
     * @param value 
     * @return 
     */  
    public static boolean setNX(String key, String value){  
        return setIfNotExists(key, value);  
    }  
      
    /** 
     * 仅当key不存在时则设置值，成功返回true，存在时不设置值 
     * @param key 
     * @param value 
     * @param seconds，秒数，不能小于0 
     * @return 
     */  
    public static boolean setIfNotExists(String key, String value, long seconds){  
        if(seconds < 0){  
            return false;  
        }  
        return set(key, value, NX, EX, seconds);  
    }  
      
    /** 
     * 仅当key不存在时则设置值，成功返回true，存在时不设置值，同setIfNotExists(key, value, seconds) 
     * @param key 
     * @param value 
     * @param seconds 
     * @return 
     */  
    public static boolean setNX(String key, String value, Long seconds){  
        return setIfNotExists(key, value, seconds);  
    }  
      
    /** 
     * 当key存在时则设置值，成功返回true，不存在不设置值 
     * @param key 
     * @param value 
     * @return 
     */  
    public static boolean setIfExists(String key, String value){  
        return set(key, value, XX);  
    }  
      
    /** 
     * 当key存在时则设置值，成功返回true，不存在不设置值，同setIfExists 
     * @param key 
     * @param value 
     * @return 
     */  
    public static boolean setXX(String key, String value){  
        return setIfExists(key, value);  
    }  
      
    /** 
     * 仅当key存在时则设置值，成功返回true，不存在不设置值 
     * @param key 
     * @param value 
     * @param seconds，秒数，不能小于0 
     * @return 
     */  
    public static boolean setIfExists(String key, String value, long seconds){  
        if(seconds < 0){  
            return false;  
        }  
        return set(key, value, XX, EX, seconds);  
    }  
      
    /** 
     * 仅当key存在时则设置值，成功返回true，不存在不设置值 
     * @param key 
     * @param value 
     * @param seconds，秒数，不能小于0 
     * @return 
     */  
    public static boolean setXX(String key, String value, long seconds){  
        return setIfExists(key, value, seconds);  
    }  
      
    /** 
     * 设置超期时间 
     * @param key 
     * @param seconds 为Null时，将会马上过期。可以设置-1，0，表示马上过期 
     * @return 
     */  
    public static boolean setTime(String key, Integer seconds){  
        if(seconds == null){  
            seconds = -1;  
        }  
        Jedis jedis = jedisPool.getResource();  
        Long statusCode = jedis.expire(key, seconds);  
        jedis.close();  
        if(SUCCESS_STATUS_LONG == statusCode){  
            return true;  
        }  
        return false;  
    }  
      
    /** 
     * 设置超期时间 
     * @param key 
     * @param seconds 为Null时，将会马上过期。可以设置-1，0，表示马上过期 
     * @return 
     */  
    public static boolean setOutTime(String key, Integer seconds){  
        return setTime(key, seconds);  
    }  
      
    /** 
     * 设置超期时间 
     * @param key 
     * @param seconds 秒数，为Null时，将会马上过期。可以设置-1，0，表示马上过期 
     * @return 
     */  
    public static boolean expire(String key, Integer seconds){  
        return setTime(key, seconds);  
    }  
      
    /** 
     * 判断key是否存在，存在返回true 
     * @param key 
     * @return 
     */  
    public static boolean exists(String key){  
        Jedis jedis = jedisPool.getResource();  
        boolean result = jedis.exists(key);  
        jedis.close();  
        return result;  
    }  
      
    /** 
     * 判断key是否存在，存在返回true 
     * @param key 
     * @return 
     */  
    public static boolean isExists(String key){  
        return exists(key);  
    }  
      
    /** 
     * 将key设置为永久 
     * @param key 
     * @return 
     */  
    public static boolean persist(String key){  
        long time = getTime(key);  
        if(time == -1){  
            return true;  
        }  
        Jedis jedis = jedisPool.getResource();  
        //已经是永久的，返回0  
        Long statusCode = jedis.persist(key);  
        jedis.close();  
        if(SUCCESS_STATUS_LONG == statusCode){  
            return true;  
        }  
        return false;  
    }  
      
    /** 
     * 获取剩余时间（秒） 
     * @param key 
     * @return 
     */  
    public static Long getTime(String key){  
        Jedis jedis = jedisPool.getResource();  
        Long time = jedis.ttl(key);  
        jedis.close();  
        return time;  
    }  
      
    /** 
     * 获取剩余时间（秒） 
     * @param key 
     * @return 
     */  
    public static Long Ttl(String key){  
        return getTime(key);  
    }  
      
    /** 
     * 随机获取一个key 
     * @return 
     */  
    public static String randomKey(){  
        Jedis jedis = jedisPool.getResource();  
        String key = jedis.randomKey();  
        jedis.close();  
        return key;  
    }  
      
    /** 
     * 随机获取一个key 
     * @return 
     */  
    public static String random(){  
        return randomKey();  
    }  
      
    /** 
     * 修改 key 的名称，成功返回true，如果不存在该key，则会抛错：ERR no such key 
     * 注：如果newKey已经存在，则会进行覆盖。建议使用renameNX 
     * @param oldkey 原来的key 
     * @param newKey 新的key 
     * @return 
     */  
    public static boolean rename(String oldkey, String newKey){  
        Jedis jedis = jedisPool.getResource();  
        String statusCode = jedis.rename(oldkey, newKey);  
        jedis.close();  
        System.out.println("statusCode="+statusCode);  
        if(SUCCESS_OK.equalsIgnoreCase(statusCode)){  
            return true;  
        }  
        return false;  
    }  
      
    /** 
     * 仅当 newkey 不存在时，将 key 改名为 newkey 。成功返回true 
     * @param oldkey 
     * @param newKey 
     * @return 
     */  
    public static boolean renameNX(String oldkey, String newKey){  
        Jedis jedis = jedisPool.getResource();  
        Long statusCode = jedis.renamenx(oldkey, newKey);  
        jedis.close();  
        if(SUCCESS_STATUS_LONG == statusCode){  
            return true;  
        }  
        return false;  
    }  
      
    /** 
     * 仅当 newkey 不存在时，将 key 改名为 newkey 。成功返回true 
     * @param oldkey 
     * @param newKey 
     * @return 
     */  
    public static boolean renameIfNotExists(String oldkey, String newKey){  
        return renameNX(oldkey, newKey);  
    }  
      
    /** 
     * 返回 key 所储存的值的类型。 
     * @param key 
     * @return 
     */  
    public static String type(String key){  
        Jedis jedis = jedisPool.getResource();  
        String type = jedis.type(key);  
        jedis.close();  
        return type;  
    }  
      
    /** 
     * 返回 key 所储存的值的类型。 
     * @param key 
     * @return 
     */  
    public static String getType(String key){  
        return type(key);  
    }  
      
    /** 
     * 删除key及值 
     * @param key 
     * @return 
     */  
    public static boolean del(String key){  
        Jedis jedis = jedisPool.getResource();  
        Long statusCode = jedis.del(key);  
        jedis.close();  
        if(SUCCESS_STATUS_LONG == statusCode){  
            return true;  
        }  
        return false;  
    }  
      
    /** 
     * 删除key及值 
     * @param key 
     * @return 
     */  
    public static boolean delete(String key){  
        return del(key);  
    }  
      
    /** 
     * 删除key及值 
     * @param key 
     * @return 
     */  
    public static boolean remove(String key){  
        return del(key);  
    }  
      
    /** 
     * 批量删除key及值 
     * @param key 
     * @return 
     */  
    public static boolean del(String[] keys){  
        Jedis jedis = jedisPool.getResource();  
        Long statusCode = jedis.del(keys);  
        jedis.close();  
        if(statusCode > 0){  
            return true;  
        }  
        return false;  
    }  
      
    /** 
     * 批量删除key及值 
     * @param key 
     * @return 
     */  
    public static boolean delete(String[] keys){  
        return del(keys);  
    }  
      
    /** 
     * 批量删除key及值 
     * @param key 
     * @return 
     */  
    public static boolean remove(String[] keys){  
        return del(keys);  
    }  
}
