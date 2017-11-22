package zhku.jsj141.service;

import java.io.Serializable;
import java.util.List;

import zhku.jsj141.dao.BookDao;
import zhku.jsj141.entity.user.Book;
import zhku.jsj141.utils.user.bookUtils;

public interface BookService {

	public abstract BookDao getBookDao();

	public abstract void setBookDao(BookDao bookDao);

	public abstract Serializable add(Book book);

	public abstract String checkbid(Book book);

	public abstract List<Book> find(Book book, String name);

	public abstract boolean update(Book book);

}