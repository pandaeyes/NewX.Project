package newx.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import newx.taglib.base.IRecordSetOwner;
import newx.taglib.base.PrividerParam;
import newx.taglib.base.RecordProvider;

public class RecordProviderTag extends BodyTagSupport {

	private String id = "";
	
	private String outParam = "";
	
	private String sql = "";
	
	private RecordProvider provider = null;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getOutParam() {
		return outParam;
	}

	public void setOutParam(String outParam) {
		this.outParam = outParam;
	}
	
	public void addPram(PrividerParam param) {
		provider.addPram(param);
	}

	public int doStartTag() throws JspException {
		provider = new RecordProvider();
		return super.doStartTag();
	}
	
	public int doEndTag() throws JspException {
		IRecordSetOwner owner = (IRecordSetOwner)getParent();
		provider.setId(id);
		provider.setOutParam(outParam);
		provider.setSql(sql);
		provider.parse();
		owner.execute(provider);
		reset();
		return EVAL_PAGE;
	}
	
	private void reset() {
		id = "";
		outParam = "";
		sql = "";
		provider = null;
	}
}
