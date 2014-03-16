package newx.listener;


import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.ContextLoaderListener;

public class NewXContextLoaderListener extends ContextLoaderListener {
	
	private static ServletContext context;
	
	public void contextInitialized(ServletContextEvent event) {
		context = event.getServletContext(); 
		super.contextInitialized(event);
	}

	public void contextDestroyed(ServletContextEvent event) {
		super.contextDestroyed(event);
	}
	
	public static ServletContext getContext() {
		return context;
	}

	public static WebApplicationContext getSpringContext() {
		return WebApplicationContextUtils.getWebApplicationContext(context);
	}
}