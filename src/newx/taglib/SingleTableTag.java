package newx.taglib;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import newx.taglib.base.IRecordSetOwner;
import newx.taglib.base.MemRecord;
import newx.taglib.base.MemRecordSet;
import newx.taglib.base.RecordProvider;
import newx.taglib.base.TagService;
import newx.util.StringUtil;

public class SingleTableTag extends BodyTagSupport implements IRecordSetOwner{

	private MemRecordSet memRecordSet = null;
	private static int md5key = 0;
	
	private String id = "";
	
	private String title = "";
	
	private String width = "98%";
	
	private String getFromReq = "";
	
	private boolean readOnly = false;
	
	private boolean hasTitle = true;
	
	private String defValues = "";
	
	private boolean display = true;
	
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

	public String getGetFromReq() {
		return getFromReq;
	}

	public void setGetFromReq(String getFromReq) {
		this.getFromReq = getFromReq;
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	public boolean isHasTitle() {
		return hasTitle;
	}

	public void setHasTitle(boolean hasTitle) {
		this.hasTitle = hasTitle;
	}

	public String getDefValues() {
		return defValues;
	}

	public void setDefValues(String defValues) {
		this.defValues = defValues;
	}

	public boolean isDisplay() {
		return display;
	}

	public void setDisplay(boolean display) {
		this.display = display;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}
	
	public Object getRecordValue(String fieldName) {
		return memRecordSet.getValue(fieldName);
	}
	
	public int doStartTag() throws JspException {
		memRecordSet = new MemRecordSet();
		if (StringUtil.isNullOrEmpty(id)) {
			id = genKey();
		} 
		return super.doStartTag();
	}
	
	public int doAfterBody() throws JspException {
		return SKIP_BODY;
	}
	
	public int doEndTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			BodyContent bc = getBodyContent();
			if (hasTitle) {
				writeTitleTable(out, bc.getString());
			} else {
				writeTable(out, bc.getString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		id = "";
		return EVAL_PAGE;
	}
	
	private void writeTitleTable(JspWriter out, String bodyContent) throws IOException{
		out.println("<div align=\"center\">");
		out.println("<table class=\"dataTable\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\"  border=\"0\"  width=\"" + getWidth() + "\">");
		out.println("<tr><td nowrap class=\"single_table_title\"> &nbsp;<img ftype=\"img_click_collapse_stable\" fid=\"" + getId() + "\" style=\"cursor:pointer\" src=\"" + pageContext.getServletContext().getContextPath() + "/images/dot11.gif\" width=\"9\" height=\"9\"></a>&nbsp;" + getTitle() + " </td></tr>");
		out.println("</table>");
		out.println("<div ftype=\"div_click_collapse_stable\" fid=\"" + getId() + "\" style=\"display:block;\" class=\"panelBorder\" align=\"left\">");
		writeTable(out, bodyContent);
		out.println("</div>");
		out.println("</div>");
	}
	
	private void writeTable(JspWriter out, String bodyContent) throws IOException{
		out.println("<table class=\"datatable\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\"  border=0 fid=\"" + getId() + "\" width=\"" + getWidth() + "\">");
		out.println("<tbody>");
		out.println(bodyContent);
		out.println("</tbody>");
		out.println("</table>");
	}
	
	private String genKey() {
		md5key++;
		return "f" + StringUtil.MD5("" + md5key);
	}
	
	public void execute(RecordProvider provider) {
		ServletRequest request = pageContext.getRequest();
		if (provider.getId().indexOf("_dd_") == -1) {
			TagService.getInstance().queryForObject(memRecordSet, provider, request);
		} else {
			TagService.getInstance().query(memRecordSet, provider, request);
		}
		MemRecord record = memRecordSet.firstRecord();
		if (record != null && !StringUtil.isNullOrEmpty(provider.getOutParam())) {
			String [] outparam = provider.getOutParam().split("\\|");
			for (String name : outparam) {
				if (record.field(name) != null) {
					pageContext.setAttribute(name, record.field(name));
				}
			}
		}
	}
}
