package app.creditapp.sys.entity;
import app.base.BaseDomain;
/**
* Title: ParmRewRule.java
* Description:
* @version：1.0
**/
public class ParmRewRule extends BaseDomain {
	private String ruleSts;//是否启用[0否1是]
	private String ruleId;//筛查规则编号
	private String ruleDesc;//筛查规则描述
	private String riskLev;//规则风险级别[01提示02拦截]
	private String opProc;//对应存储过程
	private String sceneNo;//场景编号[01预进件02正式进件]

	/**
	 * @return 是否启用[0否1是]
	 */
	public String getRuleSts() {
	 	return ruleSts;
	}
	/**
	 * @设置 是否启用[0否1是]
	 * @param ruleSts
	 */
	public void setRuleSts(String ruleSts) {
	 	this.ruleSts = ruleSts;
	}
	/**
	 * @return 筛查规则编号
	 */
	public String getRuleId() {
	 	return ruleId;
	}
	/**
	 * @设置 筛查规则编号
	 * @param ruleId
	 */
	public void setRuleId(String ruleId) {
	 	this.ruleId = ruleId;
	}
	/**
	 * @return 筛查规则描述
	 */
	public String getRuleDesc() {
	 	return ruleDesc;
	}
	/**
	 * @设置 筛查规则描述
	 * @param ruleDesc
	 */
	public void setRuleDesc(String ruleDesc) {
	 	this.ruleDesc = ruleDesc;
	}
	/**
	 * @return 规则风险级别[01提示02拦截]
	 */
	public String getRiskLev() {
	 	return riskLev;
	}
	/**
	 * @设置 规则风险级别[01提示02拦截]
	 * @param riskLev
	 */
	public void setRiskLev(String riskLev) {
	 	this.riskLev = riskLev;
	}
	/**
	 * @return 对应存储过程
	 */
	public String getOpProc() {
	 	return opProc;
	}
	/**
	 * @设置 对应存储过程
	 * @param opProc
	 */
	public void setOpProc(String opProc) {
	 	this.opProc = opProc;
	}
	/**
	 * @return 场景编号[01预进件02正式进件]
	 */
	public String getSceneNo() {
	 	return sceneNo;
	}
	/**
	 * @设置 场景编号[01预进件02正式进件]
	 * @param sceneNo
	 */
	public void setSceneNo(String sceneNo) {
	 	this.sceneNo = sceneNo;
	}
}