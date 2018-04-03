package zhku.jsj141.action.user;

import java.io.File;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import zhku.jsj141.action.BaseAction;
import zhku.jsj141.entity.Type;
import zhku.jsj141.entity.Upload;
import zhku.jsj141.entity.user.Book;
import zhku.jsj141.entity.user.Favour;
import zhku.jsj141.entity.user.History;
import zhku.jsj141.entity.user.ReviewsForBook;
import zhku.jsj141.entity.user.ReviewsForReviews;
import zhku.jsj141.entity.user.User;
import zhku.jsj141.entity.user.Work;
import zhku.jsj141.service.BookService;
import zhku.jsj141.service.ManagerService;
import zhku.jsj141.service.UserService;
import zhku.jsj141.service.WorkService;
import zhku.jsj141.utils.user.MD5Utils;
import zhku.jsj141.utils.user.bookUtils;
import zhku.jsj141.utils.user.userUtils;
import zhku.jsj141.utils.user.workUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

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
public class UserAction extends BaseAction{//(用了属性封装 和BaseAction 代码少了真多) 注意ajax传数据并不会像form一样有封装
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserService userService;
	private BookService bookService;
	private WorkService workService;
	private ManagerService managerService;
	private userUtils userUtils;
	
	private File image;
	private String imageFileName;
	private String imageContentType;
	
	List<User> userlist = null;
	List<Favour> favlist = null;
	List<History> histlist = null;
	List<Type> typelist = null;
	List<Book> booklist = null;
	List<Work> worklist = null;
	List<Upload> uploadlist = null;
	List<ReviewsForReviews> rfrlist = null;
	List<ReviewsForBook> rfblist = null;
	Favour favour = new Favour();
	History history = new History();
	Upload upload = new Upload();
	
	User user = new User();//属性驱动
	Book book = new Book();
	Work work = new Work();
	ReviewsForBook rfb = new ReviewsForBook();
	ReviewsForReviews rfr = new ReviewsForReviews();
	public ReviewsForReviews getRfr() {
		return rfr;
	}
	public void setRfr(ReviewsForReviews rfr) {
		this.rfr = rfr;
	}
	public ReviewsForBook getRfb() {
		return rfb;
	}
	public void setRfb(ReviewsForBook rfb) {
		this.rfb = rfb;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public Work getWork() {
		return work;
	}
	public void setWork(Work work) {
		this.work = work;
	}
	
	public WorkService getWorkService() {
		return workService;
	}
	public void setWorkService(WorkService workService) {
		this.workService = workService;
	}
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
	public ManagerService getManagerService() {
		return managerService;
	}
	public void setManagerService(ManagerService managerService) {
		this.managerService = managerService;
	}
	public userUtils getUserUtils() {
		return userUtils;
	}
	public void setUserUtils(userUtils userUtils) {
		this.userUtils = userUtils;
	}
	public File getImage() {
		return image;
	}
	public void setImage(File image) {
		this.image = image;
	}
	public String getImageFileName() {
		return imageFileName;
	}
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
	public String getImageContentType() {
		return imageContentType;
	}
	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}
	public String register() throws Exception {// 注册 
		System.out.println("--register--");
		String code = String.valueOf(System.currentTimeMillis());
		code = code.substring(code.length() - 8, code.length());
		user.setCode(code);
		System.out.println(user.toString());
		if (user != null) {
			user.setPassword(new MD5Utils(user.getPassword()).getStr());//md5加密一下
			userService.add(user);
			userUtils.sendmail(user.getEmail(), user.getCode());
			request.setAttribute("functionname", "注册成功,激活邮件已发送到您的邮箱上,注意查收,");// loading页面需要显示
			request.setAttribute("gohere", "pages/user/login.jsp");// loading页面需要显示
			return "goto_Loading";
		}
		return NONE;
	}

