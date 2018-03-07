package zhku.jsj141.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;

import zhku.jsj141.entity.Type;
import zhku.jsj141.entity.user.Book;

public interface BookDao {

	public boolean add(Book book);

	public boolean update(Book book);

	public List<Book> select(Book book, String name);

	public List<Type> selectT();

	public List<Book> select_indistinct(Book book, String name);

	public List<Book> selectAll();

	public boolean delete(Book book);

	public List<Book> findByINIPAT(Book book1, Book book2);
	
}