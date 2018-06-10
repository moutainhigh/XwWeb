package app.creditapp.ln.entity;
import app.base.BaseDomain;
/**
* Title: PreBatch.java
* Description:
* @version：1.0
**/
public class PreBatch extends BaseDomain {
	private String brNo;//合作机构号码
	private String brName;//合作机构号码
	private String batchNo;//批次编号
	private String batchTime;//批次接收时间
	private String batchType;//进件类型[01进件上传02人工导入]
	private String batchFile;//批次文件
	private String batchErrfile;//错误文件
	private Integer batchNum;//进件笔数
	private Integer dbNum;//入库笔数
	private Integer chkNum;//筛查笔数
	private Integer chkNumOk;//筛查通过笔数
	private String batchSts;//批次处理状态[01未处理02处理中03处理完成]
	private String upTime;//最后更新时间
	private String batchDate;//批次接收日期
	private String loginid;//登录操作员
	
	public String getLoginid() {
		return loginid;
	}

	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}

	public PreBatch(){
		batchNum=0;
		dbNum=0;
		chkNumOk=0;
		chkNum=0;
	}
	
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
	public String getBrName() {
		return brName;
	}
	public void setBrName(String brName) {
		this.brName = brName;
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
	 * @return 批次文件
	 */
	public String getBatchFile() {
	 	return batchFile;
	}
	/**
	 * @设置 批次文件
	 * @param batchFile
	 */
	public void setBatchFile(String batchFile) {
	 	this.batchFile = batchFile;
	}
	/**
	 * @return 错误文件
	 */
	public String getBatchErrfile() {
	 	return batchErrfile;
	}
	/**
	 * @设置 错误文件
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
}