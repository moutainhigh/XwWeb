package app.util.ruleFiter.entity;

import java.util.Map;

public class ProcessedData {
	private String ruleId;
	/**
	 * 经过处理加工后的键值集合
	 * key：字段名，需要与校验中的字段名相同
	 * value:该字段名下的值
	 */
	private Map<String,String> processedDataMap;
//	private List<Map<String,String>> allDataMapList;
	public String getRuleId() {
		return ruleId;
	}
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}
	public Map<String, String> getProcessedDataMap() {
		return processedDataMap;
	}
	public void setProcessedDataMap(Map<String, String> processedDataMap) {
		this.processedDataMap = processedDataMap;
	}
//	public List<Map<String, String>> getAllDataMapList() {
//		return allDataMapList;
//	}
//	public void setAllDataMapList(List<Map<String, String>> allDataMapList) {
//		this.allDataMapList = allDataMapList;
//	}
	
}
