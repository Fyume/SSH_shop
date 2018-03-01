package zhku.jsj141.action.manager;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import zhku.jsj141.entity.Type;
import zhku.jsj141.entity.user.Book;
import zhku.jsj141.entity.user.User;
import zhku.jsj141.service.BookService;
import zhku.jsj141.service.ManagerService;
import zhku.jsj141.service.UserService;
import zhku.jsj141.utils.user.bookUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;

public class ManagerAction extends ActionSupport {
	private ManagerService managerService;
	private UserService userService;
	private BookService bookService;
	private bookUtils bookUtils;
	
	public ManagerService getManagerService() {
		return managerService;
	}

	public void setManagerService(ManagerService managerService) {
		this.managerService = managerService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public BookService getBookService() {
		return bookService;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}
	
	public bookUtils getBookUtils() {
		return bookUtils;
	}

	public void setBookUtils(bookUtils bookUtils) {
		this.bookUtils = bookUtils;
	}

	//获取用户信息
	public String getUser() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		List<User> list = managerService.selectAllU();
		request.getSession().setAttribute("userlist", list);
		request.getSession().setAttribute("managerType", "user");
		request.getSession().setAttribute("count", list.size());
		return "goto_edit";
	}
	//获取书本信息
	public String getBook() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		List<Type> typelist = null;
		List<Book> list = managerService.selectAllB();
		typelist = bookService.findT();
		request.getSession().setAttribute("typelist", typelist);
		request.getSession().setAttribute("booklist", list);
		request.getSession().setAttribute("managerType", "book");
		request.getSession().setAttribute("count", list.size());
		return "goto_edit";
	}
	//未完成
	public String alter_U() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		User user = new User();
		String json = (String)request.getParameter("json");
		System.out.println(json);
		JSONObject jsonobj = JSONObject.parseObject(json);
		System.out.println(jsonobj.get("uid"));
		/*String username = (String)request.getParameter("username");
		System.out.println(username);
		String u_permission = (String)request.getParameter("u_permission");
		System.out.println(u_permission);
		int u_per = Integer.parseInt(u_permission);
		user.setUid(uid);
		user = userService.find(user, "uid");
		if(user!=null){
			if(u_per==1){
				user.setU_permission(true);
			}else{
				user.setU_permission(false);
			}
			user.setUsername(username);
			userService.update(user);
		}*/
		return "goto_edit";
	}
	public String alter_B() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		User user = new User();
		String json = (String)request.getParameter("json");
		/*System.out.println(json);*/
		JSONObject jsonobj = JSONObject.parseObject(json);
		/*System.out.println(jsonobj.get("bid"));*/
		int bid = (int) jsonobj.get("bid");
		Book book = new Book();
		List<Book> list = null;
		book.setBid(bid);
		list = bookService.find(book, "bid");
		book = list.get(0);
		if(book!=null){
			book.setBname((String) jsonobj.get("bname"));
			book.setISBN((String) jsonobj.get("ISBN"));
			long publish = new Date((int)jsonobj.get("year"), (int)jsonobj.get("month"), (int)jsonobj.get("date")).getTime()/(1000*60*60);// 转换成时间戳(只需要年月日,为了不丢失精度，保留时)
			book.setPublish(publish);
			book.setDescription((String) jsonobj.get("description"));
			if(!book.getType().equals((String) jsonobj.get("type"))){//假如类型相同就不用改磁盘的位置了（总觉得改类型会疯狂读写很麻烦，考虑要不要不改磁盘位置了）
				String type = book.getType();//原来的分类
				boolean rs = bookUtils.moveBook(book.getPath(), type,(String)jsonobj.get("type"));
				if(rs){
					book.setType((String)jsonobj.get("type"));
				}
				bookService.update(book);
			}
		}
		return "goto_edit";
	}
	public String delete_U() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		User user = new User();
		String uid = (String)request.getParameter("uid");
		if(uid!=""){
			userService.delete(user);
		}
		return "goto_edit";
	}
	public String delete_B() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		User user = new User();
		/*String uid = (String)request.getParameter("uid");*/
		int bid = Integer.parseInt(request.getParameter("bid"));
		if(bid>0){
			Book book = new Book();
			List<Book> list = null;
			book.setBid(bid);
			list = bookService.find(book, "bid");
			book = list.get(0);
			if(book!=null&&book.getBname()!=null){
				if(bookUtils.removeBook(book.getPath(), book.getType())){//先删除磁盘文件
					if(bookService.delete(book)){//再删除数据库的信息
						request.setAttribute("DBRs", "true");
					}
				}
			}else{
				request.setAttribute("DBRs", "false");
			}
		}
		return "goto_edit";
	}
	public String select_B() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String json = (String)request.getParameter("json");
		JSONObject jsonobj = JSONObject.parseObject(json);
		String uid = (String) jsonobj.get("uid");
		String username = (String) jsonobj.get("username");
		int status = Integer.parseInt((String)jsonobj.get("u_status"));
		int permission = Integer.parseInt((String)jsonobj.get("u_permission"));
		boolean u_status = false;
		boolean u_permission = false;
		if(status==1){
			u_status=true;
		}
		if(permission!=0){
			u_permission=true;
		}
		System.out.println(uid+";"+username+";"+u_status+";"+u_permission);
		User user = new User();
		user.setUid(uid);
		user.setUsername(username);
		user.setU_status(u_status);
		user.setU_permission(u_permission);
		System.out.println(user.toString());
		List<User> list = userService.finds(user,status,permission);
		/*for (User user2 : list) {
			System.out.println(user2.toString());
		}*/
		request.getSession().setAttribute("userlist", list);
		JSONObject jobj = new JSONObject();
		jobj.put("select_B", true);
		PrintWriter out = response.getWriter();
		out.print(jobj.toString());
		out.flush();
		out.close();
		return "goto_edit";
	}
}
