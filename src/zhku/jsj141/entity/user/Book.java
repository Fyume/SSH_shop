package zhku.jsj141.entity.user;

import java.sql.Date;

public class Book {
	private String bid;// 书ID
	private String bname;// 书名
	private String ISBN;
	private long publish;// 出版日期
	private String description;// 描述
	private String author;// 作者
	private String type;// 分类
	private String path;// 磁盘路径
	private String image;// 书本封面路径

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
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

	public Book(String bname, String iSBN, long publish, String description,
			String author, String type) {
		super();
		this.bname = bname;
		ISBN = iSBN;
		this.publish = publish;
		this.description = description;
		this.author = author;
		this.type = type;
	}

	public Book() {
	}
}
