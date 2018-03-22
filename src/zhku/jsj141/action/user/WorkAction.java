package zhku.jsj141.action.user;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import zhku.jsj141.action.BaseAction;
import zhku.jsj141.entity.Upload;
import zhku.jsj141.entity.user.Book;
import zhku.jsj141.entity.user.User;
import zhku.jsj141.entity.user.Work;
import zhku.jsj141.service.ManagerService;
import zhku.jsj141.service.WorkService;
import zhku.jsj141.utils.user.workUtils;

import com.opensymphony.xwork2.ActionSupport;

public class WorkAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private WorkService workService;
	private ManagerService managerService;
	private File upload;
	private String uploadFileName;
	private String uploadContentType;
	private File image;
	private String imageFileName;
	private String imageContentType;
	private workUtils workUtils;
	
	Upload t_upload = new Upload();
	List<Work> worklist = null;
	
	User user = new User();
	Work work = new Work();
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Work getWork() {
		return work;
	}
	public void setWork(Work work) {
		this.work = work;
	}
	
	public WorkService getWorkService() {
		return workService;
	}

	public void setWorkService(WorkService workService) {
		this.workService = workService;
	}
	public ManagerService getManagerService() {
		return managerService;
	}
	public void setManagerService(ManagerService managerService) {
		this.managerService = managerService;
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

	// 上传用户作品（不允许重名）
	public String upload() throws Exception {
		System.out.println("uploadFileName:" + uploadFileName);
		System.out.println("uploadContentType:" + uploadContentType);
		System.out.println("imageFileName:" + imageFileName);
		System.out.println("imageContentType:" + imageContentType);
		if (user != null) {
			long publish = System.currentTimeMillis()/((1000*60)*(1000*60));
			String result = workUtils.uploadbook_U(upload, user.getUid(),
					uploadContentType, work.getWname());
			work.setUser(user);
			work.setUploadtime(publish);
			if (result != "") {
				if (result.equals("typefalse")) {
					request.setAttribute("uploadResult", "作品文件有误,请上传doc,docx,txt类型的文件");
				} else if (result.equals("dirfalse")) {
					request.setAttribute("uploadResult", "该作品已存在");
				} else {
					work.setPath(result);
					String result2 = workUtils.uploadbookI_U(image, user.getUid(),
							imageContentType);
					if (result2 != "") {
						if (result2.equals("typefalse")) {
							request.setAttribute("uploadResult", "图片文件有误,请上传jpg类型的文件");
						} else {
							work.setImage(result2);
							request.setAttribute("uploadResult", "success");
						}
					}
					boolean rs = workService.add(work);//不管封面是否上传成功都保存上传文件的信息到数据库
					if(rs){//上传记录保存
						t_upload.setWork(work);
						long time = System.currentTimeMillis()/1000;
						t_upload.setTime(time);
						managerService.addUpload(t_upload);
						request.setAttribute("uploadResult", "success");
					}
				}
			}
		}else{
			return "goto_login";
		}
		return "goto_upload";

	}
	public String getData() throws Exception {
		worklist = workService.findAll();
		request.getSession().setAttribute("classfy", "用户作品");
		request.getSession().setAttribute("worklist", worklist);
		return "goto_index";
	}
	public String readWork() throws Exception{
		int wid = Integer.parseInt(request.getParameter("wid"));
		work.setWid(wid);
		worklist = workService.find(work, "wid");
		if(worklist!=null){
			work = worklist.get(0);
			List<String> str = workUtils.readbook_U(work.getAuthor(), work.getPath());
			request.getSession().setAttribute("content", str);
			request.getSession().setAttribute("doc_count", str.size());
			request.getSession().setAttribute("page", 1);
			request.getSession().setAttribute("work", work);
			request.getSession().setAttribute("book", null);//清空（暂时保留这个，1.减少存储的大小2.方便前端判断）
			return "goto_read";
		}
		return "goto_index";
	}
	public String selectW() throws Exception{//查询作品
		String message = request.getParameter("message");//具体参数
		message = new String(message.getBytes("ISO-8859-1"),"utf-8"); 
		String flag = request.getParameter("flag");//book的某个属性
		System.out.println("message:"+message);
		System.out.println("flag:"+flag);
		if(message!=null&&flag!=null){
			if(flag.equals("wname")){
				work.setWname(message);
			}
			worklist = workService.find_indistinct(work, flag);
			request.getSession().setAttribute("worklist", worklist);
			request.getSession().setAttribute("classfy", "用户作品");
		}
		return "goto_index";
	}
}
