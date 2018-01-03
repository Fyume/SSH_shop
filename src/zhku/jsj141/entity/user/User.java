package zhku.jsj141.entity.user;

import java.util.HashSet;
import java.util.Set;


public class User {
	private String uid;//用户ID
	private String username;//用户名
	private String name;//真实姓名
	private String password;//密码
	private String address;//地址
	private String IDCN;//身份证号
	private String telnum;//电话号码
	private String email;//邮箱
	private boolean u_status=false;//激活状态,默认未激活
	private String code;//激活用激活码
	private long activateTime;//第一次激活的时间
	private boolean u_permission = false;//用户权限(默认为普通用户，只有修改为true的时候才是管理员)
	
	private Set<Work> work = new HashSet<Work>();
	
	public Set<Work> getWork() {
		return work;
	}
	public void setWork(Set<Work> work) {
		this.work = work;
	}
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
	
	public boolean isU_status() {
		return u_status;
	}
	public void setU_status(boolean u_status) {
		this.u_status = u_status;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public long getActivateTime() {
		return activateTime;
	}
	public void setActivateTime(long activateTime) {
		this.activateTime = activateTime;
	}
	
	public boolean isU_permission() {
		return u_permission;
	}
	public void setU_permission(boolean u_permission) {
		this.u_permission = u_permission;
	}
	public User(String uid, String username, String name, String password,
			String address, String iDCN, String telnum, String email, String code) {
		super();
		this.uid = uid;
		this.username = username;
		this.name = name;
		this.password = password;
		this.address = address;
		IDCN = iDCN;
		this.telnum = telnum;
		this.email = email;
		this.code = code;
	}
	public User(){
		
	}
	@Override
	public String toString() {
		return "User [uid=" + uid + ", username=" + username + ", name=" + name
				+ ", password=" + password + ", address=" + address + ", IDCN="
				+ IDCN + ", telnum=" + telnum + ", email=" + email
				+ ", u_status=" + u_status + ", code=" + code
				+ ", activateTime=" + activateTime + "]";
	}
	
	
}
