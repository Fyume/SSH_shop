package zhku.jsj141.utils.user;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class timeTag extends TagSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int type=0;//0 是system.currentTimeMillis()的    | 1 是 Date().getTime()的 (一个是上传的时间，一个是书本的出版时间)不同
	private String value;
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public int doStartTag() throws JspException{
		try{
			long time = Long.valueOf(value.trim());
			String str = "";
			if(type==1){
				Date date = new Date(time);
				str = date.getYear()+"-"+date.getMonth()+"-"+date.getDate();
			}else{
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				str = format.format(time);
			}
			pageContext.getOut().write(str);
		}catch(Exception e){
			e.printStackTrace();
		}
		return super.doStartTag();
	}
}
