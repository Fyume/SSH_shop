package zhku.jsj141.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;

import zhku.jsj141.entity.user.User;

public interface UserDao {

	public Serializable add(User user);
	public void update(User user);
	public List<User> select(User user,String name);
}
