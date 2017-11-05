package zhku.jsj141.service;

import java.io.Serializable;
import java.util.List;

import zhku.jsj141.dao.UserDao;
import zhku.jsj141.entity.User;

public class UserService {
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public Serializable add(User user){
		Serializable s= userDao.add(user);
		return s;
	}
	public boolean select(User user){
		List<User> list = userDao.select(user);
		for (User user2 : list) {
			System.out.println("uid:"+user2.getUid()+" username:"+user2.getUsername());
		}
		if(list!=null){
			return true;
		}
		return false;
	}
}
