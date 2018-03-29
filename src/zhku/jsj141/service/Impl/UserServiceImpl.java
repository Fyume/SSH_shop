package zhku.jsj141.service.Impl;

import java.util.List;

import zhku.jsj141.dao.BookDao;
import zhku.jsj141.dao.UserDao;
import zhku.jsj141.entity.user.Book;
import zhku.jsj141.entity.user.Favour;
import zhku.jsj141.entity.user.History;
import zhku.jsj141.entity.user.ReviewsForBook;
import zhku.jsj141.entity.user.ReviewsForReviews;
import zhku.jsj141.entity.user.User;
import zhku.jsj141.entity.user.Work;
import zhku.jsj141.service.UserService;

public class UserServiceImpl implements UserService{
	private UserDao userDao;
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	/**
	 * 添加用户
	 */
	@Override
	public boolean add(User user){
		boolean rs= userDao.add(user);
		return rs;
	}
	/**
	 * 查询用户id是否重复
	 */
	@Override
	public String checkuid(User user){
		System.out.println("--service--");
		String name = "uid";
		List<User> list = userDao.select(user,name);
		if(list!=null&&!list.isEmpty()){
			return "用户id已存在";
		}
		System.out.println("该uid可用("+user.getUid()+")");
		return null;
	}
	/**
	 * 查询邮箱是否重复
	 */
	@Override
	public String checkE(User user){
		System.out.println("--service--");
		String name = "Email";
		List<User> list = userDao.select(user,name);
		if(list!=null&&!list.isEmpty()){
			return "邮箱已被绑定";
		}
		System.out.println("该E可用("+user.getEmail()+")");
		return null;
	}
	/**
	 * 根据user某个属性查询
	 */
	@Override
	public List<User> finds(User user,String name){
		List<User> list = userDao.select(user,name);
		return list;
	}
	/**
	 * 更新用户
	 */
	@Override
	public boolean update(User user){
		boolean rs = userDao.update(user);
		return rs;
	}
	/**
	 * 删除用户
	 */
	@Override
	public boolean delete(User user){
		boolean rs = userDao.delete(user);
		return rs;
	}
	/**
	 * 添加收藏
	 */
	@Override
	public boolean addF(Favour favour){
		boolean rs = userDao.addF(favour);
		return rs;
	}
	/**
	 * 取消收藏
	 */
	@Override
	public boolean delF(Favour favour){
		boolean rs = userDao.delF(favour);
		return rs;
	}
	/**
	 * 查找用户的全部收藏记录
	 */
	@Override
	public List<Favour> findF(User user){
		List<Favour> list = userDao.findF(user);
		return list;
	}
	@Override
	public List<Favour> findF_Book(User user){
		List<Favour> list = userDao.findF_Book(user);
		return list;
	}
	@Override
	public List<Favour> findF_Work(User user){
		List<Favour> list = userDao.findF_Work(user);
		return list;
	}
	/**
	 * 根据bid查询用户的收藏记录
	 */
	@Override
	public List<Favour> findF(User user,Book book){
		List<Favour> list = userDao.findF(user,book);
		return list;
	}
	/**
	 * 根据book的某个属性查询用户的收藏记录
	 */
	@Override
	public List<Favour> findF(User user,Book book,String fieldName){
		List<Favour> list = userDao.findF(user,book,fieldName);
		return list;
	}
	/**
	 * 根据wid查询用户的收藏记录
	 */
	@Override
	public List<Favour> findF(User user,Work work){
		List<Favour> list = userDao.findF(user,work);
		return list;
	}
	/**
	 * 管理员筛选用户功能实现的方法
	 * 根据用户的id,username,status,permission
	 */
	@Override
	public List<User> finds(User user,int status,int permission){//不定项条件查询
		List<User> list = null;
		if(status==2&&permission==2){
			list = userDao.findByIN(user);
		}else if(status==2&&permission!=2){
			list = userDao.findByINP(user);
		}else if(status!=2&&permission==2){
			list = userDao.findByINS(user);
		}else{
			list = userDao.findByINSP(user);//ID,name,status,permission
		}
		return list;
	}
	/**
	 * 添加浏览记录
	 */
	@Override
	public boolean addHistory(History history){
		boolean rs = userDao.addHistory(history);
		return rs;
	}
	/**
	 * 查找用户全部浏览历史
	 */
	@Override
	public List<History> findH(User user){
		List<History> list = userDao.findH(user);
		return list;
	}
	/**
	 * 根据bid查找用户浏览历史
	 */
	@Override
	public List<History> findH(User user,Book book){
		List<History> list = userDao.findH(user,book);
		return list;
	}
	/**
	 * 根据wid查找用户浏览历史
	 */
	@Override
	public List<History> findH(User user,Work work){
		List<History> list = userDao.findH(user,work);
		return list;
	}
	@Override
	public boolean addRfb(ReviewsForBook rfb){
		boolean rs= userDao.addRfb(rfb);
		return rs;
	}
	@Override
	public boolean addRfr(ReviewsForReviews rfr){
		boolean rs= userDao.addRfr(rfr);
		return rs;
	}
	@Override
	public List<ReviewsForBook> findRfb(ReviewsForBook rfb){
		List<ReviewsForBook> list = userDao.findRfb(rfb);
		return list;
	}
	@Override
	public List<ReviewsForReviews> findRfr(ReviewsForReviews rfr){
		List<ReviewsForReviews> list = userDao.findRfr(rfr);
		return list;
	}
	@Override
	public List<ReviewsForBook> findRfb_Book(Book book){
		List<ReviewsForBook> list = userDao.findRfb_book(book);
		return list;
	}
	@Override
	public List<ReviewsForBook> findRfb_Work(Work work){
		List<ReviewsForBook> list = userDao.findRfb_work(work);
		return list;
	}
	
	@Override
	public List<Long> findRfb_Book_nearest(Book book,User user){
		List<Long> list = userDao.findRfb_book_nearest(book, user);
		return list;
	}
	@Override
	public List<Long> findRfb_Work_nearest(Work work,User user){
		List<Long> list = userDao.findRfb_work_nearest(work, user);
		return list;
	}
	@Override
	public List<Long> findRfr_Book_nearest(Book book,User user){
		List<Long> list = userDao.findRfr_book_nearest(book, user);
		return list;
	}
	@Override
	public List<Long> findRfr_Work_nearest(Work work,User user){
		List<Long> list = userDao.findRfr_work_nearest(work, user);
		return list;
	}
}
