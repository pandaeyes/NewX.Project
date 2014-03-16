package newx.taglib.base;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import newx.repository.AbstractDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class TagDao extends AbstractDao {

	@Autowired
	private TagDao(@Qualifier("dataSource") DataSource dataSource){
		super(dataSource);
	}
	
	public Map<String, Object> queryForMap(String sql, SqlParameterSource args) {
		try {
			return super.queryForMap(sql, args);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public List<Map<String, Object>> queryForList(String sql, SqlParameterSource args) {
		try {
			return super.queryForList(sql, args);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
}
