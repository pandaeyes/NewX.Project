package newx.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import newx.util.StringUtil;

/**
 * @author yqhuang
 *
 */
public class PanelTag extends BodyTagSupport {
	
	private String id = "";
	
	private String title = "";
	
	private String width = "98%";
	
	private boolean collapse = true;
	
	private static int md5key = 0;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isCollapse() {
		return collapse;
	}

	public void setCollapse(boolean collapse) {
		this.collapse = collapse;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public int doStartTag() throws JspException {
		if (StringUtil.isNullOrEmpty(id)) {
			id = genKey();
		} 
		return super.doStartTag();
	}
	
	public int doEndTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
			BodyContent bc = getBodyContent();
			out.println("<div align=\"center\">");
			out.println("<table class=\"dataTable\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\"  border=\"0\"  width=\"" + getWidth() + "\">");
			out.println("<tr><td nowrap class=\"single_table_title\"> &nbsp;<img fid=\"click_collapse_id\" id=\"" + getId() + "\" style=\"cursor:pointer\" src=\"" + pageContext.getServletContext().getContextPath() + "/images/dot11.gif\" width=\"9\" height=\"9\"></a>&nbsp;" + getTitle() + " </td></tr>");
			out.println("</table>");
			out.println("<div fid=\"click_collapse_id\" id=" + getId() + " style=\"display:block; width:" + getWidth() + ";\" class=\"panelBorder\" align=\"left\">");
			out.println(bc.getString());
			out.println("</div>");
			out.println("</div>");
		} catch(IOException e) {
            throw new JspException(e.toString());
        }
		id = "";
	    return EVAL_PAGE;
    }
	
	private synchronized String genKey() {
		md5key++;
		return "f" + StringUtil.MD5("" + md5key);
	}
}
