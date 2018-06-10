package app.creditapp.inf.entity;

/**
 * @作者 DHCC-ZKX
 * @日期 July 18, 2016
 * @描述   还款计划单笔查询--输入实体类
 */
public class WsIn2202 {
	private String brNo;//合作机构号
	private String pactNo;//合同号
	
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
	
}