	public String checkuid() throws Exception {// 检测用户ID重名（ajax）
		System.out.println("--checkuid--");
		 response.setCharacterEncoding("UTF-8");//
		 /*
		 ** 过滤器不知道为什么不起作用（貌似是Struts2的问题）
		 */
		String uid = request.getParameter("uid");
		System.out.println("--action--");
		System.out.println("uid:" + uid);
		if (uid != null && uid != "") {
			user.setUid(uid);
			String checkresult = userService.checkuid(user);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("check_uid", checkresult);
			String result = JSONObject.toJSONString(map);
			System.out.println("result:" + result);
			PrintWriter out = response.getWriter();
			out.write(result);
			close(out);
		}
		return NONE;
	}

	public String activate() throws Exception {// 激活帐号
		String code = request.getParameter("code");
		System.out.println("code:" + code);
		user.setCode(code);
		userlist = userService.finds(user, "code");// 找下数据库有没有这个账号
		if (userlist.size()!=0) {
			user = userlist.get(0);
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
	public String update() throws Exception {// 修改个人信息
		System.out.println("-------update---------");
		User user2 = (User) request.getSession().getAttribute("user");
		if(user2!=null){
			userlist = userService.finds(user2, "uid");
			if(userlist.size()!=0){
				user2 = userlist.get(0);
			}
			String rs = "";
			if(image!=null){
				if(user2.getImage()==null){
					rs = userUtils.uploadI(image, user2.getUid(), imageContentType);
				}else{
					rs = userUtils.changeI(image, user2.getUid(), user2.getImage(), imageContentType);
				}
				System.out.println("rs:"+rs);
				if(!rs.equals("")){
					//修改数据库表 头像信息
					user2.setImage(rs);
				}
			}
			//直接更新user好像会覆盖。。。这就很尴尬
			user2.setUsername(user.getUsername());
			user2.setName(user.getName());
			user2.setAddress(user.getAddress());
			user2.setIDCN(user.getIDCN());
			user2.setTelnum(user.getTelnum());
			userService.update(user2);
			request.getSession().setAttribute("user", user2);
		}
		return "goto_user";

	}
	/********************************************************/
	public String updateEmail() throws Exception {// 修改邮箱
		request.setCharacterEncoding("UTF-8");
		String uid = request.getParameter("uid2");
		String email = request.getParameter("email");
		user.setUid(uid);
		userlist = userService.finds(user, "uid");
		if (userlist.size()!=0) {
			user = userlist.get(0);
			if (user.getEmail().equals(email)) {
				request.setAttribute("upmail_email", "邮箱不需要修改");
			} else {
				user.setEmail(email);
				// 重新设置成未激活状态
				String code = "" + System.currentTimeMillis();
				code = code.substring(code.length() - 10, code.length());
				user.setCode(code);// 更新时间戳
				user.setU_status(false);
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
		response.setCharacterEncoding("UTF-8");// 过滤器不知道为什么不起作用（貌似是Struts2的问题）
		String email = request.getParameter("email");
		System.out.println("--action--");
		System.out.println("E:" + email);
		if (email != null && email != "") {
			user.setEmail(email);
			String checkresult = userService.checkE(user);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("check_E", checkresult);
			String result = JSONObject.toJSONString(map);
			System.out.println("result:" + result);
			PrintWriter out = response.getWriter();
			out.write(result);
			close(out);
		}
		return NONE;
	}

	@SuppressWarnings("unused")
	public String resendEmail() throws Exception {// 重发激活邮件
		request.setCharacterEncoding("UTF-8");
		String uid = request.getParameter("uid1");
		user.setUid(uid);
		userlist = userService.finds(user, "uid");
		if (userlist.size()!=0) {
			user = userlist.get(0);
			if (!user.isU_status()) {// 激活状态判断
				String code = String.valueOf(System.currentTimeMillis());
				code = code.substring(code.length() - 8, code.length());
				user.setCode(code);// 更新时间戳
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
		String s_vCode = (String) request.getSession()
				.getAttribute("checkcode");
		String vCode = (String) request.getParameter("验证码");
		System.out.println("s_vCode:" + s_vCode + " vCode:" + vCode);
		String checkbox = request.getParameter("checkbox");
		System.out.println(checkbox);
		Cookie[] cookies = request.getCookies();
		String password = null;
		if(cookies!=null){//有 则取cookie
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals("user")){
					if(cookie.getValue()!=""){
						String info = cookie.getValue();
						int n = info.indexOf(",");
						String uid = info.substring(0,n);//cookie的
						user.setUid(uid);
						password = info.substring(n+1,info.length());
					}
				}
			}
		}
		if(password==null){//cookie里面没有
			if(s_vCode!=null){
				if (s_vCode.equalsIgnoreCase(vCode)) {
					/*或许可以搞个手机绑定然后通过手机也可以登录，后面再搞了*/
					password = new MD5Utils(user.getPassword()).getStr();
				}else{
					System.out.println("验证码错误");
					request.setAttribute("vCode_flag", "验证码错误");
					return "goto_login";
				}
			}
		}
		if(password==null){
			return NONE;
		}
		userlist = userService.finds(user, "uid");
		if (userlist.size()!=0) {
			user = userlist.get(0);
			if (user.isU_status()) {// 已激活
				String time = String.valueOf(System.currentTimeMillis());
				time = time.substring(time.length() - 8, time.length());
				long time2 = user.getPs_time() - Integer.parseInt(time);
				if(time2>=180000||user.getPs_time()==0){
					String rpassword = user.getPassword();
					System.out.println(rpassword+";"+password);
					if (rpassword.equals(password)) {
						request.getSession().setAttribute("user", null);//为了redis中的登陆表
						request.getSession().setAttribute("user", user);
						System.out.println("login_ok");
						
						if("1".equals(checkbox)){//使用cookie
							String info = user.getUid()+","+ rpassword;
							Cookie cookie = new Cookie("user", info);
							cookie.setMaxAge(60*60*24*365);//单位是秒 设为一年（365天）
							cookie.setPath("/");//cookie可访问的位置？（整个服务器）
							response.addCookie(cookie);
						}
						
						Cookie sessionId = new Cookie("JSESESSIONID",request.getSession().getId());
						response.addCookie(sessionId);
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
						request.setAttribute("uidpass_flag", "用户或者密码错误");
						logOut();
					}
				}else{
					request.setAttribute("uidpass_flag", "密码输错3次,请3分钟后再试");
					logOut();
				}
			} else {
				System.out.println("帐号未激活");
				request.setAttribute("uidpass_flag", "该帐号未激活");
				logOut();
			}
		} else {
			System.out.println("用户名错误");
			request.setAttribute("uidpass_flag", "用户或者密码错误");
			logOut();
		}
		String www = request.getParameter("www");
		if("login".equals(www)){
			return "goto_login";
		}else{
			return NONE;
		}
	}
	public String logOut() throws Exception {// 注销
		request.getSession().setAttribute("user", null);//清空
		Cookie[] cookies= request.getCookies();
		PrintWriter out = response.getWriter();
		int n = 0;
		for (Cookie cookie : cookies) {
			if(cookie.getName().equals("user")){
				if(!"".equals(cookie.getValue())){
					n +=1 ;
					out.print("111");
				}
			}
		}
		if(n==0){
			out.print("");
			close(out);
		}
		return "goto_index";
	}
	public String addF() throws Exception{//添加收藏(ajax)
		user = (User) request.getSession().getAttribute("user");
		String json = (String)request.getParameter("json");
		JSONObject jsonObj = JSONObject.parseObject(json);
		PrintWriter out = response.getWriter();//好像不返回数据ajax会没反应..
		if(user!=null){
			String font = (String)jsonObj.get("font");
			int id = (int) jsonObj.get("id");
			favour.setUser(user);
			if(font.equals("bid")){
				book.setBid(id);
				booklist = bookService.find(book, "bid");
				if(booklist.size()!=0){
					book = booklist.get(0);
					favour.setBook(book);//外键 存实体
					long time = System.currentTimeMillis();
					favour.setTime(time);
					favlist = userService.findF(user,book);
					if(favlist.isEmpty()){//为空则添加
						userService.addF(favour);
					}
				}
			}else if(font.equals("wid")){
				work.setWid(id);
				worklist = workService.find(work, "wid");
				if(worklist.size()!=0){
					work = worklist.get(0);
					favour.setWork(work);//外键 存实体
					long time = System.currentTimeMillis();
					favour.setTime(time);
					favlist = userService.findF(user,work);
					if(favlist.isEmpty()){//为空则添加
						userService.addF(favour);
					}
				}
			}
		}
		String where = (String) jsonObj.get("where");
		out.print("1");
		close(out);
		return "goto_"+where;
	}
	public String delF() throws Exception{//取消收藏
		user = (User) request.getSession().getAttribute("user");
		String json = (String)request.getParameter("json");
		JSONObject jsonObj = JSONObject.parseObject(json);
		PrintWriter out = response.getWriter();//好像不返回数据ajax会没反应..
		System.out.println(json);
		if(user!=null){
			String font = (String)jsonObj.get("font");
			int id = (int) jsonObj.get("id");
			favour.setUser(user);
			if(font.equals("bid")){
				book.setBid(id);
				booklist = bookService.find(book, "bid");
				if(booklist.size()!=0){
					book = booklist.get(0);
					favour.setBook(book);//外键 存实体
					long time = System.currentTimeMillis();
					favour.setTime(time);
					favlist = userService.findF(user,book);
					if(!favlist.isEmpty()){
						favour = favlist.get(0);
						userService.delF(favour);
					}
				}
			}else if(font.equals("wid")){
				work.setWid(id);
				worklist = workService.find(work, "wid");
				if(worklist.size()!=0){
					work = worklist.get(0);
					favour.setWork(work);//外键 存实体
					long time = System.currentTimeMillis();
					favour.setTime(time);
					favlist = userService.findF(user,work);
					if(!favlist.isEmpty()){
						favour = favlist.get(0);
						userService.delF(favour);
					}
				}
			}
		}
		String where = (String) jsonObj.get("where");
		out.print("1");
		close(out);
		return "goto_"+where;
	}
	public String getHistAndFav() throws Exception{//获取用户相关的书本浏览记录和收藏记录（后来加上的 查询有关上传该书本的信息）
		System.out.println("--------getHistAndFav------------");
		user = (User) request.getSession().getAttribute("user");
		String json = (String)request.getParameter("json");
		JSONObject jsonObj = JSONObject.parseObject(json);
		PrintWriter out = response.getWriter();//好像不返回数据ajax会没反应..
		String font = (String)jsonObj.get("font");
		int id = (int) jsonObj.get("id");
		Map<String, Object> map = new HashMap<String, Object>();
		if(user!=null){
			if(font.equals("bid")){
				book.setBid(id);
				favlist = userService.findF(user, book);
				histlist = userService.findH(user, book);
			}else if(font.equals("wid")){
				work.setWid(id);
				favlist = userService.findF(user, work);
				histlist = userService.findH(user, work);
			}
			if(histlist.size()!=0){//有 则将浏览历史加入
				history = histlist.get(0);
				request.getSession().setAttribute("history", history);
				map.put("h_page", history.getPageNum());//有  最近浏览的页数
			}else{
				map.put("h_page", 0);//无
				request.getSession().setAttribute("history", null);
			}
			if(favlist.size()!=0){//有 则提示已收藏
				favour = favlist.get(0);
				request.getSession().setAttribute("favour", favour);
				map.put("f_flag", 1);//有
			}else{
				map.put("f_flag", 0);//无
				request.getSession().setAttribute("favour", null);
			}
		}
		if(font.equals("bid")){
			book.setBid(id);
			uploadlist = managerService.findUpload(book);
		}else if(font.equals("wid")){
			work.setWid(id);
			uploadlist = managerService.findUpload(work);
		}
		if(uploadlist.size()!=0){
			upload = uploadlist.get(0);
			request.getSession().setAttribute("uploadRecord", upload);//上传记录
			map.put("time", upload.getTime());
			map.put("managerID", upload.getUser().getUid());
		}
		System.out.println("Map:"+map);
		out.print(JSONObject.toJSONString(map));
		close(out);
		return "goto_book";
	}
	public String history() throws Exception{//记录浏览历史 有 则更新
		user = (User) request.getSession().getAttribute("user");
		if(user!=null){
			String json = (String)request.getParameter("json");
			JSONObject jsonObj = JSONObject.parseObject(json);
			/*System.out.println("font:"+jsonObj.get("font"));
			System.out.println("id:"+jsonObj.get("id"));
			System.out.println("page:"+jsonObj.get("page"));*/
			String font = (String)jsonObj.get("font");
			int id = (int) jsonObj.get("id");
			int page = (int) jsonObj.get("page");
			request.getSession().setAttribute("page", page);
			history.setUser(user);
			if(font.equals("bid")){
				book.setBid(id);
				booklist = bookService.find(book, "bid");
				if(booklist.size()!=0){
					book = booklist.get(0);
					history.setBook(book);
					histlist = userService.findH(user,book);
					if(histlist.size()!=0){//假如数据库有过记录则沿用原来的记录，更新一下就好
						history = histlist.get(0);
					}
					history.setPageNum(page);
					long time = System.currentTimeMillis()/(1000*60);//只保留到分
					history.setTime(time);
					userService.addHistory(history);
				}
			}else if(font.equals("wid")){
				work.setWid(id);
				worklist = workService.find(work, "wid");
				if(worklist.size()!=0){
					work = worklist.get(0);
					history.setWork(work);
					histlist = userService.findH(user,work);
					if(histlist.size()!=0){//假如数据库有过记录则沿用原来的记录，更新一下就好
						history = histlist.get(0);
					}
					history.setPageNum(page);
					long time = System.currentTimeMillis()/(1000*60);//只保留到分
					history.setTime(time);
					userService.addHistory(history);
				}
			}
		}
		PrintWriter out = response.getWriter();//好像不返回数据ajax会没反应(好像和ajax设置的数据格式有关？)..
		out.print("1");
		close(out);
		return "goto_book";
	}
	public String getMyFavBy() throws Exception{
		user = (User) request.getSession().getAttribute("user");
		if(user==null){
			return "goto_login";
		}
		int type = Integer.valueOf(request.getParameter("type"));
		switch (type) {
			case 0:
				favlist = userService.findF_Book(user);break;
			case 1:
				book.setType("网络小说");
				favlist = userService.findF(user, book, "type");break;
			case 2:
				book.setType("文学作品");
				favlist = userService.findF(user, book, "type");break;
			case 3:
				book.setType("社会科学");
				favlist = userService.findF(user, book, "type");break;
			default:
				break;
		}
		if(type==4){
			favlist = userService.findF_Work(user);
			request.getSession().setAttribute("myFav_Work", favlist);
			request.getSession().setAttribute("myFav_Book", null);
		}else{
			request.getSession().setAttribute("myFav_Book", favlist);
			request.getSession().setAttribute("myFav_Work", null);
		}
		request.getSession().setAttribute("myFav_size", favlist.size());
		//是不是应该规范点用pageBean以及每个访问都是访问服务器而不是将一堆数据存到session来确保数据的实时性？
		return "goto_user";
	}
	/******************评论模块*****************/
	/*获取当前书本的评论
	* 忘了查完之后hibernate的session关闭了
	* 又为了不让页面一下子加载这么多东西，就将获取评论给分开了
	* action层开session就乱套了
	* （虽然现在service层也是被我弃置了就是了 好像除了传数据 验证之类的，就没什么实质性的代码了）
	* 然后，只能进行一次查询
	*/
	public String getReviews() throws Exception{
		System.out.println("---------getReviews---------");
		book = (Book) request.getSession().getAttribute("book");
		if(book!=null){
			rfblist = userService.findRfb_Book(book);
		}else{
			work = (Work) request.getSession().getAttribute("work");
			if(work!=null){
				rfblist = userService.findRfb_Work(work);
			}
		}
		rfblist = JSON.parseObject(JSON.toJSONString(rfblist),new TypeReference<List<ReviewsForBook>>(){});
		request.getSession().setAttribute("rfb_set", rfblist);
		return "goto_book";
	}
	public String Review() throws Exception{//对书本评论
		System.out.println("---------Review---------");
		user = (User) request.getSession().getAttribute("user");
		System.out.println(rfb.getContent());
		if(user==null){
			return "goto_login";
		}
		Map<String,String> map = new HashMap<String, String>();
		PrintWriter out =  response.getWriter();
		if(rfb.getContent()==null||rfb.getContent().trim().isEmpty()){
			map.put("reviewsRs", "评论内容不能为空");
			out.print(JSON.toJSONString(map));
			close(out);
			return NONE;
		}
		rfb.setUser(user);
		book = (Book) request.getSession().getAttribute("book");
		List<Long> llist = null;
		long time = -1;
		long now = System.currentTimeMillis()/1000;
		if(book!=null){
			rfb.setBook(book);
			llist = userService.findRfb_Book_nearest(book, user);//找到用户的回复时间集合
		}else{
			work = (Work) request.getSession().getAttribute("work");
			if(work!=null){
				rfb.setWork(work);
				llist = userService.findRfb_Work_nearest(work, user);//找到用户的回复时间集合
			}else{
				return "goto_book";
			}
		}
		if(llist.size()!=0){
			time = llist.get(0);
			if((now - time)>30){//每隔30秒才能回复一次
				rfb.setTime(now);
				userService.addRfb(rfb);
				getReviews();//更新session
				map.put("reviewsRs", "评论成功");
			}else{
				map.put("reviewsRs", "30秒才能回复一次");
			}
		}else{
			rfb.setTime(now);
			userService.addRfb(rfb);
			getReviews();//更新session
			map.put("reviewsRs", "评论成功");
		}
		out.print(JSON.toJSONString(map));
		close(out);
		//记得在bookaction和workaction中添加加载评论记录的功能 至于是什么时候加载这些细节后面再说了
		return "goto_book";
	}
	
	public String ReviewForR() throws Exception{//对用户回复
		System.out.println("---------ReviewForR---------");
		user = (User) request.getSession().getAttribute("user");
		if(user==null){
			return "goto_login";
		}
		Map<String,String> map = new HashMap<String, String>();
		PrintWriter out =  response.getWriter();
		if(rfr.getContent()==null||rfr.getContent().trim().isEmpty()){
			map.put("reviewsRs", "评论内容不能为空");
			out.print(JSON.toJSONString(map));
			close(out);
			return NONE;
		}
		String fontAndId = (String) request.getParameter("font_id");
		int n = fontAndId.indexOf(":");
		String font = fontAndId.substring(0,n);
		int id = Integer.valueOf(fontAndId.substring(n+1,fontAndId.length()));
		if(font.equals("rfb")){
			rfb.setRbid(id);
			rfblist = userService.findRfb(rfb);
			if(rfblist.size()!=0){
				rfb = rfblist.get(0);
				rfr.setRfb(rfb);//外键 所在书评
				rfr.setUser2(rfb.getUser());//回复谁
			}
		}else if(font.equals("rfr")){
			rfr.setRrid(id);
			rfrlist = userService.findRfr(rfr);
			if(rfrlist.size()!=0){
				ReviewsForReviews rfr1 = rfrlist.get(0);
				rfr.setRfb(rfr1.getRfb());//外键 所在书评
				rfr.setUser2(rfr1.getUser1());//回复谁
			}else{
				return "goto_book";
			}
		}
		rfr.setUser1(user);//评论人
		book = (Book) request.getSession().getAttribute("book");
		List<Long> llist = null;
		long time = -1;
		long now = System.currentTimeMillis()/1000;
		if(book!=null){
			rfr.setBook(book);
			llist = userService.findRfr_Book_nearest(book, user);
		}else{
			work = (Work) request.getSession().getAttribute("work");
			if(work!=null){
				rfr.setWork(work);
				llist = userService.findRfr_Work_nearest(work, user);
			}else{
				return "goto_book";
			}
		}
		if(llist.size()!=0){
			time = llist.get(0);
			if((now - time)>30){//每隔30秒才能回复一次
				rfr.setTime(now);
				userService.addRfr(rfr);
				getReviews();//更新session
				map.put("reviewsRs", "评论成功");
			}else{
				map.put("reviewsRs", "评论间隔30秒！");
			}
		}else{
			rfr.setTime(now);
			userService.addRfr(rfr);
			getReviews();//更新session
			map.put("reviewsRs", "评论成功");
		}
		out.print(JSON.toJSONString(map));
		close(out);
		return "goto_book";
	}
	@SuppressWarnings("unchecked")
	public String getMyReviews() throws Exception{//获取所有相关评论
		user = (User) request.getSession().getAttribute("user");
		if(user==null){
			return "goto_login";
		}
		rfrlist = userService.findRfr_User1(user);//主动评论的
		String rfr1_str = JSON.toJSONString(rfrlist);
		/*为了前端load页面的时候不会报session关闭全部值都取出来重写了一遍、、、
		 * 当然了 这样很不可取。当表多了，表和表之间的关系错综烦杂的时候这个负担可不是一般的大。。。
		 * 不过 假如是前后端分离的情况，应该要考虑写一下nativeSQL将需要的数据查出来封装到json以便ajax调用吧，
		 * 不过为了方便，就不自己写sql语句了
		*/
		rfrlist = JSON.parseObject(rfr1_str,new TypeReference<List<ReviewsForReviews>>(){});
		request.getSession().setAttribute("MyRfrSet",rfrlist);
		return "goto_user";
	}
	@SuppressWarnings("unchecked")
	public String getReviewsAboutMe() throws Exception{//获取所有相关评论
		user = (User) request.getSession().getAttribute("user");
		if(user==null){
			return "goto_login";
		}
		rfrlist = userService.findRfr_User2(user);//主动评论的
		String rfr2_str = JSON.toJSONString(rfrlist);
		rfrlist = JSON.parseObject(rfr2_str,new TypeReference<List<ReviewsForReviews>>(){});
		request.getSession().setAttribute("MyRfrSet",rfrlist);
		return "goto_user";
	}
	//随机读取书本或作品
	public String random() throws Exception{
		double n = Math.random();
		System.out.println(n);
		List<Integer> list1 = bookService.selectBid();
		List<Integer> list2 = workService.selectWid();
		int s1 = list1.size();
		int s2 = list2.size();
		if(n<(s1/(double)(s1+s2))){//书本
			book.setBid(list1.get((int)(Math.random()*s1)));
			booklist = bookService.find(book, "bid");
			if(booklist.size()!=0){
				book = booklist.get(0);
				List<String> str = bookUtils.readbook(book.getType(), book.getPath());
				request.getSession().setAttribute("content", str);//书本内容
				request.getSession().setAttribute("doc_count", str.size());//读取到的行数
				request.getSession().setAttribute("page", 1);//默认为第1页
				request.getSession().setAttribute("book", book);
				request.getSession().setAttribute("work", null);
			}
		}else{//作品
			work.setWid(list2.get((int)(Math.random()*s2)));
			worklist = workService.find(work, "wid");
			if(worklist.size()!=0){
				work = worklist.get(0);
				List<String> str = workUtils.readbook_U(work.getUser().getUid(), work.getPath());
				request.getSession().setAttribute("content", str);//书本内容
				request.getSession().setAttribute("doc_count", str.size());//读取到的行数
				request.getSession().setAttribute("page", 1);//默认为第1页
				request.getSession().setAttribute("book", null);
				request.getSession().setAttribute("work", work);
			}
		}
		return "goto_book";
	}
	//获取更新提示
	public String updateFlag() throws Exception{
		user = (User) request.getSession().getAttribute("user");
		if(user==null){
			return NONE;
		}
		userlist = userService.finds(user, "uid");
		PrintWriter out = response.getWriter();
		int a[] = {0,0};
		if(userlist.size()!=0){
			user = userlist.get(0);//重新开启session
			Set<ReviewsForReviews> rfr_set = user.getRfr2();//自己被评论的记录
			Set<Favour> fav_set = user.getFavour();
			for (Favour favour : fav_set) {
				if(favour.getUpdateFlag()==1){
					//提示红点
					request.getSession().setAttribute("updateFlag", true);
					a[0]=1;
					break;
				}
			}
			for (ReviewsForReviews rfr : rfr_set) {
				if(rfr.getFlag()==1){//有人评论
					request.getSession().setAttribute("updateFlag2", true);
					a[1]=1;
					break;
				}
			}
		}
		if(a[0]==0){
			request.getSession().setAttribute("updateFlag", null);
		}
		if(a[1]==0){
			request.getSession().setAttribute("updateFlag2", null);
		}
		String str  = JSON.toJSONString(a);
		out.print(str);
		close(out);
		return NONE;
	}
	
	/********************************************************/
}
