package app.creditapp.cred.entity;
import app.base.BaseDomain;
/**
* Title: CifEval.java
* Description:
* @version：1.0
**/
public class CifEval extends BaseDomain {
	private String evalDesc;//评级原因
	private String evalDate;//评级时间
	private String evalSts;//评级状态[01生效02失效]
	private String grade;//评级结果[A B C]
	private String idNo;//证件号码
	private String cifName;//客户名称
	private String cifNo;//客户编号
	private String evalNo;//评级编号
	private String appId;//申请号
	private Integer score;//评分分数
	private String idType;//证件类型
	private String brNo;//进件机构
	private String resultId;//客户评级返回ID
	private String query;
	private String loginid;//登录人员
	
	public String getLoginid() {
		return loginid;
	}
	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}
	/**
	 * 评级分数
	 */
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
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
	 * @return 证件号码
	 */
	public String getIdNo() {
	 	return idNo;
	}
	/**
	 * @设置 证件号码
	 * @param idNo
	 */
	public void setIdNo(String idNo) {
	 	this.idNo = idNo;
	}
	/**
	 * @return 客户名称
	 */
	public String getCifName() {
	 	return cifName;
	}
	/**
	 * @设置 客户名称
	 * @param cifName
	 */
	public void setCifName(String cifName) {
	 	this.cifName = cifName;
	}
	/**
	 * @return 客户编号
	 */
	public String getCifNo() {
	 	return cifNo;
	}
	/**
	 * @设置 客户编号
	 * @param cifNo
	 */
	public void setCifNo(String cifNo) {
	 	this.cifNo = cifNo;
	}
	/**
	 * @return 评级编号
	 */
	public String getEvalNo() {
	 	return evalNo;
	}
	/**
	 * @设置 评级编号
	 * @param evalNo
	 */
	public void setEvalNo(String evalNo) {
	 	this.evalNo = evalNo;
	}
	
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	public String getBrNo() {
		return brNo;
	}
	public void setBrNo(String brNo) {
		this.brNo = brNo;
	}
	/**
	 * @return the resultId
	 */
	public String getResultId() {
		return resultId;
	}
	/**
	 * @param resultId the resultId to set
	 */
	public void setResultId(String resultId) {
		this.resultId = resultId;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
}