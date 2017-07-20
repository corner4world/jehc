package jehc.junitmodules.junit.redis;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import jehc.xtmodules.xtcore.base.BaseJunit;
import jehc.xtmodules.xtcore.cache.redis.RedisUtil;

public class RedisJunit extends BaseJunit{
	@Autowired
	RedisUtil redisUtil;
	@Test
	public void reidsTest(){
        redisUtil.flushDB();
        redisUtil.set("string_test", "string_test");
        System.out.println("===string类型====："+redisUtil.get("string_test"));

        redisUtil.sadd("set_test", "set_1");
        redisUtil.sadd("set_test", "set_2");
        redisUtil.sadd("set_test", "set_3");
        System.out.println("===set类型====："+redisUtil.smembers("set_test"));

        redisUtil.hset("Hash_test", "orderNo", "2017-08-01");
        System.out.println("===hash类型====："+redisUtil.hget("Hash_test", "orderNo"));

        Map<String, String>maps=new HashMap<String, String>();
        maps.put("orderid", "NJ02102120");
        maps.put("ordermoney", "1200");
        redisUtil.hmset("Hash_test2", maps);
        System.out.println("===hash类型测试2====："+redisUtil.hmget("Hash_test2","orderid","ordermoney"));

        redisUtil.lpush("List", "list0");
        redisUtil.lpush("List", "list1");
        redisUtil.lpush("List", "list2");
        System.out.println("===List类型====："+redisUtil.lrange("List", 0, -1));

        redisUtil.zadd("sorted set", 10, "set1");
        redisUtil.zadd("sorted set", 2, "set2");
        redisUtil.zadd("sorted set", 3, "set3");
        System.out.println("==sorted sett类型====："+redisUtil.zrevrangeByScore("sorted set", 10, 0));
	}
}
