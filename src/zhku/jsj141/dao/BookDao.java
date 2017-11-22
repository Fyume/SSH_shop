package zhku.jsj141.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;

import zhku.jsj141.entity.user.Book;

public interface BookDao {

	public abstract void setHibernateTemplate(
			HibernateTemplate hibernateTemplate);

	public Serializable add(Book book);

	public abstract void update(Book book);

	public abstract List<Book> select(Book book, String name);

}