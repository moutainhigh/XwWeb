package app.creditapp.proj.entity;
import app.base.BaseDomain;
/**
* Title: ProjParm.java
* Description:
* @version：1.0
**/
public class ProjParm extends BaseDomain {
	private String upOpno;//修改人员
	private String upOpname;//修改人员名称
	private String upDate;//修改日期
	private String txDate;//登记日期
	private String opNo;//操作员
	private String opName;//操作员名称
	private String deptNo;//登记部门
	private String filler;//备注
	private Float leverRate;//资金杠杆比例
	private Integer backDays;//回购天数阈值[逾期超过该天数系统自动进行回购操作]
	private Integer compstDays;//代偿天数阈值
	private Float rgAppRate;//人工复核比例阈值
	private Float mangFeerate;//信托管理费率[默认为千分之五]
	private String truPayType;//信托报酬收费类型[信托管理费、放贷服务费、融资报酬,用三位二进制表示，例如001就是只收融资报酬]
	private String projName;//项目名称
	private String projNo;//项目编号
	//新增字段
	private int overDays = 10;//逾期天数
	private int fundEndDays = 10;//资金到期兑付天数
	private int projEndDays = 10;//项目到期天数
	private int intDays = 10;//结息到期天数
	
	private String endDay;
	private String overDay;
	public String getEndDay() {
		return endDay;
	}
	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}
	public String getOverDay() {
		return overDay;
	}
	public void setOverDay(String overDay) {
		this.overDay = overDay;
	}
	/**
	 * @return 修改人员
	 */
	
	public String getUpOpno() {
	 	return upOpno;
	}
	
	/**
	 * @设置 修改人员
	 * @param upOpno
	 */
	public void setUpOpno(String upOpno) {
	 	this.upOpno = upOpno;
	}
	/**
	 * @return 修改日期
	 */
	public String getUpDate() {
	 	return upDate;
	}
	/**
	 * @设置 修改日期
	 * @param upDate
	 */
	public void setUpDate(String upDate) {
	 	this.upDate = upDate;
	}
	/**
	 * @return 登记日期
	 */
	public String getTxDate() {
	 	return txDate;
	}
	/**
	 * @设置 登记日期
	 * @param txDate
	 */
	public void setTxDate(String txDate) {
	 	this.txDate = txDate;
	}
	/**
	 * @return 操作员
	 */
	public String getOpNo() {
	 	return opNo;
	}
	/**
	 * @设置 操作员
	 * @param opNo
	 */
	public void setOpNo(String opNo) {
	 	this.opNo = opNo;
	}
	/**
	 * @return 登记部门
	 */
	public String getDeptNo() {
	 	return deptNo;
	}
	/**
	 * @设置 登记部门
	 * @param deptNo
	 */
	public void setDeptNo(String deptNo) {
	 	this.deptNo = deptNo;
	}
	/**
	 * @return 备注
	 */
	public String getFiller() {
	 	return filler;
	}
	/**
	 * @设置 备注
	 * @param filler
	 */
	public void setFiller(String filler) {
	 	this.filler = filler;
	}
	/**
	 * @return 资金杠杆比例
	 */
	public Float getLeverRate() {
	 	return leverRate;
	}
	/**
	 * @设置 资金杠杆比例
	 * @param leverRate
	 */
	public void setLeverRate(Float leverRate) {
	 	this.leverRate = leverRate;
	}
	/**
	 * @return 回购天数阈值[逾期超过该天数系统自动进行回购操作]
	 */
	public Integer getBackDays() {
	 	return backDays;
	}
	/**
	 * @设置 回购天数阈值[逾期超过该天数系统自动进行回购操作]
	 * @param backDays
	 */
	public void setBackDays(Integer backDays) {
	 	this.backDays = backDays;
	}
	/**
	 * @return 人工复核比例阈值
	 */
	public Float getRgAppRate() {
	 	return rgAppRate;
	}
	/**
	 * @设置 人工复核比例阈值
	 * @param rgAppRate
	 */
	public void setRgAppRate(Float rgAppRate) {
	 	this.rgAppRate = rgAppRate;
	}
	
	public Float getMangFeerate() {
		return mangFeerate;
	}
	public void setMangFeerate(Float mangFeerate) {
		this.mangFeerate = mangFeerate;
	}
	/**
	 * @return 信托报酬收费类型[信托管理费、放贷服务费、融资报酬,用三位二进制表示，例如001就是只收融资报酬]
	 */
	public String getTruPayType() {
	 	return truPayType;
	}
	/**
	 * @设置 信托报酬收费类型[信托管理费、放贷服务费、融资报酬,用三位二进制表示，例如001就是只收融资报酬]
	 * @param truPayType
	 */
	public void setTruPayType(String truPayType) {
	 	this.truPayType = truPayType;
	}
	/**
	 * @return 项目名称
	 */
	public String getProjName() {
	 	return projName;
	}
	/**
	 * @设置 项目名称
	 * @param projName
	 */
	public void setProjName(String projName) {
	 	this.projName = projName;
	}
	/**
	 * @return 项目编号
	 */
	public String getProjNo() {
	 	return projNo;
	}
	/**
	 * @设置 项目编号
	 * @param projNo
	 */
	public void setProjNo(String projNo) {
	 	this.projNo = projNo;
	}
	public int getOverDays() {
		return overDays;
	}
	public void setOverDays(int overDays) {
		this.overDays = overDays;
	}
	public int getFundEndDays() {
		return fundEndDays;
	}
	public void setFundEndDays(int fundEndDays) {
		this.fundEndDays = fundEndDays;
	}
	public int getProjEndDays() {
		return projEndDays;
	}
	public void setProjEndDays(int projEndDays) {
		this.projEndDays = projEndDays;
	}
	public int getIntDays() {
		return intDays;
	}
	public void setIntDays(int intDays) {
		this.intDays = intDays;
	}
	public String getUpOpname() {
		return upOpname;
	}
	public void setUpOpname(String upOpname) {
		this.upOpname = upOpname;
	}
	public String getOpName() {
		return opName;
	}
	public void setOpName(String opName) {
		this.opName = opName;
	}
	public Integer getCompstDays() {
		return compstDays;
	}
	public void setCompstDays(Integer compstDays) {
		this.compstDays = compstDays;
	}
}