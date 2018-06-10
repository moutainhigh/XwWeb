package app.creditapp.inf.entity;

/**
 * @作者 DHCC-LIU
 * @日期 Jun 23, 2016
 * @描述  欠款信息查询[3203] 输入信息
 */
public class WsIn3203 {
	
	private String brNo;//合作机构号
	private String pactNo;//合同号
	private String cnt;//期次号
	private int    pageNo;//页码
	private int    pageSize;//每页条数


	public String getBrNo() {
		return brNo;
	}
	public void setBrNo(String brNo) {
		this.brNo = brNo;
	}
	public String getPactNo() {
		return pactNo;
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
	public void setPactNo(String pactNo) {
		this.pactNo = pactNo;
	}
	public String getCnt() {
		return cnt;
	}
	public void setCnt(String cnt) {
		this.cnt = cnt;
	}
	

}
