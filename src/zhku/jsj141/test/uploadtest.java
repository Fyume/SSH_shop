package zhku.jsj141.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.junit.Test;

import com.opensymphony.xwork2.ActionSupport;

public class uploadtest extends ActionSupport {
	private File upload;
	private String uploadFileName;
	private String uploadContentType;

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
		String realPath = "D:\\SSH_test\\main\\test";
		System.out.println("realPath:" + realPath);
		File dir = new File(realPath);
		System.out.println("3333333");
		if (!dir.exists()) {// 假如没有 则新建一个文件夹
			dir.mkdir();
		}
		if (upload != null) {
			System.out.println("4444444");
			FileUtils.moveFile(upload, new File(dir, uploadFileName));
			System.out.println("5555555");
		}
		return "goto_test";
	}
	public String show() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		response.setCharacterEncoding("GBK");
		PrintWriter out = response.getWriter();
		File text = new File("D:\\SSH_test\\main\\test\\111.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(text),"GBK"));
		String read=null;
		List<String> list = new ArrayList<String>();
		while((read=reader.readLine())!=null){
			list.add(read);
			out.write("<p>"+read+"</p>");
		}
		request.getSession().setAttribute("list",list);
		out.flush();
		out.close();
		return "goto_test";
	}
	@Test
	public void testmove(){
		File file = new File("D:\\SSH_test\\main\\test\\111.txt");
	/*	File[] list = file.listFiles();
		for (File file2 : list) {
			if(file2.renameTo(new File("D:\\SSH_test\\transform\\test\\"+file2.getName()))){
				System.out.println("111");
			}else{
				System.out.println("222");
			}
		}*/
		System.out.println(file.listFiles()==null);//不是文件夹
	}
}
