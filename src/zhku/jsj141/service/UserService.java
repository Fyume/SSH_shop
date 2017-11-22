package zhku.jsj141.service;

import java.io.Serializable;

import zhku.jsj141.dao.UserDao;
import zhku.jsj141.entity.user.User;

public interface UserService {

	public abstract void setUserDao(UserDao userDao);

	public abstract Serializable add(User user);

	public abstract String checkuid(User user);

	public abstract String checkE(User user);

	public abstract User find(User user, String name);

	public abstract boolean update(User user);

}