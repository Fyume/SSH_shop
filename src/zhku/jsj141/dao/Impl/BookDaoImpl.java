package zhku.jsj141.dao.Impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.HibernateTemplate;

import zhku.jsj141.dao.BookDao;
import zhku.jsj141.entity.Type;
import zhku.jsj141.entity.user.Book;

public class BookDaoImpl implements BookDao{
	private HibernateTemplate hibernateTemplate;
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	public boolean add(Book book) {
		try{
			hibernateTemplate.save(book);
		}catch(DataAccessException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	@Override
	public boolean update(Book book){
		try{
			hibernateTemplate.saveOrUpdate(book);
		}catch(DataAccessException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	@Override
	public List<Book> selectAll(){
		List<Book> list = (List<Book>) hibernateTemplate.find("from Book");
		return list;
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<Book> select_indistinct(Book book, String name) {
		String name_m = name.substring(0, 1).toUpperCase()+name.substring(1,name.length());
		List<Book> list = null;
		try {
			list = (List<Book>) hibernateTemplate.find("from Book where "
					+ name + " like ?","%"+
					book.getClass().getMethod("get" + name_m)
							.invoke(book)+"%");//反射机制调用方法
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
	public List<Book> select(Book book, String name) {
		String name_m = name.substring(0, 1).toUpperCase()+name.substring(1,name.length());
		List<Book> list = null;
		try {
			list = (List<Book>) hibernateTemplate.find("from Book where "
					+ name + " =?",
					book.getClass().getMethod("get" + name_m)
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
	@SuppressWarnings("unchecked")
	@Override
	public List<Type> selectT(){
		List<Type> list = (List<Type>) hibernateTemplate.find("from Type");
		return list;
	}
	@Override
	public boolean delete(Book book){
		try{
			hibernateTemplate.delete(book);
		}catch(DataAccessException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Book> findByINIPAT(Book book1, Book book2){//不定项筛选
		List<Book> list = null;
		if(book1.getBid()==-1){
			if(book2.getPublish()!=0){
				list = (List<Book>) hibernateTemplate.find("from Book where bname like ? and ISBN like ? and publish >= ? and publish <= ? and author like ? and type like ?","%"+book1.getBname()+"%","%"+book1.getISBN()+"%",book1.getPublish(),book2.getPublish(),"%"+book1.getAuthor()+"%","%"+book1.getType()+"%");
			}else{
				list = (List<Book>) hibernateTemplate.find("from Book where bname like ? and ISBN like ? and publish >= ? and author like ? and type like ?","%"+book1.getBname()+"%","%"+book1.getISBN()+"%",book1.getPublish(),"%"+book1.getAuthor()+"%","%"+book1.getType()+"%");
			}
		}else{
			if(book2.getPublish()!=0){
				list = (List<Book>) hibernateTemplate.find("from Book where bid like ? and bname like ? and ISBN like ? and publish >= ? and publish <= ? and author like ? and type like ?", book1.getBid(),"%"+book1.getBname()+"%","%"+book1.getISBN()+"%",book1.getPublish(),book2.getPublish(),"%"+book1.getAuthor()+"%","%"+book1.getType()+"%");
			}else{
				list = (List<Book>) hibernateTemplate.find("from Book where bid like ? and bname like ? and ISBN like ? and publish >= ? and author like ? and type like ?", book1.getBid(),"%"+book1.getBname()+"%","%"+book1.getISBN()+"%",book1.getPublish(),"%"+book1.getAuthor()+"%","%"+book1.getType()+"%");
			}
		}
		return list;
	}
}
