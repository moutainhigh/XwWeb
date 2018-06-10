package app.creditapp.bat.entity;
import app.base.BaseDomain;
/**
* Title: RptPrdtDaily.java
* Description:
* @version：1.0
**/
public class RptPrdtDaily extends BaseDomain {
	private String rptDate;//报表日期
	private String rptType;//日期类型[01-月初02-上旬03-中旬04-月底05-天]
	private String projNo;//项目编号
	private String prdtType;//产品大类
	private String prdtNo;//产品号
	private Integer cifNum;//户数
	private Integer lnCnt;//笔数
	private Double lnBal;//余额
	private Double overBal;//逾期余额
	private Double intstBal;//欠息
	private Double cntYear;//本年发放笔数
	private Double cntMon;//本月发放笔数
	private Double cntDay;//本日发放笔数
	private Double cntAvg;//日均笔数
	private Double amtTot;//累放金额
	private Double amtYear;//本年累放
	private Double amtMon;//本月累放
	private Double amtDay;//本日累放
	private Double amtAvg;//日均放贷
	
	//之后添加
	private Double maxAmt;//阶梯放贷规模上线
	private Float offRate;//产品折扣率
	
	private String lnType;//产品大类

	/**
	 * @return 报表日期
	 */
	public String getRptDate() {
	 	return rptDate;
	}
	/**
	 * @设置 报表日期
	 * @param rptDate
	 */
	public void setRptDate(String rptDate) {
	 	this.rptDate = rptDate;
	}
	/**
	 * @return 日期类型[01-月初02-上旬03-中旬04-月底05-天]
	 */
	public String getRptType() {
	 	return rptType;
	}
	/**
	 * @设置 日期类型[01-月初02-上旬03-中旬04-月底05-天]
	 * @param rptType
	 */
	public void setRptType(String rptType) {
	 	this.rptType = rptType;
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
	/**
	 * @return 产品号
	 */
	public String getPrdtNo() {
	 	return prdtNo;
	}
	/**
	 * @设置 产品号
	 * @param prdtNo
	 */
	public void setPrdtNo(String prdtNo) {
	 	this.prdtNo = prdtNo;
	}
	/**
	 * @return 总贷款余额
	 */
	/**
	 * @return 累放金额
	 */
	public Double getAmtTot() {
	 	return amtTot;
	}
	/**
	 * @设置 累放金额
	 * @param amtTot
	 */
	public void setAmtTot(Double amtTot) {
	 	this.amtTot = amtTot;
	}
	/**
	 * @return 本年累放
	 */
	public Double getAmtYear() {
	 	return amtYear;
	}
	/**
	 * @设置 本年累放
	 * @param amtYear
	 */
	public void setAmtYear(Double amtYear) {
	 	this.amtYear = amtYear;
	}
	/**
	 * @return 本月累放
	 */
	public Double getAmtMon() {
	 	return amtMon;
	}
	/**
	 * @设置 本月累放
	 * @param amtMon
	 */
	public void setAmtMon(Double amtMon) {
	 	this.amtMon = amtMon;
	}
	/**
	 * @return 本日累放
	 */
	public Double getAmtDay() {
	 	return amtDay;
	}
	/**
	 * @设置 本日累放
	 * @param amtDay
	 */
	public void setAmtDay(Double amtDay) {
	 	this.amtDay = amtDay;
	}
	/**
	 * @return 应收未收利息
	 */
	public Double getIntstBal() {
	 	return intstBal;
	}
	/**
	 * @设置 应收未收利息
	 * @param intstBal
	 */
	public void setIntstBal(Double intstBal) {
	 	this.intstBal = intstBal;
	}
	public Double getMaxAmt() {
		return maxAmt;
	}
	//之后添加
	public void setMaxAmt(Double maxAmt) {
		this.maxAmt = maxAmt;
	}
	public Float getOffRate() {
		return offRate;
	}
	public void setOffRate(Float offRate) {
		this.offRate = offRate;
	}
	public String getPrdtType() {
		return prdtType;
	}
	public void setPrdtType(String prdtType) {
		this.prdtType = prdtType;
	}
	public Double getLnBal() {
		return lnBal;
	}
	public void setLnBal(Double lnBal) {
		this.lnBal = lnBal;
	}
	public Double getOverBal() {
		return overBal;
	}
	public void setOverBal(Double overBal) {
		this.overBal = overBal;
	}
	public String getLnType() {
		return lnType;
	}
	public void setLnType(String lnType) {
		this.lnType = lnType;
	}
	public Integer getCifNum() {
		return cifNum;
	}
	public void setCifNum(Integer cifNum) {
		this.cifNum = cifNum;
	}
	public Integer getLnCnt() {
		return lnCnt;
	}
	public void setLnCnt(Integer lnCnt) {
		this.lnCnt = lnCnt;
	}
	public Double getCntYear() {
		return cntYear;
	}
	public void setCntYear(Double cntYear) {
		this.cntYear = cntYear;
	}
	public Double getCntMon() {
		return cntMon;
	}
	public void setCntMon(Double cntMon) {
		this.cntMon = cntMon;
	}
	public Double getCntDay() {
		return cntDay;
	}
	public void setCntDay(Double cntDay) {
		this.cntDay = cntDay;
	}
	public Double getCntAvg() {
		return cntAvg;
	}
	public void setCntAvg(Double cntAvg) {
		this.cntAvg = cntAvg;
	}
	public Double getAmtAvg() {
		return amtAvg;
	}
	public void setAmtAvg(Double amtAvg) {
		this.amtAvg = amtAvg;
	}
}