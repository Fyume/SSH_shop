package zhku.jsj141.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;

import zhku.jsj141.entity.user.Work;

public interface WorkDao {

	public abstract void setHibernateTemplate(
			HibernateTemplate hibernateTemplate);

	public abstract Serializable add(Work work);

	public abstract void update(Work work);

	public abstract List<Work> select(Work work, String name);

	public abstract List<Work> selectAll();

	public abstract List<Work> select_indistinct(Work work, String name);

}