package newx.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import newx.framework.FrameworkService;

import org.jdom.Element;
import org.springframework.jdbc.datasource.DataSourceUtils;

public class SysUtil {

	public static Connection getConnection() {
		return DataSourceUtils.getConnection((DataSource)BeanFactory.getBean("dataSource"));
	}
	
	public static Connection getConnection(String dataSource) {
		return DataSourceUtils.getConnection((DataSource)BeanFactory.getBean(dataSource));
	}
	
	public static void release(ResultSet rs, Statement stmt, Connection conn) throws SQLException {
        if (rs != null) {
            rs.close();
            rs = null;
        }
        if (stmt != null) {
            stmt.close();
            stmt = null;
        }
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
        return;
    }
	
	public static String getAttributeValue(Element element, String attrName, String defaultVal) {
		String val = element.getAttributeValue(attrName);
		if (val != null) {
			return val.trim();
		} else {
			return defaultVal;
		}
	}
	
	public static String getTitle() {
		return FrameworkService.getInstance().getTitle();
	}
}
