package app.creditapp.approve.entity;
import app.base.BaseDomain;
/**
 * @description 	审批意见表单页面实体通用类
 * @version 1.0
 */
public class ApproveForm extends BaseDomain {
	
	private String cif_no;//客户号
	private String cif_name;//客户名称
	private String app_no;//申请号
	private String app_idea;//审批意见
	private String idea_desc;//意见描述
	private String task_id;//审批任务编号
	private Double amt;//金额
	private Double app_amt;//审批金额
	private String vou_type;//担保方式
	private String ln_use;//贷款用途
	private Double rate;//利率
	private Double app_rate;//审批利率
	private String prod_no;//产品号
	private String prod_name;//产品名
	private String rollBackNode;//退回节点
	private String app_type;//审批类型
	private String flow_id;//流程编号
	private String role_no;//审批岗位
	private String task_type;//任务类型
	private String nextNode;//下一节点
	private String flowKey;//流程标示
	private String app_item;//批复要素
	private String risk_point;//风险要点
	private String app_content;//审批内容
	private Integer term_mon;//期限月
	private Integer app_term_mon;//审批期限月
	private Integer term_day;//期限日
	private Integer app_term_day;//审批期限日
	private String five_sts;//五级分类状态
	private String app_five_sts;//审批五级分类
	private String dis_type;//处置方案
	private String bad_reason;//不良原因
	private Double old_app_amt;//原申请金额
	private Double old_bal;//原申请余额
	private Double fee_rate;//手续费率
	private Double fee_amt;//手续费金额
	private Double pact_amt;//合同金额
	private Double pbc_base_rate;//人行同期同档次基准年利率
	private Double base_rate;//行内行业基准年利率
	private Double risk_rate;//风险溢价系数
	private Double rate_rebate;//综合贡献度折扣
	private Double ln_rate;//执行年利率
	private String flt_type;//利率浮动方式
	private Double flt_rate;//利率浮动值
	private String beg_date;//生效日期
	private String adj_rate_type;//待调整利率类型
	private String fine_cal_type;//罚息结息方式
	private Double fine_rate;//罚息利率
	private String cmpd_rate_type;//复利结息方式
	private Double cmpd_rate;//复息利率
	private String main_busi;//主营业务
	private String principal;//集团负责人
	private Double reg_fund;//注册资本
	private String sign_no;//预警信号
	private String if_action;//是否紧急行动
	private String action_type;//紧急行动措施
	private String free_reason;//建议解除预警原因
	private String payee_name_old;//原收款人全称
	private String payee_acno_old;//原收款人帐号
	private String payee_bank_old;//原收款人开户行
	private String rep_bnkno_old;//原收款行行号
	private String payee_name;//收款人全称
	private String payee_acno;//收款人帐号
	private String payee_bank;//收款人开户行
	private String rep_bnkno;//收款行行号 
	private String apply_type;
	private String reason;
	private String vou_type_desc;
	private String pact_no;
	private Double pay_sum; 
	private Double pay_inte;
	private Double break_amt;
	private Double less_bre_amt;
	private String return_id;
	private String return_type;
	private String return_way;
	private String chane_plan_way;
	private String pay_inte_way;
	private String Old_repay_type;
	private String new_repay_type;
	private String old_pay_type;//原支付方式
	private String new_pay_type;//新支付方式
	private String asse_name;//资产池名称
	private Double asse_amt;  //本金
	private Double asse_bal;  //本金余额
	private String asse_type;//资产池名称
	private String state_tfer_type;//形态转移类型
	private String old_four_sts;//原四级分类状态
	private String four_sts;//四级分类状态
	private String old_non_arl_sts;//原非应计状态
	private String non_arl_sts;//非应计状态
	private String bf_name;
	private String proj_name;
	private String sc_name;
	private String end_date;
	private String gage_name;
	private Double bank_amt;
	private String pda_type;
	private String pda_model;
	private String gage_loc;
	private String if_apply_chn;//是否变更授信条件
	
