package zhku.jsj141.daoImpl;

import java.io.Serializable;

import org.springframework.orm.hibernate5.HibernateTemplate;

import zhku.jsj141.entity.User;

public class UserDaoImpl {
	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	public Serializable add(User user){
		Serializable s = hibernateTemplate.save(user);
		return s;
	}
}
