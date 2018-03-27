package zhku.jsj141.Interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

import zhku.jsj141.entity.user.User;

public class roleCtrlInterceptor implements Interceptor{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		System.out.println("------roleCtrlInterceptor--------");
		//拦截器里面是通过这个方法获取request的
		HttpServletRequest request = (HttpServletRequest) invocation.getInvocationContext().get(StrutsStatics.HTTP_REQUEST);
		User user = (User) request.getSession().getAttribute("user");
		if(user!=null){
			System.out.println(user.toString());
			if(user.isU_permission()){//管理员
				invocation.invoke();
			}
		}
		// TODO Auto-generated method stub
		return "goto_login";
	}  
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
}