	public Double getBank_amt() {
		return bank_amt;
	}
	public void setBank_amt(Double bankAmt) {
		bank_amt = bankAmt;
	}
	public String getGage_name() {
		return gage_name;
	}
	public void setGage_name(String gageName) {
		gage_name = gageName;
	}
	public String getPda_type() {
		return pda_type;
	}
	public void setPda_type(String pdaType) {
		pda_type = pdaType;
	}
	public String getPda_model() {
		return pda_model;
	}
	public void setPda_model(String pdaModel) {
		pda_model = pdaModel;
	}
	public String getGage_loc() {
		return gage_loc;
	}
	public void setGage_loc(String gageLoc) {
		gage_loc = gageLoc;
	}
	public String getAsse_type() {
		return asse_type;
	}
	public void setAsse_type(String asseType) {
		asse_type = asseType;
	}
	public Double getAmt() {
		return amt;
	}
	public void setAmt(Double amt) {
		this.amt = amt;
	}
	public String getVou_type() {
		return vou_type;
	}
	public void setVou_type(String vou_type) {
		this.vou_type = vou_type;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public String getProd_no() {
		return prod_no;
	}
	public void setProd_no(String prod_no) {
		this.prod_no = prod_no;
	}
	public String getCif_no() {
		return cif_no;
	}
	public void setCif_no(String cif_no) {
		this.cif_no = cif_no;
	}
	public String getCif_name() {
		return cif_name;
	}
	public void setCif_name(String cif_name) {
		this.cif_name = cif_name;
	}
	public String getApp_no() {
		return app_no;
	}
	public void setApp_no(String app_no) {
		this.app_no = app_no;
	}
	public String getApp_idea() {
		return app_idea;
	}
	public void setApp_idea(String app_idea) {
		this.app_idea = app_idea;
	}
	public String getIdea_desc() {
		return idea_desc;
	}
	public void setIdea_desc(String idea_desc) {
		this.idea_desc = idea_desc;
	}
	public String getTask_id() {
		return task_id;
	}
	public void setTask_id(String task_id) {
		this.task_id = task_id;
	}
	public Double getApp_amt() {
		return app_amt;
	}
	public void setApp_amt(Double app_amt) {
		this.app_amt = app_amt;
	}
	public Double getApp_rate() {
		return app_rate;
	}
	public void setApp_rate(Double app_rate) {
		this.app_rate = app_rate;
	}
	public String getRollBackNode() {
		return rollBackNode;
	}
	public void setRollBackNode(String rollBackNode) {
		this.rollBackNode = rollBackNode;
	}
	public String getApp_type() {
		return app_type;
	}
	public void setApp_type(String app_type) {
		this.app_type = app_type;
	}
	public String getFlow_id() {
		return flow_id;
	}
	public void setFlow_id(String flow_id) {
		this.flow_id = flow_id;
	}
	public String getRole_no() {
		return role_no;
	}
	public void setRole_no(String role_no) {
		this.role_no = role_no;
	}
	public String getTask_type() {
		return task_type;
	}
	public void setTask_type(String task_type) {
		this.task_type = task_type;
	}
	public String getNextNode() {
		return nextNode;
	}
	public void setNextNode(String nextNode) {
		this.nextNode = nextNode;
	}
	public String getFlowKey() {
		return flowKey;
	}
	public void setFlowKey(String flowKey) {
		this.flowKey = flowKey;
	}
	public String getApp_item() {
		return app_item;
	}
	public void setApp_item(String app_item) {
		this.app_item = app_item;
	}
	public String getRisk_point() {
		return risk_point;
	}
	public void setRisk_point(String risk_point) {
		this.risk_point = risk_point;
	}
	public String getApp_content() {
		return app_content;
	}
	public void setApp_content(String app_content) {
		this.app_content = app_content;
	}
	public Integer getTerm_mon() {
		return term_mon;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String endDate) {
		end_date = endDate;
	}
	public void setTerm_mon(Integer term_mon) {
		this.term_mon = term_mon;
	}
	public Integer getApp_term_mon() {
		return app_term_mon;
	}
	public void setApp_term_mon(Integer app_term_mon) {
		this.app_term_mon = app_term_mon;
	}
	public Integer getTerm_day() {
		return term_day;
	}
	public void setTerm_day(Integer term_day) {
		this.term_day = term_day;
	}
	public Integer getApp_term_day() {
		return app_term_day;
	}
	public void setApp_term_day(Integer app_term_day) {
		this.app_term_day = app_term_day;
	}
	public String getFive_sts() {
		return five_sts;
	}
	public void setFive_sts(String five_sts) {
		this.five_sts = five_sts;
	}
	public String getApp_five_sts() {
		return app_five_sts;
	}
	public void setApp_five_sts(String app_five_sts) {
		this.app_five_sts = app_five_sts;
	}
	public String getDis_type() {
		return dis_type;
	}
	public void setDis_type(String dis_type) {
		this.dis_type = dis_type;
	}
	public String getBad_reason() {
		return bad_reason;
	}
	public void setBad_reason(String bad_reason) {
		this.bad_reason = bad_reason;
	}
	public Double getOld_app_amt() {
		return old_app_amt;
	}
	public void setOld_app_amt(Double old_app_amt) {
		this.old_app_amt = old_app_amt;
	}
	public Double getOld_bal() {
		return old_bal;
	}
	public void setOld_bal(Double old_bal) {
		this.old_bal = old_bal;
	}
	public Double getFee_rate() {
		return fee_rate;
	}
	public void setFee_rate(Double fee_rate) {
		this.fee_rate = fee_rate;
	}
	public Double getFee_amt() {
		return fee_amt;
	}
	public void setFee_amt(Double fee_amt) {
		this.fee_amt = fee_amt;
	}
	public Double getPact_amt() {
		return pact_amt;
	}
	public void setPact_amt(Double pact_amt) {
		this.pact_amt = pact_amt;
	}
	public Double getPbc_base_rate() {
		return pbc_base_rate;
	}
	public void setPbc_base_rate(Double pbc_base_rate) {
		this.pbc_base_rate = pbc_base_rate;
	}
	public Double getBase_rate() {
		return base_rate;
	}
	public void setBase_rate(Double base_rate) {
		this.base_rate = base_rate;
	}
	public Double getRisk_rate() {
		return risk_rate;
	}
	public String getIf_apply_chn() {
		return if_apply_chn;
	}
	public void setIf_apply_chn(String ifApplyChn) {
		if_apply_chn = ifApplyChn;
	}
	public void setRisk_rate(Double risk_rate) {
		this.risk_rate = risk_rate;
	}
	public Double getRate_rebate() {
		return rate_rebate;
	}
	public void setRate_rebate(Double rate_rebate) {
		this.rate_rebate = rate_rebate;
	}
	public Double getLn_rate() {
		return ln_rate;
	}
	public void setLn_rate(Double ln_rate) {
		this.ln_rate = ln_rate;
	}
	public String getFlt_type() {
		return flt_type;
	}
	public void setFlt_type(String flt_type) {
		this.flt_type = flt_type;
	}
	public Double getFlt_rate() {
		return flt_rate;
	}
	public void setFlt_rate(Double flt_rate) {
		this.flt_rate = flt_rate;
	}
	public String getBeg_date() {
		return beg_date;
	}
	public void setBeg_date(String beg_date) {
		this.beg_date = beg_date;
	}
	public String getAdj_rate_type() {
		return adj_rate_type;
	}
	public void setAdj_rate_type(String adj_rate_type) {
		this.adj_rate_type = adj_rate_type;
	}
	public String getFine_cal_type() {
		return fine_cal_type;
	}
	public void setFine_cal_type(String fine_cal_type) {
		this.fine_cal_type = fine_cal_type;
	}
	public Double getFine_rate() {
		return fine_rate;
	}
	public String getVou_type_desc() {
		return vou_type_desc;
	}
	public void setVou_type_desc(String vouTypeDesc) {
		vou_type_desc = vouTypeDesc;
	}
	public void setFine_rate(Double fine_rate) {
		this.fine_rate = fine_rate;
	}
	public String getCmpd_rate_type() {
		return cmpd_rate_type;
	}
	public void setCmpd_rate_type(String cmpd_rate_type) {
		this.cmpd_rate_type = cmpd_rate_type;
	}
	public Double getCmpd_rate() {
		return cmpd_rate;
	}
	public void setCmpd_rate(Double cmpd_rate) {
		this.cmpd_rate = cmpd_rate;
	}
	public String getMain_busi() {
		return main_busi;
	}
	public void setMain_busi(String main_busi) {
		this.main_busi = main_busi;
	}
	public String getPrincipal() {
		return principal;
	}
	public void setPrincipal(String principal) {
		this.principal = principal;
	}
	public Double getReg_fund() {
		return reg_fund;
	}
	public void setReg_fund(Double reg_fund) {
		this.reg_fund = reg_fund;
	}
	public String getSign_no() {
		return sign_no;
	}
	public void setSign_no(String signNo) {
		sign_no = signNo;
	}
	public String getFree_reason() {
		return free_reason;
	}
	public void setFree_reason(String freeReason) {
		free_reason = freeReason;
	}
	public String getIf_action() {
		return if_action;
	}
	public void setIf_action(String ifAction) {
		if_action = ifAction;
	}
	public String getAction_type() {
		return action_type;
	}
	public void setAction_type(String actionType) {
		action_type = actionType;
	}
	public String getPayee_name_old() {
		return payee_name_old;
	}
	public void setPayee_name_old(String payeeNameOld) {
		payee_name_old = payeeNameOld;
	}
	public String getPayee_acno_old() {
		return payee_acno_old;
	}
	public void setPayee_acno_old(String payeeAcnoOld) {
		payee_acno_old = payeeAcnoOld;
	}
	public String getPayee_bank_old() {
		return payee_bank_old;
	}
	public void setPayee_bank_old(String payeeBankOld) {
		payee_bank_old = payeeBankOld;
	}
	public String getRep_bnkno_old() {
		return rep_bnkno_old;
	}
	public void setRep_bnkno_old(String repBnknoOld) {
		rep_bnkno_old = repBnknoOld;
	}
	public String getPayee_name() {
		return payee_name;
	}
	public void setPayee_name(String payeeName) {
		payee_name = payeeName;
	}
	public String getPayee_acno() {
		return payee_acno;
	}
	public void setPayee_acno(String payeeAcno) {
		payee_acno = payeeAcno;
	}
	public String getPayee_bank() {
		return payee_bank;
	}
	public void setPayee_bank(String payeeBank) {
		payee_bank = payeeBank;
	}
	public String getRep_bnkno() {
		return rep_bnkno;
	}
	public void setRep_bnkno(String repBnkno) {
		rep_bnkno = repBnkno;
	}
	public String getApply_type() {
		return apply_type;
	}
	public void setApply_type(String applyType) {
		apply_type = applyType;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getProd_name() {
		return prod_name;
	}
	public void setProd_name(String prodName) {
		prod_name = prodName;
	}
	public String getLn_use() {
		return ln_use;
	}
	public void setLn_use(String lnUse) {
		ln_use = lnUse;
	}
	public String getPact_no() {
		return pact_no;
	}
	public void setPact_no(String pactNo) {
		pact_no = pactNo;
	}
	public Double getPay_sum() {
		return pay_sum;
	}
	public void setPay_sum(Double paySum) {
		pay_sum = paySum;
	}
	public Double getPay_inte() {
		return pay_inte;
	}
	public void setPay_inte(Double payInte) {
		pay_inte = payInte;
	}
	public Double getBreak_amt() {
		return break_amt;
	}
	public void setBreak_amt(Double breakAmt) {
		break_amt = breakAmt;
	}
	public Double getLess_bre_amt() {
		return less_bre_amt;
	}
	public void setLess_bre_amt(Double lessBreAmt) {
		less_bre_amt = lessBreAmt;
	}
	public String getReturn_type() {
		return return_type;
	}
	public void setReturn_type(String returnType) {
		return_type = returnType;
	}
	public String getReturn_way() {
		return return_way;
	}
	public void setReturn_way(String returnWay) {
		return_way = returnWay;
	}
	public String getChane_plan_way() {
		return chane_plan_way;
	}
	public void setChane_plan_way(String chanePlanWay) {
		chane_plan_way = chanePlanWay;
	}
	public String getPay_inte_way() {
		return pay_inte_way;
	}
	public void setPay_inte_way(String payInteWay) {
		pay_inte_way = payInteWay;
	}
	public String getReturn_id() {
		return return_id;
	}
	public void setReturn_id(String returnId) {
		return_id = returnId;
	}
	public String getOld_repay_type() {
		return Old_repay_type;
	}
	public void setOld_repay_type(String oldRepayType) {
		Old_repay_type = oldRepayType;
	}
	public String getNew_repay_type() {
		return new_repay_type;
	}
	public void setNew_repay_type(String newRepayType) {
		new_repay_type = newRepayType;
	}
	public String getAsse_name() {
		return asse_name;
	}
	public void setAsse_name(String asseName) {
		asse_name = asseName;
	}
	public Double getAsse_amt() {
		return asse_amt;
	}
	public void setAsse_amt(Double asseAmt) {
		asse_amt = asseAmt;
	}
	public Double getAsse_bal() {
		return asse_bal;
	}
	public void setAsse_bal(Double asseBal) {
		asse_bal = asseBal;
	}
	public String getState_tfer_type() {
		return state_tfer_type;
	}
	public void setState_tfer_type(String stateTferType) {
		state_tfer_type = stateTferType;
	}
	public String getOld_four_sts() {
		return old_four_sts;
	}
	public void setOld_four_sts(String oldFourSts) {
		old_four_sts = oldFourSts;
	}
	public String getFour_sts() {
		return four_sts;
	}
	public void setFour_sts(String fourSts) {
		four_sts = fourSts;
	}
	public String getOld_non_arl_sts() {
		return old_non_arl_sts;
	}
	public void setOld_non_arl_sts(String oldNonArlSts) {
		old_non_arl_sts = oldNonArlSts;
	}
	public String getNon_arl_sts() {
		return non_arl_sts;
	}
	public void setNon_arl_sts(String nonArlSts) {
		non_arl_sts = nonArlSts;
	}
	public String getBf_name() {
		return bf_name;
	}
	public void setBf_name(String bfName) {
		bf_name = bfName;
	}
	public String getProj_name() {
		return proj_name;
	}
	public void setProj_name(String projName) {
		proj_name = projName;
	}
	public String getSc_name() {
		return sc_name;
	}
	public void setSc_name(String scName) {
		sc_name = scName;
	}
	public String getOld_pay_type() {
		return old_pay_type;
	}
	public void setOld_pay_type(String oldPayType) {
		old_pay_type = oldPayType;
	}
	public String getNew_pay_type() {
		return new_pay_type;
	}
	public void setNew_pay_type(String newPayType) {
		new_pay_type = newPayType;
	}
	
}