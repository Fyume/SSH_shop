package zhku.jsj141.dao.Impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.HibernateTemplate;

import zhku.jsj141.dao.BookDao;
import zhku.jsj141.entity.user.Book;

public class BookDaoImpl implements BookDao{
	private HibernateTemplate hibernateTemplate;
	@Override
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	@Override
	public Serializable add(Book book) {
		Serializable s = hibernateTemplate.save(book);
		return s;
	}
	@Override
	public void update(Book book){
		hibernateTemplate.saveOrUpdate(book);
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<Book> select(Book book, String name) {

		List<Book> list = null;
		try {
			list = (List<Book>) hibernateTemplate.find("from Book where "
					+ name + "=?",
					book.getClass().getMethod("get" + name)
							.invoke(book));//反射机制调用方法
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
