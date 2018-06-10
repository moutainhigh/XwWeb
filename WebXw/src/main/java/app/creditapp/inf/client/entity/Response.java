package app.creditapp.inf.client.entity;

import java.util.List;

public class Response {
	
	private String ruleCode;
	private String resultId;
	private String ruleMsg;
	private List<RuleFact> ruleFact;
	
	public String getRuleCode() {
		return ruleCode;
	}
	public void setRuleCode(String ruleCode) {
		this.ruleCode = ruleCode;
	}
	public String getResultId() {
		return resultId;
	}
	public void setResultId(String resultId) {
		this.resultId = resultId;
	}
	/**
	 * @return the ruleMsg
	 */
	public String getRuleMsg() {
		return ruleMsg;
	}
	/**
	 * @param ruleMsg the ruleMsg to set
	 */
	public void setRuleMsg(String ruleMsg) {
		this.ruleMsg = ruleMsg;
	}
	public List<RuleFact> getRuleFact() {
		return ruleFact;
	}
	public void setRuleFact(List<RuleFact> ruleFact) {
		this.ruleFact = ruleFact;
	}
}