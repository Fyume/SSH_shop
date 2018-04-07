package zhku.jsj141.service.aspect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import zhku.jsj141.action.BaseAction;
import zhku.jsj141.dao.BookDao;
import zhku.jsj141.dao.ManagerDao;
import zhku.jsj141.dao.UserDao;
import zhku.jsj141.dao.WorkDao;
import zhku.jsj141.entity.Upload;
import zhku.jsj141.entity.manager.Operate_m;
import zhku.jsj141.entity.user.Book;
import zhku.jsj141.entity.user.User;
import zhku.jsj141.entity.user.Work;
import zhku.jsj141.test.testClass;

public class Record{//要不要直接操作dao层？ 因为service层都被增强了 记得spring的事务配置。。被坑的不少
	/**
	 * 
	 */
	private String SimpleName;//类的简单名
	private String Method;//方法名
	private String value1;//实体类的json字符串(更新前)
	private String value2;//实体类的json字符串(更新后)
	User user = new User();
	Book book = new Book();
	Work work = new Work();
	List<User> userlist = null;
	List<Book> booklist = null;
	List<Work> worklist = null;
	Operate_m operate_m = new Operate_m();
	
	private ManagerDao managerDao;//只能通过注入获得。。。被坑了一把
	private BookDao bookDao;//发现好像如果要实现update的数据更新前后的存储的话全部dao都要调用。。。。
	private UserDao userDao;
	public ManagerDao getManagerDao() {
		return managerDao;
	}
	public void setManagerDao(ManagerDao managerDao) {
		this.managerDao = managerDao;
	}
	public BookDao getBookDao() {
		return bookDao;
	}
	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}
	public UserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public boolean record(ProceedingJoinPoint jp){
		System.out.println("--------------Record-------------");
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		user = (User) request.getSession().getAttribute("user");
		if(user==null||!user.isU_permission()){//仅针对管理员对书本和用户的增删改
			try {
				jp.proceed();
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}
		operate_m.setUser(user);
		System.out.println("u_per:"+operate_m.getUser().isU_permission());
		SimpleName = jp.getSignature().getDeclaringType().getSimpleName();
		Method = jp.getSignature().getName();
		long time = System.currentTimeMillis()/(1000);
		operate_m.setTime(time);
		/*System.out.println(jp.getSignature());
		System.out.println(jp.getSignature().getDeclaringType());
		System.out.println(jp.getSignature().getDeclaringTypeName());*/
		if(Method.equals("add")){
			operate_m.setType_flag(1);
		}
		if(Method.equals("delete")){
			operate_m.setType_flag(2);
		}
		if(Method.equals("update")){
			operate_m.setType_flag(3);
		}
		int type_flag = operate_m.getType_flag();
		if(SimpleName.equals("UserServiceImpl")){//由于只给了管理用户和书本的接口 就不记录关于收藏表 历史表的操作了
			operate_m.setEntity("user");
			User user2 = (User) jp.getArgs()[0];
			switch(type_flag){
				case 1:
					try {
						jp.proceed();
					} catch (Throwable e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					value2 = tojson(user2);
					operate_m.setValue_after(value2);
					managerDao.addRecord(operate_m);
					break;
				case 2:
					value1 = tojson(user2);
					operate_m.setValue_before(value1);
					managerDao.addRecord(operate_m);
					try {
						jp.proceed();
					} catch (Throwable e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case 3:
					value2 = tojson(user2);
					operate_m.setValue_after(value2);
					User user3 = new User();
					user3.setUid(((User) jp.getArgs()[0]).getUid());
					user3 = userDao.select(user3, "uid").get(0);
					if(!user3.getUsername().equals(user2.getUsername())||user3.isU_permission()!=user2.isU_permission()){
						value1 = tojson(user3);
						operate_m.setValue_before(value1);
						managerDao.addRecord(operate_m);
					}
					try {
						jp.proceed();
					} catch (Throwable e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}break;
				default:
					try {
						jp.proceed();
					} catch (Throwable e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
			}
		}else if(SimpleName.equals("BookServiceImpl")){//由于只给了管理用户和书本的接口 就不记录关于收藏表 历史表的操作了
			operate_m.setEntity("book");
			book = (Book) jp.getArgs()[0];
			switch(type_flag){
				case 1:
					try {
						jp.proceed();
					} catch (Throwable e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					value2 = tojson(book);
					operate_m.setValue_after(value2);
					managerDao.addRecord(operate_m);
					break;
				case 2:
					value1 = tojson(book);
					operate_m.setValue_before(value1);
					managerDao.addRecord(operate_m);
					try {
						jp.proceed();
					} catch (Throwable e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case 3:
					value2 = tojson(book);
					operate_m.setValue_after(value2);
					book = new Book();
					book.setBid(((Book) jp.getArgs()[0]).getBid());
					book = bookDao.select(book, "bid").get(0);
					value1 = tojson(book);
					operate_m.setValue_before(value1);
					managerDao.addRecord(operate_m);
					try {
						jp.proceed();
					} catch (Throwable e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}break;
				default:
					try {
						jp.proceed();
					} catch (Throwable e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
			}
		}else{
			try {
				jp.proceed();
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}
	//要求javabean的编写需要将hibernate表和表之间配置关系时引入的对象放到基本属性的后面，不然就会少转了对象后面的基本属性
	/* 使用指南: （参考test类） 突然感觉fastjson这个包好强大啊，不过有不把实体对象转成json这个方法就好了 对hibernate的项目不太友好
	 * 	System.out.println(tojson(tc));
	 * 	System.out.println(JSON.toJSONString(tc));
	 * 
	 * 	System.out.println(tc.toString());
	 * 	System.out.println(JSON.parseObject(JSON.toJSONString(tc),new TypeReference<testClass>(){}).toString());
	 */
	public String tojson(Object object){//想不到写了这么个玩意儿。。。。就为了转json成功（不带由于配hibernate表与表之间的关系而加的实体对象或者set）
		String json = null;
		/*System.out.println(object.toString());*/
		Field[] field = object.getClass().getDeclaredFields();
		for (int i = 0;i<(field.length);i++) {
			if(i==0){
				json = "{";
			}else{
				json = json+",";
			}
			String name = field[i].getName();//参数名字
			String type = field[i].getGenericType().toString();//参数类型
			int num = type.lastIndexOf(".");
			if(num!=-1){//被String类型坑了一把
				type = type.substring(num+1, type.length());
			}
			/*System.out.println("name:"+name+" ; type:"+type);*/
			name = name.substring(0, 1).toUpperCase()+name.substring(1, name.length());//用于反射机制获取值
			if(type.equals("boolean")){
				boolean bl = false;
				try {
					bl = (boolean) object.getClass().getMethod("is"+name).invoke(object);
					json = json+"\""+field[i].getName()+"\":"+bl;
				} catch (IllegalAccessException | IllegalArgumentException
						| InvocationTargetException | NoSuchMethodException
						| SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(type.equals("int")||type.equals("long")||type.equals("String")){
				Object value = null;
				try {
					value = object.getClass().getMethod("get"+name).invoke(object);
				} catch (IllegalAccessException | IllegalArgumentException
						| InvocationTargetException | NoSuchMethodException
						| SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(type.equals("String")){//只有这个String类型比较独特啊
					value = "\""+value+"\"";
				}
				json = json+"\""+field[i].getName()+"\":"+value;
			}else{
				json = json.substring(0, json.length()-1)+"}";//把逗号去掉,结束
				break;
			}
			if(i==(field.length-1)){
				json = json + "}";
			}
			/*System.out.println(field[i].getGenericType());
			System.out.println(field[i].getName());*/
		}
		return json;
	}
}
