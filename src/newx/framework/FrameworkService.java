package newx.framework;

import java.io.InputStream;

import newx.exception.CommonErrorCode;
import newx.exception.NewXException;
import newx.listener.NewXContextLoaderListener;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;


public class FrameworkService implements IModule {
	
	private static FrameworkService service = null;
	
	private boolean debug = false;
	private String title = "Demo";
	
	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	private FrameworkService() {}
	
	public synchronized static FrameworkService getInstance() {
		if (service == null) {
			service = new FrameworkService();
		}
		return service;
	}
	
	public void activate() {
		InputStream is = NewXContextLoaderListener.getContext().getResourceAsStream("/WEB-INF/sysconfig.xml");
		SAXBuilder builder = new SAXBuilder();
		Document doc = null;
		try {
			doc = builder.build(is);
			Element elem = doc.getRootElement().getChild("debug");
			String debug = elem.getText();
			if ("true".equalsIgnoreCase(debug)) {
				setDebug(true);
			}
			elem = doc.getRootElement().getChild("systitle");
			setTitle(elem.getText());
			is.close();
		} catch (Exception e) {
			throw new NewXException(CommonErrorCode.PARSE_XML_ERROR, e).setDetail("sysconfig.xml");
		}
	}
}
