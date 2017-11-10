package zhku.jsj141.action;

import java.io.PrintWriter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import zhku.jsj141.entity.User;
import zhku.jsj141.service.UserService;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
@Controller
public class UserAction extends ActionSupport {
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public UserService getUserService() {
		return userService;
	}
	public String checkuid() throws Exception {
		System.out.println("checkuid");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");//过滤器不知道为什么不起作用
		String uid = request.getParameter("uid");
		System.out.println("--action--");
		System.out.println("uid:"+uid);
		if(uid!=null&&uid!=""){
			User user = new User();
			user.setUid(uid);
			String checkresult = userService.checkuid(user);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("check_uid",checkresult);
			String result = JSONObject.toJSONString(map);
			System.out.println("result:"+result);
			PrintWriter out = response.getWriter();
			out.write(result);
			out.flush();
			out.close();
		}
		return NONE;
    }
	public String register() throws Exception {
		System.out.println("register");
		HttpServletRequest request = ServletActionContext.getRequest();
		String uid = request.getParameter("用户ID");
		String username = request.getParameter("用户名");
		String name = request.getParameter("姓名");
		String password = request.getParameter("密码");
		String address = request.getParameter("地址");
		String IDCN = request.getParameter("身份证号码");
		String telnum = request.getParameter("电话");
		String email = request.getParameter("邮箱");
		User user = new User(uid,name,username,password,address,IDCN,telnum,email);
		System.out.println(user.toString());
		if(user!=null){
			Serializable s =userService.add(user);
			request.setAttribute("uid", s);
			request.setAttribute("functionname", "注册成功,");//loading页面需要显示
			request.setAttribute("gohere", "/user/login.jsp");//loading页面需要显示
			return "register_ok";
		}
        return NONE;
    }
}
