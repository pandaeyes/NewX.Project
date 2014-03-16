package newx.taglib;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import newx.exception.CommonErrorCode;
import newx.exception.NewXException;
import newx.taglib.base.IRecordSetOwner;
import newx.taglib.base.MemRecord;
import newx.taglib.base.MemRecordSet;
import newx.taglib.base.MultiTableHead;
import newx.taglib.base.RecordProvider;
import newx.taglib.base.TagService;
import newx.util.StringUtil;
import newx.util.Test;

public class MultiTableTag extends BodyTagSupport implements IRecordSetOwner {
	
	private MemRecordSet memRecordSet = null;
	private boolean execute = false;
	private List<MultiTableHead> headList = null;
	private static int md5key = 0;
	
	private String id = "";
	private String title = "";
	private String width = "98%";
	private boolean hasTitle = true;
	private int size = 10;
	
	private int totalRow = 0;
	private int totalPage = 0;
	private int pageIndex = 1;
	
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

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public boolean isHasTitle() {
		return hasTitle;
	}

	public void setHasTitle(boolean hasTitle) {
		this.hasTitle = hasTitle;
	}

	public void addHead(MultiTableHead head) {
		headList.add(head);
	}
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int doStartTag() throws JspException {
		memRecordSet = new MemRecordSet();
		execute = false;
		headList = new ArrayList<MultiTableHead>();
		if (StringUtil.isNullOrEmpty(id)) {
			id = genKey();
		} 
		return super.doStartTag();
	}
	
	public int doEndTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			BodyContent bc = getBodyContent();
			if (hasTitle) {
				writeTitleTable(out);
			} else {
				writeTable(out);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}
	
	private void writeTitleTable(JspWriter out) throws IOException{
		out.println("<div align=\"center\">");
		out.println("<table class=\"dataTable\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\"  border=\"0\"  width=\"" + getWidth() + "\">");
		out.println("<tr><td nowrap class=\"single_table_title\"> &nbsp;<img ftype=\"img_click_collapse_mtable\" fid=\"" + getId() + "\" style=\"cursor:pointer\" src=\"" + pageContext.getServletContext().getContextPath() + "/images/dot11.gif\" width=\"9\" height=\"9\"></a>&nbsp;" + getTitle() + " </td></tr>");
		out.println("</table>");
		out.println("<div ftype=\"div_click_collapse_mtable\" fid=\"" + getId() + "\" style=\"display:block; width:" + getWidth() + ";\" class=\"panelBorder\" align=\"center\">");
		writeTable(out);
		out.println("</div>");
		out.println("</div>");
	}
	
