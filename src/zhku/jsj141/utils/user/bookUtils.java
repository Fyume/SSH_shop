package zhku.jsj141.utils.user;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.commons.io.FileUtils;

public class bookUtils {
	private static String DiskPath = "D:\\SSH_test\\";// 默认存取路径

	// 读取磁盘文件
	public String readbook(String type, String FileName)
			throws FileNotFoundException, UnsupportedEncodingException {
		File dir = new File(DiskPath + type);// 文件按分类存放
		if (!dir.exists()) {// 若不存在，则创建
			return "没有这个文件夹";
		} else {
			File text = new File(dir, FileName);
			if (!text.exists()) {
				return "没有这个文件";
			} else {
				try {
					BufferedReader in = new BufferedReader(
							new InputStreamReader(new FileInputStream(text),
									"GBK"));
					String read = null;
					while ((read = in.readLine()) != null) {
						System.out.println(read);
					}
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return "success";
	}

	// 读取用户作品
	public String readUbook(String uid, String FileName)
			throws FileNotFoundException, UnsupportedEncodingException {
		File dir = new File(DiskPath + uid);// 每个用户拥有自己的文件夹存放自己的作品,不进行分类了
		if (!dir.exists()) {// 若不存在，则创建
			return "该用户不存在";
		} else {
			File text = new File(dir, FileName);
			if (!text.exists()) {
				return "没有这个作品";
			} else {
				try {
					BufferedReader in = new BufferedReader(
							new InputStreamReader(new FileInputStream(text),
									"GBK"));
					String read = null;
					while ((read = in.readLine()) != null) {
						System.out.println(read);
					}
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return "success";
	}

	// 用户上传作品
	public String uploadbook(File upload, String uid, String uploadContentType) {
		File dir = new File(DiskPath + uid);// 每个用户拥有自己的文件夹存放自己的作品
		String type = null;// 文件后缀
		if (upload != null) {
			if (!dir.exists()) {// 若文件夹不存在，则创建
				dir.mkdir();
			}
			if (uploadContentType.equals("text/plain")) {
				type = ".txt";
			} else if (uploadContentType.equals("application/msword")) {
				type = ".doc";
			} else if (uploadContentType
					.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document")) {
				type = ".docx";
			}
			if (type != null) {
				try {
					String time = "" + System.currentTimeMillis();
					time = time.substring(time.length() - 10, time.length());// 保留后10位的时间戳
					FileUtils
							.moveFile(upload, new File(dir, uid + time + type));// 存放到磁盘的时候重新命名，以免重复
					return uid + time + type;// 返回文件名
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return "";
	}
	// 管理员功能
}
