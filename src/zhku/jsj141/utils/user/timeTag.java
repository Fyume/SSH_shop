package zhku.jsj141.utils.user;

import java.text.SimpleDateFormat;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class timeTag extends TagSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String value;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public int doStartTag() throws JspException{
		value = "" + value;//防止空指针？
		try{
			long time = Long.valueOf(value.trim());
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH点");
			String str = format.format(time);
			pageContext.getOut().write(str);
		}catch(Exception e){
			e.printStackTrace();
		}
		return super.doStartTag();
	}
}
