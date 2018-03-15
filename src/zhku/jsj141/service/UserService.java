package zhku.jsj141.service;

import java.io.Serializable;
import java.util.List;

import zhku.jsj141.dao.UserDao;
import zhku.jsj141.entity.user.Book;
import zhku.jsj141.entity.user.Favour;
import zhku.jsj141.entity.user.History;
import zhku.jsj141.entity.user.User;
import zhku.jsj141.entity.user.Work;

public interface UserService {

	public boolean add(User user);
	
	public boolean update(User user);

	public boolean delete(User user);

	public String checkuid(User user);

	public String checkE(User user);
	
	public List<User> finds(User user, int status, int permission);
	
	public List<User> finds(User user, String name);

	public boolean addF(Favour favour);

	public boolean addHistory(History history);
	
	public List<History> findH(User user);

	public List<History> findH(User user, Book book);
	
	public List<History> findH(User user, Work work);

	public List<Favour> findF(User user);

	public List<Favour> findF(User user, Book book);
	
	public List<Favour> findF(User user, Work work);

	public boolean delF(Favour favour);

}