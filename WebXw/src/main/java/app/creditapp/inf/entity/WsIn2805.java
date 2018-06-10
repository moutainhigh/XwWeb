package app.creditapp.inf.entity;


/**
 * @作者 DHCC-WANG
 * @日期 Jul 21, 2016
 * @描述 提前还款结果查询--输入实体类 ln_batch
 */
public class WsIn2805 {
	
	private String brNo; // 合作机构编号
	private String batNo;// 批次号
	private String pactNo;// 合同号
	private int pageNo; // 页码
	private int pageSize; // 每页条数
	/**
	 * @return 合作机构编号
	 */
	public String getBrNo() {
		return brNo;
	}
	/**
	 * @param 合作机构编号
	 */
	public void setBrNo(String brNo) {
		this.brNo = brNo;
	}
	/**
	 * @return 批次号
	 */
	public String getBatNo() {
		return batNo;
	}
	/**
	 * @param 批次号
	 */
	public void setBatNo(String batNo) {
		this.batNo = batNo;
	}
	/**
	 * @return 合同号
	 */
	public String getPactNo() {
		return pactNo;
	}
	/**
	 * @param 合同号
	 */
	public void setPactNo(String pactNo) {
		this.pactNo = pactNo;
	}
	/**
	 * @return 页码
	 */
	public int getPageNo() {
		return pageNo;
	}
	/**
	 * @param 页码
	 */
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	/**
	 * @return 每页条数
	 */
	public int getPageSize() {
		return pageSize;
	}
	/**
	 * @param 每页条数
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
}
