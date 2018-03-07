package zhku.jsj141.test;

import java.text.SimpleDateFormat;
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

import zhku.jsj141.dao.BookDao;
import zhku.jsj141.dao.UserDao;
import zhku.jsj141.dao.Impl.BookDaoImpl;
import zhku.jsj141.entity.user.User;

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
		System.out.println((time.getYear()+1900)+"年"+(time.getMonth()+1)+"月"+time.getDate()+"日"+time.getHours()+"时"+time.getMinutes()+"分"+time.getSeconds()+"秒");
	}
}
