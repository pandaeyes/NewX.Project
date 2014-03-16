package newx.taglib.base;

import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import newx.framework.IModule;
import newx.taglib.ParamTag;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

public class TagService implements IModule {

	private static final Logger log = Logger.getLogger(TagService.class);
	
	private static TagService instance = null;
	
	@Autowired
	private TagDao tagDao;
	
	private TagService() {}
	
	public static TagService getInstance() {
		if (instance == null) {
			instance = new TagService();
		}
		return instance;
	}
	
	public void activate() {
//		String sql = "select name, ctime from log_activity where name = :name";
//		MapSqlParameterSource paramMap = new MapSqlParameterSource();
//		paramMap.addValue("name", "0");
//		List<Map> list = tagDao.query(sql, new FieldColumnMapRowMapper(), paramMap);
//		for (Map m : list) {
//			System.out.print("=====:" + m.get("name"));
//			System.out.println("=====:" + m.get("ctime"));
//		}
	}
	
	public void queryForObject(MemRecordSet recordSet, RecordProvider provider, ServletRequest request) {
		String sql = provider.getSql();
		MapSqlParameterSource paramMap = getParamMap(request, provider);
		Map data = tagDao.queryForMap(sql, paramMap);
		recordSet.populate(provider.getId(), data);
	}
	
	public void query(MemRecordSet recordSet, RecordProvider provider, ServletRequest request) {
		String sql = provider.getSql();
		MapSqlParameterSource paramMap = getParamMap(request, provider);
		List<Map<String, Object>> list = tagDao.queryForList(sql, paramMap);
		recordSet.populate(provider.getId(), list);
	}
	
	public void queryLimit(MemRecordSet recordSet, RecordProvider provider, ServletRequest request, int offset, int limit) {
		String sql = provider.getSql();
		StringBuffer buf = new StringBuffer(sql.length()+20).append(sql);
		if (offset>0) {
			buf.append(" limit ")
				.append(offset)
				.append(", ")
				.append(limit);
		}
		else {
			buf.append(" limit ").append(limit);
		}
		sql = buf.toString();
		MapSqlParameterSource paramMap = getParamMap(request, provider);
		List<Map<String, Object>> list = tagDao.queryForList(sql, paramMap);
		recordSet.populate(provider.getId(), list);
	}
	
	public int getResultSetCount(RecordProvider provider, ServletRequest request) {
		String sql = provider.getSql();
		String countSQL = "select count(*) from (" + sql + ") _ct";
		MapSqlParameterSource paramMap = getParamMap(request, provider);
		Map data = tagDao.queryForMap(countSQL, paramMap);
		for (Object obj : data.values()) {
			return Integer.parseInt(obj.toString());
		}
		return 0;
	}
	
	private MapSqlParameterSource getParamMap(ServletRequest request, RecordProvider provider) {
		Map<String, PrividerParam> paramMap = provider.getParamMap();
		List<PrividerParam> parseParam = provider.getParseParam();
		MapSqlParameterSource sqlParam = new MapSqlParameterSource();
		Enumeration enumer = request.getParameterNames();
		Object paramName = null;
		for (PrividerParam pp : paramMap.values()) {
			sqlParam.addValue(pp.getName(), pp.getValue());
		}
		while (enumer.hasMoreElements()) {
			paramName = enumer.nextElement();
			if (paramMap.get(paramName) == null) {
				for (PrividerParam pParam : parseParam) {
					if (pParam.getName().equals(paramName) && request.getParameter(paramName.toString()) != null) {
						if (pParam.getType().equals(ParamTag.INT)) {
							sqlParam.addValue(paramName.toString(), Integer.parseInt(request.getParameter(paramName.toString())));
						} else if (pParam.getType().equals(ParamTag.DOUBLE)) {
							sqlParam.addValue(paramName.toString(), Double.parseDouble(request.getParameter(paramName.toString())));
						} else {
							sqlParam.addValue(paramName.toString(), request.getParameter(paramName.toString()));
						}
						break;
						
					}
				}
			}
		}
		return sqlParam;
	}
	
	private boolean findPrividerParam(Object name, List<PrividerParam> paramList) {
		for (PrividerParam p : paramList) {
			if (name.equals(p.getName())) {
				return true;
			}
		}
		return false;
	}
}
