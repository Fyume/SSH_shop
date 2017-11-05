package zhku.jsj141.action;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import zhku.jsj141.entity.User;
import zhku.jsj141.service.UserService;

public class UserAction_register extends ActionSupport{
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String uid = request.getParameter("uid");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String address = request.getParameter("address");
		String IDCN = request.getParameter("IDCN");
		String telnum = request.getParameter("telnum");
		String email = request.getParameter("email");
		User user = new User(uid,username,password,address,IDCN,telnum,email);
		Serializable s =userService.add(user);
		if(s!=null||!s.equals("")){
			request.getSession().setAttribute("uid", s);
			return "ok";
		}
        return "false";
    }
}
