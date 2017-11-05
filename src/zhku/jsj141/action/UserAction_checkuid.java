package zhku.jsj141.action;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import zhku.jsj141.entity.User;
import zhku.jsj141.service.UserService;

import com.opensymphony.xwork2.ActionSupport;

public class UserAction_checkuid extends ActionSupport {
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String uid = request.getParameter("uid");
		System.out.println(uid);
		User user = new User();
		user.setUid(uid);
		request.getSession().setAttribute("uid_check", userService.select(user));
		return SUCCESS;
    }
}
