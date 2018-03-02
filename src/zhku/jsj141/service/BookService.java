package zhku.jsj141.service;

import java.io.Serializable;
import java.util.List;

import zhku.jsj141.dao.BookDao;
import zhku.jsj141.entity.Type;
import zhku.jsj141.entity.user.Book;

public interface BookService {

	public abstract BookDao getBookDao();

	public abstract void setBookDao(BookDao bookDao);

	public abstract Serializable add(Book book);

	public abstract String checkbid(Book book);

	public abstract List<Book> find(Book book, String name);

	public abstract boolean update(Book book);

	public abstract List<Type> findT();

	public abstract List<Book> find_indistinct(Book book, String name);

	public abstract List<Book> findAll();

	public abstract boolean delete(Book book);

	public abstract List<Book> finds(Book book1, Book book2);
}