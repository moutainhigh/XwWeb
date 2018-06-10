package accounting.domain.loan;
/**
* Title: LnBatch.java
* Description:
* @作者 su
* @日期 2018-3-20
* @version：1.0
**/
public class LnBatch extends accounting.domain.sys.CMISDomain {
	private String brNo;//合作机构号码
	private String batchNo;//批次编号
	private String batchDate;//批次接收日期
	private String batchTime;//批次接收时间
	private String batchType;//进件类型[01进件上传02人工导入]
	private String batchFile;//进件文件名称
	private String batchErrfile;//进件错误文件名称
	private Integer batchNum;//进件笔数
	private Integer dbNum;//入库笔数
	private Integer chkNum;//筛查笔数
	private Integer chkNumOk;//筛查通过笔数
	private Integer autoNum;//自动审批笔数
	private Integer appNum;//人工审批笔数
	private Integer appNumOk;//人工审批通过笔数
	private Integer lnNum;//放款申请笔数
	private Integer lnNumFz;//放款待分账笔数
	private Integer lnNumOk;//放款成功笔数
	private String batchSts;//批次处理状态[01未处理02处理中03处理完成]
	private String upTime;//最后更新时间

	private Double appAmt;
	private Double dueAmt;
	
	/**
	 * @return 合作机构号码
	 */
	public String getBrNo() {
	 	return brNo;
	}
	/**
	 * @设置 合作机构号码
	 * @param brNo
	 */
	public void setBrNo(String brNo) {
	 	this.brNo = brNo;
	}
	/**
	 * @return 批次编号
	 */
	public String getBatchNo() {
	 	return batchNo;
	}
	/**
	 * @设置 批次编号
	 * @param batchNo
	 */
	public void setBatchNo(String batchNo) {
	 	this.batchNo = batchNo;
	}
	/**
	 * @return 批次接收日期
	 */
	public String getBatchDate() {
	 	return batchDate;
	}
	/**
	 * @设置 批次接收日期
	 * @param batchDate
	 */
	public void setBatchDate(String batchDate) {
	 	this.batchDate = batchDate;
	}
	/**
	 * @return 批次接收时间
	 */
	public String getBatchTime() {
	 	return batchTime;
	}
	/**
	 * @设置 批次接收时间
	 * @param batchTime
	 */
	public void setBatchTime(String batchTime) {
	 	this.batchTime = batchTime;
	}
	/**
	 * @return 进件类型[01进件上传02人工导入]
	 */
	public String getBatchType() {
	 	return batchType;
	}
	/**
	 * @设置 进件类型[01进件上传02人工导入]
	 * @param batchType
	 */
	public void setBatchType(String batchType) {
	 	this.batchType = batchType;
	}
	/**
	 * @return 进件文件名称
	 */
	public String getBatchFile() {
	 	return batchFile;
	}
	/**
	 * @设置 进件文件名称
	 * @param batchFile
	 */
	public void setBatchFile(String batchFile) {
	 	this.batchFile = batchFile;
	}
	/**
	 * @return 进件错误文件名称
	 */
	public String getBatchErrfile() {
	 	return batchErrfile;
	}
	/**
	 * @设置 进件错误文件名称
	 * @param batchErrfile
	 */
	public void setBatchErrfile(String batchErrfile) {
	 	this.batchErrfile = batchErrfile;
	}
	/**
	 * @return 进件笔数
	 */
	public Integer getBatchNum() {
	 	return batchNum;
	}
	/**
	 * @设置 进件笔数
	 * @param batchNum
	 */
	public void setBatchNum(Integer batchNum) {
	 	this.batchNum = batchNum;
	}
	/**
	 * @return 入库笔数
	 */
	public Integer getDbNum() {
	 	return dbNum;
	}
	/**
	 * @设置 入库笔数
	 * @param dbNum
	 */
	public void setDbNum(Integer dbNum) {
	 	this.dbNum = dbNum;
	}
	/**
	 * @return 筛查笔数
	 */
	public Integer getChkNum() {
	 	return chkNum;
	}
	/**
	 * @设置 筛查笔数
	 * @param chkNum
	 */
	public void setChkNum(Integer chkNum) {
	 	this.chkNum = chkNum;
	}
	/**
	 * @return 筛查通过笔数
	 */
	public Integer getChkNumOk() {
	 	return chkNumOk;
	}
	/**
	 * @设置 筛查通过笔数
	 * @param chkNumOk
	 */
	public void setChkNumOk(Integer chkNumOk) {
	 	this.chkNumOk = chkNumOk;
	}
	/**
	 * @return 自动审批笔数
	 */
	public Integer getAutoNum() {
	 	return autoNum;
	}
	/**
	 * @设置 自动审批笔数
	 * @param autoNum
	 */
	public void setAutoNum(Integer autoNum) {
	 	this.autoNum = autoNum;
	}
	/**
	 * @return 人工审批笔数
	 */
	public Integer getAppNum() {
	 	return appNum;
	}
	/**
	 * @设置 人工审批笔数
	 * @param appNum
	 */
	public void setAppNum(Integer appNum) {
	 	this.appNum = appNum;
	}
	/**
	 * @return 人工审批通过笔数
	 */
	public Integer getAppNumOk() {
	 	return appNumOk;
	}
	/**
	 * @设置 人工审批通过笔数
	 * @param appNumOk
	 */
	public void setAppNumOk(Integer appNumOk) {
	 	this.appNumOk = appNumOk;
	}
	/**
	 * @return 放款申请笔数
	 */
	public Integer getLnNum() {
	 	return lnNum;
	}
	/**
	 * @设置 放款申请笔数
	 * @param lnNum
	 */
	public void setLnNum(Integer lnNum) {
	 	this.lnNum = lnNum;
	}
	/**
	 * @return 放款待分账笔数
	 */
	public Integer getLnNumFz() {
	 	return lnNumFz;
	}
	/**
	 * @设置 放款待分账笔数
	 * @param lnNumFz
	 */
	public void setLnNumFz(Integer lnNumFz) {
	 	this.lnNumFz = lnNumFz;
	}
	/**
	 * @return 放款成功笔数
	 */
	public Integer getLnNumOk() {
	 	return lnNumOk;
	}
	/**
	 * @设置 放款成功笔数
	 * @param lnNumOk
	 */
	public void setLnNumOk(Integer lnNumOk) {
	 	this.lnNumOk = lnNumOk;
	}
	/**
	 * @return 批次处理状态[01未处理02处理中03处理完成]
	 */
	public String getBatchSts() {
	 	return batchSts;
	}
	/**
	 * @设置 批次处理状态[01未处理02处理中03处理完成]
	 * @param batchSts
	 */
	public void setBatchSts(String batchSts) {
	 	this.batchSts = batchSts;
	}
	/**
	 * @return 最后更新时间
	 */
	public String getUpTime() {
	 	return upTime;
	}
	/**
	 * @设置 最后更新时间
	 * @param upTime
	 */
	public void setUpTime(String upTime) {
	 	this.upTime = upTime;
	}
	/**
	 * @return the appAmt
	 */
	public Double getAppAmt() {
		return appAmt;
	}
	/**
	 * @param appAmt the appAmt to set
	 */
	public void setAppAmt(Double appAmt) {
		this.appAmt = appAmt;
	}
	/**
	 * @return the dueAmt
	 */
	public Double getDueAmt() {
		return dueAmt;
	}
	/**
	 * @param dueAmt the dueAmt to set
	 */
	public void setDueAmt(Double dueAmt) {
		this.dueAmt = dueAmt;
	}
}