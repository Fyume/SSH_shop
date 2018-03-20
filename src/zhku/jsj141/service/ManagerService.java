package zhku.jsj141.service;

import java.util.List;

import zhku.jsj141.entity.manager.Operate_m;
import zhku.jsj141.entity.user.Book;
import zhku.jsj141.entity.user.User;

public interface ManagerService {

	public List<User> selectAllU();

	public List<Book> selectAllB();

	public List<Operate_m> findRecord();

}
