package zhku.jsj141.test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import zhku.jsj141.dao.BookDao;
import zhku.jsj141.dao.UserDao;
import zhku.jsj141.dao.Impl.BookDaoImpl;
import zhku.jsj141.entity.user.User;
import zhku.jsj141.utils.user.MD5Utils;

public class test {
/*	Logger log = Logger.getLogger(test.class);
	public test(){
		System.out.println("log log");
	}
*/	public static void main(String[] args) throws MessagingException {
	/*	PropertyConfigurator.configure("/SSH_test/src/log4j2.properties");
		test t = new test();*/
	final Properties props = new Properties();  
    /* 
     * 可用的属性： mail.store.protocol / mail.transport.protocol / mail.host / 
     * mail.user / mail.from 
     */  
    // 表示SMTP发送邮件，需要进行身份验证  
    props.put("mail.smtp.auth", "true");  
    props.put("mail.smtp.host", "smtp.163.com");  
    // 发件人的账号  
    props.put("mail.user", "xxx947530369@163.com");  
    // 访问SMTP服务时需要提供的密码  
    props.put("mail.password", "xXx122560234");  

    // 构建授权信息，用于进行SMTP进行身份验证  
    Authenticator authenticator = new Authenticator() {  
        @Override  
        protected PasswordAuthentication getPasswordAuthentication() {  
            // 用户名、密码  
            String userName = props.getProperty("mail.user");  
            String password = props.getProperty("mail.password");  
            return new PasswordAuthentication(userName, password);  
        }  
    };  
    // 使用环境属性和授权信息，创建邮件会话  
    Session mailSession = Session.getInstance(props, authenticator);  
    // 创建邮件消息  
    MimeMessage message = new MimeMessage(mailSession);  
    // 设置发件人  
    InternetAddress form = new InternetAddress(  
            props.getProperty("mail.user"));  
    message.setFrom(form);  

    // 设置收件人  
    InternetAddress to = new InternetAddress("454628528@qq.com");  
    message.setRecipient(RecipientType.TO, to);  

    // 设置邮件标题  
    message.setSubject("邮件的标题");  

    // 设置邮件的内容体  
    message.setContent("测试邮件", "text/html;charset=UTF-8");  

    // 发送邮件  
    Transport.send(message);  
	}
	@Test
	public void testleng(){
		String uid = "111";
		String uid_code = "111222222";
		System.out.println(Integer.parseInt(uid_code.substring(uid.length(),uid_code.length()))-11111);
	}
	@Test
	public void testString(){
		String name="aaa";
		String name_m = name.substring(0, 1).toUpperCase()+name.substring(1,name.length());
		System.out.println(name_m);
	}
	@Test
	public void time(){
		/*long tramp = 1519315200;
		Date time = new Date(tramp*1000);
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
		String str = format.format(time);
		System.out.println(time.getDay());*/
		/*long tramp = new Date(2018, 2, 26).getTime()/(1000*60*60);
		long tramp2 = new Date(1990,2,3).getTime()/(1000*60*60);
		System.out.println(tramp);
		System.out.println(tramp2);*/
		Date time = new Date(System.currentTimeMillis()/(1000*60)*(1000*60));
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH点");
		String str = format.format(time);
		System.out.println((time.getYear()+1900)+"年"+(time.getMonth()+1)+"月"+time.getDate()+"日"+time.getHours()+"时"+time.getMinutes()+"分"+time.getSeconds()+"秒");
		System.out.println(str);
		/*String str = "";
		str = iii();
		System.out.println(str==null);*/
	}
	@Test
	public void iii(){
		testClass tc = new testClass();
		/*System.out.println(tc.toString());
		System.out.println(JSON.toJSONString(tc));
		Field[] field = tc.getClass().getDeclaredFields();
		for (Field field2 : field) {
			System.out.println(field2.getGenericType());
			System.out.println(field2.getName());
		}*/
		System.out.println(tojson(tc));
		System.out.println(JSON.toJSONString(tc));
		System.out.println(tc.toString());
		System.out.println(JSON.parseObject(JSON.toJSONString(tc),new TypeReference<testClass>(){}).toString());
		/*testClass tc2 = (testClass) JSON.parse(tojson(tc));
		System.out.println(tc2.toString());*/
	}
	public String tojson(Object object){
		String json = null;
		/*System.out.println(object.toString());*/
		Field[] field = object.getClass().getDeclaredFields();
		for (int i = 0;i<(field.length);i++) {
			if(i==0){
				json = "{";
			}else{
				json = json+",";
			}
			String name = field[i].getName();//参数名字
			String type = field[i].getGenericType().toString();//参数类型
			int num = type.lastIndexOf(".");
			if(num!=-1){//被String类型坑了一把
				type = type.substring(num+1, type.length());
			}
			System.out.println("name:"+name+" ; type:"+type);
			name = name.substring(0, 1).toUpperCase()+name.substring(1, name.length());//用于反射机制获取值
			if(type.equals("boolean")){
				boolean bl = false;
				try {
					bl = (boolean) object.getClass().getMethod("is"+name).invoke(object);
					json = json+"\""+field[i].getName()+"\":"+bl;
				} catch (IllegalAccessException | IllegalArgumentException
						| InvocationTargetException | NoSuchMethodException
						| SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(type.equals("int")||type.equals("long")||type.equals("String")){
				Object value = null;
				try {
					value = object.getClass().getMethod("get"+name).invoke(object);
				} catch (IllegalAccessException | IllegalArgumentException
						| InvocationTargetException | NoSuchMethodException
						| SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(type.equals("String")){//只有这个String类型比较独特啊
					value = "\""+value+"\"";
				}
				json = json+"\""+field[i].getName()+"\":"+value;
			}else{
				json = json.substring(0, json.length()-1)+"}";//把逗号去掉,结束
				break;
			}
			if(i==(field.length-1)){
				json = json + "}";
			}
			/*System.out.println(field[i].getGenericType());
			System.out.println(field[i].getName());*/
		}
		return json;
	}
	@Test
	public void testjson(){
		test3 t3 = new test3();
		t3.setT3(123);
		test4 t4 = new test4();
		t4.setT4(456);
		t4.setT4str("test4");
		t3.setTest4(t4);
		t3.setTest4_1(t4);
		System.out.println(JSON.toJSONString(t4));
		System.out.println(JSON.toJSONString(t3));
	}
}
