package zhku.jsj141.entity;

public class User {
	private String uid;
	private String username;
	private String name;
	private String password;
	private String address;
	private String IDCN;//身份证号
	private String telnum;//电话号码
	private String email;//邮箱
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getIDCN() {
		return IDCN;
	}
	public void setIDCN(String iDCN) {
		IDCN = iDCN;
	}
	public String getTelnum() {
		return telnum;
	}
	public void setTelnum(String telnum) {
		this.telnum = telnum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public User(String uid,String username,String password,String address,String IDCN,String telnum,String email){
		this.uid = uid;
		this.username = username;
		this.password = password;
		this.address = address;
		this.IDCN = IDCN;
		this.telnum = telnum;
		this.email = email;
	}
	public User(){
		
	}
}
