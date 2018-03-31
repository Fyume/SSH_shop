package zhku.jsj141.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class readtest {

	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
		File dir = new File("D:\\SSH_test\\test");
		if(!dir.exists()){
			dir.mkdir();
		}
		List<String> list = new LinkedList<String>();
		File text = new File(dir,"notice.txt");
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader( new FileInputStream(text),"GBK"));
			String read =null;
			while((read=in.readLine())!=null){
				list.add(String.valueOf(read.length())+":");
				/*if(read.length()>=60){
					String str1 = read.substring(0, 60);
					str1 = str1+"\n";
					String str2 = read.substring(60, read.length());
					read = str1 + str2;
				}*/
				list.add(read);
			}
			in.close();
			int i = 0;
			for (String string : list) {
				i++;
				if(i%2==0){
					System.out.println(string);
				}else{
					System.out.print(string);
				}
			}
			//网页一行的字数（60）
			/*System.out.println("本书由“尹子”整理. 想获得更多免费电子书请加小编微信：ynventon QQ群：534205772 小编喜欢结交一些喜".length());
*/		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://127.0.0.1/ssh_test?useUnicode=true&characterEncoding=utf-8";
		String user ="root";
		String password = "root";
		Connection con = (Connection) DriverManager.getConnection(url, user, password);
		String sql = "insert into test values(?)";
		SimpleDateFormat format = new SimpleDateFormat("yyy年MM月dd日-HH:mm:ss");
		Date date = new Date(1996-1900,2-1,31);
		PreparedStatement pre = (PreparedStatement) con.prepareStatement(sql);
		pre.setDate(1, date);
		pre.executeUpdate();
		pre.close();
		con.close();*/
	}

}
