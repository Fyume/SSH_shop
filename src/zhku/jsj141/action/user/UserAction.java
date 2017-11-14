package zhku.jsj141.action.user;

import java.io.PrintWriter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import zhku.jsj141.entity.user.User;
import zhku.jsj141.service.user.UserService;
import zhku.jsj141.utils.user.userUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;

@Controller
/*
 * @Namespace("/")
 * 
 * @ParentPackage("struts-default,json-default")
 * 
 * @Actions({
 * 
 * @Action(value="checkuid"),
 * 
 * @Action(value="register") })
 */
public class UserAction extends ActionSupport {
	private UserService userService;
	private userUtils utils;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserService getUserService() {
		return userService;
	}
	
	public userUtils getUtils() {
		return utils;
	}

	public void setUtils(userUtils utils) {
		this.utils = utils;
	}

	public String checkuid() throws Exception {// 检测用户ID重名（ajax）
		System.out.println("--checkuid--");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");// 过滤器不知道为什么不起作用（貌似是Struts2的问题）
		String uid = request.getParameter("uid");
		System.out.println("--action--");
		System.out.println("uid:" + uid);
		if (uid != null && uid != "") {
			User user = new User();
			user.setUid(uid);
			String checkresult = userService.checkuid(user);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("check_uid", checkresult);
			String result = JSONObject.toJSONString(map);
			System.out.println("result:" + result);
			PrintWriter out = response.getWriter();
			out.write(result);
			out.flush();
			out.close();
		}
		return NONE;
	}

	public String register() throws Exception {// 注册
		System.out.println("--register--");
		HttpServletRequest request = ServletActionContext.getRequest();
		String uid = request.getParameter("用户ID");
		String username = request.getParameter("用户名");
		String name = request.getParameter("姓名");
		String password = request.getParameter("密码");
		String address = request.getParameter("地址");
		String IDCN = request.getParameter("身份证号码");
		String telnum = request.getParameter("电话");
		String email = request.getParameter("邮箱");
		String code = uid + System.currentTimeMillis();
		User user = new User(uid, name, username, password, address, IDCN,
				telnum, email, code);
		System.out.println(user.toString());
		if (user != null) {/*
							 * Serializable s =userService.add(user);
							 * request.setAttribute("uid", s); 
							 * utils.sendmail(email,user.getCode());
							 */

			request.setAttribute("functionname", "注册成功,激活邮件已发送到您的邮箱上，");// loading页面需要显示
			request.setAttribute("gohere", "pages/user/login.jsp");// loading页面需要显示
			return "gotoLoading";
		}
		return NONE;
	}

	public String activate() throws Exception {// 激活帐号
		HttpServletRequest request = ServletActionContext.getRequest();
		String code = request.getParameter("code");
		User user = new User();
		user.setCode(code);
		user = userService.find(user, "Code");// 找下数据库有没有这个账号
		if (user != null) {
			int uid_l = user.getUid().length();// uid长度，需要剪掉
			long before = Integer
					.parseInt(code.substring(uid_l, code.length()));//存入数据库时的时间
			long between = System.currentTimeMillis() - before;//相差毫秒数
			if (between >= 0 && between <= 600000) {//有效期10分钟
				user.setCode("");
				user.setU_status(true);
				userService.update(user);
				request.setAttribute("functionname", "激活成功,");// loading页面需要显示
				request.setAttribute("gohere", "pages/user/login.jsp");// loading页面需要显示
				return "gotoLoading";
			}else{
				request.setAttribute("functionname", "激活过时,");// loading页面需要显示
				request.setAttribute("gohere", "pages/user/activate.jsp");// loading页面需要显示
				return "gotoLoading";
			}
		}
		return NONE;
	}

	/*
	 * public String updateall() throws Exception{ HttpServletRequest request =
	 * ServletActionContext.getRequest(); request.setCharacterEncoding("UTF-8");
	 * request.getParameter(""); return NONE;
	 * 
	 * }
	 */
	public String updateEmail() throws Exception {//修改邮箱
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");
		String uid = request.getParameter("uid");
		String email = request.getParameter("email");
		User user = new User();
		user.setUid(uid);
		user = userService.find(user, "Uid");
		if (user != null) {
			if (user.getEmail().equals(email)) {
				request.setAttribute("upmail_email", "邮箱不需要修改");
			}else{
				user.setEmail(email);
				//重新设置成未激活状态
				user.setCode(user.getUid()+System.currentTimeMillis());
				user.setU_status(false);
				//发送激活邮件
				utils.sendmail(email,user.getCode());
				request.setAttribute("functionname", "邮箱修改成功,激活邮件已发到邮箱,");// loading页面需要显示
				request.setAttribute("gohere", "pages/user/login.jsp");// loading页面需要显示
				return "gotoLoading";
			}
		} else {
			request.setAttribute("upmail_uid", "该用户不存在");
		}
		return "goto_activate";
	}
	public String resendEmail() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");
		String uid = request.getParameter("uid");
		User user = new User();
		user.setUid(uid);
		user = userService.find(user, "Uid");
		if(user!=null){
			user.setCode(user.getUid()+System.currentTimeMillis());//更新时间戳
			utils.sendmail(user.getEmail(), user.getCode());//重新发送邮件
			request.setAttribute("functionname", "激活邮件已发重新送到您的邮箱上,");// loading页面需要显示
			request.setAttribute("gohere", "pages/user/login.jsp");// loading页面需要显示
			return "gotoLoading";
		}else{
			request.setAttribute("resendE_uid", "用户名不存在");
		}
		return "goto_activate";
	}
	@SuppressWarnings("unused")
	public String login() throws Exception {// 登录
		System.out.println("--login--");
		HttpServletRequest request = ServletActionContext.getRequest();
		String s_vCode = (String) request.getSession()
				.getAttribute("checkcode");
		String vCode = (String) request.getParameter("验证码");
		System.out.println("s_vCode:" + s_vCode + " vCode:" + vCode);
		if (vCode != "" && vCode != null) {
			if (vCode.equalsIgnoreCase(s_vCode)) {
				String uid = request.getParameter("用户ID");
				/*
				 * String username = request.getParameter("用户名"); String name =
				 * request.getParameter("姓名"); String email =
				 * request.getParameter("邮箱");
				 */
				String password = request.getParameter("密码");
				User user = new User();
				user.setUid(uid);
				user = userService.find(user, "Uid");
				System.out.println(user.toString());
				if (user.getUid() != null) {// 有这个用户
					String rpassword = user.getPassword();
					if (rpassword.equals(password)) {
						System.out.println("login_ok");
						return "login_ok";
					} else {
						System.out.println("密码错误");
						request.setAttribute("uidpass_flag", "用户或者密码错误");
					}
				} else {
					System.out.println("用户名错误");
					request.setAttribute("uidpass_flag", "用户或者密码错误");
				}
			} else {
				System.out.println("验证码错误");
				request.setAttribute("vCode_flag", "验证码错误");
			}
		}

		return "login_false";
	}
}
