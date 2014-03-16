package newx.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.Tag;

import newx.taglib.base.PrividerParam;

/**
 * @author yqhuang
 *
 */
public class ParamTag extends BodyTagSupport {
	
	public static String STRING = "S"; 	//()
	public static String INT = "I";    	//[]
	public static String DOUBLE = "D";  //{}

	private String name = "";
	
	private String type = STRING;
	
	private Object value = null;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
	
	public int doEndTag() throws JspException {
		Tag tag = getParent();
		if (tag != null && tag instanceof RecordProviderTag) {
			PrividerParam param = new PrividerParam();
			param.setName(name);
			param.setType(type);
			if (type.equals("I")) {
				param.setValue(Integer.parseInt(value.toString()));
			} else if (type.equals("D")) {
				param.setValue(Double.parseDouble(value.toString()));
			} else {
				param.setValue(value);
			}
			((RecordProviderTag)tag).addPram(param);
		}
		name = "";
		type = STRING;
		value = null;
		return EVAL_PAGE;
	}
}
