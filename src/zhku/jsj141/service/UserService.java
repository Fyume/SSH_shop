package zhku.jsj141.service;

import java.util.List;

import zhku.jsj141.entity.user.Book;
import zhku.jsj141.entity.user.Favour;
import zhku.jsj141.entity.user.History;
import zhku.jsj141.entity.user.ReviewsForBook;
import zhku.jsj141.entity.user.ReviewsForReviews;
import zhku.jsj141.entity.user.User;
import zhku.jsj141.entity.user.Work;

public interface UserService {
	/**
	 * 添加用户
	 */
	public boolean add(User user);
	/**
	 * 更新用户
	 */
	public boolean update(User user);
	/**
	 * 删除用户
	 */
	public boolean delete(User user);
	/**
	 * 查询用户id是否重复
	 */
	public String checkuid(User user);
	/**
	 * 查询邮箱是否重复
	 */
	public String checkE(User user);
	/**
	 * 管理员筛选用户功能实现的方法
	 * 根据用户的id,username,status,permission
	 */
	public List<User> finds(User user, int status, int permission);
	/**
	 * 根据user某个属性查询
	 */
	public List<User> finds(User user, String name);
	/**
	 * 添加收藏
	 */
	public boolean addF(Favour favour);
	/**
	 * 添加浏览记录
	 */
	public boolean addHistory(History history);
	/**
	 * 查找用户全部浏览历史
	 */
	public List<History> findH(User user);
	/**
	 * 根据bid查找用户浏览历史
	 */
	public List<History> findH(User user, Book book);
	/**
	 * 根据wid查找用户浏览历史
	 */
	public List<History> findH(User user, Work work);
	/**
	 * 查找用户的全部收藏记录
	 */
	public List<Favour> findF(User user);
	/**
	 * 查找用户收藏的所有作品记录
	 * @param user
	 * @return
	 */
	public List<Favour> findF_Work(User user);
	/**
	 * 查找用户收藏的所有书本记录
	 * @param user
	 * @return
	 */
	public List<Favour> findF_Book(User user);
	/**
	 * 根据bid查询用户的收藏记录
	 */
	public List<Favour> findF(User user, Book book);
	/**
	 * 根据book的某个属性查询用户的收藏记录
	 */
	public List<Favour> findF(User user, Book book, String fieldName);;
	/**
	 * 根据wid查询用户的收藏记录
	 */
	public List<Favour> findF(User user, Work work);
	/**
	 * 取消收藏
	 */
	public boolean delF(Favour favour);
	/**
	 * 添加书本or作品评论
	 * @param rfb
	 * @return
	 */
	public boolean addRfb(ReviewsForBook rfb);
	/**
	 * 添加回复评论
	 * @param rfb
	 * @return
	 */
	public boolean addRfr(ReviewsForReviews rfr);

}