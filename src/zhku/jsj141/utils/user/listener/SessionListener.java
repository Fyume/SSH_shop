package zhku.jsj141.utils.user.listener;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import redis.clients.jedis.Jedis;
import zhku.jsj141.action.BaseAction;
import zhku.jsj141.entity.user.User;
import zhku.jsj141.utils.user.JedisUtils;

public class SessionListener implements HttpSessionListener,HttpSessionAttributeListener{
	JedisUtils jedisUtils = null;
	Jedis jedis = null;
	User user = null;
	Map<String,String> map = new HashMap<String,String>();
	boolean rs = false;
	
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {//创建session的时候 也连接redis
		System.out.println("-------session创建---------");
		jedisUtils = new JedisUtils();
		jedis = jedisUtils.getJedis();
		arg0.getSession().setAttribute("jedis", jedis);//方便action层调用
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		/*jedis.hdel("t_Login", arg0.);*/
		System.out.println("-------session销毁---------");
		long now = System.currentTimeMillis()/1000;
		updateRedis(now);//删除过期用户
	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent arg0) {
		System.out.println("-----存到session中---------");
		System.out.println(arg0.getName());
		if(arg0.getName().equals("user")){//当user存到session的时候 也就是登录的时候
			user = (User) arg0.getValue();
			System.out.println(user.toString());
			map.put(user.getUid(), String.valueOf(System.currentTimeMillis()/1000));
			jedis.hmset("Login_time", map);//之后就是根据uid找到登录时间 进而操作在线表
		}
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent arg0) {
		System.out.println("-----取出session---------");
		System.out.println(arg0.getName());
		if(arg0.getName().equals("user")){//当user存到session的时候 也就是登录的时候
			user = (User) arg0.getValue();
			System.out.println(user.toString());
			jedis.hdel("Login_time", user.getUid());//之后就是根据uid找到登录时间 进而操作在线表
			long now = System.currentTimeMillis()/1000;
			updateRedis(now);//删除过期用户s
		}
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		System.out.println("-----替换session的值---------");
		System.out.println(arg0.getName());
		/*long now = System.currentTimeMillis()/1000;
		if(arg0.getName().equals("user")){//当user存到session的时候 也就是登录的时候
			user = (User) arg0.getValue();
			System.out.println(user.toString());
			if(!checkUid(user.getUid(),now)){//没找到 也就是30分钟内没有登录过
				map.put(user.getUid(), String.valueOf(now));
				jedis.hmset("Login_time", map);
			}
		}*/
	}
	public void updateRedis(long now){
		map = jedis.hgetAll("Login_time");
		Iterator<Entry<String, String>> iterator = map.entrySet().iterator();
		while(iterator.hasNext()){//遍历map
			Entry<String, String> entry = iterator.next();
			System.out.print("uid:"+entry.getKey());
			String time = entry.getValue();
			System.out.println(" ; time:"+time);
			long delta_time = now-Integer.parseInt(time);
			if(delta_time>=1800){
				/*iterator.remove();//有没有必要？*/
				jedis.hdel("Login_time", entry.getKey());//删除过期用户记录
			}
		}
	}
	public boolean checkUid(String uid,long time){
		map = jedis.hgetAll("Login_time");
		Iterator<Entry<String, String>> iterator = map.entrySet().iterator();
		while(iterator.hasNext()){//遍历map
			Entry<String, String> entry = iterator.next();
			System.out.println("uid:"+entry.getKey());
			if(entry.getKey().equals(uid)){
				System.out.print(" bingo!");
				entry.setValue(String.valueOf(time));
				rs = true;
			}
		}
		jedis.hmset("Login_time", map);//更新
		return rs;
	}
}
