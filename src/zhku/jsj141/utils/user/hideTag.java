package zhku.jsj141.utils.user;

import java.text.SimpleDateFormat;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class hideTag extends TagSupport{
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
		if(!value.isEmpty()){
			try{
				value = value.substring(0,3)+"********"+value.substring(value.length()-6,value.length());
				pageContext.getOut().write(value);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return super.doStartTag();
	}
}
