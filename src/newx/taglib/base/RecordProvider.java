package newx.taglib.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import newx.taglib.ParamTag;
import newx.util.StringUtil;

import org.apache.log4j.Logger;

public class RecordProvider {
	
	private static final Logger log = Logger.getLogger(RecordProvider.class);
	
	private String id = "";
	
	private String outParam = "";
	
	private String sql = "";
	
	private List<PrividerParam> parseParam = new ArrayList<PrividerParam>();
	
	private Map<String, PrividerParam> paramMap = new HashMap<String, PrividerParam>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOutParam() {
		return outParam;
	}

	public void setOutParam(String outParam) {
		this.outParam = outParam;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}
	
	public void addPram(PrividerParam param) {
		paramMap.put(param.getName(), param);
	}
	
	public List<PrividerParam> getParseParam() {
		return parseParam;
	}

	public Map<String, PrividerParam> getParamMap() {
		return paramMap;
	}

	public void parse() {
		String regExAll = "(\\$\\{\\s*(\\w+)\\s*\\})|(\\$\\(\\s*(\\w+)\\s*\\))|(\\$\\[\\s*(\\w+)\\s*\\])|(:(\\w+))";
		String regEx = "(\\$\\{\\s*(\\w+)\\s*\\})|(\\$\\(\\s*(\\w+)\\s*\\))|(\\$\\[\\s*(\\w+)\\s*\\])";
		Pattern p = Pattern.compile(regExAll);
		Matcher m = p.matcher(sql);
		while (m.find()) {
			if (m.group(1) != null) {
				PrividerParam pram = new PrividerParam();
				pram.setName(m.group(2));
				pram.setType(ParamTag.DOUBLE);
				parseParam.add(pram);
				sql = sql.replaceFirst(regEx, ":" + pram.getName());
			} else if (m.group(3) != null) {
				PrividerParam pram = new PrividerParam();
				pram.setName(m.group(4));
				pram.setType(ParamTag.STRING);
				parseParam.add(pram);
				sql = sql.replaceFirst(regEx, ":" + pram.getName());
			} else if (m.group(5) != null) {
				PrividerParam pram = new PrividerParam();
				pram.setName(m.group(6));
				pram.setType(ParamTag.INT);
				parseParam.add(pram);
				sql = sql.replaceFirst(regEx, ":" + pram.getName());
			} else if (m.group(7) != null) {
				PrividerParam pram = new PrividerParam();
				pram.setName(m.group(8));
				pram.setType(ParamTag.STRING);
				parseParam.add(pram);
			}
		}
	}
}
