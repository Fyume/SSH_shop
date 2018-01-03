package zhku.jsj141.service.Impl;

import java.util.List;

import zhku.jsj141.dao.ManagerDao;
import zhku.jsj141.service.ManagerService;
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
}
