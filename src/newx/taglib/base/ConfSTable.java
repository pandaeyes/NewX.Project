package newx.taglib.base;

import newx.util.SysUtil;

import org.jdom.Element;

public class ConfSTable {
	
	private String id = "";
	
	private String desc = "";
	
	private String width = "98%";
	
	private String renderStr = "";
	
	private String hiddenStr = "";

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getRenderStr() {
		return renderStr;
	}

	public void setRenderStr(String renderStr) {
		this.renderStr = renderStr;
	}

	public String getHiddenStr() {
		return hiddenStr;
	}

	public void setHiddenStr(String hiddenStr) {
		this.hiddenStr = hiddenStr;
	}

	public void readXML(Element element) {
		id = SysUtil.getAttributeValue(element, "id", "");
		desc = SysUtil.getAttributeValue(element, "desc", "");
		width = SysUtil.getAttributeValue(element, "width", "98%");
		renderStr = SysUtil.getAttributeValue(element, "renderStr", "");
		hiddenStr = SysUtil.getAttributeValue(element, "hiddenStr", "");
	}
}
 