package zhku.jsj141.action.user;

import java.io.PrintWriter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import zhku.jsj141.entity.Type;
import zhku.jsj141.entity.user.Book;
import zhku.jsj141.entity.user.Favour;
import zhku.jsj141.entity.user.History;
import zhku.jsj141.entity.user.User;
import zhku.jsj141.entity.user.Work;
import zhku.jsj141.service.BookService;
import zhku.jsj141.service.UserService;
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
public class UserAction extends ActionSupport{
	private UserService userService;
	private BookService bookService;
	private userUtils userUtils;

	
	public BookService getBookService() {
		return bookService;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserService getUserService() {
		return userService;
	}

	public userUtils getUserUtils() {
		return userUtils;
	}

	public void setUserUtils(userUtils userUtils) {
		this.userUtils = userUtils;
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
		String code = String.valueOf(System.currentTimeMillis());
		code = code.substring(code.length() - 8, code.length());
		User user = new User(uid, username, name, password, address, IDCN,
				telnum, email, code,0,0);
		System.out.println(user.toString());
		if (user != null) {
			userService.add(user);
			userUtils.sendmail(email, user.getCode());
			request.setAttribute("functionname", "注册成功,激活邮件已发送到您的邮箱上,注意查收,");// loading页面需要显示
			request.setAttribute("gohere", "pages/user/login.jsp");// loading页面需要显示
			return "goto_Loading";
		}
		return NONE;
	}

	public String checkuid() throws Exception {// 检测用户ID重名（ajax）
		System.out.println("--checkuid--");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		 response.setCharacterEncoding("UTF-8");//
		 /*
		 ** 过滤器不知道为什么不起作用（貌似是Struts2的问题）
		 */String uid = request.getParameter("uid");
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

	public String activate() throws Exception {// 激活帐号
		HttpServletRequest request = ServletActionContext.getRequest();
		String code = request.getParameter("code");
		System.out.println("code:" + code);
		User user = new User();
		user.setCode(code);
		user = userService.find(user, "code");// 找下数据库有没有这个账号
		System.out.println(user.toString());
		if (user != null) {
			String sys = String.valueOf(System.currentTimeMillis());
			sys = sys.substring(sys.length() - 8, sys.length());
			System.out.println(sys);
			int now = Integer.parseInt(sys);
			System.out.println("now:" + now);
			int between = now - Integer.parseInt(code);// 相差毫秒数
			System.out.println("between:" + between);
			if (between >= 0 && between <= 600000) {// 有效期10分钟
				if (!user.isU_status()) {// 若未激活
					user.setCode("");
					user.setU_status(true);
					user.setActivateTime(System.currentTimeMillis());
					userService.update(user);
					request.getSession().setAttribute("user", user);// 将用户信息存放到session方便操作
					request.setAttribute("functionname", "激活成功,");// loading页面需要显示
					request.setAttribute("gohere", "pages/user/login.jsp");// loading页面需要显示
					return "goto_Loading";
				} else {
					request.setAttribute("functionname", "该帐号已激活,");// loading页面需要显示
					request.setAttribute("gohere", "pages/user/login.jsp");// loading页面需要显示
					return "goto_Loading";
				}
			} else {
				request.setAttribute("functionname", "激活过时,");// loading页面需要显示
				request.setAttribute("gohere", "pages/user/activate.jsp");// loading页面需要显示
				return "goto_Loading";
			}
		}
		return NONE;
	}
	/****************************未实现**************************/
	public String updateall() throws Exception {// 修改个人信息
		HttpServletRequest request = ServletActionContext.getRequest();

		request.getParameter("");
		return NONE;

	}
	/********************************************************/
	public String updateEmail() throws Exception {// 修改邮箱
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");
		String uid = request.getParameter("uid2");
		String email = request.getParameter("email");
		User user = new User();
		user.setUid(uid);
		user = userService.find(user, "uid");
		if (user != null) {
			if (user.getEmail().equals(email)) {
				request.setAttribute("upmail_email", "邮箱不需要修改");
			} else {
				user.setEmail(email);
				// 重新设置成未激活状态
				String code = "" + System.currentTimeMillis();
				code = code.substring(code.length() - 10, code.length());
				user.setCode(code);// 更新时间戳
				user.setU_status(false);
				userService.update(user);
				// 发送激活邮件
				userUtils.sendmail(email, user.getCode());
				request.setAttribute("functionname", "邮箱修改成功,激活邮件已发到邮箱,");// loading页面需要显示
				request.setAttribute("gohere", "pages/user/login.jsp");// loading页面需要显示
				return "goto_Loading";
			}
		} else {
			request.setAttribute("upmail_uid", "该用户不存在");
		}
		return "goto_activate";
	}

	public String checkE() throws Exception {// 检测邮箱有没有重复绑定（ajax）
		System.out.println("--checkuid--");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");// 过滤器不知道为什么不起作用（貌似是Struts2的问题）
		String email = request.getParameter("email");
		System.out.println("--action--");
		System.out.println("E:" + email);
		if (email != null && email != "") {
			User user = new User();
			user.setEmail(email);
			String checkresult = userService.checkE(user);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("check_E", checkresult);
			String result = JSONObject.toJSONString(map);
			System.out.println("result:" + result);
			PrintWriter out = response.getWriter();
			out.write(result);
			out.flush();
			out.close();
		}
		return NONE;
	}

	@SuppressWarnings("unused")
	public String resendEmail() throws Exception {// 重发激活邮件
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");
		String uid = request.getParameter("uid1");
		User user = new User();
		user.setUid(uid);
		user = userService.find(user, "uid");
		System.out.println(user.toString());
		if (user != null) {
			if (!user.isU_status()) {// 激活状态判断
				String code = String.valueOf(System.currentTimeMillis());
				code = code.substring(code.length() - 8, code.length());
				user.setCode(code);// 更新时间戳
				userService.update(user);
				userUtils.sendmail(user.getEmail(), user.getCode());// 重新发送邮件
				request.setAttribute("functionname", "激活邮件已发重新送到您的邮箱上,");// loading页面需要显示
				request.setAttribute("gohere", "pages/user/login.jsp");// loading页面需要显示
				return "goto_Loading";
			} else {
				request.setAttribute("functionname", "该帐号已激活,");// loading页面需要显示
				request.setAttribute("gohere", "pages/user/login.jsp");// loading页面需要显示
				return "goto_Loading";
			}
		} else {
			request.setAttribute("resendE_uid", "用户名不存在");
		}
		return "goto_activate";
	}

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
				/*或许可以搞个手机绑定然后通过手机也可以登录，后面再搞了
				 * String username = request.getParameter("用户名"); String name =
				 * request.getParameter("姓名"); String email =
				 * request.getParameter("邮箱");
				 */
				String password = request.getParameter("密码");
				User user = new User();
				user.setUid(uid);
				user = userService.find(user, "uid");
				System.out.println(user.toString());
				if (user.getUid() != null) {// 有这个用户
					if (user.isU_status()) {// 已激活
						String time = String.valueOf(System.currentTimeMillis());
						time = time.substring(time.length() - 8, time.length());
						long time2 = user.getPs_time() - Integer.parseInt(time);
						if(time2>=180000||user.getPs_time()==0){
							String rpassword = user.getPassword();
							if (rpassword.equals(password)) {
								request.getSession().setAttribute("user", user);
								System.out.println("login_ok");
								List<Type> typelist = null;
								List<Book> booklist = null;
								Book book = new Book();
								typelist = bookService.findT();
								booklist = bookService.findAll();
								request.getSession().setAttribute("typelist", typelist);
								request.getSession().setAttribute("classfy", "网络小说");
								request.getSession().setAttribute("booklist", booklist);
								if(user.isU_permission()){//如果是管理员
									return "goto_manager";
								}else{
									return "goto_index";
								}
							} else {
								System.out.println("密码错误");
								int num = user.getPs_false();
								if(num==3){
									user.setPs_false(0);
								}else{
									user.setPs_false(num+1);
								}
								String ps_time = String.valueOf(System.currentTimeMillis());
								ps_time = ps_time.substring(ps_time.length() - 8, ps_time.length());
								user.setPs_time(Integer.valueOf(ps_time));
								userService.update(user);
								request.setAttribute("uidpass_flag", "用户或者密码错误");
							}
						}else{
							request.setAttribute("uidpass_flag", "密码输错3次,请3分钟后再试");
						}
					} else {
						System.out.println("帐号未激活");
						request.setAttribute("uidpass_flag", "该帐号未激活");
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

		return "goto_login";
	}
	public String logOut() throws Exception {// 注销
		HttpServletRequest request = ServletActionContext.getRequest();
		request.getSession().setAttribute("user", null);//清空
		return "goto_index";
	}
	public String addF() throws Exception{//添加收藏
		HttpServletRequest request = ServletActionContext.getRequest();
		int bid = Integer.parseInt(request.getParameter("bid"));
		int wid = Integer.parseInt(request.getParameter("wid"));
		String page = (String)request.getParameter("page");
		User user = (User) request.getSession().getAttribute("user");
		Favour favour = new Favour();
		if(page!=null){
			favour.setBid(bid);
			favour.setUid(user.getUid());
			long time = System.currentTimeMillis();
			favour.setTime(time);
			userService.addF(favour);
			request.getSession().setAttribute("page", page);
		}
		return "goto_read";
	}
	/**************************未完成**************************/
	public String history() throws Exception{//记录浏览历史
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		User user = (User) request.getSession().getAttribute("user");
		if(user!=null){
			String uid = user.getUid();
			String json = (String)request.getParameter("json");
			JSONObject jsonObj = JSONObject.parseObject(json);
			System.out.println("font:"+jsonObj.get("font"));
			System.out.println("id:"+jsonObj.get("id"));
			System.out.println("page:"+jsonObj.get("page"));
			String font = (String)jsonObj.get("font");
			int id = (int) jsonObj.get("id");
			int page = (int) jsonObj.get("page");
			request.getSession().setAttribute("page", page);
			History history = new History();
			List<History> list = null;
			history.setUid(uid);
			if(font=="bid"){
				Book book = new Book();
				book.setBid(id);
				history.setBid(id);
				list = userService.findH(user,book);
				if(list!=null){//假如数据库有过记录则沿用原来的记录，更新一下就好
					history = list.get(0);
				}
				history.setPageNum(page);
				long time = System.currentTimeMillis()/(1000*60);//只保留到分
				history.setTime(time);
				userService.addHistory(history);
			}else if(font=="wid"){
				Work work = new Work();
				work.setWid(id);
				history.setWid(id);
				list = userService.findH(user,work);
				if(list!=null){//假如数据库有过记录则沿用原来的记录，更新一下就好
					history = list.get(0);
				}
				history.setPageNum(page);
				long time = System.currentTimeMillis()/(1000*60);//只保留到分
				history.setTime(time);
				userService.addHistory(history);
			}
		}
		PrintWriter out = response.getWriter();//好像不返回数据ajax会没反应..
		out.print("1");
		out.flush();
		out.close();
		return "goto_book";
	}
	
	public String getData() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		User user = (User) request.getSession().getAttribute("user");
		
		return "goto_User";
	}
	/********************************************************/
}
