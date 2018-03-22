package zhku.jsj141.entity;

import zhku.jsj141.entity.user.Book;
import zhku.jsj141.entity.user.User;
import zhku.jsj141.entity.user.Work;

public class Upload {
	/*本来打算在aop增强上面做的也就是Operate_m上面 想了想 好像要改很多东西 
	 *比如 不需要tojson方法了 直接建外键并且前端的数据处理也不同了 所以就没改了
	 *
	 */
	private int ulid;//主键
	private String uid;
	private int bid;
	private int wid;
	private long time;//上传时间戳
	
	private Work work;
	private User user;
	private Book book;
	public int getUlid() {
		return ulid;
	}
	public void setUlid(int ulid) {
		this.ulid = ulid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public int getWid() {
		return wid;
	}
	public void setWid(int wid) {
		this.wid = wid;
	}
	public Work getWork() {
		return work;
	}
	public void setWork(Work work) {
		this.work = work;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "Upload [ulid=" + ulid + ", uid=" + uid + ", bid=" + bid
				+ ", wid=" + wid + ", time=" + time + ", work=" + work
				+ ", user=" + user + ", book=" + book + "]";
	}
	
}
