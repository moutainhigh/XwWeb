package app.creditapp.inf.entity;


/**
 * @作者 DHCC-LIU
 * @日期 Jun 23, 2016
 * @描述   扣款结果查询[2302]输入信息
 */
public class WsIn2302 {
	
	private String brNo;//合作机构号
	private String batNo;//批次编号
	private String pactNo;//合同号
	private int    pageNo;//页码
	private int    pageSize;//每页条数
	public String getBrNo() {
		return brNo;
	}
	public void setBrNo(String brNo) {
		this.brNo = brNo;
	}
	public String getBatNo() {
		return batNo;
	}
	public void setBatNo(String batNo) {
		this.batNo = batNo;
	}
	public String getPactNo() {
		return pactNo;
	}
	public void setPactNo(String pactNo) {
		this.pactNo = pactNo;
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
