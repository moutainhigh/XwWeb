package app.util.ruleFiter.entity;

import java.util.Map;

import app.util.ruleFiter.entity.ValidateParm;

public class ValidateRuleEntity {
	private String ruleId;
	private String ruleName;
	private Map<String,ValidateParm> validateMap;
	public String getRuleId() {
		return ruleId;
	}
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}
	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	public Map<String, ValidateParm> getValidateMap() {
		return validateMap;
	}
	public void setValidateMap(Map<String, ValidateParm> validateMap) {
		this.validateMap = validateMap;
	}
}
