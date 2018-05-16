package zhku.jsj141.utils.user;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class userUtils extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String FimagePath = "F:\\java\\SSH_test\\WebRoot\\images\\user\\userImg";// 头像最终存放路径
	//验证码长宽
	private int width = 100;
	private int height = 30;
	//发送邮件
	//发送uid+时间戳的
	public String sendmail(String emailAddress ,String code) throws Exception{//发送激活链接
		final Properties props = new Properties();  
	    /* 
	     * 可用的属性： mail.store.protocol / mail.transport.protocol / mail.host / 
	     * mail.user / mail.from 
	     */  
	    // 表示SMTP发送邮件，需要进行身份验证  
	    props.put("mail.smtp.auth", "true");  
	    props.put("mail.smtp.host", "smtp.163.com"); 
	    /*props.put("mail.smtp.port", "465");
	    props.put("mail.smtp.socketFactory.fallback", "false");
	    props.put("mail.smtp.socketFactory.port", "465");
	    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");*/
	    // 发件人的账号  
	    props.put("mail.user", "Fyume_test@163.com");  
	    // 访问SMTP服务时需要提供的密码  
	    props.put("mail.password", "xXx569874123xXx");  

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
	    InternetAddress form = new InternetAddress(props.getProperty("mail.user"));  
	    message.setFrom(form);  
	    
	    // 设置收件人  
	    InternetAddress to = new InternetAddress(emailAddress);  
	    message.setRecipient(RecipientType.TO, to);  

	    // 设置邮件标题  
	    message.setSubject("激活邮件");  
	    
	    
	    // 设置邮件的内容体  
	    /*message.setContent("<h1>激活请点击以下链接(有效时间10分钟)</h1><h3>"
	    		+ "<a href='http://47.106.104.150:8080/SSH_test/userAction_activate?code="+code+"'>点击跳转激活</a>"
	    				+ "</h3>", "text/html;charset=UTF-8");*/  
	    message.setContent("<h1>激活请点击以下链接(有效时间10分钟)</h1><h3>"
	    		+ "<a href='http://localhost:8080/SSH_test/userAction_activate?code="+code+"'>点击跳转激活</a>"
	    				+ "</h3>", "text/html;charset=UTF-8");
	    // 发送邮件  
	    Transport.send(message);  
	    return "email_ok";
	}
	public String sendmail_ps(String emailAddress ,String uid) throws Exception{//发送修改后的密码
		String str = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<6;i++){
			String s=str.charAt(new Random().nextInt(str.length()))+"";
			sb.append(s);
		}
		str = sb.toString().trim();
		String psCode = new MD5Utils(str).getStr();
		final Properties props = new Properties();  
	    /* 
	     * 可用的属性： mail.store.protocol / mail.transport.protocol / mail.host / 
	     * mail.user / mail.from 
	     */  
	    // 表示SMTP发送邮件，需要进行身份验证  
	    props.put("mail.smtp.auth", "true");  
	    props.put("mail.smtp.host", "smtp.163.com");  
	    /*props.put("mail.smtp.port", "465");
	    props.put("mail.smtp.socketFactory.fallback", "false");
	    props.put("mail.smtp.socketFactory.port", "465");
	    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");*/
	    // 发件人的账号  
	    props.put("mail.user", "Fyume_test@163.com");  
	    // 访问SMTP服务时需要提供的密码  
	    props.put("mail.password", "xXx569874123xXx");  

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
	    InternetAddress form = new InternetAddress(props.getProperty("mail.user"));  
	    message.setFrom(form);  

	    // 设置收件人  
	    InternetAddress to = new InternetAddress(emailAddress);  
	    message.setRecipient(RecipientType.TO, to);  

	    // 设置邮件标题  
	    message.setSubject("改密邮件");  

	    // 设置邮件的内容体  
	    /*message.setContent("<h1>新密码如下：(如果不是本人修改的密码请不要点击该链接)</h1><h3>"
	    		+ "<a href='http://47.106.104.150:8080/SSH_test/userAction_confirmUpdatePs?uid="+uid+"&code="+psCode+"'>点击改密生效</a>"
	    				+ "</h3>", "text/html;charset=UTF-8");*/  
	    message.setContent("<h1>新密码如下："+str+"(如果不是本人修改的密码请不要点击该链接)</h1><h3>"
	    		+ "<a href='http://localhost:8080/SSH_test/userAction_confirmUpdatePs?uid="+uid+"&code="+psCode+"'>点击改密生效</a>"
	    				+ "</h3>", "text/html;charset=UTF-8");  

	    // 发送邮件  
	    Transport.send(message);  
	    return str;
	}
	//生成验证码
	public String checkCode() throws Exception{
		System.out.println("--checkCode--");
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		BufferedImage image = new BufferedImage(130, 30,BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		// 设置底色
		setbackground(g);
		// 设置边框
		setborder(g);
		// 设置随机线
		setline(g);
		// 设置字体
		String s=setfont((Graphics2D) g);
		request.getSession().setAttribute("checkcode", s);//验证码存放在session中
		// 输出图片
		response.setContentType("image/png");
		response.setDateHeader("expires", -1);
		response.setHeader("cache-control", "no-cache");
		response.setHeader("pragma", "no-cache");
		ImageIO.write(image, "jpg", response.getOutputStream());
		return NONE;
	}

	private String setfont(Graphics2D g) {
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Consolas", Font.BOLD, 20));
		int x=20;			
		String str = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<4;i++){
			String s=str.charAt(new Random().nextInt(str.length()))+"";
			sb.append(s);
			int temp=new Random().nextInt()%30;
			double rotate=temp*Math.PI/180;
			g.rotate(rotate, x, 20);
			g.drawString(s, x, 20);
			g.rotate(-rotate, x, 20);
			x+=20;
			
		}
		return sb.toString();
	}

	private void setline(Graphics g) {
		g.setColor(Color.YELLOW);
		for (int i = 0; i < 5; i++) {
			int x1 = new Random().nextInt(width);
			int x2 = new Random().nextInt(width);
			int y1 = new Random().nextInt(height);
			int y2 = new Random().nextInt(height);
			g.drawLine(x1, y1, x2, y2);
		}
	}

	private void setbackground(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);

	}

	private void setborder(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawRect(1, 1, width - 2, height - 2);

	}
	//上传头像
	public String uploadI(File image,String uid, String imageContentType) {
		if (image != null) {
			String filename = uid;// 文件名
			File dir = new File(FimagePath);
			String ctype = null;// 文件后缀
			if (imageContentType.equals("image/jpeg")) {
				ctype = ".jpg";
			}
			if (ctype != null) {
				try {
					FileUtils.moveFile(image, new File(dir, filename + ctype));// 存放到磁盘的时候重新命名，以免重复
					return "\\" + filename + ctype;// 返回部分路径
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return "";
	}
	//更改头像
	public String changeI(File image, String uid, String path, String imageContentType) {
		if (image != null) {
			try {
				FileUtils.forceDelete(new File(FimagePath+"\\"+path));//先删除磁盘中的头像图片
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String filename = uid;// 文件名
			File dir = new File(FimagePath);
			String ctype = null;// 文件后缀
			if (imageContentType.equals("image/jpeg")) {
				ctype = ".jpg";
			}
			if (ctype != null) {
				try {
					FileUtils.moveFile(image, new File(dir, filename + ctype));// 存放到磁盘的时候重新命名，以免重复
					return "\\" + filename + ctype;// 返回部分路径
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return "";
	}
	
}
