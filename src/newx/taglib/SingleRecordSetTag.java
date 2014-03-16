package newx.taglib;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import newx.taglib.base.IRecordSetOwner;
import newx.taglib.base.MemRecord;
import newx.taglib.base.MemRecordSet;
import newx.taglib.base.RecordProvider;
import newx.taglib.base.TagService;

public class SingleRecordSetTag extends BodyTagSupport implements IRecordSetOwner {
	
	private MemRecordSet memRecordSet = new MemRecordSet();
	
	private String id = null;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SingleRecordSetTag() {
		memRecordSet = new MemRecordSet();
	}
	
	public int doStartTag() throws JspException {
		memRecordSet.clear();
		return super.doStartTag();
	}
	
	public int doAfterBody() throws JspException {
		return SKIP_BODY;
	}
	
	public int doEndTag() throws JspException {
		MemRecord record = memRecordSet.firstRecord();
		for (Object name : record.getFieldNames()) {
			pageContext.setAttribute("" + name, record.field("" + name));
		}
		return EVAL_PAGE;
	}
	
	public void execute(RecordProvider provider) {
		ServletRequest request = pageContext.getRequest();
		if (provider.getId().indexOf("_dd_") == -1) {
			TagService.getInstance().queryForObject(memRecordSet, provider, request);
		} else {
			TagService.getInstance().query(memRecordSet, provider, request);
		}
	}
}
