package newx.listener;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import newx.annotation.ContextInitialized;
import newx.exception.CommonErrorCode;
import newx.exception.NewXException;
import newx.framework.IListenerBean;

public class BeanCustomEventListtener implements ServletContextListener {
	
	public void contextInitialized(ServletContextEvent evt) {
		Map map = NewXContextLoaderListener.getSpringContext().getBeansOfType(IListenerBean.class);
		if (map != null) {
			for (Iterator it = map.values().iterator(); it.hasNext();) {
				Object bean = it.next();
				Class clz = bean.getClass();
				for (Method method : clz.getDeclaredMethods()) {
					if (method.getAnnotation(ContextInitialized.class) != null) {
						try {
							method.invoke(bean, new Object[]{});
						} catch (Exception e) {
							throw new NewXException(CommonErrorCode.CONTEXTINITIALIZED_EVENT_ERROR, e);
						}
					}
				}
			}
		}
	}

	public void contextDestroyed(ServletContextEvent evt) {
	}
}
