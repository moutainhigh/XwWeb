package app.creditapp.fund.entity;
import app.base.BaseDomain;
/**
* Title: FundProvService.java
* Description:
* @version：1.0
**/
public class FundProvService extends BaseDomain {
	private String provServiceNo;//收益计提放贷服务费编号
	private String provProjNo;//项目收益计提编号
	private String projNo;//项目编号
	private String stepNo;//阶梯编号
	private Double minAmt;//阶梯放贷规模下线
	private Double maxAmt;//阶梯放贷规模上线
	private Float  svRate;//服务费率
	private Double servicefee;//服务费
	private String jtBegDate;//计提起始日期
	private String jtEndDate;//计提到期日期
	private String prdtNo;//产品编号
	private String prdtName;//产品名称
	private Float  offRate;//折扣率
	private Double prdtDueAmt;//产品在本阶梯段内放贷规模
	private Double prdtServicefee;//产品在本阶梯段内服务费
	private String opNo;//计提人员
	private String txDate;//计提日期
	private String filler;//备注

	/**
	 * @return 收益计提放贷服务费编号
	 */
	public String getProvServiceNo() {
	 	return provServiceNo;
	}
	/**
	 * @设置 收益计提放贷服务费编号
	 * @param provServiceNo
	 */
	public void setProvServiceNo(String provServiceNo) {
	 	this.provServiceNo = provServiceNo;
	}
	/**
	 * @return 项目收益计提编号
	 */
	public String getProvProjNo() {
	 	return provProjNo;
	}
	/**
	 * @设置 项目收益计提编号
	 * @param provProjNo
	 */
	public void setProvProjNo(String provProjNo) {
	 	this.provProjNo = provProjNo;
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
	 * @return 阶梯编号
	 */
	public String getStepNo() {
	 	return stepNo;
	}
	/**
	 * @设置 阶梯编号
	 * @param stepNo
	 */
	public void setStepNo(String stepNo) {
	 	this.stepNo = stepNo;
	}
	/**
	 * @return 阶梯放贷规模下线
	 */
	public Double getMinAmt() {
	 	return minAmt;
	}
	/**
	 * @设置 阶梯放贷规模下线
	 * @param minAmt
	 */
	public void setMinAmt(Double minAmt) {
	 	this.minAmt = minAmt;
	}
	/**
	 * @return 阶梯放贷规模上线
	 */
	public Double getMaxAmt() {
	 	return maxAmt;
	}
	/**
	 * @设置 阶梯放贷规模上线
	 * @param maxAmt
	 */
	public void setMaxAmt(Double maxAmt) {
	 	this.maxAmt = maxAmt;
	}
	/**
	 * @return 服务费率
	 */
	public Float getSvRate() {
	 	return svRate;
	}
	/**
	 * @设置 服务费率
	 * @param svRate
	 */
	public void setSvRate(Float svRate) {
	 	this.svRate = svRate;
	}
	/**
	 * @return 服务费
	 */
	public Double getServicefee() {
	 	return servicefee;
	}
	/**
	 * @设置 服务费
	 * @param servicefee
	 */
	public void setServicefee(Double servicefee) {
	 	this.servicefee = servicefee;
	}
	/**
	 * @return 计提起始日期
	 */
	public String getJtBegDate() {
	 	return jtBegDate;
	}
	/**
	 * @设置 计提起始日期
	 * @param jtBegDate
	 */
	public void setJtBegDate(String jtBegDate) {
	 	this.jtBegDate = jtBegDate;
	}
	/**
	 * @return 计提到期日期
	 */
	public String getJtEndDate() {
	 	return jtEndDate;
	}
	/**
	 * @设置 计提到期日期
	 * @param jtEndDate
	 */
	public void setJtEndDate(String jtEndDate) {
	 	this.jtEndDate = jtEndDate;
	}
	/**
	 * @return 产品编号
	 */
	public String getPrdtNo() {
	 	return prdtNo;
	}
	/**
	 * @设置 产品编号
	 * @param prdtNo
	 */
	public void setPrdtNo(String prdtNo) {
	 	this.prdtNo = prdtNo;
	}
	/**
	 * @return 产品名称
	 */
	public String getPrdtName() {
	 	return prdtName;
	}
	/**
	 * @设置 产品名称
	 * @param prdtName
	 */
	public void setPrdtName(String prdtName) {
	 	this.prdtName = prdtName;
	}
	/**
	 * @return 折扣率
	 */
	public Float getOffRate() {
	 	return offRate;
	}
	/**
	 * @设置 折扣率
	 * @param offRate
	 */
	public void setOffRate(Float offRate) {
	 	this.offRate = offRate;
	}
	/**
	 * @return 产品在本阶梯段内放贷规模
	 */
	public Double getPrdtDueAmt() {
	 	return prdtDueAmt;
	}
	/**
	 * @设置 产品在本阶梯段内放贷规模
	 * @param prdtDueAmt
	 */
	public void setPrdtDueAmt(Double prdtDueAmt) {
	 	this.prdtDueAmt = prdtDueAmt;
	}
	/**
	 * @return 产品在本阶梯段内服务费
	 */
	public Double getPrdtServicefee() {
	 	return prdtServicefee;
	}
	/**
	 * @设置 产品在本阶梯段内服务费
	 * @param prdtServicefee
	 */
	public void setPrdtServicefee(Double prdtServicefee) {
	 	this.prdtServicefee = prdtServicefee;
	}
	/**
	 * @return 计提人员
	 */
	public String getOpNo() {
	 	return opNo;
	}
	/**
	 * @设置 计提人员
	 * @param opNo
	 */
	public void setOpNo(String opNo) {
	 	this.opNo = opNo;
	}
	/**
	 * @return 计提日期
	 */
	public String getTxDate() {
	 	return txDate;
	}
	/**
	 * @设置 计提日期
	 * @param txDate
	 */
	public void setTxDate(String txDate) {
	 	this.txDate = txDate;
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
}