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
	private WorkService workService;
	private File upload;
	private String uploadFileName;
	private String uploadContentType;
	private File image;
	private String imageFileName;
	private String imageContentType;
	private workUtils workUtils;
	
	public WorkService getWorkService() {
		return workService;
	}

	public void setWorkService(WorkService workService) {
		this.workService = workService;
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

	public workUtils getWorkUtils() {
		return workUtils;
	}

	public void setWorkUtils(workUtils workUtils) {
		this.workUtils = workUtils;
	}

	//上传用户作品（不允许重名）
	public String upload_U() throws Exception {
		System.out.println("uploadFileName:" + uploadFileName);
		System.out.println("uploadContentType:" + uploadContentType);
		System.out.println("imageFileName:" + imageFileName);
		System.out.println("imageContentType:" + imageContentType);
		HttpServletRequest request = ServletActionContext.getRequest();
		/*User user =(User) request.getSession().getAttribute("user");
		if(user!=null){
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
		String workpath = workUtils.uploadbook_U(upload, user.getUid(), uploadContentType,work.getWname());
		if(workpath!=""){
			work.setPath(workpath);//
		}
		String imgpath = workUtils.uploadbookI_U(image, user.getUid(), uploadContentType,work.getWname());
		if(imgpath!=""){
			work.setImage(imgpath);//
		}
		}*/
		/*workService.update(book);*/
		String result = workUtils.uploadbook_U(upload, "uid", uploadContentType,"workname");
		if(result!=""){
			if(result.equals("typefalse")){
				System.out.println("作品文件有误,请上传doc,docx,txt类型的文件");
			}else if(result.equals("dirfalse")){
				System.out.println("该作品已存在");
			}else{
				System.out.println("上传成功");
				String result2 = workUtils.uploadbookI_U(image, "uid", imageContentType,"workname");
				if(result2!=""){
					if(result2.equals("typefalse")){
						System.out.println("图片文件有误,请上传jpg类型的文件");
					}else{
						System.out.println("上传成功");
					}
				}
				
			}
		}
		return "goto_upload";
		
	}
}
