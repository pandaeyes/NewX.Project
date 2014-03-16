package newx.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import newx.taglib.base.MultiTableHead;

public class MultiTableHeadTag extends BodyTagSupport {
	
	private String name = "";
	private String txt = "";
	private String dict = "";
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTxt() {
		return txt;
	}
	public void setTxt(String txt) {
		this.txt = txt;
	}
	public String getDict() {
		return dict;
	}
	public void setDict(String dict) {
		this.dict = dict;
	}
	
	public int doEndTag() throws JspException {
		MultiTableTag table = (MultiTableTag)getParent();
		MultiTableHead head = new MultiTableHead();
		head.setName(name);
		head.setTxt(txt);
		head.setDict(dict);
		table.addHead(head);
		name = "";
		txt = "";
		dict = "";
		return EVAL_PAGE;
	}
}
