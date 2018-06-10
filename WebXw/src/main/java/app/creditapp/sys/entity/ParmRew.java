package app.creditapp.sys.entity;
import app.base.BaseDomain;
/**
* Title: ParmRew.java
* Description:
* @version：1.0
**/
public class ParmRew extends BaseDomain {
	private String rewNo;//方案编号
	private String rewName;//方案名称
	private String rewType;//预警类型[01预警02消息]
	private String rewLevel;//预警级别[01红色02黄色03蓝色]
	private Double rewValue;//预警阈值
	private String rewUnit;//阈值单位
	private String rewDeal;//处理方式[01短信02邮件03FTP]
	private String rewCyc;//提醒周期[01每天]
	private String rewAcpt;//接受人[01运营人员02合作机构03全部]
	private String rewSts;//状态[01正常02停用]
	private String txDate;//登记时间
	private String upDate;//更新时间

	/**
	 * @return 方案编号
	 */
	public String getRewNo() {
	 	return rewNo;
	}
	/**
	 * @设置 方案编号
	 * @param rewNo
	 */
	public void setRewNo(String rewNo) {
	 	this.rewNo = rewNo;
	}
	/**
	 * @return 方案名称
	 */
	public String getRewName() {
	 	return rewName;
	}
	/**
	 * @设置 方案名称
	 * @param rewName
	 */
	public void setRewName(String rewName) {
	 	this.rewName = rewName;
	}
	/**
	 * @return 预警类型[01预警02消息]
	 */
	public String getRewType() {
	 	return rewType;
	}
	/**
	 * @设置 预警类型[01预警02消息]
	 * @param rewType
	 */
	public void setRewType(String rewType) {
	 	this.rewType = rewType;
	}
	/**
	 * @return 预警级别[01红色02黄色03蓝色]
	 */
	public String getRewLevel() {
	 	return rewLevel;
	}
	/**
	 * @设置 预警级别[01红色02黄色03蓝色]
	 * @param rewLevel
	 */
	public void setRewLevel(String rewLevel) {
	 	this.rewLevel = rewLevel;
	}
	/**
	 * @return 预警阈值
	 */
	public Double getRewValue() {
	 	return rewValue;
	}
	/**
	 * @设置 预警阈值
	 * @param rewValue
	 */
	public void setRewValue(Double rewValue) {
	 	this.rewValue = rewValue;
	}
	/**
	 * @return 阈值单位
	 */
	public String getRewUnit() {
	 	return rewUnit;
	}
	/**
	 * @设置 阈值单位
	 * @param rewUnit
	 */
	public void setRewUnit(String rewUnit) {
	 	this.rewUnit = rewUnit;
	}
	/**
	 * @return 处理方式[01短信02邮件03FTP]
	 */
	public String getRewDeal() {
	 	return rewDeal;
	}
	/**
	 * @设置 处理方式[01短信02邮件03FTP]
	 * @param rewDeal
	 */
	public void setRewDeal(String rewDeal) {
	 	this.rewDeal = rewDeal;
	}
	/**
	 * @return 提醒周期[01每天]
	 */
	public String getRewCyc() {
	 	return rewCyc;
	}
	/**
	 * @设置 提醒周期[01每天]
	 * @param rewCyc
	 */
	public void setRewCyc(String rewCyc) {
	 	this.rewCyc = rewCyc;
	}
	/**
	 * @return 接受人[01运营人员02合作机构03全部]
	 */
	public String getRewAcpt() {
	 	return rewAcpt;
	}
	/**
	 * @设置 接受人[01运营人员02合作机构03全部]
	 * @param rewAcpt
	 */
	public void setRewAcpt(String rewAcpt) {
	 	this.rewAcpt = rewAcpt;
	}
	/**
	 * @return 状态[01正常02停用]
	 */
	public String getRewSts() {
	 	return rewSts;
	}
	/**
	 * @设置 状态[01正常02停用]
	 * @param rewSts
	 */
	public void setRewSts(String rewSts) {
	 	this.rewSts = rewSts;
	}
	/**
	 * @return 登记时间
	 */
	public String getTxDate() {
	 	return txDate;
	}
	/**
	 * @设置 登记时间
	 * @param txDate
	 */
	public void setTxDate(String txDate) {
	 	this.txDate = txDate;
	}
	/**
	 * @return 更新时间
	 */
	public String getUpDate() {
	 	return upDate;
	}
	/**
	 * @设置 更新时间
	 * @param upDate
	 */
	public void setUpDate(String upDate) {
	 	this.upDate = upDate;
	}
}