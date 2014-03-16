package newx.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.Tag;

import newx.util.StringUtil;

public class HtmlTdTag extends BodyTagSupport {
	
	/**
	 * 1=提示文本;
	 * 2=输入文本;
	 * 3=文本输入区;
	 * 5=字典下拉列表;
	 * 6=日期输入框;
	 * 7=公民身份号码;
	 * 8=单选按钮;
	 * 9=复选按钮;
	 * 10=多选字典下拉列表;
	 */
	public static final int text = 1;
	public static final int input = 2;
	public static final int area = 3;
	public static final int dict = 5;
	public static final int date = 6;
	public static final int idcard = 7;
	public static final int radio = 8;
	public static final int checkbox = 9;
	public static final int mdict = 10;
	
	/**
	 * 单位格类型
	 */
	private int type = text;

	/**
	 * 参数描述
	 */
	private String txt = "";
	
	/**
	 * 单元格宽度
	 */
	private String twidth = "16%";
	
	/**
	 * 单元格跨度
	 */
	private int tcolspan = 1;
	
	/**
	 * 参数名称
	 */
	private String name = "";
	
	/**
	 * 自定义类型
	 */
	private String ftype = "";
	
	/**
	 * 自定义ID
	 */
	private String fid = "";
	
	/**
	 * 描述与输入框的顺序desc/asc/none
	 */
	private String order = "asc";
	
	/**
	 * 字典名称 type = dict 有校
	 */
	private String dname = "";
	
	/**
	 * 单元格宽度
	 */
	private String width = "16%";
	
	/**
	 * 单元格跨度
	 */
	private int colspan = 1;
	
	/**
	 * 自定义串
	 */
	private String cus = "";
	
	/**
	 * 默认值
	 */
	private String defv = "";
	
	/**
	 * 校验串
	 */
	private String vld = "";
	
	/**
	 * 是否只读
	 */
	private boolean readonly = false;
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getTxt() {
		return txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}

	public String getTwidth() {
		return twidth;
	}

	public void setTwidth(String twidth) {
		this.twidth = twidth;
	}

	public int getTcolspan() {
		return tcolspan;
	}

	public void setTcolspan(int tcolspan) {
		this.tcolspan = tcolspan;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFtype() {
		return ftype;
	}

	public void setFtype(String ftype) {
		this.ftype = ftype;
	}

	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public int getColspan() {
		return colspan;
	}

	public void setColspan(int colspan) {
		this.colspan = colspan;
	}

	public String getCus() {
		return cus;
	}

	public void setCus(String cus) {
		this.cus = cus;
	}

	public String getDefv() {
		return defv;
	}

	public void setDefv(String defv) {
		this.defv = defv;
	}

	public String getVld() {
		return vld;
	}

	public void setVld(String vld) {
		this.vld = vld;
	}

	public boolean isReadonly() {
		return readonly;
	}

	public void setReadonly(boolean readonly) {
		this.readonly = readonly;
	}

	public int doEndTag() throws JspException {
		SingleTableTag tableTag = (SingleTableTag)findAncestorWithClass(this, SingleTableTag.class);
		try {
			JspWriter out = pageContext.getOut();
			ICell tcell = null;
			ICell ecell = null;
			if (!"none".equals(order)) {
				tcell = new TextCall(this);
			}
			switch (type) {
				case input:
					ecell = new InputCall(this);
					break;
				default:
					break;
			}
			if ("none".equals(order)) {
				if (ecell != null) ecell.write(out, tableTag);
			} else if ("desc".equals(order)) {
				if (ecell != null) ecell.write(out, tableTag);
				if (tcell != null) tcell .write(out, tableTag);
			} else {
				if (tcell != null) tcell .write(out, tableTag);
				if (ecell != null) ecell.write(out, tableTag);
			} 
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}
}

abstract class ICell {
	protected HtmlTdTag tag = null;
	public ICell(HtmlTdTag tag) {
		this.tag = tag;
	}
	public abstract void write(JspWriter out, SingleTableTag tableTag) throws IOException;
}

class TextCall extends ICell {
	public TextCall(HtmlTdTag tag) {
		super(tag);
	}
	public void write(JspWriter out, SingleTableTag tableTag) throws IOException {
		out.print("<td width=\"" + tag.getTwidth() + "\" valign=\"middle\" class=\"tdprompt\"");
		if (tag.getTcolspan() > 1) {
			out.print(" colspan=\"" + tag.getTcolspan() + "\" ");
		}
		out.print(">");
		out.print(tag.getTxt());
		out.println("</td>");
	}
}

class InputCall extends ICell {
	public InputCall(HtmlTdTag tag) {
		super(tag);
	}
	public void write(JspWriter out, SingleTableTag tableTag) throws IOException {
		out.print("<td width=\"" + tag.getWidth() + "\" valign=\"middle\" class=\"tdinput\"");
		if (tag.getColspan() > 1) {
			out.print(" colspan=" + tag.getColspan() + " ");
		}
		out.print(">");
		out.print("<input name=\"" + tag.getName() + "\" id=\"" + tag.getName() + "\" type=\"text\" class=\"solidborder\" size=\"12\"");
		if (!StringUtil.isNullOrEmpty(tag.getFid())) {
			out.print(" fid=\"" + tag.getFid() + "\" ");
		}
		if (!StringUtil.isNullOrEmpty(tag.getFtype())) {
			out.print(" ftype=\"" + tag.getFtype() + "\" ");
		}
		if (!StringUtil.isNullOrEmpty(tag.getCus())) {
			out.print(" " + tag.getCus() + " ");
		}
		if (!StringUtil.isNullOrEmpty(tag.getVld())) {
			out.print(" vld=\"" + tag.getVld() + " ");
		}
		if (tableTag.getRecordValue(tag.getName()) != null) {
			Object val = tableTag.getRecordValue(tag.getName());
			out.print(" value=\"" + StringUtil.replaceHTML(val == null ? null : val.toString()) + "\" ");
		}
		out.print(">");
		out.println("</td>");
	}
}
