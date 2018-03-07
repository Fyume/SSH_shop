package zhku.jsj141.service;

import java.io.Serializable;
import java.util.List;

import zhku.jsj141.dao.BookDao;
import zhku.jsj141.entity.Type;
import zhku.jsj141.entity.user.Book;

public interface BookService {

	public boolean add(Book book);

	public String checkbid(Book book);

	public List<Book> find(Book book, String name);

	public boolean update(Book book);

	public List<Type> findT();

	public List<Book> find_indistinct(Book book, String name);

	public List<Book> findAll();

	public boolean delete(Book book);

	public List<Book> finds(Book book1, Book book2);
}