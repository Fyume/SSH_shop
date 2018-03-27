package zhku.jsj141.dao.Impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.HibernateTemplate;

import zhku.jsj141.dao.UserDao;
import zhku.jsj141.entity.user.Book;
import zhku.jsj141.entity.user.Favour;
import zhku.jsj141.entity.user.History;
import zhku.jsj141.entity.user.ReviewsForBook;
import zhku.jsj141.entity.user.ReviewsForReviews;
import zhku.jsj141.entity.user.User;
import zhku.jsj141.entity.user.Work;

public class UserDaoImpl implements UserDao{
	private HibernateTemplate hibernateTemplate;
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	@Override
	public boolean add(User user) {
		try{
			hibernateTemplate.save(user);
		}catch(DataAccessException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	@Override
	public boolean update(User user){
		try{
			hibernateTemplate.saveOrUpdate(user);
		}catch(DataAccessException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	@Override
	public boolean delete(User user){
		try{
			hibernateTemplate.delete(user);
		}catch(DataAccessException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	@Override
	public boolean addF(Favour favour){
		try{
			hibernateTemplate.save(favour);
		}catch(DataAccessException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	@Override
	public boolean delF(Favour favour){
		try{
			hibernateTemplate.delete(favour);
		}catch(DataAccessException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<User> select(User user, String name) {
		String name_m = name.substring(0, 1).toUpperCase()+name.substring(1,name.length());
		List<User> list = null;
		try {
			if(name.equals("u_status")==false&&name.equals("u_permission")==false){
			list = (List<User>) hibernateTemplate.find("from User where "
					+ name + "=?",
					user.getClass().getMethod("get" + name_m)
							.invoke(user));//反射机制调用方法
			}else{//布尔值的获取方法名不同
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
	public boolean addHistory(History history) {
		try{
			hibernateTemplate.saveOrUpdate(history);
		}catch(DataAccessException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	////////////收藏相关///////////
	@SuppressWarnings("unchecked")
	@Override
	public List<Favour> findF(User user) {
		List<Favour> list = null;
		list = (List<Favour>) hibernateTemplate.find("from Favour where uid = ?",user.getUid());
		return list;
	}
	@Override
	public List<Favour> findF_Book(User user) {
		List<Favour> list = null;
		list = (List<Favour>) hibernateTemplate.find("from Favour f where f.user.uid = ? and f.book.bid is not null",user.getUid());
		System.out.println(list.size());
		return list;
	}
	@Override
	public List<Favour> findF_Work(User user) {
		List<Favour> list = null;
		list = (List<Favour>) hibernateTemplate.find("from Favour f where f.user.uid = ? and f.work.wid is not null",user.getUid());
		return list;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Favour> findF(User user,Book book) {
		List<Favour> list = null;
		list = (List<Favour>) hibernateTemplate.find("from Favour where uid = ? and bid = ?",user.getUid(),book.getBid());
		return list;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List findF(User user,Book book,String fieldName) {//或许可以做成公共类 根据book的属性筛选favour
		String f_fieldName = fieldName.substring(0,1).toUpperCase()+fieldName.substring(1,fieldName.length());//首字母大写
		List<Favour> list = null;
		try {
			list = (List<Favour>) hibernateTemplate.find("from Favour f where uid = ? and f.book."+fieldName+" = ?",user.getUid(),book.getClass().getMethod("get" + f_fieldName)
					.invoke(book));//反射机制调用方法
		} catch (DataAccessException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Favour> findF(User user,Work work) {
		List<Favour> list = null;
		list = (List<Favour>) hibernateTemplate.find("from Favour where uid = ? and wid = ?",user.getUid(),work.getWid());
		return list;
	}
	///////////////浏览历史相关//////////////
	@SuppressWarnings("unchecked")
	@Override
	public List<History> findH(User user) {
		List<History> list = null;
		list = (List<History>) hibernateTemplate.find("from History where uid = ?",user.getUid());
		return list;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<History> findH(User user,Book book) {
		List<History> list = null;
		list = (List<History>) hibernateTemplate.find("from History where uid = ? and bid = ?",user.getUid(),book.getBid());
		return list;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<History> findH(User user,Work work) {
		List<History> list = null;
		list = (List<History>) hibernateTemplate.find("from History where uid = ? and wid = ?",user.getUid(),work.getWid());
		return list;
	}
	//用于管理员筛选用户资料
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
	@Override
	public boolean addRfb(ReviewsForBook rfb) {
		try{
			hibernateTemplate.save(rfb);
		}catch(DataAccessException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	@Override
	public boolean addRfr(ReviewsForReviews rfr) {
		try{
			hibernateTemplate.save(rfr);
		}catch(DataAccessException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<ReviewsForBook> findRfb(ReviewsForBook rfb) {
		List<ReviewsForBook> list = null;
		list = (List<ReviewsForBook>) hibernateTemplate.find("from ReviewsForBook rfb where rfb.rbid = ?",rfb.getRbid());
		return list;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<ReviewsForReviews> findRfr(ReviewsForReviews rfr) {
		List<ReviewsForReviews> list = null;
		list = (List<ReviewsForReviews>) hibernateTemplate.find("from ReviewsForReviews rfr where rfr.rrid = ?",rfr.getRrid());
		return list;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<ReviewsForBook> findRfb_book(Book book) {
		List<ReviewsForBook> list = null;
		list = (List<ReviewsForBook>) hibernateTemplate.find("from ReviewsForBook where bid = ?",book.getBid());
		return list;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<ReviewsForBook> findRfb_work(Work work){
		List<ReviewsForBook> list = null;
		list = (List<ReviewsForBook>) hibernateTemplate.find("from ReviewsForBook where wid = ?",work.getWid());
		return list;
	}
	/*@SuppressWarnings("unchecked")
	public void test(){
		List<ReviewsForBook> list = null;
		list = (List<ReviewsForBook>) hibernateTemplate.find("from ReviewsForBook");
	}*/
}
