package zhku.jsj141.action.user;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.alibaba.fastjson.JSON;

import zhku.jsj141.action.BaseAction;
import zhku.jsj141.entity.Type;
import zhku.jsj141.entity.Upload;
import zhku.jsj141.entity.user.Book;
import zhku.jsj141.entity.user.Favour;
import zhku.jsj141.entity.user.User;
import zhku.jsj141.entity.user.Work;
import zhku.jsj141.service.BookService;
import zhku.jsj141.service.ManagerService;
import zhku.jsj141.service.UserService;
import zhku.jsj141.service.WorkService;
import zhku.jsj141.utils.user.bookUtils;


public class BookAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BookService bookService;
	private UserService userService;
	private WorkService workService;
	private ManagerService managerService;
	
	private File upload;
	private String uploadFileName;
	private String uploadContentType;
	private File image;
	private String imageFileName;
	private String imageContentType;
	
	Upload t_upload = new Upload();
	List<Type> typelist = null;
	List<Book> booklist = null;
	List<Work> worklist = null;
	
	User user = new User();
	Book book = new Book();
	
	private List<File> flist;//书本批量上传用
	private List<String> flistFileName;
	private List<String> flistContentType;
	private List<File> ilist;//封面批量上传用
	private List<String> ilistFileName;
	private List<String> ilistContentType;
	private List<Book> blist = new ArrayList<Book>();
	private List<String> ylist = new ArrayList<String>();
	private List<String> mlist = new ArrayList<String>();
	private List<String> dlist = new ArrayList<String>();
	public List<String> getYlist() {
		return ylist;
	}
	public void setYlist(List<String> ylist) {
		this.ylist = ylist;
	}
	public List<String> getMlist() {
		return mlist;
	}
	public void setMlist(List<String> mlist) {
		this.mlist = mlist;
	}
	public List<String> getDlist() {
		return dlist;
	}
	public void setDlist(List<String> dlist) {
		this.dlist = dlist;
	}
	public List<Book> getBlist() {
		return blist;
	}
	public void setBlist(List<Book> blist) {
		this.blist = blist;
	}
	public List<File> getFlist() {
		return flist;
	}
	public void setFlist(List<File> flist) {
		this.flist = flist;
	}
	public List<File> getIlist() {
		return ilist;
	}
	public void setIlist(List<File> ilist) {
		this.ilist = ilist;
	}
	public List<String> getFlistFileName() {
		return flistFileName;
	}
	public void setFlistFileName(List<String> flistFileName) {
		this.flistFileName = flistFileName;
	}
	public List<String> getFlistContentType() {
		return flistContentType;
	}
	public void setFlistContentType(List<String> flistContentType) {
		this.flistContentType = flistContentType;
	}
	public List<String> getIlistFileName() {
		return ilistFileName;
	}
	public void setIlistFileName(List<String> ilistFileName) {
		this.ilistFileName = ilistFileName;
	}
	public List<String> getIlistContentType() {
		return ilistContentType;
	}
	public void setIlistContentType(List<String> ilistContentType) {
		this.ilistContentType = ilistContentType;
	}
	
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public BookService getBookService() {
		return bookService;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}
	public ManagerService getManagerService() {
		return managerService;
	}
	public void setManagerService(ManagerService managerService) {
		this.managerService = managerService;
	}
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
	
	//页面初始数据获取（包括用户作品）
	public String getData() throws Exception {
		typelist = bookService.findT();
		request.getSession().setAttribute("typelist", typelist);
		booklist = bookService.findAll();
		request.getSession().setAttribute("booklist", booklist);
		//
		worklist = workService.findAll();
		request.getSession().setAttribute("worklist", worklist);
		//
		return "goto_index";
	}
	// 上传书本
	public String upload() throws Exception {
		System.out.println("uploadFileName:" + uploadFileName);
		System.out.println("uploadContentType:" + uploadContentType);
		System.out.println("imageFileName:" + imageFileName);
		System.out.println("imageContentType:" + imageContentType);
		/*String type_flag = (String) request.getParameter("type_flag");*/
		int year = Integer.parseInt(request.getParameter("year"));
		int month = Integer.parseInt(request.getParameter("month"));
		int day = Integer.parseInt(request.getParameter("day"));
		long publish = new Date(year, month, day).getTime()/(1000*60*60);// 转换成时间戳(只需要年月日,为了不丢失精度，保留时)
		book.setPublish(publish);
		user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			t_upload.setUser(user);
			String result = bookUtils.uploadbook(upload, book.getType(),
					uploadContentType, book.getBname());
			if (result != "") {
				if (result.equals("typefalse")) {
					request.setAttribute("uploadResult",
							"文件有误,请上传doc,docx,txt类型的文件");
				} else if (result.equals("dirfalse")) {
					request.setAttribute("uploadResult", "该书本已存在");
				} else {
					book.setPath(result);
					String result2 = bookUtils.uploadbookI(image,
							imageContentType);
					if (result2 != "") {
						if (result2.equals("typefalse")) {
							request.setAttribute("uploadResult",
									"图片文件有误,请上传jpg类型的文件");
						} else {
							book.setImage(result2);
							boolean rs = bookService.add(book);
							if(rs){
								t_upload.setBook(book);
								long time = System.currentTimeMillis()/1000;
								t_upload.setTime(time);
								managerService.addUpload(t_upload);
								request.setAttribute("uploadResult", "success");
							}
						}
					}
				}
			}
		} else {
			return "goto_login";
		}
		return "goto_upload";

	}
	//获取书本内容
	public String readBook() throws Exception{
		int bid = Integer.parseInt(request.getParameter("bid"));
		user = (User) request.getSession().getAttribute("user");
		book.setBid(bid);
		booklist = bookService.find(book, "bid");
		if(booklist.size()!=0){
			book = booklist.get(0);
			List<String> str = bookUtils.readbook(book.getType(), book.getPath());
			request.getSession().setAttribute("content", str);//书本内容
			request.getSession().setAttribute("doc_count", str.size());//读取到的行数
			request.getSession().setAttribute("page", 1);//默认为第1页
			request.getSession().setAttribute("book", book);
			request.getSession().setAttribute("work", null);//类似workaction里面的
			if(user!=null){
				List<Favour> favlist = userService.findF(user,book);
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
	//查询书本
	public String selectB() throws Exception{
		String flag = request.getParameter("flag");//book的某个属性
		String message = request.getParameter("message");//具体参数
		/*message = new String(message.getBytes("ISO-8859-1"),"utf-8");*/ //URL传参好像是默认ISO-8859-1？反正试了发现这个可以
		System.out.println("message:"+message);
		System.out.println("flag:"+flag);
		if(message!=null&&flag!=null){
			if(flag.equals("type")){
				book.setType(message);
				request.getSession().setAttribute("classfy", message);
			}else if(flag.equals("work")){
				book.setType_flag(message);
			}else if(flag.equals("bname")){
				book.setBname(message);
			}else if(flag.equals("author")){
				book.setAuthor(message);
			}
			booklist = bookService.find_indistinct(book, flag);
			for (Book book2 : booklist) {
				System.out.println(book2.toString());
			}
			request.getSession().setAttribute("worklist", null);
			request.getSession().setAttribute("booklist", booklist);
			request.getSession().setAttribute("listSize", booklist.size());//用于分页
		}
		return NONE;
	}
	//更新书本内容(仅更新文档,数据库表的其他属性不更新,和前端有关)
	public String update() throws Exception{
		System.out.println("uploadFileName:" + uploadFileName);
		System.out.println("uploadContentType:" + uploadContentType);
		/*String type_flag = (String) request.getParameter("type_flag");*/
		System.out.println(book.toString());
		user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			return "goto_login";
		}
		t_upload.setUser(user);
		if(book!=null&&upload!=null){
			booklist = bookService.find(book, "bid");
			if(booklist.size()!=0){
				book = booklist.get(0);
				boolean rs = bookUtils.removeBook(book.getPath(), book.getType());//先删除磁盘的旧内容
				if(rs){
					String result = bookUtils.uploadbook(upload, book.getType(),
							uploadContentType, book.getBname());
					if (result != "") {
						if (result.equals("typefalse")) {
							request.setAttribute("uploadResult",
									"文件有误,请上传doc,docx,txt类型的文件");
						} else if (result.equals("dirfalse")) {
							request.setAttribute("uploadResult", "该书本已存在");
						} else {
							book.setPath(result);
							boolean rs2 = bookService.update(book);//持久化断掉了
							if(rs2){
								//更新提示
								book = bookService.find(book, "bid").get(0);
								Set<Favour> fav_set= book.getFavour();
								for (Favour favour : fav_set) {
									favour.setUpdateFlag(1);//更新
								}
								t_upload.setBook(book);
								long time = System.currentTimeMillis()/1000;
								t_upload.setTime(time);
								managerService.updateUpload(t_upload);
								request.setAttribute("uploadResult", "success");
							}
						}
					}
				}
			}
		}
		return "goto_edit";
	}
	//修改书本封面
	public String updateI() throws Exception{
		System.out.println(image+";"+imageFileName+";"+imageContentType);
		int bid = Integer.parseInt(request.getParameter("bid"));
		book.setBid(bid);
		booklist = bookService.find(book, "bid");
		if(booklist!=null){
			book = booklist.get(0);
			/*System.out.println("bid:"+bid);
			System.out.println("image: "+image+"\nimageFN: "+imageFileName+"\nimageCT:"+imageContentType);*/
			String path = bookUtils.uploadbookI(image, imageContentType);
			if(path!=""){//把原来的图片删除掉,数据库的路径也改了
				bookUtils.removeBookI(book.getImage());
				book.setImage(path);
				bookService.update(book);
			}
		}
		return "goto_edit";
	}
	//批量上传
	public String bulkUpload() throws Exception{
		user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			return "goto_login";
		}
		List<String> uploadResult = new ArrayList<String>();
		for(int i=0;i<flist.size();i++){
			File file = flist.get(i);
			String fileName = flistFileName.get(i);
			String contType = flistContentType.get(i);
			System.out.println("file:"+file+";fileName:"+fileName+";contType:"+contType);
			File image = ilist.get(i);
			String imageName = ilistFileName.get(i);
			String imageType = ilistContentType.get(i);
			System.out.println("image:"+image+";imageName:"+imageName+";imageType:"+imageType);
			int year = Integer.valueOf(ylist.get(i));
			int month = Integer.valueOf(mlist.get(i));
			int day = Integer.valueOf(dlist.get(i));
			System.out.println(year+"年"+month+"月"+day+"日");
			long publish = new Date(year, month, day).getTime()/(1000*60*60);// 转换成时间戳(只需要年月日,为了不丢失精度，保留时)
			blist.get(i).setPublish(publish);
			Upload t_upload = new Upload();//不新建应该会变成持久态？
			t_upload.setUser(user);
			String result = bookUtils.uploadbook(file, blist.get(i).getType(),
					contType, blist.get(i).getBname());
			if (result != "") {
				if (result.equals("typefalse")) {
					uploadResult.add("文件"+i+"有误,请上传doc,docx,txt类型的文件");
				} else if (result.equals("dirfalse")) {
					uploadResult.add("第"+i+"本书已存在");
				} else {
					blist.get(i).setPath(result);
					String result2 = bookUtils.uploadbookI(image,
							imageType);
					if (result2 != "") {
						if (result2.equals("typefalse")) {
							uploadResult.add("图片文件"+i+"有误,请上传jpg类型的文件");
						} else {
							blist.get(i).setImage(result2);
							boolean rs = bookService.add(blist.get(i));
							if(rs){
								t_upload.setBook(blist.get(i));
								long time = System.currentTimeMillis()/1000;
								t_upload.setTime(time);
								managerService.addUpload(t_upload);
								uploadResult.add("第"+i+"本success");
							}
						}
					}
				}
			}
		}
		String rs = JSON.toJSONString(uploadResult);
		request.setAttribute("uploadResultList",rs);
		return "goto_bulkUpload";
	}
	/***************测试****************/
/*	public String test() throws Exception{
		for (File tst : test) {
			System.out.println(tst);
		}
		for (String filename : testfn) {
			System.out.println(filename);
		}
		for (String conttype : testct) {
			System.out.println(conttype);
		}
		return NONE;
	}*/
}
