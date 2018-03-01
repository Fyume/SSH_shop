package zhku.jsj141.service;

import java.io.Serializable;
import java.util.List;

import zhku.jsj141.dao.UserDao;
import zhku.jsj141.entity.user.Favour;
import zhku.jsj141.entity.user.User;

public interface UserService {

	public void setUserDao(UserDao userDao);

	public Serializable add(User user);

	public String checkuid(User user);

	public String checkE(User user);

	public User find(User user, String name);

	public boolean update(User user);

	public boolean delete(User user);

	public boolean addF(Favour favour);

	public List<User> finds(User user, int status, int permission);

}