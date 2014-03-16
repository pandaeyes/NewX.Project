package newx.taglib.base;

import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;

/**
 * MemRecordSet中使用的记录对象
 * @author huang
 */
public class MemRecord {

	private ListOrderedMap values = null;
	
	public MemRecord() {
		values = new ListOrderedMap();
	}
	
	public Object field(int index) {
		Object value = values.getValue(index);
		if (value == null) {
			return null;
		} else {
			return value;
		}
	}
	
	public Object field(String fieldName) {
		Object value = values.get(fieldName);
		if (value == null) {
			return null;
		} else {
			return value;
		}
	}
	
	public Object put(String fieldName, Object fieldValue) {
		return values.put(fieldName, fieldValue);
	}
	
	public int getFieldCount() {
		return values.size();
	}
	
	public int getFieldIndex(String fieldName) {
		return values.indexOf(fieldName);
	}
	
	public List getFieldNames() {
		return values.keyList();
	}
}
