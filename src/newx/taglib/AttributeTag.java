package newx.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class AttributeTag  extends BodyTagSupport {
	
	public String name = "";
	
	/**
	 * 范围  page/request/
	 */
	public String scope = "page";
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public int doEndTag() throws JspException {
		Object value = null;
		if ("page".equals(scope)) {
			value = pageContext.getAttribute(name);
		} else if ("request".equals(scope)) {
			value = pageContext.getRequest().getAttribute(name);
		} else if ("context".equals(scope)) {
			value = pageContext.getServletContext().getAttribute(name);
		}
		try {
			if (value != null)
				pageContext.getOut().write(value.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

}
