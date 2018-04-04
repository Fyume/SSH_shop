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
	/**
	 * 找书评
	 * @param rfb
	 * @return
	 */
	public List<ReviewsForBook> findRfb(ReviewsForBook rfb);
	/**
	 * 找回复评论
	 * @param rfb
	 * @return
	 */
	public List<ReviewsForReviews> findRfr(ReviewsForReviews rfr);
	/**
	 * 找某本书的书评
	 * @param book
	 * @return
	 */
	public List<ReviewsForBook> findRfb_Book(Book book);
	/**
	 * 找某作品的书评
	 * @param book
	 * @return
	 */
	public List<ReviewsForBook> findRfb_Work(Work work);
	/**
	 * 找该用户在该书本的最近回复
	 * @param book
	 * @param user
	 * @return List<Long>
	 */
	public List<Long> findRfb_Book_nearest(Book book, User user);
	/**
	 * 找该用户在该作品的最近回复
	 * @param work
	 * @param user
	 * @return List<Long>
	 */
	public List<Long> findRfb_Work_nearest(Work work, User user);
	/**
	 * 找该用户在该书本评论的最近回复
	 * @param book
	 * @param user
	 * @return List<Long>
	 */
	public List<Long> findRfr_Book_nearest(Book book, User user);
	/**
	 * 找该用户在该作品评论的最近回复
	 * @param work
	 * @param user
	 * @return List<Long>
	 */
	public List<Long> findRfr_Work_nearest(Work work, User user);
	/**
	 * 更新收藏表
	 * @param favour
	 * @return
	 */
	public boolean updateF(Favour favour);
	/**
	 * 寻找自己评论过的记录
	 * @param user
	 * @return
	 */
	public List<ReviewsForReviews> findRfr_User1(User user);
	/**
	 * 寻找被评论过的记录
	 * @param user
	 * @return
	 */
	public List<ReviewsForReviews> findRfr_User2(User user);
	/**
	 * 返回用户所有书评
	 * @param user
	 * @return
	 */
	public List<ReviewsForBook> findRfb(User user);

}