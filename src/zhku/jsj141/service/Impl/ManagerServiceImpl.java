package zhku.jsj141.service.Impl;

import java.util.List;

import zhku.jsj141.dao.ManagerDao;
import zhku.jsj141.service.ManagerService;
import zhku.jsj141.entity.Upload;
import zhku.jsj141.entity.manager.Operate_m;
import zhku.jsj141.entity.user.Book;
import zhku.jsj141.entity.user.User;
import zhku.jsj141.entity.user.Work;
public class ManagerServiceImpl implements ManagerService {
	private ManagerDao managerDao;

	public ManagerDao getManagerDao() {
		return managerDao;
	}

	public void setManagerDao(ManagerDao managerDao) {
		this.managerDao = managerDao;
	}
	@Override
	public List<User> selectAllU(){
		List<User> list = null;
		list = managerDao.selectAllU();
		return list;
	}
	@Override
	public List<Book> selectAllB(){
		List<Book> list = null;
		list = managerDao.selectAllB();
		return list;
	}

	@Override
	public List<Operate_m> findAllRecord() {
		List<Operate_m> list = null;
		list = managerDao.findAllRecord();
		return list;
	}
	@Override
	public List<Operate_m> findRecord(Operate_m operate_m,String name) {
		List<Operate_m> list = null;
		list = managerDao.findRecord(operate_m,name);
		return list;
	}
	@Override
	public boolean addUpload(Upload upload){
		boolean rs = managerDao.addUpload(upload);
		return rs;
	}
	@Override
	public boolean updateUpload(Upload upload){
		boolean rs = managerDao.updateUpload(upload);
		return rs;
	}
	@Override
	public List<Upload> findUpload(Book book){
		List<Upload> list = managerDao.findUpload(book);
		return list;
	}
	@Override
	public List<Upload> findUpload(User user){
		List<Upload> list = managerDao.findUpload(user);
		return list;
	}
	@Override
	public List<Upload> findUpload(Work work){
		List<Upload> list = managerDao.findUpload(work);
		return list;
	}
}
