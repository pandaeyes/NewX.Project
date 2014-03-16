//package newx.taglib.base;
//
//import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
//import java.sql.SQLException;
//import java.util.Map;
//
//import org.springframework.jdbc.core.ColumnMapRowMapper;
//import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
//import org.springframework.jdbc.support.JdbcUtils;
//
///**
// * 结果返回FieldValue值
// * @author huang
// */
//public class FieldColumnMapRowMapper extends ColumnMapRowMapper implements ParameterizedRowMapper<Map> {
//
//	public Map mapRow(ResultSet rs, int rowNum) throws SQLException {
//		ResultSetMetaData rsmd = rs.getMetaData();
//		int columnCount = rsmd.getColumnCount();
//		Map mapOfColValues = createColumnMap(columnCount);
//		for (int i = 1; i <= columnCount; ++i) {
//			String key = getColumnKey(JdbcUtils.lookupColumnName(rsmd, i));
//			Object obj = getColumnValue(rs, i);
////			int type = rsmd.getColumnType(i);
////			mapOfColValues.put(key, new FieldValue(obj, type));
//			mapOfColValues.put(key, obj);
//		}
//		return mapOfColValues;
//	}
//}
