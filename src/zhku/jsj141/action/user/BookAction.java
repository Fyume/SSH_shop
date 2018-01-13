package zhku.jsj141.action.user;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import zhku.jsj141.entity.Type;
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
	private File image;
	private String imageFileName;
	private String imageContentType;
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

	public String getData() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		List<Type> typelist = null;
		List<Book> booklist = null;
		Book book = new Book();
		book.setType("网络小说");
		typelist = bookService.findT();
		booklist = bookService.find(book, "type");
		request.getSession().setAttribute("typelist", typelist);
		request.getSession().setAttribute("classfy", "网络小说");
		request.getSession().setAttribute("booklist", booklist);
		return "goto_index";
	}

	// 上传书本
	public String upload_U() throws Exception {
		System.out.println("uploadFileName:" + uploadFileName);
		System.out.println("uploadContentType:" + uploadContentType);
		System.out.println("imageFileName:" + imageFileName);
		System.out.println("imageContentType:" + imageContentType);
		HttpServletRequest request = ServletActionContext.getRequest();
		String title = (String) request.getParameter("upload_title");
		String ISBN = (String) request.getParameter("ISBN");
		String type = (String) request.getParameter("type");
		String type_flag = (String) request.getParameter("type_flag");
		int year = Integer.parseInt(request.getParameter("year"));
		int month = Integer.parseInt(request.getParameter("month"));
		int day = Integer.parseInt(request.getParameter("day"));
		long publish = new Date(year - 1900, month - 1, day).getTime();// 转换成时间戳
		String description = (String) request.getParameter("description");
		String author = (String) request.getParameter("author");
		Book book = new Book();
		book.setAuthor(author);
		book.setDescription(description);
		book.setBname(title);
		book.setISBN(ISBN);
		book.setPublish(publish);
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			String result = bookUtils.uploadbook(upload, type,
					uploadContentType, title);

			if (result != "") {
				book.setType(type);
				if (result.equals("typefalse")) {
					request.setAttribute("uploadResult",
							"作品文件有误,请上传doc,docx,txt类型的文件");
				} else if (result.equals("dirfalse")) {
					request.setAttribute("uploadResult", "该作品已存在");
				} else {
					book.setPath(result);
					String result2 = bookUtils.uploadbookI(image, "uid",
							imageContentType);
					if (result2 != "") {
						if (result2.equals("typefalse")) {
							request.setAttribute("uploadResult",
									"图片文件有误,请上传jpg类型的文件");
						} else {
							book.setImage(result2);
							bookService.add(book);
							request.setAttribute("uploadResult", "success");
						}
					}
				}
			}
		} else {
			return "goto_login";
		}
		return "goto_upload";

	}
	public String readBook() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		int bid = Integer.parseInt(request.getParameter("bid"));
		Book book = new Book();
		book.setBid(bid);
		List<Book> list = bookService.find(book, "bid");
		book = list.get(0);
		if(book.getBname()!=null&&!book.getBname().isEmpty()){
			List<String> str = bookUtils.readbook(book.getType(), book.getPath());
			request.getSession().setAttribute("content", str);
			request.getSession().setAttribute("doc_count", str.size());
			request.getSession().setAttribute("page", 1);
			request.getSession().setAttribute("book", book);
			return "goto_read";
		}
		return "goto_index";
	}
	public String selectB() throws Exception{//查询书本
		HttpServletRequest request = ServletActionContext.getRequest();
		String message = request.getParameter("message");//具体参数
		message = new String(message.getBytes("ISO-8859-1"),"utf-8"); 
		String flag = request.getParameter("flag");//book的某个属性
		System.out.println("message:"+message);
		System.out.println("flag:"+flag);
		Book book = new Book();
		if(message!=null&&flag!=null){
			/*String Flag = flag.substring(0, 1).toUpperCase()+flag.substring(1,flag.length());
			book.getClass().getMethod("set" + Flag).invoke(book,message);
			System.out.println(book.getClass().getMethod("get" + Flag)
							.invoke(book));*/
			if(flag.equals("type")){
				book.setType(message);
			}else if(flag.equals("type_flag")){
				book.setType_flag(message);
			}else if(flag.equals("bname")){
				book.setBname(message);
			}else if(flag.equals("author")){
				book.setAuthor(message);
			}
			List<Book> list = bookService.find_indistinct(book, flag);
			for (Book book2 : list) {
				System.out.println(book2.toString());
			}
			request.getSession().setAttribute("booklist", list);
		}
		return "goto_index";
	}
}
