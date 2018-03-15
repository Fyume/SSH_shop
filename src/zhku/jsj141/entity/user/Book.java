package zhku.jsj141.entity.user;

import java.util.HashSet;
import java.util.Set;


public class Book {
	private int bid;// 书ID
	private String bname;// 书名
	private String ISBN;
	private long publish;// 出版日期
	private String description;// 描述
	private String author;// 作者
	private String type;// 大分类
	private String path;// 磁盘路径
	private String image;// 书本封面路径
	private String type_flag;//书本类型（建立在大分类下的）,用“;”分开
	
	private Set<Favour> favour = new HashSet<Favour>();
	private Set<History> history = new HashSet<History>();
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
	
	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public long getPublish() {
		return publish;
	}

	public void setPublish(long publish) {
		this.publish = publish;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
	
	public String getType_flag() {
		return type_flag;
	}

	public void setType_flag(String type_flag) {
		this.type_flag = type_flag;
	}

	public Book(String bname, String iSBN, long publish, String description,
			String author, String type,String type_flag) {
		super();
		this.bname = bname;
		ISBN = iSBN;
		this.publish = publish;
		this.description = description;
		this.author = author;
		this.type = type;
		this.type_flag = type_flag;
	}

	public Book() {
	}

	@Override
	public String toString() {
		return "Book [bid=" + bid + ", bname=" + bname + ", ISBN=" + ISBN
				+ ", publish=" + publish + ", description=" + description
				+ ", author=" + author + ", type=" + type + ", path=" + path
				+ ", image=" + image + ", type_flag=" + type_flag + "]";
	}
}
