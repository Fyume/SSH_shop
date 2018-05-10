package zhku.jsj141.action.user;

import java.io.File;
import java.util.List;

import zhku.jsj141.action.BaseAction;
import zhku.jsj141.entity.Upload;
import zhku.jsj141.entity.user.Favour;
import zhku.jsj141.entity.user.User;
import zhku.jsj141.entity.user.Work;
import zhku.jsj141.service.ManagerService;
import zhku.jsj141.service.UserService;
import zhku.jsj141.service.WorkService;
import zhku.jsj141.utils.user.workUtils;


public class WorkAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private WorkService workService;
	private UserService userService;
	private ManagerService managerService;
	private File upload;
	private String uploadFileName;
	private String uploadContentType;
	private File image;
	private String imageFileName;
	private String imageContentType;
	
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
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
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
	//所有作品
	public String getData() throws Exception {
		worklist = workService.findAll();
		request.getSession().setAttribute("classfy", "用户作品");
		request.getSession().setAttribute("worklist", worklist);
		request.getSession().setAttribute("booklist", null);
		request.getSession().setAttribute("listSize", worklist.size());
		/*test();*/
		return "goto_index";
	}
	// 上传用户作品（不允许重名）
	public String upload() throws Exception {
		System.out.println("uploadFileName:" + uploadFileName);
		System.out.println("uploadContentType:" + uploadContentType);
		System.out.println("imageFileName:" + imageFileName);
		System.out.println("imageContentType:" + imageContentType);
		user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			long publish = System.currentTimeMillis()/1000;
			String result = workUtils.uploadbook_U(upload, user.getUid(),
					uploadContentType, work.getWname());
			work.setUser(user);
			work.setUploadtime(publish);
			t_upload.setUser(user);
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
					getData();//刷新session（其实好像没必要）
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
	public String getMyWork() throws Exception {//有必要吗？感觉直接前端el点出来就好了。。。
		user = (User) request.getSession().getAttribute("user");
		if(user==null){
			return "goto_login";
		}
		work.setAuthor(user.getUid());
		worklist = workService.find(work, "author");
		request.getSession().setAttribute("myWork", worklist);
		request.getSession().setAttribute("myWork_flag", worklist.size());
		return "goto_user";
	}
	/*public String test() throws Exception{
		System.out.println("---test----");
		return "goto_upload";
	}*/
	public String readWork() throws Exception{
		int wid = Integer.parseInt(request.getParameter("wid"));
		work.setWid(wid);
		worklist = workService.find(work, "wid");
		if(worklist.size()!=0){
			work = worklist.get(0);
			List<String> str = workUtils.readbook_U(work.getUser().getUid(), work.getPath());
			request.getSession().setAttribute("content", str);
			request.getSession().setAttribute("doc_count", str.size());
			request.getSession().setAttribute("page", 1);
			request.getSession().setAttribute("work", work);
			request.getSession().setAttribute("book", null);//清空（暂时保留这个，1.减少存储的大小2.方便前端判断）
			user = (User) request.getSession().getAttribute("user");
			if(user!=null){
				List<Favour> favlist = userService.findF(user,work);
				if(favlist.size()!=0){
					Favour favour = favlist.get(0);
					if(favour.getUpdateFlag()!=0){
						favour.setUpdateFlag(0);//清零
						userService.updateF(favour);
						request.getSession().setAttribute("updateFlag", null);
					}
				}
			}
			return "goto_book";
		}
		return "goto_index";
	}
	public String selectW() throws Exception{//查询作品
		String message = request.getParameter("message");//具体参数
		message = new String(message.getBytes("ISO-8859-1"),"utf-8"); 
		String flag = request.getParameter("flag");//wname
		System.out.println("message:"+message);
		System.out.println("flag:"+flag);
		if(message!=null&&flag!=null){
			if(flag.equals("wname")){
				work.setWname(message);
			}
			worklist = workService.find_indistinct(work, flag);
			request.getSession().setAttribute("booklist", null);
			request.getSession().setAttribute("worklist", worklist);
			request.getSession().setAttribute("classfy", "用户作品");
		}
		return "goto_index";
	}
	//更新用户作品内容(包括所有属性)
	public String update() throws Exception{
		System.out.println("uploadFileName:" + uploadFileName);
		System.out.println("uploadContentType:" + uploadContentType);
		/*String type_flag = (String) request.getParameter("type_flag");*/
		System.out.println(work.toString());
		user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			return "goto_login";
		}
		t_upload.setUser(user);
		if(work!=null){
			worklist = workService.find(work, "wid");
			if(worklist.size()!=0){
				Work work2 = worklist.get(0);//持久态
				if(!work2.getUser().getUid().equals(user.getUid())){//有人通过非法手段修改别人的作品信息
					request.getSession().setAttribute("user",null);
					return "goto_login";
				}
				/*图片的*/
				if(image!=null){
					String path = workUtils.uploadbookI_U(image, work2.getUser().getUid(), uploadContentType);
					if(path!=""){//把原来的图片删除掉,数据库的路径也改了
						workUtils.removeWorkI(work2.getImage());
						work2.setImage(path);
					}
				}
				if(upload!=null){
					boolean rs = workUtils.removeWork(work2.getUser().getUid(), work2.getWname());//先删除磁盘的旧内容
					if(rs){
						String result = workUtils.uploadbook_U(upload, work2.getUser().getUid(),
								uploadContentType, work.getWname());//新的名字
						if (result != "") {
							if (result.equals("typefalse")) {
								request.setAttribute("uploadResult",
										"文件有误,请上传doc,docx,txt类型的文件");
							} else if (result.equals("dirfalse")) {
								request.setAttribute("uploadResult", "该书本已存在");
							} else {
								work2.setPath(result);
								t_upload.setWork(work2);
								long time = System.currentTimeMillis()/1000;
								t_upload.setTime(time);
								managerService.updateUpload(t_upload);
							}
						}
					}
				}
				work2.setWname(work.getWname());
				work2.setDescription(work.getDescription());
				workService.update(work2);
				request.setAttribute("upload", "success");
			}
		}
		return "goto_user";
	}
	//修改作品封面
	public String updateI() throws Exception{
		System.out.println(image+";"+imageFileName+";"+imageContentType);
		int wid = Integer.parseInt(request.getParameter("wid"));
		work.setWid(wid);
		worklist = workService.find(work, "wid");
		if(worklist!=null){
			work = worklist.get(0);
			/*System.out.println("bid:"+bid);
			System.out.println("image: "+image+"\nimageFN: "+imageFileName+"\nimageCT:"+imageContentType);*/
			String path = workUtils.uploadbookI_U(image, work.getUser().getUid(), imageContentType);
			if(path!=""){//把原来的图片删除掉,数据库的路径也改了
				workUtils.removeWorkI(work.getImage());
				work.setImage(path);
				workService.update(work);
			}
		}
		return "goto_edit";
	}
	public String delete() throws Exception{
		int wid = Integer.valueOf(request.getParameter("wid"));
		work.setWid(wid);
		worklist = workService.find(work, "wid");
		System.out.println("--deleteOK----");
		if(worklist.size()!=0){
			work = worklist.get(0);
			List<Upload> list = managerService.findUpload(work);
			if(list.size()!=0){
				t_upload = list.get(0);
				if(managerService.deleteUpload(t_upload)){
					if(workUtils.removeWork(work.getUser().getUid(), work.getWname())){
						workService.delete(work);
					}
				}
			}
		}
		return getMyWork();
	}
	
}
