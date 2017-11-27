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

import org.apache.commons.io.FileUtils;

public class workUtils {
	private static String DiskPath = "D:\\SSH_test\\main\\";// 默认存取路径
	private static String userPath = "user\\";

	// 读取用户作品
	public List<String> readbook_U(String uid, String bpath)
			throws FileNotFoundException, UnsupportedEncodingException {
		String totalpath = DiskPath + userPath + uid;// 每个用户拥有自己的文件夹存放自己的作品,不进行分类了
		File dir = new File(totalpath);
		List<String> list = new LinkedList<String>();
		if (!dir.exists()) {// 若不存在，则创建
			System.out.println("用户 " + uid + "没作品");
		} else {
			File text = new File(dir, bpath);
			if (!text.exists()) {
				System.out.println("没有这个作品");
			} else {
				try {
					BufferedReader in = new BufferedReader(
							new InputStreamReader(new FileInputStream(text),
									"GBK"));
					String read = null;
					while ((read = in.readLine()) != null) {
						list.add(read);
					}
					in.close();
					return list;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	// 用户上传作品(标题不允许重复)
	public String uploadbook_U(File upload, String uid,
			String uploadContentType, String bname) {
		String totalpath = DiskPath + userPath + uid + "\\" + bname;// 每个用户拥有自己的文件夹存放自己的作品,不进行分类了
		File dir = new File(totalpath);
		String ctype = null;// 文件后缀
		if (upload != null) {
			if (uploadContentType.equals("text/plain")) {
				ctype = ".txt";
			} else if (uploadContentType.equals("application/msword")) {
				ctype = ".doc";
			} else if (uploadContentType
					.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document")) {
				ctype = ".docx";
			}
			if (ctype != null) {
				if (!dir.exists()) {// 若文件夹不存在，则创建
					dir.mkdir();

					try {
						String time = "" + System.currentTimeMillis();
						time = time
								.substring(time.length() - 10, time.length());// 保留后10位的时间戳
						FileUtils.moveFile(upload, new File(dir, uid + time
								+ ctype));// 存放到磁盘的时候重新命名，以免重复
						return bname + "\\" + uid + time + ctype;// 返回部分路径名
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					return "dirfalse";//作品已存在
				}
			} else {
				return "typefalse";// 文件类型不符
			}
		}
		return "";
	}

	// 用户上传作品的封面
	public String uploadbookI_U(File image, String uid,
			String uploadContentType, String bname) {
		String totalpath = DiskPath + userPath + uid + "\\" + bname;
		File dir = new File(totalpath);
		String ctype = null;// 文件后缀
		if (image != null) {
			if (uploadContentType.equals("image/jpeg")) {
				ctype = ".jpg";
			}
			if (ctype != null) {
				try {
					String time = "" + System.currentTimeMillis();
					time = time.substring(time.length() - 10, time.length());// 保留后10位的时间戳
					FileUtils.moveFile(image, new File(dir, uid + "_img_"
							+ time + ctype));// 存放到磁盘的时候重新命名，以免重复
					return bname + "\\" + uid + time + ctype;// 返回部分路径
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				return "typefalse";// 文件类型不符
			}
		}
		return "";
	}

}
