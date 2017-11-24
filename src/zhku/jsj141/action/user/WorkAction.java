package zhku.jsj141.action.user;

import java.io.File;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import zhku.jsj141.entity.user.User;
import zhku.jsj141.entity.user.Work;
import zhku.jsj141.service.WorkService;
import zhku.jsj141.utils.user.workUtils;

import com.opensymphony.xwork2.ActionSupport;

public class WorkAction extends ActionSupport {
	private WorkService worksService;
	private File upload;
	private String uploadFileName;
	private String uploadContentType;
	private File image;
	private String imageFileName;
	private String imageContentType;
	private workUtils worksUtils;
	
	public WorkService getWorksService() {
		return worksService;
	}

	public void setWorksService(WorkService worksService) {
		this.worksService = worksService;
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

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public String getImageContentType() {
		return imageContentType;
	}

	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}

	public workUtils getWorksUtils() {
		return worksUtils;
	}

	public void setWorksUtils(workUtils worksUtils) {
		this.worksUtils = worksUtils;
	}

	//上传用户作品（不允许重名）
	public String upload_U() throws Exception {
		System.out.println("uploadFileName:" + uploadFileName);
		System.out.println("uploadContentType:" + uploadContentType);
		HttpServletRequest request = ServletActionContext.getRequest();
		User user =(User) request.getSession().getAttribute("user");
		String bname = request.getParameter("bname");
		String iSBN = request.getParameter("ISBN");
		int publish_yyyy = Integer.parseInt(request.getParameter("publish_yyyy"));
		int publish_MM = Integer.parseInt(request.getParameter("publish_MM"));
		int publish_dd = Integer.parseInt(request.getParameter("publish_dd"));
		long publish = new Date(publish_yyyy-1900, publish_MM-1, publish_dd).getTime();
	    String description = request.getParameter("description");
		String author = request.getParameter("author");
		String type = request.getParameter("type");
		Work work= new Work(bname, publish, description, author);
		String bookpath = worksUtils.uploadbook_U(upload, user.getUid(), uploadContentType,work.getWname());
		if(bookpath!=""){
			work.setPath(bookpath);//
		}
		String imgpath = worksUtils.uploadbookI_U(upload, user.getUid(), uploadContentType,work.getWname());
		if(imgpath!=""){
			work.setImage(imgpath);//
		}
		/*bookService.update(book);*/
		return "goto_test";
	}
}
