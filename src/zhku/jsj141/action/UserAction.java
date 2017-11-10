package zhku.jsj141.action;

import java.io.PrintWriter;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import zhku.jsj141.entity.User;
import zhku.jsj141.service.UserService;

import com.opensymphony.xwork2.ActionSupport;
@Controller
public class UserAction extends ActionSupport {
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public String checkuid() throws Exception {
		System.out.println("checkuid");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");//过滤器不知道为什么不起作用
		PrintWriter out = response.getWriter();
		String uid = request.getParameter("uid");
		System.out.println("--action--");
		System.out.println(uid);
		if(uid!=null&&uid!=""){
			User user = new User();
			user.setUid(uid);
			out.print(userService.checkuid(user));
			
		}
		out.flush();
		out.close();
		return NONE;
    }
	public String register() throws Exception {
		System.out.println("register");
		HttpServletRequest request = ServletActionContext.getRequest();
		String uid = request.getParameter("uid");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String address = request.getParameter("address");
		String IDCN = request.getParameter("IDCN");
		String telnum = request.getParameter("telnum");
		String email = request.getParameter("email");
		User user = new User(uid,username,password,address,IDCN,telnum,email);
		System.out.println(user.toString());
		/*if(user!=null){
			Serializable s =userService.add(user);
			request.setAttribute("uid", s);
			request.setAttribute("functionname", "注册成功,");//loading页面需要显示
			return "ok";
		}*/
        return NONE;
    }
}
