package newx.taglib;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import newx.exception.CommonErrorCode;
import newx.exception.NewXException;
import newx.taglib.base.IRecordSetOwner;
import newx.taglib.base.MemRecordSet;
import newx.taglib.base.RecordProvider;
import newx.taglib.base.TagService;

public class MultiRecordSetTag extends BodyTagSupport implements IRecordSetOwner{

	private MemRecordSet memRecordSet = null;
	
	private String id = null;
	
	private boolean execute = false;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int doStartTag() throws JspException {
		memRecordSet = new MemRecordSet();
		execute = false;
		return super.doStartTag();
	}
	
	public int doAfterBody() throws JspException {
		return SKIP_BODY;
	}
	
	public int doEndTag() throws JspException {
		pageContext.setAttribute(id, memRecordSet);
		return EVAL_PAGE;
	}
	
	public void execute(RecordProvider provider) {
		if (execute) {
			throw new NewXException(CommonErrorCode.MULTI_RECORD_ERROR);
		}
		ServletRequest request = pageContext.getRequest();
		TagService.getInstance().query(memRecordSet, provider, request);
		execute = true;
	}
}
