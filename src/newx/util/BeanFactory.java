package newx.util;

import java.util.Map;

import newx.exception.CommonErrorCode;
import newx.exception.NewXException;
import newx.listener.NewXContextLoaderListener;

public class BeanFactory {
	
	public static Object getBean(String Name) {
		return NewXContextLoaderListener.getSpringContext().getBean(Name);
	}
	
	public static <T> T getBean(String Name, Class<T> type){
		Object bean = NewXContextLoaderListener.getSpringContext().getBean(Name, type);
		return (T)bean;
	}
	
	public static <T> T getBean(Class<T> type){
		Map map = NewXContextLoaderListener.getSpringContext().getBeansOfType(type);
		if (map.size() == 1) {
			return (T)map.values().iterator().next();
		} else {
			throw new NewXException(CommonErrorCode.BEAN_TYPE_ERROR).setDetail(type.toString());
		}
	}
	
	public static <T> Map<String, T> getBeans(Class<T> type){
		Map<String, T> map = NewXContextLoaderListener.getSpringContext().getBeansOfType(type);
		if (map.size() != 0) {
			return map;
		} else {
			throw new NewXException(CommonErrorCode.BEAN_NOT_FOUND).setDetail(type.toString());
		}
	}
}
