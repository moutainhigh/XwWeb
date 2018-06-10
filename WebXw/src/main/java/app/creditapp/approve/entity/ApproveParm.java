package app.creditapp.approve.entity;
import app.base.BaseDomain;
/**
 * @description 	流程变量传递参数
 * @version 1.0
 */
public class ApproveParm extends BaseDomain {
	
	private double amt;//金额
	private String vou_type;//担保方式
	private double rate;//利率
	private String prod_no;//产品号
	private String occur_type;//发生类型
	private String cap_type;//资金来源
	private String approve_user;
	
	
	public double getAmt() {
		return amt;
	}
	public void setAmt(double amt) {
		this.amt = amt;
	}
	public String getVou_type() {
		return vou_type;
	}
	public void setVou_type(String vou_type) {
		this.vou_type = vou_type;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public String getProd_no() {
		return prod_no;
	}
	public void setProd_no(String prod_no) {
		this.prod_no = prod_no;
	}
	public String getOccur_type() {
		return occur_type;
	}
	public void setOccur_type(String occur_type) {
		this.occur_type = occur_type;
	}
	public String getCap_type() {
		return cap_type;
	}
	public String getApprove_user() {
		return approve_user;
	}
	public void setApprove_user(String approveUser) {
		approve_user = approveUser;
	}
	public void setCap_type(String cap_type) {
		this.cap_type = cap_type;
	}

}