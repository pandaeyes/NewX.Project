package newx.taglib;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import newx.util.StringUtil;

public class FormTag  extends BodyTagSupport {
	
	private String id = null;
	private String action = null;
	private String method = "post";
	private static int key = 1;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public int doStartTag() throws JspException {
		if (StringUtil.isNullOrEmpty(id)) {
			id = "form" + key;
			key++;
		}
		return super.doStartTag();
	}
	
	public int doEndTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			if (StringUtil.isNullOrEmpty(action)) {
				action = ((HttpServletRequest)pageContext.getRequest()).getContextPath() + "/action/MainAction";
			}
			out.println("<form id=\"" + id + "\" action=\""+ action + "\" method=\"" + method + "\">");
			BodyContent bc = getBodyContent();
			out.println(bc.getString());
			out.println("</form>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		action = null;
		method = "post";
		return EVAL_PAGE;
	}
}
