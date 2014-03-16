package newx.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class HtmlTemplateTag extends BodyTagSupport {
	
	private String title = "欢迎访问";
	
	private String nav = "true";
	
	public String getNav() {
		return nav;
	}

	public void setNav(String nav) {
		this.nav = nav;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public int doEndTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
			BodyContent bc = getBodyContent();
			String context = pageContext.getServletContext().getContextPath();
			out.println("<HTML>");
			out.println("<HTML>");
			out.println("<HEAD>");
			out.println("<TITLE>" + getTitle() + "</TITLE>");
			out.println("<META http-equiv=Content-Type content=\"text/html; charset=UTF-8\">");
			out.println("<LINK href=\"" + context + "/css/menu.css\" type=text/css rel=StyleSheet>");
			out.println("<script src=\"" + context + "/js/menu.js\"></script>");
			out.println("<script src=\"" + context + "/js/operate.js\"></script>");
			out.println("<script SRC=\"" + context + "/js/pz_chromeless.js\"></script>");
			out.println("<script SRC=\"" + context + "/js/chromelesswin.js\"></script>");
			out.println("<script src=\"" + context + "/js/check.js\"></script>");
			out.println("<script src=\"" + context + "/js/showhide.js\"></script>");
			out.println("<script src=\"" + context + "/js/tablesort.js\"></script>");
			out.println("<script src=\"" + context + "/js/calendar.js\"></script>");
			out.println("<script src=\"" + context + "/js/print.js\"></script>");
			out.println("<script src=\"" + context + "/js/lemisTree.js\"></script>");
			out.println("<script src=\"" + context + "/js/Globals.js\"></script>");
			out.println("<script src=\"" + context + "/js/app_common.js\"></script>");
			out.println("<script src=\"" + context + "/js/batchpro.js\"></script>");
			out.println("<script src=\"" + context + "/js/other.js\"></script>");
			out.println("<script src=\"" + context + "/js/lunar.js\"></script>");
			out.println("<script src=\"" + context + "/js/jquery-1.8.2.js\"></script>");
			out.println("</HEAD>");
			out.println("<body background=\"" + context + "/images/main_bg.gif\" leftmargin=\"0\" topmargin=\"0\" style=\"overflow-x:auto;overflow-y:auto\" >");
			if ("true".equalsIgnoreCase(getNav())) {
				String navId = "" + pageContext.getSession().getAttribute("navId");
				String nav = NavigateTag.getNavigate(navId);
				out.println(nav);
			}
			out.println(bc.getString());
			out.println("</body>");
			out.println("</HTML>");
		} catch(IOException e) {
            throw new JspException(e.toString());
        }
	    return EVAL_PAGE;
    }
}
