package zhku.jsj141.service;

import java.io.Serializable;

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
}
