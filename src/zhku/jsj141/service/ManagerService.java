package zhku.jsj141.service;

import java.util.List;

import zhku.jsj141.entity.Upload;
import zhku.jsj141.entity.manager.Operate_m;
import zhku.jsj141.entity.user.Book;
import zhku.jsj141.entity.user.User;
import zhku.jsj141.entity.user.Work;

public interface ManagerService {

	public List<User> selectAllU();

	public List<Book> selectAllB();

	public List<Operate_m> findAllRecord();

	public List<Operate_m> findRecord(Operate_m operate_m, String name);

	public boolean addUpload(Upload upload);
	
	public boolean updateUpload(Upload upload);

	public List<Upload> findUpload(User user);
	
	public List<Upload> findUpload(Work work);
	
	public List<Upload> findUpload(Book book);

	public boolean deleteUpload(Upload upload);

}
