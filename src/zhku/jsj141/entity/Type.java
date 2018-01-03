package zhku.jsj141.entity;


public class Type {//存储分类级别的表
	private int tid;//主键
	private String type;//大分类
	private String type_flag;//小标签
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getType_flag() {
		return type_flag;
	}
	public void setType_flag(String type_flag) {
		this.type_flag = type_flag;
	}
	
	public Type(){
		
	}
}
