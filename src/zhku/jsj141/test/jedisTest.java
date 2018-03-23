package zhku.jsj141.test;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import zhku.jsj141.utils.user.JedisUtils;

public class jedisTest {
	JedisUtils jedisutils = new JedisUtils();
	@Test
	public void jTest(){
		Jedis jedis = jedisutils.getJedis();
		System.out.println(jedis.hgetAll("test"));
	}
}
