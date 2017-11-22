package zhku.jsj141.action.user;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import zhku.jsj141.entity.user.Book;
import zhku.jsj141.entity.user.User;
import zhku.jsj141.service.BookService;
import zhku.jsj141.utils.user.bookUtils;

import com.opensymphony.xwork2.ActionSupport;

public class BookAction extends ActionSupport {
	private BookService bookService;
	private File upload;
	private String uploadFileName;
	private String uploadContentType;
	private bookUtils bookUtils;
	
	public bookUtils getBookUtils() {
		return bookUtils;
	}

	public void setBookUtils(bookUtils bookUtils) {
		this.bookUtils = bookUtils;
	}

	public BookService getBookService() {
		return bookService;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	public String upload() throws Exception {
		System.out.println("uploadFileName:" + uploadFileName);
		System.out.println("uploadContentType:" + uploadContentType);
		HttpServletRequest request = ServletActionContext.getRequest();
		User user =(User) request.getSession().getAttribute("user");
		Book book = new Book();
		String result = bookUtils.uploadbook(upload, user.getUid(), uploadContentType);
		if(result!=""){
			book.setPath(result);//
		}
		return "goto_test";
	}
}
