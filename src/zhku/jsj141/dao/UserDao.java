package zhku.jsj141.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;

import zhku.jsj141.entity.user.Favour;
import zhku.jsj141.entity.user.User;

public interface UserDao {

	public abstract void setHibernateTemplate(
			HibernateTemplate hibernateTemplate);

	public abstract Serializable add(User user);

	public abstract void update(User user);

	public abstract List<User> select(User user, String name);

	public abstract void delete(User user);

	public abstract void addF(Favour favour);

}