	private void writeTable(JspWriter out) throws IOException{
		if (hasTitle) {
			out.println("<table align=center width=\"100%\" class=\"list_table\" name=\"" + getId() + "\" id=\"" + getId() + "\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">");
		} else {
			out.println("<table align=center width=\"" + getWidth() + "\" class=\"list_table\" name=\"" + getId() + "\" id=\"" + getId() + "\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">");
		}
		out.println("<thead>");
		out.println("<tr class=\"list_table_thead_tr_title\">");
		for (MultiTableHead head : headList) {
			out.println("<td width=\"11%\" class=\"list_table_thead_td_title\" nowrap>" + head.getTxt() + "</td>");
		}
		out.println("</tr>");
		out.println("</thead>");
		out.println("<tbody>");
		int count = memRecordSet.getRowCount();
		MemRecord record = null;
		String trClass = "";
		for (int i  = 0; i < count; i++) {
			record = memRecordSet.record(i);
			trClass = (i%2) == 0 ? "list_table_tbody_tr" : "list_table_tbody_tr1";
			out.println("<tr name=\"TR" + i + "\" id=\"TR" + i + "\" class=\"" + trClass + "\" onMouseOut=\"this.className='" + trClass + "';\" onMouseOver=\"this.className='list_table_tbody_tr1_mouseover';\">");
			for (MultiTableHead head : headList) {
				out.println("<td class=\"list_table_tbody_td\" align=\"left\" nowrap>" + StringUtil.replaceHTML(record.field(head.getName()))+ "</td>");
			}
			out.println("</tr>");
		}
		out.println("</tbody>");
		out.println("</table>");
		writePageInfo(out);
	}
	
	private void writePageInfo(JspWriter out) throws IOException{
		String pageUrl = procPageURL((HttpServletRequest)pageContext.getRequest());
		String ppageUrl = null;
		String npageUrl = null;
		out.print("<table class=\"list_table_pageSplitInfo\" width=\"" + getWidth() + "\" align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
		out.print("<tr><td nowrap><div class=\"list_table_pageSplitInfo\">");
		out.print("共有 <b> " + totalRow + " </b>条记录&nbsp;&nbsp;");
		out.print("<b> " + totalPage + " </b> 页&nbsp;&nbsp;当前是第 <b> " + pageIndex + "  </b> 页&nbsp;&nbsp;");
		if (pageIndex <= 1) {
			ppageUrl = pageUrl + "&" + getPageIndexName() + "=" + pageIndex;
			out.print("上页&nbsp;&nbsp");
		} else {
			ppageUrl = pageUrl + "&" + getPageIndexName() + "=" + (pageIndex - 1);
			out.print("<span ftype=\"span_click_pageturn\" fid=\"p" + id + "\" class=\"link_normal\" onMouseOut=\"this.className='link_normal';\" onMouseOver=\"this.className='link_hover';\">上页</span>&nbsp;&nbsp");
		}
		if (pageIndex >= totalPage) {
			npageUrl = pageUrl + "&" + getPageIndexName() + "=" + pageIndex;
			out.print("下页&nbsp;&nbsp");
		} else {
			npageUrl = pageUrl + "&" + getPageIndexName() + "=" + (pageIndex + 1);
			out.print("<span ftype=\"span_click_pageturn\" fid=\"n" + id + "\" class=\"link_normal\" onMouseOut=\"this.className='link_normal';\" onMouseOver=\"this.className='link_hover';\">下页</span>&nbsp;&nbsp");
		}
		out.print("翻到第<input class=\"tdinput\" type=text size=3 id=\"pc" + id + "\">页&nbsp;");
		out.print("<span ftype=\"span_click_pageturn_cus\" fid=\"c" + id + "\"  style=\"text-align: center; cursor:pointer;\"><img src=\"" + pageContext.getServletContext().getContextPath() + "/images/go.gif\"> </span>");
		out.println("<input id=\"p" + id + "\" type=\"hidden\" value=\"" + StringUtil.replaceHTML(ppageUrl) + "\"/>");
		out.println("<input id=\"n" + id + "\" type=\"hidden\" value=\"" + StringUtil.replaceHTML(npageUrl) + "\"/>");
		out.println("<input id=\"c" + id + "\" type=\"hidden\" value=\"" + StringUtil.replaceHTML(pageUrl + "&" + getPageIndexName() + "=") + "\"/>");
		out.println("<input id=\"tc" + id + "\" type=\"hidden\" value=\"" + totalPage + "\"/>");
		out.print("</div></td></tr>");
		out.println("</table>");
	}
	
	public void execute(RecordProvider provider) {
		if (execute) {
			throw new NewXException(CommonErrorCode.MULTI_RECORD_ERROR);
		}
		ServletRequest request = pageContext.getRequest();
		String pin = request.getParameter(getPageIndexName());
		if (!StringUtil.isNullOrEmpty(pin)) {
			pageIndex = Integer.parseInt(pin);
		}
		TagService.getInstance().queryLimit(memRecordSet, provider, request, (pageIndex <= 0 ? 0 : (pageIndex - 1) * size), size);
		String tr = request.getParameter(getTotalRowName());
		if (!StringUtil.isNullOrEmpty(tr)) {
			totalRow = Integer.parseInt(tr);
			totalPage = totalRow/size + (totalRow%size == 0 ? 0 : 1);
		} else {
			totalRow = TagService.getInstance().getResultSetCount(provider, request);
			totalPage = totalRow/size + (totalRow%size == 0 ? 0 : 1);
		}
		execute = true;
	}
	
	public String procPageURL(HttpServletRequest request) {
		StringBuffer newPageURL = new StringBuffer("");
		String actionType = null;
		if (request.getAttribute("javax.servlet.forward.request_uri") == null) {
			actionType = request.getRequestURI();
		} else {
			actionType = "" + request.getAttribute("javax.servlet.forward.request_uri");
		}
		newPageURL.append(actionType);
		
        Enumeration pNameEnum = request.getParameterNames();
        int i = 0;
        while (pNameEnum.hasMoreElements()) {
            String paramName = pNameEnum.nextElement().toString();
            String paramValue = request.getParameter(paramName);
            paramValue = paramValue.replaceAll("&", "%26");
            if (!getPageIndexName().equals(paramName) && !getTotalRowName().equals(paramName)) {
	            if (i == 0) {
	                newPageURL.append("?" + paramName + "=" + paramValue);
	                i++;
	            } else {
	                newPageURL.append("&" + paramName + "=" + paramValue);
	                i++;
	            }
            }
        }
        newPageURL.append("&" + getTotalRowName() + "=" + totalRow);
        return newPageURL.toString();
	}
	
	private String getTotalRowName() {
		if (id.startsWith("f") && id.length() == 33) {
			return "__totalRow";
		} else {
			return "__totalRow_" + id;
		}
	}
	private String getPageIndexName() {
		if (id.startsWith("f") && id.length() == 33) {
			return "__pageIndex";
		} else {
			return "__pageIndex_" + id;
		}
	}
	
	private String genKey() {
		md5key++;
		return "f" + StringUtil.MD5("" + md5key);
	}
}
