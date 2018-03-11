package zhku.jsj141.entity.user;

import java.util.HashSet;
import java.util.Set;

public class Work {
	private int wid;// 作品ID           自增
	private String wname;// 作品名（标题）
	private long uploadtime;// 上传日期
	private String description;// 描述
	private String author;// 作者                              外键
	private String path;// 磁盘路径
	private String image;// 书本封面路径
	
	private Set<Favour> favour = new HashSet<Favour>();
	private Set<History> history = new HashSet<History>();
	private User user;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Set<Favour> getFavour() {
		return favour;
	}
	public void setFavour(Set<Favour> favour) {
		this.favour = favour;
	}
	public Set<History> getHistory() {
		return history;
	}
	public void setHistory(Set<History> history) {
		this.history = history;
	}
	
	
	public int getWid() {
		return wid;
	}
	public void setWid(int wid) {
		this.wid = wid;
	}
	public String getWname() {
		return wname;
	}
	public void setWname(String wname) {
		this.wname = wname;
	}
	public long getUploadtime() {
		return uploadtime;
	}
	public void setUploadtime(long uploadtime) {
		this.uploadtime = uploadtime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Work( String wname, long uploadtime, String description,
			String author,String path,String image) {
		super();
		this.wname = wname;
		this.uploadtime = uploadtime;
		this.description = description;
		this.author = author;
		this.path = path;
		this.image = image;
	}
	public Work() {
	}
	
}
