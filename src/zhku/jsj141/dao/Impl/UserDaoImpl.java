package zhku.jsj141.dao.Impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.HibernateTemplate;

import zhku.jsj141.dao.UserDao;
import zhku.jsj141.entity.user.Favour;
import zhku.jsj141.entity.user.User;

public class UserDaoImpl implements UserDao{
	private HibernateTemplate hibernateTemplate;
	@Override
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public Serializable add(User user) {
		Serializable s = hibernateTemplate.save(user);
		return s;
	}
	@Override
	public void update(User user){
		hibernateTemplate.saveOrUpdate(user);
	}
	@Override
	public void delete(User user){
		hibernateTemplate.delete(user);
	}
	@Override
	public void addF(Favour favour){
		hibernateTemplate.save(favour);
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<User> select(User user, String name) {
		String name_m = name.substring(0, 1).toUpperCase()+name.substring(1,name.length());
		List<User> list = null;
		try {
			if(name.equals("u_status")==false){
			list = (List<User>) hibernateTemplate.find("from User where "
					+ name + "=?",
					user.getClass().getMethod("get" + name_m)
							.invoke(user));//反射机制调用方法
			}else if(name.equals("u_status")){//布尔值的获取方法名不同
				list = (List<User>) hibernateTemplate.find("from User where "
						+ name + "=?",
						user.getClass().getMethod("is" + name_m)
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
	@Override
	public List<User> findByINSP(User user){
		@SuppressWarnings("unchecked")
		List<User> list = (List<User>) hibernateTemplate.find("from User where uid like ? and username like ? and u_status = ? and u_permission = ?", "%"+user.getUid()+"%","%"+user.getUsername()+"%",user.isU_status(),user.isU_permission());
		return list;
	}
	@Override
	public List<User> findByIN(User user){
		@SuppressWarnings("unchecked")
		List<User> list = (List<User>) hibernateTemplate.find("from User where uid like ? and username like ?", "%"+user.getUid()+"%","%"+user.getUsername()+"%");
		return list;
	}
	@Override
	public List<User> findByINS(User user){
		@SuppressWarnings("unchecked")
		List<User> list = (List<User>) hibernateTemplate.find("from User where uid like ? and username like ? and u_status = ?", "%"+user.getUid()+"%","%"+user.getUsername()+"%",user.isU_status());
		return list;
	}
	@Override
	public List<User> findByINP(User user){
		@SuppressWarnings("unchecked")
		List<User> list = (List<User>) hibernateTemplate.find("from User where uid like ? and username like ? and u_permission = ?", "%"+user.getUid()+"%","%"+user.getUsername()+"%",user.isU_permission());
		return list;
	}
}
