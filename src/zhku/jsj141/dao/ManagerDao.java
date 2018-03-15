package zhku.jsj141.dao;

import java.util.List;

import zhku.jsj141.entity.manager.Operate_m;
import zhku.jsj141.entity.user.Book;
import zhku.jsj141.entity.user.User;

public interface ManagerDao {

	public List<User> selectAllU();

	public List<Book> selectAllB();

	public boolean addRecord(Operate_m operate_m);

	public List<Operate_m> findRecord();

}
