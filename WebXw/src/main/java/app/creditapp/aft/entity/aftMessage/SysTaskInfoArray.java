package app.creditapp.aft.entity.aftMessage;

import java.util.List;

public class SysTaskInfoArray {
	private Integer endNum;
	private Integer oldEndNum;
	private Integer pageCounts;
	private Integer pageNo;
	private Integer pageSize;
	private Integer pageSum;
	private boolean hasNext;
	private boolean hasPrevious;
	private List<String> params;
	private String paramsStr;
	private Integer startRow;
	private List<SysTaskInfoResult> result;
	public Integer getEndNum() {
		return endNum;
	}
	public void setEndNum(Integer endNum) {
		this.endNum = endNum;
	}
	public Integer getStartRow() {
		return startRow;
	}
	public void setStartRow(Integer startRow) {
		this.startRow = startRow;
	}
	public Integer getOldEndNum() {
		return oldEndNum;
	}
	public void setOldEndNum(Integer oldEndNum) {
		this.oldEndNum = oldEndNum;
	}
	public Integer getPageCounts() {
		return pageCounts;
	}
	public void setPageCounts(Integer pageCounts) {
		this.pageCounts = pageCounts;
	}
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageSum() {
		return pageSum;
	}
	public void setPageSum(Integer pageSum) {
		this.pageSum = pageSum;
	}
	public boolean isHasNext() {
		return hasNext;
	}
	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}
	public boolean isHasPrevious() {
		return hasPrevious;
	}
	public void setHasPrevious(boolean hasPrevious) {
		this.hasPrevious = hasPrevious;
	}
	public List<String> getParams() {
		return params;
	}
	public void setParams(List<String> params) {
		this.params = params;
	}
	public String getParamsStr() {
		return paramsStr;
	}
	public void setParamsStr(String paramsStr) {
		this.paramsStr = paramsStr;
	}
	public List<SysTaskInfoResult> getResult() {
		return result;
	}
	public void setResult(List<SysTaskInfoResult> result) {
		this.result = result;
	}
	
}
