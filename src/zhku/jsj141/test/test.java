package zhku.jsj141.test;

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
}
