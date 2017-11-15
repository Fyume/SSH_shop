package zhku.jsj141.dao.Impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.HibernateTemplate;

import zhku.jsj141.dao.UserDao;
import zhku.jsj141.entity.user.User;

public class UserDaoImpl implements UserDao {
	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public Serializable add(User user) {
		Serializable s = hibernateTemplate.save(user);
		return s;
	}
	public void update(User user){
		hibernateTemplate.saveOrUpdate(user);
	}
	@SuppressWarnings("unchecked")
	public List<User> select(User user, String name) {

		List<User> list = null;
		try {
			if(name.equals("u_status")==false){
			list = (List<User>) hibernateTemplate.find("from User where "
					+ name + "=?",
					user.getClass().getMethod("get" + name)
							.invoke(user));//反射机制调用方法
			}else if(name.equals("u_status")){//布尔值的获取方法名不同
				list = (List<User>) hibernateTemplate.find("from User where "
						+ name + "=?",
						user.getClass().getMethod("is" + name)
								.invoke(user));
			}
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
