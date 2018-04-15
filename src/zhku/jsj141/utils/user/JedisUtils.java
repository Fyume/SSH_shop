package zhku.jsj141.utils.user;

import java.io.File;

import redis.clients.jedis.Jedis;
public class JedisUtils {
	private Jedis jedis;
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
	public JedisUtils(){
		openExe();//启动
		connectRedis();//连接
		System.out.println("创建Redis连接！");
	}
	public Jedis getJedis(){//获取redis
		System.out.println("获取jedis!");
		return jedis;
	}
}
/*public class JedisUtils {
	private Jedis jedis;
	//又学到了一样东西 Runtime.getRuntime().exec() (假如要执行与工作目录（环境）有关的批处理文件，那么就用exec(批处理文件路径，))
	public void openExe() {//启动redis
	    final Runtime runtime = Runtime.getRuntime();  
	    try {  
	    	runtime.exec("C:\\Redis\\Redis\\redis-server.exe");
	    } catch (final Exception e) {  
	        System.out.println("Error exec!");  
	    }  
	}
	public void connectRedis(){
		jedis = new Jedis("127.0.0.1", 6379);//地址，端口
		jedis.auth("redis");//访问密码
	}
	public JedisUtils(){
		openExe();//启动
		connectRedis();//连接
		System.out.println("创建Redis连接！");
	}
	public Jedis getJedis(){//获取redis
		System.out.println("获取jedis!");
		return jedis;
	}
}*/