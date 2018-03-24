package zhku.jsj141.utils.user;

import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class timeTag2 extends TagSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String value;
	private String num;
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
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
			Date date = new Date(time);
			int year = date.getYear();
			String str1 = "<input id='year"+num+"' type='text' onchange='return checkPublish('"+num+"');' value='"+year+"'>年";
			int month = date.getMonth();
			String str2 = "<input id='month"+num+"' type='text' onchange='return checkPublish('"+num+"');' value='"+month+"'>月";
			int day = date.getDate();
			String str3 = "<input id='date"+num+"' type='text' onchange='return checkPublish('"+num+"');' value='"+day+"'>日";
			pageContext.getOut().write(str1+str2+str3);
		}catch(Exception e){
			e.printStackTrace();
		}
		return super.doStartTag();
	}
}
