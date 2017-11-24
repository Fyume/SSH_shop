package zhku.jsj141.dao.Impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.HibernateTemplate;

import zhku.jsj141.dao.WorkDao;
import zhku.jsj141.entity.user.Work;


public class WorkDaoImpl implements WorkDao {
	private HibernateTemplate hibernateTemplate;
	@Override
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	@Override
	public Serializable add(Work work) {
		Serializable s = hibernateTemplate.save(work);
		return s;
	}
	@Override
	public void update(Work work){
		hibernateTemplate.saveOrUpdate(work);
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<Work> select(Work work, String name) {

		List<Work> list = null;
		try {
			list = (List<Work>) hibernateTemplate.find("from Work where "
					+ name + "=?",
					work.getClass().getMethod("get" + name)
							.invoke(work));//反射机制调用方法
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
