package zhku.jsj141.service.user;

import java.io.Serializable;
import java.util.List;

import zhku.jsj141.dao.user.UserDao;
import zhku.jsj141.entity.user.User;

public class UserService {
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public Serializable add(User user){//增
		Serializable s= userDao.add(user);
		return s;
	}
	public String checkuid(User user){//查询用户id是否重复
		System.out.println("--service--");
		String name = "Uid";
		List<User> list = userDao.select(user,name);
		if(list!=null&&!list.isEmpty()){
			for (User user2 : list) {
				System.out.println("uid:"+user2.getUid()+" username:"+user2.getUsername());
			}
			return "用户id已存在";
		}
		System.out.println("该uid可用("+user.getUid()+")");
		return null;
	}
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
	public boolean update(User user){
		userDao.update(user);
		return false;
	}
}
