package zhku.jsj141.service.Impl;

import java.util.List;

import zhku.jsj141.dao.WorkDao;
import zhku.jsj141.entity.user.Work;
import zhku.jsj141.service.WorkService;

public class WorkServiceImpl implements WorkService {
	private WorkDao workDao;

	public WorkDao getWorkDao() {
		return workDao;
	}
	public void setWorkDao(WorkDao workDao) {
		this.workDao = workDao;
	}

	@Override
	public boolean add(Work work) {// 增
		boolean rs = workDao.add(work);
		return rs;
	}

	@Override
	public List<Work> find(Work work, String name) {//精确查询
		List<Work> list = workDao.select(work, name);
		return list;
	}
	@Override
	public List<Work> find_indistinct(Work work, String name) {//模糊查询
		List<Work> list = workDao.select_indistinct(work, name);
		return list;
	}
	@Override
	public boolean update(Work work) {
		workDao.update(work);
		return true;
	}
	@Override
	public List<Work> findAll(){
		List<Work> list = workDao.selectAll();
		return list;
	}
	@Override
	public boolean delete(Work work){
		boolean rs = workDao.delete(work);
		return rs;
	}
}
