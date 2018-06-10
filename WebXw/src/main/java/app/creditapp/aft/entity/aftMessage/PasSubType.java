package app.creditapp.aft.entity.aftMessage;

import java.util.List;

public class PasSubType {
	private List<String> criteriaLists;
	private List<String> customQuery;
	private Integer endNum;
	private String keyName;
	private String optCode;
	private String optName;
	private Integer seqn;
	private Integer startNumAndEndNum;
	private Integer startNum;
	private String sts;
	public List<String> getCriteriaLists() {
		return criteriaLists;
	}
	public void setCriteriaLists(List<String> criteriaLists) {
		this.criteriaLists = criteriaLists;
	}
	public List<String> getCustomQuery() {
		return customQuery;
	}
	public void setCustomQuery(List<String> customQuery) {
		this.customQuery = customQuery;
	}
	public Integer getEndNum() {
		return endNum;
	}
	public void setEndNum(Integer endNum) {
		this.endNum = endNum;
	}
	public String getKeyName() {
		return keyName;
	}
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
	public String getOptCode() {
		return optCode;
	}
	public void setOptCode(String optCode) {
		this.optCode = optCode;
	}
	public String getOptName() {
		return optName;
	}
	public void setOptName(String optName) {
		this.optName = optName;
	}
	public Integer getSeqn() {
		return seqn;
	}
	public void setSeqn(Integer seqn) {
		this.seqn = seqn;
	}
	public Integer getStartNumAndEndNum() {
		return startNumAndEndNum;
	}
	public void setStartNumAndEndNum(Integer startNumAndEndNum) {
		this.startNumAndEndNum = startNumAndEndNum;
	}
	public Integer getStartNum() {
		return startNum;
	}
	public void setStartNum(Integer startNum) {
		this.startNum = startNum;
	}
	public String getSts() {
		return sts;
	}
	public void setSts(String sts) {
		this.sts = sts;
	}
	
}
