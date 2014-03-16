package newx.exception;

import java.io.PrintStream;

import org.apache.commons.io.output.ByteArrayOutputStream;

public class NewXException extends RuntimeException implements java.io.Serializable {
	
	private Enum errorCode;
	private String detail;
	private Throwable t;
	
	public NewXException(Enum errorCode) {
		super(errorCode.toString());
		this.errorCode = errorCode;
	}
	
	public NewXException(Enum errorCode, Throwable t) {
		// 如果传入的t已经是NewXException，则所有的errorCode都以这个为准，忽略传入的errorCode
		super(getErrorCode(errorCode, t).toString(), getCause(t));
		if (t instanceof NewXException) {
			this.errorCode = ((NewXException) t).getErrorCode();
			this.t = ((NewXException) t).getCause();
			this.detail = ((NewXException) t).detail;
			if (this.t == null)
				this.t = t;
		} else {
			this.errorCode = errorCode;
			this.t = t;
		}
		if (this.t != null) {
			StackTraceElement[] newTrace = new StackTraceElement[3];
			newTrace[0] = this.getStackTrace()[0];
			newTrace[1] = new StackTraceElement(".", ".", "...", -1);
			for (StackTraceElement item : this.t.getStackTrace()) {
				if (item.getClassName().startsWith("newx")) {
					newTrace[2] = item;
					break;
				}
			}
			if (newTrace[2] != null)
				this.setStackTrace(newTrace);
		}
	}
	
	private static Enum getErrorCode(Enum errorCode, Throwable t) {
		Enum code = null;
		if (t instanceof NewXException)
			code = ((NewXException) t).getErrorCode();
		else
			code = errorCode;
		return code;
	}

	private static Throwable getCause(Throwable t) {
		return (t instanceof NewXException) ? t.getCause() : t;
	}
	
	public NewXException setDetail(String detail) {
		this.detail = detail;
		return this;
	}

	public String getDetail() {
		return detail;
	}

	public Enum getErrorCode() {
		return errorCode;
	}

	public String getStackTraceInfo() {
		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(bao);
		printStackTrace(ps);
		ps.close();
		return bao.toString();
	}

	public String getMsg() {
		return errorCode.toString();
	}

	public String toString() {
		String msg = getMsg();
		if (detail != null)
			msg += ":" + detail;
		return msg;
	}
}
