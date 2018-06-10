package app.creditapp.inf.entity;

/**
 * @作者 DHCC-SONG
 * @日期 Jun 28, 2016
 * @描述 查征实体类
 */
public class WsIn4101 {
	private String batchNo;//批次号码
	private String czPactNo;//查证流水号
	private String brNo;//合作机构号码
	private String appDate;//进件日期
	private String cifName;//客户名称
	private String idType;//证件类型
	private String idNo;//证件号码
	private String czAuth;//是否有查征授权
	private String czId;//是否身份证信息
	private String url; // 征信报告批次文件路径
	private String chkDesc; // 校验结果
	
	//新增
	private String crpReason;//查询原因
	
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
	public String getAppDate() {
		return appDate;
	}
	public void setAppDate(String appDate) {
		this.appDate = appDate;
	}
	public String getCifName() {
		return cifName;
	}
	public void setCifName(String cifName) {
		this.cifName = cifName;
	}
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getChkDesc() {
		return chkDesc;
	}
	public void setChkDesc(String chkDesc) {
		this.chkDesc = chkDesc;
	}
	public String getCzAuth() {
		return czAuth;
	}
	public void setCzAuth(String czAuth) {
		this.czAuth = czAuth;
	}
	public String getCzId() {
		return czId;
	}
	public void setCzId(String czId) {
		this.czId = czId;
	}
	public String getCzPactNo() {
		return czPactNo;
	}
	public void setCzPactNo(String czPactNo) {
		this.czPactNo = czPactNo;
	}
	public String getCrpReason() {
		return crpReason;
	}
	public void setCrpReason(String crpReason) {
		this.crpReason = crpReason;
	}

}
