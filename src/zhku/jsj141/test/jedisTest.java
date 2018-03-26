package zhku.jsj141.test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

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
	@Test
	public void jtest(){
		Jedis jedis = jedisutils.getJedis();
		Map<String,String> map = new HashMap<String,String>();
		map = jedis.hgetAll("Login_time");
		Iterator<Entry<String, String>> iterator = map.entrySet().iterator();
		while(iterator.hasNext()){//遍历map
			Entry<String, String> entry = iterator.next();
			System.out.print("uid:"+entry.getKey());
			String time = entry.getValue();
			System.out.println(" ; time:"+time);
			long delta_time = (System.currentTimeMillis()/1000)-Integer.parseInt(time);
			if(delta_time>=1800){
				System.out.println("yes");
				iterator.remove();
				jedis.hdel("Login_time", entry.getKey());
			}
		}
		map = jedis.hgetAll("Login_time");
	}
}
