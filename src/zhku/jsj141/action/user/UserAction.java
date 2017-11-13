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

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserService getUserService() {
		return userService;
	}

	public String checkuid() throws Exception {// 检测用户ID重名
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
		String code = uid+new Random(999999999);
		User user = new User(uid, name, username, password, address, IDCN,
				telnum, email,code);
		System.out.println(user.toString());
		if (user != null) {/*
							 * Serializable s =userService.add(user);
							 * request.setAttribute("uid", s);
							 * userUtils util = new userUtils();	
							 * util.sendmail(email,user.getCode());
							 */
			
			request.setAttribute("functionname", "注册成功,激活邮件已发送到您的邮箱上，");// loading页面需要显示
			request.setAttribute("gohere", "pages/user/login.jsp");// loading页面需要显示
			return "register_ok";
		}
		return NONE;
	}
	public String activate() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		String code = request.getParameter("code");
		User user = new User();
		user.setCode(code);
		user = userService.find(user, "Code");
		if(user!=null){
			user.setCode("");
			user.setU_status(true);
			userService.update(user);
			request.setAttribute("functionname", "激活成功,");// loading页面需要显示
			request.setAttribute("gohere", "pages/user/login.jsp");// loading页面需要显示
			return "activate_ok";
		}
		return NONE;
	}
	@SuppressWarnings("unused")
	public String login() throws Exception {// 登录
		System.out.println("--login--");
		HttpServletRequest request = ServletActionContext.getRequest();
		String s_vCode = (String) request.getSession()
				.getAttribute("checkcode");
		String vCode = (String) request.getParameter("验证码");
		System.out.println("s_vCode:"+s_vCode+" vCode:"+vCode);
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
				user = userService.find(user,"Uid");
				System.out.println(user.toString());
				if (user.getUid() != null) {//有这个用户
					String rpassword = user.getPassword();
					if (rpassword.equals(password)) {
						System.out.println("login_ok");
						return "login_ok";
					}else{
						System.out.println("密码错误");
						request.setAttribute("uidpass_flag", "用户或者密码错误");
					}
				}else{
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
