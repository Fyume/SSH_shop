package zhku.jsj141.utils.user;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

public class bookUtils {
	/*private static String DiskPath = "D:\\SSH_test\\main\\";// 默认存取路径
	private static String FimagePath = "D:\\SSH_test\\images\\bookImg";// 图片最终存放路径
	private static String managerPath = "manager\\";*/
	
	private static String DiskPath = "C:\\SSH_test\\main\\";// 默认存取路径
	private static String FimagePath = "C:\\Program Files\\Apache Software Foundation\\Tomcat 7.0\\webapps\\SSH_test\\images\\bookImg";// 图片最终存放路径 
	private static String managerPath = "manager\\";
	 
	// 读取管理员上传的文件
	public static List<String> readbook(String type, String bpath)
			throws FileNotFoundException, UnsupportedEncodingException {
		String totalpath = DiskPath + managerPath + type;
		File dir = new File(totalpath);// 文件按分类存放
		List<String> list = new LinkedList<String>();
		if (!dir.exists()) {// 若不存在，则创建
			System.out.println("找不到" + totalpath);
		} else {
			File text = new File(dir, bpath);
			if (!text.exists()) {
				System.out.println("找不到" + totalpath + "\\" + bpath);
			} else {
				try {
					BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(text),"GBK"));
					String read = null;
					boolean mssyCode = false;
					while ((read = in.readLine()) != null) {
						if(read.indexOf("�")!=-1){//乱码的话(判断的贼简陋。。。)
							mssyCode = true;
							break;
						}
						int line = 50;// 根据页面的显示要求
										// 每50个字符换一行显示(虽然我也觉得这样做挺滑稽的，或许应该交给前端
										// 方便前后端分离？)
						int num = read.length() / line;
						if (num >= 1) {
							String str1 = "";
							String str2 = "";
							for (int i = 1; i <= num; i++) {
								str1 = str1
										+ read.substring(line * (i - 1), line
												* i) + "</h4><h4>";
							}
							str2 = read.substring(num * line, read.length());
							read = str1 + str2;
						}
						read = "　　" + read;
						list.add(read);
					}
					in.close();
					if(mssyCode){
						list.clear();
						BufferedReader in1 = new BufferedReader(
								new InputStreamReader(new FileInputStream(text),"UTF-8"));
						while ((read = in1.readLine()) != null) {
							int line = 50;// 根据页面的显示要求
											// 每50个字符换一行显示(虽然我也觉得这样做挺滑稽的，或许应该交给前端
											// 方便前后端分离？)
							int num = read.length() / line;
							if (num >= 1) {
								String str1 = "";
								String str2 = "";
								for (int i = 1; i <= num; i++) {
									str1 = str1
											+ read.substring(line * (i - 1), line
													* i) + "</h4><h4>";
								}
								str2 = read.substring(num * line, read.length());
								read = str1 + str2;
							}
							read = "　　" + read;
							list.add(read);
						}
						in1.close();
					}
					return list;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	// 管理员功能
	// 上传书本
	public static String uploadbook(File upload, String type,
			String uploadContentType, String bname) {
		String folder = bname;
		String totalpath = DiskPath + managerPath + type + "\\";
		File dir = new File(totalpath + folder);//
		String ctype = null;// 文件后缀
		if (upload != null) {
			if (!dir.exists()) {// 若文件夹不存在，则创建
				dir.mkdir();
			} else {// 若有重名文件
				int i = 1;
				while (dir.exists()) {
					folder = bname + "(" + i + ")";
					dir = new File(totalpath + folder);
					i++;
				}
			}
			if (uploadContentType.equals("text/plain")) {
				ctype = ".txt";
			} else if (uploadContentType.equals("application/msword")) {
				ctype = ".doc";
			} else if (uploadContentType
					.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document")) {
				ctype = ".docx";
			}
			if (ctype != null) {
				try {
					String time = "" + System.currentTimeMillis();
					time = time.substring(time.length() - 10, time.length());// 保留后10位的时间戳
					FileUtils.moveFile(upload, new File(dir, bname + time
							+ ctype));// 存放到磁盘的时候重新命名，以免重复
					return folder + "\\" + bname + time + ctype;// 返回部分路径
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return "";
	}

	/*
	 * // 上传书本的封面（由于是先上传书本，所以直接用上传作品之后返回的路径） public String uploadbookI(File
	 * image, String type, String uploadContentType, String path) { if (image !=
	 * null) { int len = path.indexOf("\\"); int len2 = path.indexOf("."); if
	 * (len > 0 && len2 > 0) { String filename = path.substring(len + 1,
	 * len2);// 书本的文件名 String folder = path.substring(0, len);// 书本所在的文件夹 File
	 * dir = new File(DiskPath + managerPath +type + "\\" + folder); String
	 * ctype = null;// 文件后缀 if (uploadContentType.equals("image/jpeg")) { ctype
	 * = ".jpg"; } if (ctype != null) { try { FileUtils.moveFile(image, new
	 * File(dir, filename + "_img" + ctype));// 存放到磁盘的时候重新命名，以免重复 return folder
	 * + "\\" + filename + "_img" + ctype;// 返回部分路径 } catch (IOException e) { //
	 * TODO Auto-generated catch block e.printStackTrace(); } } } } return ""; }
	 */
	// 上传书本的封面
	public static String uploadbookI(File image, String imageContentType) {
		if (image != null) {
			String time = String.valueOf(System.currentTimeMillis());
			String filename = time.substring(time.length() - 10, time.length());// 文件名
			File dir = new File(FimagePath);
			String ctype = null;// 文件后缀
			if (imageContentType.equals("image/jpeg")) {
				ctype = ".jpg";
			}
			if (ctype != null) {
				try {
					FileUtils.moveFile(image, new File(dir, filename + ctype));// 存放到磁盘的时候重新命名，以免重复
					return "\\" + filename + ctype;// 返回部分路径
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return "";

	}

	public static boolean removeBookI(String path) {// 删除磁盘中存放的书本封面
		try {
			FileUtils.forceDelete(new File(FimagePath + "\\" + path));
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean removeBook(String path, String type) {// 删除磁盘中存放的书本
		String totalpath = DiskPath + managerPath + type + "\\";
		String folder = path.substring(0, path.indexOf("\\"));// 获取文件夹名（可能有重名）
		try {
			FileUtils.forceDelete(new File(totalpath + folder));
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean moveBook(String path, String type, String newType) {// 移动磁盘中存放的书本
		String folder = path.substring(0, path.indexOf("\\"));
		String totalpath = DiskPath + managerPath + type + "\\";
		String totalpath2 = DiskPath + managerPath + newType + "\\";
		try {
			FileUtils.moveDirectoryToDirectory(new File(totalpath + folder),
					new File(totalpath2), true);// 测试一下
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	public static boolean isMessyCode(String strName) {// 网上找的判断乱码
		Pattern p = Pattern.compile("\\s*|t*|r*|n*");
		Matcher m = p.matcher(strName);
		String after = m.replaceAll("");
		String temp = after.replaceAll("\\p{P}", "");
		char[] ch = temp.trim().toCharArray();
		float chLength = ch.length;
		float count = 0;
		for (int i = 0; i < ch.length; i++) {
			char c = ch[i];
			if (!Character.isLetterOrDigit(c)) {
				if (!isChinese(c)) {
					count = count + 1;
				}
			}
		}
		float result = count / chLength;
		if (result > 0.4) {
			return true;
		} else {
			return false;
		}

	}
	private static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}
		return false;
	}
}
