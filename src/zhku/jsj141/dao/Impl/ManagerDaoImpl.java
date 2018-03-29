package zhku.jsj141.dao.Impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.HibernateTemplate;

import zhku.jsj141.dao.ManagerDao;
import zhku.jsj141.entity.Upload;
import zhku.jsj141.entity.manager.Operate_m;
import zhku.jsj141.entity.user.Book;
import zhku.jsj141.entity.user.History;
import zhku.jsj141.entity.user.User;
import zhku.jsj141.entity.user.Work;
public class ManagerDaoImpl implements ManagerDao{
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<User> selectAllU(){
		List<User> list = null;
		hibernateTemplate.getSessionFactory().getCurrentSession().clear();
		list = (List<User>) hibernateTemplate.find("from User");
		System.out.println("----ManagerDao----");
		for (User user : list) {
			System.out.println(user.toString());
		}
		return list;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Book> selectAllB(){
		List<Book> list = null;
		hibernateTemplate.getSessionFactory().getCurrentSession().clear();
		list = (List<Book>) hibernateTemplate.find("from Book");
		System.out.println("------ManagerDao");
		for (Book book : list) {
			System.out.println(book.toString());
		}
		return list;
	}
	@Override
	public boolean addUpload(Upload upload){
		try{
			hibernateTemplate.save(upload);
		}catch(DataAccessException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	@Override
	public boolean updateUpload(Upload upload){
		try{
			hibernateTemplate.update(upload);
		}catch(DataAccessException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Upload> findUpload(Work work){
		List<Upload> list = null;
		try{
			list = (List<Upload>) hibernateTemplate.find("from Upload where wid = ?",work.getWid());
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		return list;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Upload> findUpload(User user){
		List<Upload> list = null;
		try{
			list = (List<Upload>) hibernateTemplate.find("from Upload where uid = ?",user.getUid());
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		return list;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Upload> findUpload(Book book){
		List<Upload> list = null;
		try{
			list = (List<Upload>) hibernateTemplate.find("from Upload where bid = ?",book.getBid());
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public boolean addRecord(Operate_m operate_m){
		try{
			hibernateTemplate.save(operate_m);
		}catch(DataAccessException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Operate_m> findAllRecord(){
		List<Operate_m> list = null;
		try{
			list = (List<Operate_m>) hibernateTemplate.find("from Operate_m");
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		return list;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Operate_m> findRecord(Operate_m operate_m,String name){
		String name_m = name.substring(0, 1).toUpperCase()+name.substring(1,name.length());
		List<Operate_m> list = null;
		try{
			list = (List<Operate_m>) hibernateTemplate.find("from Operate_m where "+ name + "=?",
					operate_m.getClass().getMethod("get" + name_m)
					.invoke(operate_m));
		}catch(DataAccessException| IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException e){
			e.printStackTrace();
		}
		return list;
	}
}
