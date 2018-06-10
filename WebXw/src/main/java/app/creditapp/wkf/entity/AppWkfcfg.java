package app.creditapp.wkf.entity;
import app.base.BaseDomain;
/**
* Title: AppWkfcfg.java
* Description:
* @version：1.0
**/
public class AppWkfcfg extends BaseDomain {
	private String id;//
	private String brNo;//机构号
	private String appType;//审批类型 1贷款 2评级 3授信 4五级 5合作方
//	private String wkfNo;//流程编号
	//private String wkfName;//流程名称
	private String prdtNo;//产品号
	private String brName;//机构名称
	private String prdtName;
//	private String wkfDisc;
	private String txBrno;//登记机构
	private String txDate;//登记日期
	private String txUserId;//登记人
	private String txModifyDate;//修改日期
	private String txBrnoName;
	
	/**workflow property**/
	private String processKey;//流程标示
	private String processId;//流程dbid
	private String processDesc;//流程描述
	private String remark;//流程说明
	private String processDefId;//流程定义编号
	private String processSts;//流程状态0-启用1-未启用
	private String beiZhu;//备注
	private String checkManager;//复核管理员
	private String managerPass;//复核密码
	
	private String bs_no;//查询条件-开办机构
	
	
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPrdtName() {
		return prdtName;
	}
	public String getTxBrnoName() {
		return txBrnoName;
	}
	public void setTxBrnoName(String txBrnoName) {
		this.txBrnoName = txBrnoName;
	}
	public void setPrdtName(String prdtName) {
		this.prdtName = prdtName;
	}
	public String getProcessId() {
		return processId;
	}
	public void setProcessId(String processId) {
		this.processId = processId;
	}
	public String getBrName() {
		return brName;
	}
	public void setBrName(String brName) {
		this.brName = brName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPrdtNo() {
		return prdtNo;
	}
	public void setPrdtNo(String prdtNo) {
		this.prdtNo = prdtNo;
	}
	/**
	 * @return 机构号
	 */
	 public String getBrNo() {
	 	return brNo;
	 }
	 /**
	 * @设置 机构号
	 * @param brNo
	 */
	 public void setBrNo(String brNo) {
	 	this.brNo = brNo;
	 }
	/**
	 * @return 审批类型 1贷款 2评级 3授信 4五级 5合作方
	 */
	 public String getAppType() {
	 	return appType;
	 }
	 /**
	 * @设置 审批类型 1贷款 2评级 3授信 4五级 5合作方
	 * @param appType
	 */
	 public void setAppType(String appType) {
	 	this.appType = appType;
	 }
	/**
	 * @return 流程编号
	 */
	 public String getProcessKey() {
	 	return processKey;
	 }
	 /**
	 * @设置 流程编号
	 * @param wkfNo
	 */
	 public void setProcessKey(String processKey) {
	 	this.processKey = processKey;
	 }
	/**
	 * @return 流程名称
	 */
	 public String getProcessDesc() {
	 	return processDesc;
	 }
	 /**
	 * @设置 流程名称
	 * @param wkfName
	 */
	 public void setProcessDesc(String processDesc) {
	 	this.processDesc = processDesc;
	 }
	public String getTxBrno() {
		return txBrno;
	}
	public void setTxBrno(String txBrno) {
		this.txBrno = txBrno;
	}
	public String getTxDate() {
		return txDate;
	}
	public void setTxDate(String txDate) {
		this.txDate = txDate;
	}
	public String getProcessDefId() {
		return processDefId;
	}
	public void setProcessDefId(String processDefId) {
		this.processDefId = processDefId;
	}
	public String getProcessSts() {
		return processSts;
	}
	public void setProcessSts(String processSts) {
		this.processSts = processSts;
	}
	public String getTxUserId() {
		return txUserId;
	}
	public void setTxUserId(String txUserId) {
		this.txUserId = txUserId;
	}
	public String getTxModifyDate() {
		return txModifyDate;
	}
	public void setTxModifyDate(String txModifyDate) {
		this.txModifyDate = txModifyDate;
	}
	public String getBeiZhu() {
		return beiZhu;
	}
	public void setBeiZhu(String beiZhu) {
		this.beiZhu = beiZhu;
	}
	public String getCheckManager() {
		return checkManager;
	}
	public void setCheckManager(String checkManager) {
		this.checkManager = checkManager;
	}
	public String getManagerPass() {
		return managerPass;
	}
	public void setManagerPass(String managerPass) {
		this.managerPass = managerPass;
	}
	public String getBs_no() {
		return bs_no;
	}
	public void setBs_no(String bsNo) {
		bs_no = bsNo;
	}
}