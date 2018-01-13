package zhku.jsj141.action.user;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import zhku.jsj141.entity.user.Book;
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

	// 上传用户作品（不允许重名）
	public String upload_U() throws Exception {
		System.out.println("uploadFileName:" + uploadFileName);
		System.out.println("uploadContentType:" + uploadContentType);
		System.out.println("imageFileName:" + imageFileName);
		System.out.println("imageContentType:" + imageContentType);
		HttpServletRequest request = ServletActionContext.getRequest();
		String title = (String) request.getParameter("upload_title");
		String description = (String) request.getParameter("description");
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			long publish = System.currentTimeMillis();
			String result = workUtils.uploadbook_U(upload, user.getUid(),
					uploadContentType, title);
			Work work = new Work();
			work.setAuthor(user.getUid());
			work.setDescription(description);
			work.setWname(title);
			work.setUploadtime(publish);
			if (result != "") {
				if (result.equals("typefalse")) {
					request.setAttribute("uploadResult", "作品文件有误,请上传doc,docx,txt类型的文件");
				} else if (result.equals("dirfalse")) {
					request.setAttribute("uploadResult", "该作品已存在");
				} else {
					work.setPath(result);
					String result2 = workUtils.uploadbookI_U(image, "uid",
							imageContentType);
					if (result2 != "") {
						if (result2.equals("typefalse")) {
							request.setAttribute("uploadResult", "图片文件有误,请上传jpg类型的文件");
						} else {
							work.setImage(result2);
							request.setAttribute("uploadResult", "success");
						}
					}
					workService.add(work);//不管封面是否上传成功都保存上传文件的信息到数据库
				}
			}
		}else{
			return "goto_login";
		}
		return "goto_upload";

	}
	public String getData() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		List<Work> worklist = null;
		Work work = new Work();
		worklist = workService.findAll();
		request.getSession().setAttribute("classfy", "用户作品");
		request.getSession().setAttribute("worklist", worklist);
		return "goto_index";
	}
	public String readWork() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		int wid = Integer.parseInt(request.getParameter("wid"));
		Work work = new Work();
		work.setWid(wid);
		List<Work> list = workService.find(work, "wid");
		work = list.get(0);
		if(work.getWname()!=null&&!work.getWname().isEmpty()){
			List<String> str = workUtils.readbook_U(work.getAuthor(), work.getPath());
			request.getSession().setAttribute("content", str);
			request.getSession().setAttribute("doc_count", str.size());
			request.getSession().setAttribute("page", 1);
			request.getSession().setAttribute("book", work);
			return "goto_read";
		}
		return "goto_index";
	}
	public String selectW() throws Exception{//查询作品
		HttpServletRequest request = ServletActionContext.getRequest();
		String message = request.getParameter("message");//具体参数
		message = new String(message.getBytes("ISO-8859-1"),"utf-8"); 
		String flag = request.getParameter("flag");//book的某个属性
		System.out.println("message:"+message);
		System.out.println("flag:"+flag);
		Work work = new Work();
		if(message!=null&&flag!=null){
			/*String Flag = flag.substring(0, 1).toUpperCase()+flag.substring(1,flag.length());
			book.getClass().getMethod("set" + Flag).invoke(book,message);
			System.out.println(book.getClass().getMethod("get" + Flag)
							.invoke(book));*/
			if(flag.equals("wname")){
				work.setWname(message);
			}
			List<Work> list = workService.find_indistinct(work, flag);
			request.getSession().setAttribute("worklist", list);
			request.getSession().setAttribute("classfy", "用户作品");
		}
		return "goto_index";
	}
}
