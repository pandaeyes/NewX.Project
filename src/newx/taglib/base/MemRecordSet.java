package newx.taglib.base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import newx.exception.CommonErrorCode;
import newx.exception.NewXException;

/**
 * 对象提供了在内存中保存表格型数据的功能
 * @author huang
 */
public class MemRecordSet {

	private ArrayList<MemRecord> records = null;
	private MemRecord curRecord = null;
	
	public MemRecordSet() {
		records = new ArrayList<MemRecord>();
	}
	
	/**
	 * 单记录数据集
	 * @param map
	 */
	public void populate(String dbSrcId, Map<String, Object> map) {
		if (records.size() > 1) {
			throw new NewXException(CommonErrorCode.SINGLE_RECORD_ERROR);
		}
		curRecord = firstRecord();
		if (curRecord == null) {
			curRecord = appendRecord();
		}
		for (Iterator<String> it = map.keySet().iterator(); it.hasNext();) {
			String key = it.next();
			Object fieldValue = map.get(key);
			curRecord.put(key, fieldValue);
		}
	}
	
	/**
	 * 多记录数据集
	 * @param dbSrcId
	 * @param list
	 */
	public void populate(String dbSrcId, List<Map<String, Object>> list) {
		if (records.size() != 0 && dbSrcId.indexOf("_dd_") == -1) {
			throw new NewXException(CommonErrorCode.MULTI_RECORD_ERROR);
		}
		if (dbSrcId.indexOf("_dd_") == -1) {
			for (Map<String, Object> row : list) {
				curRecord = appendRecord();
				for (Iterator<String> it = row.keySet().iterator(); it.hasNext();) {
					String key = it.next();
					Object fieldValue = row.get(key);
					curRecord.put(key, fieldValue);
				}
			}
		} else {
			curRecord = firstRecord();
			if (curRecord == null)
				curRecord = appendRecord();
			curRecord.put(dbSrcId, list);
		}
	}
	
	public Object cell(int colIndex, int rowIndex) {
		MemRecord record = record(rowIndex);
		if (record == null) {
			return null;
		} else {
			return record.field(colIndex);
		}
	}
	
	public Object cell(String fieldName,int rowIndex) {
		MemRecord record = record(rowIndex);
		if (record == null) {
			return null;
		} else {
			return record.field(fieldName);
		}
	}
	
	public Object getValue(String fieldName) {
        try {
        	if (curRecord.field(fieldName) == null) {
        		return null;
        	} else {
        		return curRecord.field(fieldName);
        	}
        } catch(Exception e) {
        	throw new NewXException(CommonErrorCode.SINGLE_RECORD_ERROR, e);
        }
    }
	
	public int getRowCount() {
		return records.size();
	}
	
	public MemRecord record(int index) {
		return records.get(index);
	}

	public MemRecord firstRecord() {
		if (records.size() > 0) {
			curRecord = records.get(0);
			return curRecord;
		} else {
			return null;
		}
	}
	
	public MemRecord appendRecord() {
		MemRecord record = new MemRecord();
		records.add(record);
		return record;
	}
	
	public void clear() {
        records.clear();
        curRecord = null;
    }
}
