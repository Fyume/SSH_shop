package zhku.jsj141.entity.user;

public class pageBean {
	private int pageIndex = 1;    // 需要显示的页码
	private int totalPages = 1;   // 总页数
	private int pageSize = 10;    // 每页记录数
	private int totalRecords = 0; // 总记录数
	private boolean isHavePrePage = false;  // 是否有上一页
	private boolean isHaveNextPage = false; // 是否有下一页
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}
	public boolean isHavePrePage() {
		return isHavePrePage;
	}
	public void setHavePrePage(boolean isHavePrePage) {
		this.isHavePrePage = isHavePrePage;
	}
	public boolean isHaveNextPage() {
		return isHaveNextPage;
	}
	public void setHaveNextPage(boolean isHaveNextPage) {
		this.isHaveNextPage = isHaveNextPage;
	}
	
}
