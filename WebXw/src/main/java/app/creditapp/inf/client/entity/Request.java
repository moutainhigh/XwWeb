package app.creditapp.inf.client.entity;

import java.util.List;

public class Request {

	private String ruleName;
	private String resultId;
	private List<RuleFact> ruleFact;
	
	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	public List<RuleFact> getRuleFact() {
		return ruleFact;
	}
	public void setRuleFact(List<RuleFact> ruleFact) {
		this.ruleFact = ruleFact;
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
}
