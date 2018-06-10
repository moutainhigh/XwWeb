package app.creditapp.wkf.entity;

import app.base.BaseDomain;

public class WkfParm extends BaseDomain implements java.io.Serializable {
	public WkfParm() {
	}
	private String app_no;  //申请号
	private String br_no;	//机构号
	private String cif_type;//客户类型 1对公  2个人
	private String prdt_no; //产品号
	private String grade;	//信用等级（评级）
	private String app_type;//审批类型 1：贷款 2：评级  3：授信 4：五级 5：合作方
	private Double amt;		//金额
	private String five_sts;//五级等级（五级）
	private String vou_type;//担保方式
	private String vou_type_sub;
	private String auth_true;//是否授信
	private Integer term_month;//期限月（贷款）
	private Double county_max;//县阈值（贷款）
	private Double city_max;  //市阈值（贷款）
	private Double province_max;//省阈值（贷款）
	private String wf_app_value; //业务数据串 （格式：  申请号：+app_no,利率: +rate）把想在审批列表显示的，都拼进去
	private String auth_type;//授信类型细分：授信申请、授信调整
	private String prj_type;//合作方类型细分
	private String br_lev;//机构级别
	private Integer adv_grade;//评级审批等级
	private String grade_pers;//个人评定级别
	private String grade_corp;//对公评级
	private String arti_sts;//调整前五级状态
	private String bad_five_sts;//不良贷款上迁
	private String better_five_sts;//正常贷款上迁
	private String down_five_sts;//正常下迁
	private String br_depart; //总行客户经理部门
	private String occur_type;//发生类型
	private String wkf_vou_type;//是否信用贷款
	private String wkf_cif_type;//客户类型
	private String prod_no;//产品号
	private String loan_oper_qua;//经营方式
	private String wkf_risk_type;//授信分类
	
	//get set
	public String getApp_no() {
		return app_no;
	}
	public void setApp_no(String appNo) {
		app_no = appNo;
	}
	public String getBr_no() {
		return br_no;
	}
	public void setBr_no(String brNo) {
		br_no = brNo;
	}
	public String getCif_type() {
		return cif_type;
	}
	public void setCif_type(String cifType) {
		cif_type = cifType;
	}
	public String getPrdt_no() {
		return prdt_no;
	}
	public void setPrdt_no(String prdtNo) {
		prdt_no = prdtNo;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getApp_type() {
		return app_type;
	}
	public void setApp_type(String appType) {
		app_type = appType;
	}
	public Double getAmt() {
		return amt;
	}
	public void setAmt(Double amt) {
		this.amt = amt;
	}
	public String getFive_sts() {
		return five_sts;
	}
	public void setFive_sts(String fiveSts) {
		five_sts = fiveSts;
	}
	public String getVou_type() {
		return vou_type;
	}
	public void setVou_type(String vouType) {
		vou_type = vouType;
	}
	public String getAuth_true() {
		return auth_true;
	}
	public void setAuth_true(String authTrue) {
		auth_true = authTrue;
	}
	public Integer getTerm_month() {
		return term_month;
	}
	public String getLoan_oper_qua() {
		return loan_oper_qua;
	}
	public void setLoan_oper_qua(String loanOperQua) {
		loan_oper_qua = loanOperQua;
	}
	public void setTerm_month(Integer termMonth) {
		term_month = termMonth;
	}
	public Double getCounty_max() {
		return county_max;
	}
	public void setCounty_max(Double countyMax) {
		county_max = countyMax;
	}
	public Double getCity_max() {
		return city_max;
	}
	public void setCity_max(Double cityMax) {
		city_max = cityMax;
	}
	public Double getProvince_max() {
		return province_max;
	}
	public void setProvince_max(Double provinceMax) {
		province_max = provinceMax;
	}
	public String getDown_five_sts() {
		return down_five_sts;
	}
	public void setDown_five_sts(String downFiveSts) {
		down_five_sts = downFiveSts;
	}
	public String getWf_app_value() {
		return wf_app_value;
	}
	public void setWf_app_value(String wfAppValue) {
		wf_app_value = wfAppValue;
	}
	public String getAuth_type() {
		return auth_type;
	}
	public void setAuth_type(String authType) {
		auth_type = authType;
	}
	public String getPrj_type() {
		return prj_type;
	}
	public void setPrj_type(String prjType) {
		prj_type = prjType;
	}
	public Integer getAdv_grade() {
		return adv_grade;
	}
	public void setAdv_grade(Integer adv_grade) {
		this.adv_grade = adv_grade;
	}
	public String getGrade_pers() {
		return grade_pers;
	}
	public void setGrade_pers(String grade_pers) {
		this.grade_pers = grade_pers;
	}
	public String getGrade_corp() {
		return grade_corp;
	}
	public void setGrade_corp(String grade_corp) {
		this.grade_corp = grade_corp;
	}
	public String getArti_sts() {
		return arti_sts;
	}
	public void setArti_sts(String arti_sts) {
		this.arti_sts = arti_sts;
	}
	public String getBr_lev() {
		return br_lev;
	}
	public String getVou_type_sub() {
		return vou_type_sub;
	}
	public void setVou_type_sub(String vou_type_sub) {
		this.vou_type_sub = vou_type_sub;
	}
	public void setBr_lev(String br_lev) {
		this.br_lev = br_lev;
	}
	public String getBad_five_sts() {
		return bad_five_sts;
	}
	public void setBad_five_sts(String bad_five_sts) {
		this.bad_five_sts = bad_five_sts;
	}
	public String getBetter_five_sts() {
		return better_five_sts;
	}
	public void setBetter_five_sts(String better_five_sts) {
		this.better_five_sts = better_five_sts;
	}
	public String getBr_depart() {
		return br_depart;
	}
	public void setBr_depart(String brDepart) {
		br_depart = brDepart;
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
	public String getWkf_cif_type() {
		return wkf_cif_type;
	}
	public void setWkf_cif_type(String wkfCifType) {
		wkf_cif_type = wkfCifType;
	}
	public String getProd_no() {
		return prod_no;
	}
	public void setProd_no(String prodNo) {
		prod_no = prodNo;
	}
	public String getWkf_risk_type() {
		return wkf_risk_type;
	}
	public void setWkf_risk_type(String wkfRiskType) {
		wkf_risk_type = wkfRiskType;
	}
	
}
