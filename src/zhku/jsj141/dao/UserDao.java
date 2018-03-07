package zhku.jsj141.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;


import zhku.jsj141.entity.user.Book;
import zhku.jsj141.entity.user.Favour;
import zhku.jsj141.entity.user.History;
import zhku.jsj141.entity.user.User;
import zhku.jsj141.entity.user.Work;

public interface UserDao {

	public void setHibernateTemplate(
			HibernateTemplate hibernateTemplate);

	public boolean add(User user);

	public boolean update(User user);

	public List<User> select(User user, String name);

	public boolean delete(User user);

	public boolean addF(Favour favour);

	public List<User> findByIN(User user);

	public List<User> findByINSP(User user);

	public List<User> findByINS(User user);

	public List<User> findByINP(User user);

	public boolean addHistory(History history);
	
	public List<History> findH(User user);

	public List<History> findH(User user, Book book);
	
	public List<History> findH(User user, Work work);

}