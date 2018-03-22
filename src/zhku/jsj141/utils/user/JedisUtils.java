package zhku.jsj141.utils.user;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.junit.Test;

import redis.clients.jedis.Jedis;
//暂时是个测试类
public class JedisUtils {
	private Jedis jedis;
	@Test
	//又学到了一样东西 Runtime.getRuntime().exec() (假如要执行与工作目录（环境）有关的批处理文件，那么就用exec(批处理文件路径，))
	public void openExe() {//启动redis
	    final Runtime runtime = Runtime.getRuntime();  
	    File dir = new File("F:\\redis\\Redis-x64-3.2.100");
	    Process process = null;  
	    try {  
	        process = runtime.exec("F:\\redis\\Redis-x64-3.2.100\\redis-server_startup.bat", null, dir);
	    } catch (final Exception e) {  
	        System.out.println("Error exec!");  
	    }  
	}
	public void connectRedis(){
		jedis = new Jedis("127.0.0.1", 6379);//地址，端口
		jedis.auth("redis");//访问密码
	}
	@Test
	public void testadd(){//连接redis
		openExe();//启动
		connectRedis();//连接
		jedis.set("name", "abc");
		System.out.println(jedis.get("name"));
		jedis.append("name", "def");
		System.out.println(jedis.get("name"));
		jedis.del("name");
		System.out.println(jedis.get("name"));
		Map<String,String> map = new HashMap<String, String>();
		map.put("name","红婷婷");
		map.put("work","sing");
		Iterator<String> iterator = map.keySet().iterator();
		while(iterator.hasNext()){
			String s = iterator.next();
			System.out.println("--"+s+":"+map.get(s));
		}
		jedis.hmset("message", map);
		System.out.println(jedis.hmget("message","work"));
		jedis.lpush("lmessage", "ゆう十","singer","man");
		System.out.println(jedis.lrange("lmessage", 0, 2));
		Iterator<String> iter = jedis.hkeys("message").iterator();
		
	}
}
