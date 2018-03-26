package zhku.jsj141.entity.user;

import java.util.HashSet;
import java.util.Set;

public class ReviewsForBook {
	private int rbid;//主键
	private String uid;//评论人ID
	private int bid;//评论相关的书本ID
	private int wid;//评论相关的作品ID
	private String content;//回复内容
	private long time;//评论时间（查询的时候根据这个排序就行了）
	
	private User user;
	private Book book;
	private Work work;
	private Set<ReviewsForReviews> rfr = new HashSet<ReviewsForReviews>();
	public int getRbid() {
		return rbid;
	}
	public void setRbid(int rbid) {
		this.rbid = rbid;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
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
	public Work getWork() {
		return work;
	}
	public void setWork(Work work) {
		this.work = work;
	}
	public Set<ReviewsForReviews> getRfr() {
		return rfr;
	}
	public void setRfr(Set<ReviewsForReviews> rfr) {
		this.rfr = rfr;
	}
	@Override
	public String toString() {
		return "ReviewsForBook [rbid=" + rbid + ", uid=" + uid + ", bid=" + bid
				+ ", wid=" + wid + ", content=" + content + ", time=" + time
				+ "]";
	}
	
}
