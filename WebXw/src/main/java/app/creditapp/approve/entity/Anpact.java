package app.creditapp.approve.entity;

public class Anpact {
 private String br1No;//机构号
 private String pact1No;//合同号
 private String APPR_STS;//标记状态01放款02不作处理03放款失败

public String getBr1No() {
	return br1No;
}
public void setBr1No(String br1No) {
	this.br1No = br1No;
}
public String getPact1No() {
	return pact1No;
}
public void setPact1No(String pact1No) {
	this.pact1No = pact1No;
}
public String getAPPR_STS() {
	return APPR_STS;
}
public void setAPPR_STS(String aPPRSTS) {
	APPR_STS = aPPRSTS;
}
 
}
