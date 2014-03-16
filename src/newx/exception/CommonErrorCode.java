package newx.exception;

public enum CommonErrorCode {
	
	CONTEXTINITIALIZED_EVENT_ERROR("ContextInitialized事件出错了"),
	BEAN_NOT_FOUND("找不到bean"),
	BEAN_TYPE_ERROR("bean类型有误"),
	ACTION_NAME_NOT_FOUND("找不到Action名字"),
	PARSE_XML_ERROR("读XML文件出错了"),
	EXECUTE_ACTION_ERROR("执行action出错了"),
	SINGLE_RECORD_ERROR("单记录只允许一条MemRecord"),
	MULTI_RECORD_ERROR("多记录表格只允许一个数据源"),
	;
	
	private final String msg;
	
	private CommonErrorCode(String msg) {
		this.msg = msg;
	}
	
	public String getMsg() {
		return msg == null ? this.name() : msg;
	}

	public String getDetail() {
		return this.name() + (msg == null ? "" : ":" + msg); 
	}
	
	public String toString() {
		return getMsg();
	}
}
