package zhku.jsj141.service;

import java.io.Serializable;
import java.util.List;

import zhku.jsj141.dao.UserDao;
import zhku.jsj141.entity.user.User;

public interface UserService {
	public Serializable add(User user);
	public String checkuid(User user);
	public User find(User user,String name);
	public boolean update(User user);
}
