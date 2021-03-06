package zhku.jsj141.dao.Impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.HibernateTemplate;

import zhku.jsj141.dao.WorkDao;
import zhku.jsj141.entity.user.Work;


public class WorkDaoImpl implements WorkDao {
	private HibernateTemplate hibernateTemplate;
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	@Override
	public boolean add(Work work) {
		try{
			hibernateTemplate.save(work);
		}catch(DataAccessException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	@Override
	public boolean update(Work work){
		try{
			hibernateTemplate.merge(work);
		}catch(DataAccessException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Work> selectAll(){
		hibernateTemplate.getSessionFactory().getCurrentSession().clear();
		List<Work> list = (List<Work>) hibernateTemplate.find("from Work");
		return list;
	}
	//用来随机读取的
		@SuppressWarnings("unchecked")
		@Override
		public List<Integer> selectWid(){
			hibernateTemplate.getSessionFactory().getCurrentSession().clear();
			List<Integer> list = (List<Integer>) hibernateTemplate.find("select wid from Work");
			return list;
		}
	@Override
	@SuppressWarnings("unchecked")
	public List<Work> select_indistinct(Work work, String name) {
		String name_m = name.substring(0, 1).toUpperCase()+name.substring(1,name.length());
		List<Work> list = null;
		try {
			hibernateTemplate.getSessionFactory().getCurrentSession().clear();
			list = (List<Work>) hibernateTemplate.find("from Work where "
					+ name + " like ?","%"+
					work.getClass().getMethod("get" + name_m)
							.invoke(work)+"%");//反射机制调用方法
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
	@SuppressWarnings("unchecked")
	public List<Work> select(Work work, String name) {
		String name_m = name.substring(0, 1).toUpperCase()+name.substring(1,name.length());//get方法 字段首字母大写
		List<Work> list = null;
		try {
			hibernateTemplate.getSessionFactory().getCurrentSession().clear();
			list = (List<Work>) hibernateTemplate.find("from Work where "
					+ name + "=?",
					work.getClass().getMethod("get" + name_m)
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
	@Override
	public boolean delete(Work work){
		try{
			hibernateTemplate.delete(work);
		}catch(DataAccessException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
