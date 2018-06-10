package app.creditapp.inf.client.entity;

public class RuleReturn {
	
	private String codeName;//要素
	private String codeDes;//指标
	private String codeValue;//借款人情况
	private String codeRes;//结果
	private String codeResDes;//结果描述
	private Double score;//结果描述
	
	public String getCodeResDes() {
		return codeResDes;
	}
	public void setCodeResDes(String codeResDes) {
		this.codeResDes = codeResDes;
	}
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	public String getCodeDes() {
		return codeDes;
	}
	public void setCodeDes(String codeDes) {
		this.codeDes = codeDes;
	}
	public String getCodeValue() {
		return codeValue;
	}
	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}
	public String getCodeRes() {
		return codeRes;
	}
	public void setCodeRes(String codeRes) {
		this.codeRes = codeRes;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}


}
