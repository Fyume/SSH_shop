package zhku.jsj141.service.Impl;

import java.io.Serializable;
import java.util.List;

import zhku.jsj141.dao.WorkDao;
import zhku.jsj141.entity.user.Work;
import zhku.jsj141.service.WorkService;

public class WorkServiceImpl implements WorkService {
	private WorkDao workDao;

	@Override
	public WorkDao getWorkDao() {
		return workDao;
	}

	@Override
	public void setWorkDao(WorkDao workDao) {
		this.workDao = workDao;
	}

	@Override
	public Serializable add(Work work) {// 增
		Serializable s = workDao.add(work);
		return s;
	}

	@Override
	public List<Work> find(Work work, String name) {
		List<Work> list = workDao.select(work, name);
		return list;
	}

	@Override
	public boolean update(Work work) {
		workDao.update(work);
		return false;
	}

}
