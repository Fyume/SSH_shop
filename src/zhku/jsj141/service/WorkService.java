package zhku.jsj141.service;

import java.io.Serializable;
import java.util.List;

import zhku.jsj141.dao.WorkDao;
import zhku.jsj141.entity.user.Work;

public interface WorkService {

	public abstract WorkDao getWorkDao();

	public abstract void setWorkDao(WorkDao workDao);

	public abstract Serializable add(Work work);

	public abstract List<Work> find(Work work, String name);

	public abstract boolean update(Work work);

	public abstract List<Work> findAll();

}