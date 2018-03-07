package zhku.jsj141.service;

import java.io.Serializable;
import java.util.List;

import zhku.jsj141.dao.WorkDao;
import zhku.jsj141.entity.user.Work;

public interface WorkService {

	public boolean add(Work work);

	public List<Work> find(Work work, String name);

	public boolean update(Work work);

	public List<Work> findAll();

	public List<Work> find_indistinct(Work work, String name);

	public boolean delete(Work work);

}