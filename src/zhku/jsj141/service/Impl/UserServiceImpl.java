package zhku.jsj141.service.Impl;

import java.io.Serializable;
import java.util.List;

import zhku.jsj141.dao.UserDao;
import zhku.jsj141.entity.user.User;
import zhku.jsj141.service.UserService;

public class UserServiceImpl implements UserService{
	private UserDao userDao;

	@Override
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public Serializable add(User user){//增
		Serializable s= userDao.add(user);
		return s;
	}
	@Override
	public String checkuid(User user){//查询用户id是否重复
		System.out.println("--service--");
		String name = "uid";
		List<User> list = userDao.select(user,name);
		if(list!=null&&!list.isEmpty()){
			return "用户id已存在";
		}
		System.out.println("该uid可用("+user.getUid()+")");
		return null;
	}
	@Override
	public String checkE(User user){//查询邮箱是否重复
		System.out.println("--service--");
		String name = "Email";
		List<User> list = userDao.select(user,name);
		if(list!=null&&!list.isEmpty()){
			return "邮箱已被绑定";
		}
		System.out.println("该E可用("+user.getEmail()+")");
		return null;
	}
	@Override
	public User find(User user,String name){
		List<User> list = userDao.select(user,name);
		User u = new User(); 
		if(!list.isEmpty()){
			for (User user2 : list) {
				u = user2;
			}
		}
		return u;
	}
	@Override
	public boolean update(User user){
		userDao.update(user);
		return false;
	}
}
