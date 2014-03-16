package newx.taglib;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import newx.mod.menu.MenuService;
import newx.mod.menu.Right;

public class NavigateTag extends BodyTagSupport {
	
	private static HashMap<String, String> map = new HashMap<String, String>();

	public int doEndTag() throws JspException {
		try {
			write(pageContext.getOut());
		} catch (IOException e) {
			throw new JspException(e.toString());
		}
		return (EVAL_PAGE);
	}
	
	public void write(Writer writer) throws IOException {
		String navId = "" + pageContext.getSession().getAttribute("navId");
		String sb = "";
		if (map.get(navId) != null) {
			writer.write(map.get(navId));
		} else {
			sb = getNavigate(navId);
			writer.write(sb);
			map.put(navId, sb);
		}
	}
	
	public static String getNavigate(String navId) {
		String sb = "";
		if (map.get(navId) != null) {
			return map.get(navId);
		} else {
			Right right = null;
			right = MenuService.getInstance().getRightById(navId);
			while(right != null) {
				if ("0".equals(right.getParentid())) {
					sb = right.getRightname() + sb;
				} else {
					sb = " -> " + right.getRightname() + sb;
				}
				right = MenuService.getInstance().getRightById(right.getParentid());
			}
			sb = "<div><br>&nbsp&nbsp<b>" + sb + "</b></div>";
			map.put(navId, sb);
			return sb;
		}
	}
}
