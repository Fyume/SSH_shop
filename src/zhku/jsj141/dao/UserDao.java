package zhku.jsj141.dao;

import java.util.List;

import zhku.jsj141.entity.user.Book;
import zhku.jsj141.entity.user.Favour;
import zhku.jsj141.entity.user.History;
import zhku.jsj141.entity.user.ReviewsForBook;
import zhku.jsj141.entity.user.ReviewsForReviews;
import zhku.jsj141.entity.user.User;
import zhku.jsj141.entity.user.Work;

public interface UserDao {
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	public boolean add(User user);
	/**
	 * 更新用户
	 * @param user
	 * @return
	 */
	public boolean update(User user);
	/**
	 * 根据某个用户属性筛选用户
	 * @param user
	 * @param name
	 * @return
	 */
	public List<User> select(User user, String name);
	/**
	 * 删除用户
	 * @param user
	 * @return
	 */
	public boolean delete(User user);
	/**
	 * 添加收藏
	 * @param favour
	 * @return
	 */
	public boolean addF(Favour favour);
	/**
	 * 根据uid,username筛选用户
	 * @param user
	 * @return
	 */
	public List<User> findByIN(User user);
	/**
	 * 根据uid,username,status,permission筛选用户
	 * @param user
	 * @return
	 */
	public List<User> findByINSP(User user);
	/**
	 * 根据uid,username,status筛选用户
	 * @param user
	 * @return
	 */
	public List<User> findByINS(User user);
	/**
	 * 根据uid,username,permission筛选用户
	 * @param user
	 * @return
	 */
	public List<User> findByINP(User user);
	/**
	 * 添加历史
	 * @param history
	 * @return
	 */
	public boolean addHistory(History history);
	/**
	 * 查找用户全部浏览历史
	 * @param user
	 * @return
	 */
	public List<History> findH(User user);
	/**
	 * 根据bid查找用户浏览历史
	 * @param user
	 * @param book
	 * @return
	 */
	public List<History> findH(User user, Book book);
	/**
	 * 根据wid查找用户浏览历史
	 * @param user
	 * @param work
	 * @return
	 */
	public List<History> findH(User user, Work work);
	/**
	 * 查找用户收藏
	 * @param user
	 * @return
	 */
	public List<Favour> findF(User user);
	/**
	 * 查找用户收藏的所有书本相关记录
	 * @param user
	 * @return
	 */
	public List<Favour> findF_Book(User user);
	/**
	 * 查找用户收藏的所有书本相关记录
	 * @param user
	 * @return
	 */
	public List<Favour> findF_Work(User user);
	/**
	 * 根据bid查找用户收藏
	 * @param user
	 * @param book
	 * @return
	 */
	public List<Favour> findF(User user, Book book);
	/**
	 * 根据book的字段值查找用户的收藏
	 * @param user
	 * @param book
	 * @param fieldName
	 * @return
	 */
	public List<Favour> findF(User user, Book book, String fieldName);
	/**
	 * 根据wid查找用户收藏
	 * @param user
	 * @param work
	 * @return
	 */
	public List<Favour> findF(User user, Work work);
	/**
	 * 取消收藏
	 * @param favour
	 * @return
	 */
	public boolean delF(Favour favour);
	/**
	 * 添加书本评论
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
	 * 找评论回复
	 * @param rfr
	 * @return
	 */
	public List<ReviewsForReviews> findRfr(ReviewsForReviews rfr);
	/**
	 * 找某本书的书评
	 * @param rfr
	 * @return
	 */
	public List<ReviewsForBook> findRfb_book(Book book);
	/**
	 * 找某作品的书评
	 * @param rfr
	 * @return
	 */
	public List<ReviewsForBook> findRfb_work(Work work);
	/**
	 * 找该用户在该书本的最近回复
	 * @param book
	 * @param user
	 * @return List<Long>
	 */
	public List<Long> findRfb_book_nearest(Book book, User user);
	/**
	 * 找该用户在该作品的最近回复
	 * @param work
	 * @param user
	 * @return List<Long>
	 */
	public List<Long> findRfb_work_nearest(Work work, User user);
	/**
	 * 找该用户在该书本评论的最近回复
	 * @param book
	 * @param user
	 * @return List<Long>
	 */
	public List<Long> findRfr_book_nearest(Book book, User user);
	/**
	 * 找该用户在该作品评论的最近回复
	 * @param work
	 * @param user
	 * @return List<Long>
	 */
	public List<Long> findRfr_work_nearest(Work work, User user);
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
}