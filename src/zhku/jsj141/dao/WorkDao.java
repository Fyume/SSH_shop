package zhku.jsj141.dao;

import java.util.List;

import zhku.jsj141.entity.user.Work;

public interface WorkDao {

	public boolean add(Work work);

	public boolean update(Work work);

	public List<Work> select(Work work, String name);

	public List<Work> selectAll();

	public List<Work> select_indistinct(Work work, String name);

	public boolean delete(Work work);

	public List<Integer> selectWid();

}