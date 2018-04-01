package zhku.jsj141.entity.user;

public class ReviewsForReviews {
	private int rrid;//主键
	private int rbid;//对应的书本评论
	private String uid1;//评论人id
	private String uid2;//被回复人ID
	private int bid;//评论相关的书本ID
	private int wid;//评论相关的作品ID
	private String content;//回复内容
	private int flag=1;//消息通知用 默认有
	private long time;//评论时间（查询的时候根据这个排序就行了）
	
	private ReviewsForBook rfb;
	private User user1;
	private User user2;
	private Book book;
	private Work work;
	public int getRrid() {
		return rrid;
	}
	public void setRrid(int rrid) {
		this.rrid = rrid;
	}
	public int getRbid() {
		return rbid;
	}
	public void setRbid(int rbid) {
		this.rbid = rbid;
	}
	public String getUid1() {
		return uid1;
	}
	public void setUid1(String uid1) {
		this.uid1 = uid1;
	}
	public String getUid2() {
		return uid2;
	}
	public void setUid2(String uid2) {
		this.uid2 = uid2;
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
	public ReviewsForBook getRfb() {
		return rfb;
	}
	public void setRfb(ReviewsForBook rfb) {
		this.rfb = rfb;
	}
	public User getUser1() {
		return user1;
	}
	public void setUser1(User user1) {
		this.user1 = user1;
	}
	public User getUser2() {
		return user2;
	}
	public void setUser2(User user2) {
		this.user2 = user2;
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
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	@Override
	public String toString() {
		return "ReviewsForReviews [rrid=" + rrid + ", rbid=" + rbid + ", uid1="
				+ uid1 + ", uid2=" + uid2 + ", bid=" + bid + ", wid=" + wid
				+ ", content=" + content + ", time=" + time + ", flag=" + flag +"]";
	}
	
}
