package zhku.jsj141.action.manager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONString;

import org.apache.struts2.ServletActionContext;

import zhku.jsj141.entity.user.User;
import zhku.jsj141.service.ManagerService;
import zhku.jsj141.service.UserService;

import com.alibaba.fastjson.JSON;
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

	public String getUser() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		List<User> list = managerService.selectAllU();
		request.getSession().setAttribute("userlist", list);
		request.getSession().setAttribute("managerType", "user");
		request.getSession().setAttribute("count", list.size());
		return "goto_edit";
	}

	public String alter() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		User user = new User();
		String json = (String)request.getParameter("json");
		System.out.println(json);
		List<String> list = JSON.parseArray(json,String.class);
		for (String string : list) {
			System.out.println(string+"1");
		}
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
	public String delete() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		User user = new User();
		String uid = (String)request.getParameter("uid");
		if(uid!=""){
			userService.delete(user);
		}
		return "goto_edit";
	}
}
