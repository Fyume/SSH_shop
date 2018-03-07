package zhku.jsj141.entity.user;

public class History {
	private int hid;//主键
	private String uid;//便于找到对应用户的历史
	private int bid;//最近阅读的书本id
	private int wid;//最近阅读的作品id
	private long time;//最近阅读的时间戳(只保留到分)
	private int pageNum;//最近阅读的页码
	
	private User user;
	private Work work;
	private Book book;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Work getWork() {
		return work;
	}
	public void setWork(Work work) {
		this.work = work;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public int getHid() {
		return hid;
	}
	public void setHid(int hid) {
		this.hid = hid;
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
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	@Override
	public String toString() {
		return "History [hid=" + hid + ", uid=" + uid + ", bid=" + bid
				+ ", wid=" + wid + ", time=" + time + ", pageNum=" + pageNum
				+ "]";
	}
	
}
