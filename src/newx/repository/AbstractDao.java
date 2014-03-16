package newx.repository;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public abstract class AbstractDao {
	private static final Logger log = Logger.getLogger(AbstractDao.class);
	protected SimpleJdbcTemplate simpleJdbcTemplate;
	protected DataSource dataSource;

	public AbstractDao(DataSource dataSource) {
		this.dataSource = dataSource;
		this.simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}

	public SimpleJdbcTemplate getSimpleJdbcTemplate() {
		return this.simpleJdbcTemplate;
	}

	protected int[] batchUpdate(String sql, List<Object[]> batchArgs,
			int[] argTypes) {
		return this.simpleJdbcTemplate.batchUpdate(sql, batchArgs, argTypes);
	}

	protected int[] batchUpdate(String sql, List<Object[]> batchArgs) {
		return this.simpleJdbcTemplate.batchUpdate(sql, batchArgs);
	}

	protected int[] batchUpdate(String sql, Map<?, ?>[] batchValues) {
		return this.simpleJdbcTemplate.batchUpdate(sql, batchValues);
	}

	protected int[] batchUpdate(String sql, SqlParameterSource[] batchArgs) {
		return this.simpleJdbcTemplate.batchUpdate(sql, batchArgs);
	}

	protected <T> List<T> query(String sql, ParameterizedRowMapper<T> rm,
			Map<?, ?> args) throws DataAccessException {
		return this.simpleJdbcTemplate.query(sql, rm, args);
	}

	protected <T> List<T> query(String sql, ParameterizedRowMapper<T> rm,
			Object[] args) throws DataAccessException {
		return this.simpleJdbcTemplate.query(sql, rm, args);
	}

	protected <T> List<T> query(String sql, ParameterizedRowMapper<T> rm,
			SqlParameterSource args) throws DataAccessException {
		return this.simpleJdbcTemplate.query(sql, rm, args);
	}

	protected int queryForInt(String sql, Map<?, ?> args)
			throws DataAccessException {
		return this.simpleJdbcTemplate.queryForInt(sql, args);
	}

	protected int queryForInt(String sql, Object[] args)
			throws DataAccessException {
		return this.simpleJdbcTemplate.queryForInt(sql, args);
	}

	protected int queryForInt(String sql, SqlParameterSource args)
			throws DataAccessException {
		return this.simpleJdbcTemplate.queryForInt(sql, args);
	}

	protected List<Map<String, Object>> queryForList(String sql, Map<?, ?> args)
			throws DataAccessException {
		try {
			return this.simpleJdbcTemplate.queryForList(sql, args);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	protected List<Map<String, Object>> queryForList(String sql, Object[] args)
			throws DataAccessException {
		try {
			return this.simpleJdbcTemplate.queryForList(sql, args);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	protected List<Map<String, Object>> queryForList(String sql,
			SqlParameterSource args) throws DataAccessException {
		try {
			return this.simpleJdbcTemplate.queryForList(sql, args);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	protected long queryForLong(String sql, Map<?, ?> args)
			throws DataAccessException {
		return this.simpleJdbcTemplate.queryForLong(sql, args);
	}

	protected long queryForLong(String sql, Object[] args)
			throws DataAccessException {
		return this.simpleJdbcTemplate.queryForLong(sql, args);
	}

	protected long queryForLong(String sql, SqlParameterSource args)
			throws DataAccessException {
		return this.simpleJdbcTemplate.queryForLong(sql, args);
	}

	protected Map<String, Object> queryForMap(String sql, Map<?, ?> args)
			throws DataAccessException {
		try {
			return this.simpleJdbcTemplate.queryForMap(sql, args);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	protected Map<String, Object> queryForMap(String sql, Object[] args)
			throws DataAccessException {
		try {
			return this.simpleJdbcTemplate.queryForMap(sql, args);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	protected Map<String, Object> queryForMap(String sql, SqlParameterSource args)
			throws DataAccessException {
		try {
			return this.simpleJdbcTemplate.queryForMap(sql, args);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	protected <T> T queryForObject(String sql, Class<T> requiredType,
			Map<?, ?> args) throws DataAccessException {
		return this.simpleJdbcTemplate.queryForObject(sql, requiredType, args);
	}

	protected <T> T queryForObject(String sql, Class<T> requiredType, Object[] args)
			throws DataAccessException {
		return this.simpleJdbcTemplate.queryForObject(sql, requiredType, args);
	}

	protected <T> T queryForObject(String sql, Class<T> requiredType,
			SqlParameterSource args) throws DataAccessException {
		return this.simpleJdbcTemplate.queryForObject(sql, requiredType, args);
	}

	protected <T> T queryForObject(String sql, ParameterizedRowMapper<T> rm,
			Map<?, ?> args) throws DataAccessException {
		return this.simpleJdbcTemplate.queryForObject(sql, rm, args);
	}

	protected <T> T queryForObject(String sql, ParameterizedRowMapper<T> rm,
			Object[] args) throws DataAccessException {
		return this.simpleJdbcTemplate.queryForObject(sql, rm, args);
	}

	protected <T> T queryForObject(String sql, ParameterizedRowMapper<T> rm,
			SqlParameterSource args) throws DataAccessException {
		return this.simpleJdbcTemplate.queryForObject(sql, rm, args);
	}

	protected int update(String sql, Map<?, ?> args) throws DataAccessException {
		return this.simpleJdbcTemplate.update(sql, args);
	}

	protected int update(String sql, Object[] args) throws DataAccessException {
		return this.simpleJdbcTemplate.update(sql, args);
	}

	protected int update(String sql, SqlParameterSource args)
			throws DataAccessException {
		return this.simpleJdbcTemplate.update(sql, args);
	}

	protected SimpleJdbcCall newProcedureCall(String procedureName) {
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource);
		jdbcCall.withProcedureName(procedureName);
		return jdbcCall;
	}

	protected SimpleJdbcCall newFunctionCall(String functionName) {
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource);
		jdbcCall.withFunctionName(functionName);
		return jdbcCall;
	}
}