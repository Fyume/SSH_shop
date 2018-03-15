package zhku.jsj141.entity.user;

import java.util.HashSet;
import java.util.Set;

import zhku.jsj141.entity.manager.Operate_m;


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
	private int ps_false=0;//密码错误次数
	private long ps_time=0;//最近一次输错密码的时间戳
	
	private Set<Work> work = new HashSet<Work>();
	private Set<Favour> favour = new HashSet<Favour>();
	private Set<History> history = new HashSet<History>();
	private Set<Operate_m> operate_m = new HashSet<Operate_m>();
	public Set<Operate_m> getOperate_m() {
		return operate_m;
	}
	public void setOperate_m(Set<Operate_m> operate_m) {
		this.operate_m = operate_m;
	}
	public Set<History> getHistory() {
		return history;
	}
	public void setHistory(Set<History> history) {
		this.history = history;
	}
	public Set<Favour> getFavour() {
		return favour;
	}
	public void setFavour(Set<Favour> favour) {
		this.favour = favour;
	}
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
	public int getPs_false() {
		return ps_false;
	}
	public void setPs_false(int ps_false) {
		this.ps_false = ps_false;
	}
	public long getPs_time() {
		return ps_time;
	}
	public void setPs_time(long ps_time) {
		this.ps_time = ps_time;
	}
	public User(String uid, String username, String name, String password,
			String address, String iDCN, String telnum, String email, String code, int ps_false, long ps_time) {
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
		this.ps_false = ps_false;
		this.ps_time = ps_time;
	}
	public User(){
		
	}
	@Override
	public String toString() {
		return "User [uid=" + uid + ", username=" + username + ", name=" + name
				+ ", password=" + password + ", address=" + address + ", IDCN="
				+ IDCN + ", telnum=" + telnum + ", email=" + email
				+ ", u_status=" + u_status + ", code=" + code
				+ ", activateTime=" + activateTime + ", u_permission="
				+ u_permission+ ", ps_false=" + ps_false+ ", ps_time="
				+ ps_time + "]";
	}
	
}
