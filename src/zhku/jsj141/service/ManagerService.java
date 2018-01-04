package zhku.jsj141.service;

import java.util.List;

import zhku.jsj141.entity.user.Book;
import zhku.jsj141.entity.user.User;

public interface ManagerService {

	public abstract List<User> selectAllU();

	public abstract List<Book> selectAllB();

}
