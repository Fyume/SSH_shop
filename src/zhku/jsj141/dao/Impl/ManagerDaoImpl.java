package zhku.jsj141.dao.Impl;

import org.springframework.orm.hibernate5.HibernateTemplate;

import zhku.jsj141.dao.ManagerDao;

public class ManagerDaoImpl implements ManagerDao{
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
}
