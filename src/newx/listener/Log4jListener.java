package newx.listener;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log4jListener implements ServletContextListener {
	
	public void contextInitialized(ServletContextEvent evt) {
		ServletContext context = evt.getServletContext(); 
		try {
			String path = evt.getServletContext().getInitParameter("log4jConfigPath");
			InputStream is = context.getResourceAsStream(path);
			Properties p = new Properties();
			p.load(is);
			PropertyConfigurator.configure(p);
			Logger.getLogger(this.getClass()).info("log4j Config success");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void contextDestroyed(ServletContextEvent evt) {
	}
}
