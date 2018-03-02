package zhku.jsj141.service.Impl;

import java.io.Serializable;
import java.util.List;

import zhku.jsj141.dao.BookDao;
import zhku.jsj141.entity.Type;
import zhku.jsj141.entity.user.Book;
import zhku.jsj141.service.BookService;

public class BookServiceImpl implements BookService {
	private BookDao bookDao;
	
	@Override
	public BookDao getBookDao() {
		return bookDao;
	}
	@Override
	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}
	@Override
	public Serializable add(Book book){//增
		Serializable s= bookDao.add(book);
		return s;
	}
	@Override
	public String checkbid(Book book){//查询用户id是否重复
		String name = "bid";
		List<Book> list = bookDao.select(book,name);
		if(list!=null&&!list.isEmpty()){
			return "书本id已存在";
		}
		System.out.println("该bid可用("+book.getBid()+")");
		return null;
	}
	@Override
	public List<Book> findAll(){//获取所有书本
		List<Book> list = bookDao.selectAll();
		return list;
	}
	@Override
	public List<Book> find(Book book,String name){//精确查询
		List<Book> list = bookDao.select(book,name);
		return list;
	}
	@Override
	public List<Book> find_indistinct(Book book,String name){//模糊查询
		List<Book> list = bookDao.select_indistinct(book,name);
		return list;
	}
	@Override
	public boolean update(Book book){
		bookDao.update(book);
		return false;
	}
	@Override
	public List<Type> findT(){
		List<Type> list = bookDao.selectT();
		return list;
	}
	@Override
	public boolean delete(Book book){
		boolean rs = bookDao.delete(book);
		return rs;
	}
	@Override
	public List<Book> finds(Book book1, Book book2){
		List<Book> list = null;
		list = bookDao.findByINIPAT(book1,book2);
		return list;
	}
}
