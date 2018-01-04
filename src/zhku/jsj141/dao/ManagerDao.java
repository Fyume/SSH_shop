package zhku.jsj141.dao;

import java.util.List;

import zhku.jsj141.entity.user.Book;
import zhku.jsj141.entity.user.User;

public interface ManagerDao {

	public abstract List<User> selectAllU();

	public abstract List<Book> selectAllB();

}
