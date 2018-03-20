package zhku.jsj141.service.Impl;

import java.util.List;

import zhku.jsj141.dao.ManagerDao;
import zhku.jsj141.service.ManagerService;
import zhku.jsj141.entity.manager.Operate_m;
import zhku.jsj141.entity.user.Book;
import zhku.jsj141.entity.user.User;
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
	public List<Operate_m> findRecord() {
		List<Operate_m> list = null;
		list = managerDao.findRecord();
		return list;
	}
}
