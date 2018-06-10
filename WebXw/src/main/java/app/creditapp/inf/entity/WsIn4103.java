package app.creditapp.inf.entity;

/**
 * @作者 DHCC-SONG
 * @日期 Jun 28, 2016
 * @描述 查征实体类
 */
public class WsIn4103 {
	private String batchNo;//批次号码
//	private String pactNo;//合同号码
	private String brNo;//合作机构号码
//	private String appDate;//进件日期
//	private String cifName;//客户名称
//	private String idType;//证件类型
	private String idNo;//证件号码
//	private String url; // 征信报告批次文件路径
//	private String chkDesc; // 校验结果
	private int pageNo;
	private int pageSize;
	
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public String getBrNo() {
		return brNo;
	}
	public void setBrNo(String brNo) {
		this.brNo = brNo;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
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
