package newx.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class HtmlTrTag extends BodyTagSupport {

	public int doEndTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			out.println("<tr>");
			BodyContent bc = getBodyContent();
			out.println(bc.getString());
			out.println("</tr>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}
}
