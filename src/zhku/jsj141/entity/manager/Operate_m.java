package zhku.jsj141.entity.manager;

import zhku.jsj141.entity.user.User;

public class Operate_m {
	private int oid;//操作id
	private String managerID;//操作人员
	private String entity;//被操作的数据库表（/实体）
	private String value_before;//被操作的数据库表（/实体）的所有值(修改前)   转成json字符串存储
	private String value_after;;//被操作的数据库表（/实体）的所有值(修改后)   转成json字符串存储
	private int type_flag;//操作类型 1.增加 2.删除 3.修改
	private long uploadTime;//操作时间
	
	private User user;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public String getManagerID() {
		return managerID;
	}
	public void setManagerID(String managerID) {
		this.managerID = managerID;
	}
	public String getEntity() {
		return entity;
	}
	public void setEntity(String entity) {
		this.entity = entity;
	}
	public String getValue_before() {
		return value_before;
	}
	public void setValue_before(String value_before) {
		this.value_before = value_before;
	}
	public String getValue_after() {
		return value_after;
	}
	public void setValue_after(String value_after) {
		this.value_after = value_after;
	}
	public int getType_flag() {
		return type_flag;
	}
	public void setType_flag(int type_flag) {
		this.type_flag = type_flag;
	}
	public long getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(long uploadTime) {
		this.uploadTime = uploadTime;
	}
	@Override
	public String toString() {
		return "Operate_m [oid=" + oid + ", managerID=" + managerID
				+ ", entity=" + entity + ", value_before=" + value_before
				+ ", value_after=" + value_after + ", type_flag=" + type_flag
				+ ", uploadTime=" + uploadTime + ", user=" + user + "]";
	}
	
}
