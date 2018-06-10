package app.creditapp.inf.entity;

/**
 * @作者 DHCC-LIU
 * @日期 Jun 23, 2016
 * @描述   预审批信息查询--输入实体类
 */
public class WsIn2006 {
	
	private String brNo; // 合作机构编号
	private String prePactNo; // 预审批 ID
	private String batNo; //批次号
	private int    pageNo; //页码
	private int    pageSize;//每页条数
	
	public String getBrNo() {
		return brNo;
	}
	public void setBrNo(String brNo) {
		this.brNo = brNo;
	}
	public String getPrePactNo() {
		return prePactNo;
	}
	public void setPrePactNo(String prePactNo) {
		this.prePactNo = prePactNo;
	}
	public String getBatNo() {
		return batNo;
	}
	public void setBatNo(String batNo) {
		this.batNo = batNo;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
