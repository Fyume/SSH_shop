package zhku.jsj141.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;

import zhku.jsj141.entity.user.Favour;
import zhku.jsj141.entity.user.User;

public interface UserDao {

	public void setHibernateTemplate(
			HibernateTemplate hibernateTemplate);

	public Serializable add(User user);

	public void update(User user);

	public List<User> select(User user, String name);

	public void delete(User user);

	public void addF(Favour favour);

	public List<User> findByIN(User user);

	public List<User> findByINSP(User user);

	public List<User> findByINS(User user);

	public List<User> findByINP(User user);

}