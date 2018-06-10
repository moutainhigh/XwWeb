package app.creditapp.cred.entity;
import app.base.BaseDomain;
/**
* Title: CorpEval.java
* Description:
* @version：1.0
**/
public class CorpEval extends BaseDomain {
	private String evalNo;//评级编号[主键]
	private String brNo;//合作机构编号
	private String brName;//合作机构名称
	private String screditNo;//社会统一信用代码证
	private String grade;//评级结果[A B C]
	private String evalSts;//评级状态[01生效02失效]
	private String evalDate;//评级时间
	private String evalDesc;//评级原因
	private String resultId;//评级原因
	private String loginid;//登录人员
	
	public String getLoginid() {
		return loginid;
	}
	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}
	/**
	 * @return 评级编号[主键]
	 */
	public String getEvalNo() {
	 	return evalNo;
	}
	/**
	 * @设置 评级编号[主键]
	 * @param evalNo
	 */
	public void setEvalNo(String evalNo) {
	 	this.evalNo = evalNo;
	}
	/**
	 * @return 合作机构编号
	 */
	public String getBrNo() {
	 	return brNo;
	}
	/**
	 * @设置 合作机构编号
	 * @param brNo
	 */
	public void setBrNo(String brNo) {
	 	this.brNo = brNo;
	}
	/**
	 * @return 合作机构名称
	 */
	public String getBrName() {
	 	return brName;
	}
	/**
	 * @设置 合作机构名称
	 * @param brName
	 */
	public void setBrName(String brName) {
	 	this.brName = brName;
	}
	/**
	 * @return 社会统一信用代码证
	 */
	public String getScreditNo() {
	 	return screditNo;
	}
	/**
	 * @设置 社会统一信用代码证
	 * @param screditNo
	 */
	public void setScreditNo(String screditNo) {
	 	this.screditNo = screditNo;
	}
	/**
	 * @return 评级结果[A B C]
	 */
	public String getGrade() {
	 	return grade;
	}
	/**
	 * @设置 评级结果[A B C]
	 * @param grade
	 */
	public void setGrade(String grade) {
	 	this.grade = grade;
	}
	/**
	 * @return 评级状态[01生效02失效]
	 */
	public String getEvalSts() {
	 	return evalSts;
	}
	/**
	 * @设置 评级状态[01生效02失效]
	 * @param evalSts
	 */
	public void setEvalSts(String evalSts) {
	 	this.evalSts = evalSts;
	}
	/**
	 * @return 评级时间
	 */
	public String getEvalDate() {
	 	return evalDate;
	}
	/**
	 * @设置 评级时间
	 * @param evalDate
	 */
	public void setEvalDate(String evalDate) {
	 	this.evalDate = evalDate;
	}
	/**
	 * @return 评级原因
	 */
	public String getEvalDesc() {
	 	return evalDesc;
	}
	/**
	 * @设置 评级原因
	 * @param evalDesc
	 */
	public void setEvalDesc(String evalDesc) {
	 	this.evalDesc = evalDesc;
	}
	public String getResultId() {
		return resultId;
	}
	public void setResultId(String resultId) {
		this.resultId = resultId;
	}
}