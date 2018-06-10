package app.creditapp.inf.entity;

/**
 * * 
* @Title:WsIn2305.java 
* @Description: A类合作机构扣款查询
* <p>Company: </p> 
 */

public class WsIn2305 {
	
	private String brNo;//合作机构号
	private String pactNo;//合同号
	private int    txDate;//交易日期
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
	public void setPactNo(String pactNo) {
		this.pactNo = pactNo;
	}
	public int getTxDate() {
		return txDate;
	}
	public void setTxDate(int txDate) {
		this.txDate = txDate;
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
