package zhku.jsj141.action.manager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONString;

import org.apache.struts2.ServletActionContext;

import zhku.jsj141.entity.user.Book;
import zhku.jsj141.entity.user.User;
import zhku.jsj141.service.ManagerService;
import zhku.jsj141.service.UserService;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;

public class ManagerAction extends ActionSupport {
	private ManagerService managerService;
	private UserService userService;

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
		List<Book> list = managerService.selectAllB();
		request.getSession().setAttribute("booklist", list);
		request.getSession().setAttribute("managerType", "book");
		request.getSession().setAttribute("count", list.size());
		return "goto_edit";
	}
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
		System.out.println(json);
		JSONObject jsonobj = JSONObject.parseObject(json);
		System.out.println(jsonobj.get("bid"));
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
		String uid = (String)request.getParameter("uid");
		if(uid!=""){
			userService.delete(user);
		}
		return "goto_edit";
	}
}
