package app.creditapp.approve.entity;
import app.base.BaseDomain;
public class WkfApplySelect extends BaseDomain {
	private String app_no;//申请号
	private String wkf_id;//流程编号
	private String wkf_risk_type;//授信风险总分类
	private String wkf_cif_type;//客户类型
	private String occur_type;//发生方式
	private String wkf_vou_type;//是否信用 1-是，0-否
	private String loan_oper_qua;//经营方式
	private String br_depart;//所属部门
	private double app_amt;//授信累计金额
	private String last_opno;//最终审批人
	private String file2;//预留字段2
	private String file3;//预留字段3
	public String getApp_no() {
		return app_no;
	}
	public void setApp_no(String appNo) {
		app_no = appNo;
	}
	public String getWkf_id() {
		return wkf_id;
	}
	public void setWkf_id(String wkfId) {
		wkf_id = wkfId;
	}
	public String getWkf_risk_type() {
		return wkf_risk_type;
	}
	public void setWkf_risk_type(String wkfRiskType) {
		wkf_risk_type = wkfRiskType;
	}
	public String getWkf_cif_type() {
		return wkf_cif_type;
	}
	public void setWkf_cif_type(String wkfCifType) {
		wkf_cif_type = wkfCifType;
	}
	public String getOccur_type() {
		return occur_type;
	}
	public void setOccur_type(String occurType) {
		occur_type = occurType;
	}
	public String getWkf_vou_type() {
		return wkf_vou_type;
	}
	public void setWkf_vou_type(String wkfVouType) {
		wkf_vou_type = wkfVouType;
	}
	public String getLoan_oper_qua() {
		return loan_oper_qua;
	}
	public void setLoan_oper_qua(String loanOperQua) {
		loan_oper_qua = loanOperQua;
	}
	public String getBr_depart() {
		return br_depart;
	}
	public void setBr_depart(String brDepart) {
		br_depart = brDepart;
	}
	public String getLast_opno() {
		return last_opno;
	}
	public void setLast_opno(String lastOpno) {
		last_opno = lastOpno;
	}
	public String getFile2() {
		return file2;
	}
	public void setFile2(String file2) {
		this.file2 = file2;
	}
	public String getFile3() {
		return file3;
	}
	public void setFile3(String file3) {
		this.file3 = file3;
	}
	public double getApp_amt() {
		return app_amt;
	}
	public void setApp_amt(double appAmt) {
		app_amt = appAmt;
	}


}