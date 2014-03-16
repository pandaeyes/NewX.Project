package newx.mod.menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import newx.repository.AbstractDao;
import newx.util.SysUtil;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RightDao extends AbstractDao {

	private static final Logger log = Logger.getLogger(RightDao.class);
	int j = 1;
	
	@Autowired
	private RightDao(@Qualifier("dataSource") DataSource dataSource){
		super(dataSource);
	}
	
	public ArrayList<Right> getTopRight() {
		List<Map<String, Object>> list = this.queryForList("select id, rightname, url, rightsortno, parentid, memo from sys_right where parentid = '0'", new Object[]{});
		ArrayList<Right> rs = new ArrayList<Right>();
		for (Map<String, Object> map : list) {
			Right right = new Right();
			right.setId(map.get("id").toString());
			right.setRightname(map.get("rightname").toString());
			right.setUrl(map.get("url").toString());
			right.setRightsortno(map.get("rightsortno").toString());
			right.setParentid(map.get("parentid").toString());
			right.setMemo(map.get("memo").toString());
			rs.add(right);
		}
		return rs;
	}
	
	public Right getFirstTopRight() {
		Map<String, Object> map = this.queryForMap("select id, rightname, url, rightsortno, parentid, memo from sys_right where parentid = '0' order by rightsortno limit 1", new Object[]{});
		Right right = new Right();
		if (map != null) {
			right.setId(map.get("id").toString());
			right.setRightname(map.get("rightname").toString());
			right.setUrl(map.get("url").toString());
			right.setRightsortno(map.get("rightsortno").toString());
			right.setParentid(map.get("parentid").toString());
			right.setMemo(map.get("memo").toString());
		}
		return right;
	}
	
	public ArrayList<Right> getAllRight() {
		String sql = "select id, rightname, url, rightsortno, parentid, memo from sys_right order by rightsortno";
		List<Map<String, Object>> list = this.queryForList(sql, new Object[]{});
		ArrayList<Right> rs = new ArrayList<Right>();
		for (Map<String, Object> map : list) {
			Right right = new Right();
			right.setId(map.get("id").toString());
			right.setRightname(map.get("rightname").toString());
			right.setUrl(map.get("url").toString());
			right.setRightsortno(map.get("rightsortno").toString());
			right.setParentid(map.get("parentid").toString());
			right.setMemo(map.get("memo").toString());
			rs.add(right);
		}
		return rs;
	}
	
	public Right getRightById(String rightId) {
		Map<String, Object> map = this.queryForMap("select id, rightname, url, rightsortno, parentid, memo, memo a1, memo a2, memo a4, memo a3 from sys_right where id = ?", new Object[]{rightId});
		if (map != null) {
			Right right = new Right();
			right.setId(map.get("id").toString());
			right.setRightname(map.get("rightname").toString());
			right.setUrl(map.get("url").toString());
			right.setRightsortno(map.get("rightsortno").toString());
			right.setParentid(map.get("parentid").toString());
			right.setMemo(map.get("memo").toString());
			return right;
		} else {
			return null;
		}
	}
	
	public int test() {
//		for (int i = 0; i < 10000; i++) {
//			t();
//		}
		return 1;
//		return this.queryForInt("select count(*) from sys_right", new Object[]{});
	}
	
	public void t() {
		try {
			System.out.println("=========111=========(" + (j++) + ")");
			String sql = "select count(*) from sys_right";
			Connection conn = SysUtil.getConnection();
			System.out.println("=========222=========");
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
//			rs.getMetaData().getColumnType(column)
			SysUtil.release(rs, ps, conn);
			System.out.println("=========333=========");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
