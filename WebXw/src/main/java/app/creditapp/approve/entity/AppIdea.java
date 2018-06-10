package app.creditapp.approve.entity;

import app.base.BaseDomain;

/**
 * 功能描述：审批意见表 表名：APP_IDEA
 * 
 * @see
 * @修改日志：
 * 
 */
public class AppIdea extends BaseDomain implements java.io.Serializable {
	public AppIdea() {
	}

	// 变量定义

	private String trace_no; // 审批流水号
	private String app_no; // 申请号
	private String appType;
	private String app_idea; // 审批意见
	private Double conf_amt; // 审批确认金额
	private Double conf_rate; // 审批确认利率
	private Double norm_rate; // 基准利率
	private String conf_grade; // 审批确认信用等级
	private String grade; // 信用等级
	private Double flt_ratio; // 浮动比例
	private Integer term_mon; // 期限月
	private Integer term_day; // 期限日
	private String app_desc; // 审批意见说明
	private String app_step; // 审批岗位
	private String op_no; // 操作员
	private String br_no; // 机构
	private String app_date; // 审批日期
	private String app_time; // 审批时间
	private String beg_time; // 审批任务接到时间
	private String end_time; // 审批任务完成时间
	private Double conf_flt_ratio; // 审批确认浮动比例
	private String begin_date;           //开始日期
	private String end_date;             //结束日期
	private Double pifu_amt; //批复金额
	private Double pifu_rate;//批复利率
	private Integer pifu_month;//批复期限月
	private Integer pifu_day;//批复期限日
	private String time_kind;//期限类型
	private String old_rate;//展期申请原申请利率
	private double hide_pifu;//隐藏的批复金额用于跟输入的批复金额比较
	private double rate_pifu;//利率
	
	private String nextUser;
	private String isAssignNext;
    private String cif_no;
    private String cif_name;
	private Integer bef_sts; // 分类前状态
	private Integer aft_sts; // 分类后状态
	private String app_idea_state;
	private String app_idea_seq;
	private String taskId;
	private String prdt_no;
	private String auth_type_apply;//1--授信申请;2--授信调整
	private String cif_type;
	private String prj_type_apply;
	private String activityType;
	private String work_item_id;
	private String hx_type;
	private String hx_cause;
	private double bal;
	private String handle_reason;
	private String handle_way;
	private double bo_pro;//按保证金比例扣划
	private String transition;
	private String base_rate_type; 	
	private String ic_type;// 利率浮动类型
	private String float_type; 	// 浮动方式
	private String due_ch_type;//贷后维护操作类型
	
	private String gage_name;//押品名称
	private String gage_type;//押品类型
	private String own_name;//所有权人
	private double bank_amt;//行内认定价格
	private double gage_ratio;//抵押押率
	
	//五级分类审批页面
	private String five_sts;//五级分类
	private String app_five_sts;//审批五级分类
	
	//不良资产处置方案审批页面
	private String dis_type;//处置方案
	private double pact_amt;//合同金额
	private String bad_reason;//形成不良原因
	private String prdt_name;//产品名称
	private double put_out_amt;//已发放金额
	private String opno_name;//客户经理
	private String ln_due_type;//出账类型
	private String is_mod;//判断是否是授信调整
	private String is_ice;//综合授信台账冻结、解冻标志
	private String task_type;//贷后监控类型
	private String iceReason;//冻结解冻原因
	private String ifLowRisk;
	private String position;//工作流岗位
	
	//集团客户认定审批页面信息
	private String ver_no;//家谱版本编号
	private String group_no;//集团编号
	private String group_name;//集团名称
	private String setup_date;//成立日期
	private String principal;//集团负责人
	private double reg_fund;//注册资本
	
	//授信申请审批
	private String if_apply_chn;//是否变更授信条件
	private String prod_no;//业务品种
	private String vou_type;//担保方式
	private String ln_use;//申请用途
	private Double amt;//授信金额
	private Double rate;//授信利率
	private Double app_amt;//审批金额
	private Double app_rate;//审批利率
	private Integer app_term_mon;//审批期限月
	private Integer app_term_day;//审批期限日
	private String vou_type_desc;//担保方式详述
	private String state_tfer_type;//形态转移类型
	private String pda_type;//抵债资产类型
	private Double plan_loan_amt;//拟核销金额
	private Double real_loan_amt;//实际核销额
	private String pay_type;//支付方式
	private String old_pay_type;//原支付方式
	private String new_repay_type;//新还款方式
	private String old_repay_type;//原还款方式
	
	
	//出账申请审批信息
	private String pact_no;//合同号
	public String getVer_no() {
		return ver_no;
	}
	public void setVer_no(String verNo) {
		ver_no = verNo;
	}
	public String getGroup_no() {
		return group_no;
	}
	public void setGroup_no(String groupNo) {
		group_no = groupNo;
	}
	public double getBo_pro() {
		return bo_pro;
	}
	public String getDis_type() {
		return dis_type;
	}
	public void setDis_type(String disType) {
		dis_type = disType;
	}
	public String getBad_reason() {
		return bad_reason;
	}
	public void setBad_reason(String badReason) {
		bad_reason = badReason;
	}
	public void setBo_pro(double boPro) {
		bo_pro = boPro;
	}
	public String getTrace_no() {
		return trace_no;
	}
	public void setTrace_no(String trace_no) {
		this.trace_no = trace_no;
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
	public Double getConf_amt() {
		return conf_amt;
	}
	public void setConf_amt(Double conf_amt) {
		this.conf_amt = conf_amt;
	}
	public Double getConf_rate() {
		return conf_rate;
	}
	public void setConf_rate(Double conf_rate) {
		this.conf_rate = conf_rate;
	}
	public Double getNorm_rate() {
		return norm_rate;
	}
	public void setNorm_rate(Double norm_rate) {
		this.norm_rate = norm_rate;
	}
	public String getConf_grade() {
		return conf_grade;
	}
	public void setConf_grade(String conf_grade) {
		this.conf_grade = conf_grade;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public Double getFlt_ratio() {
		return flt_ratio;
	}
	public void setFlt_ratio(Double flt_ratio) {
		this.flt_ratio = flt_ratio;
	}
	public Integer getTerm_mon() {
		return term_mon;
	}
	public void setTerm_mon(Integer term_mon) {
		this.term_mon = term_mon;
	}
	public Integer getTerm_day() {
		return term_day;
	}
	public void setTerm_day(Integer term_day) {
		this.term_day = term_day;
	}
	public String getApp_desc() {
		return app_desc;
	}
	public void setApp_desc(String app_desc) {
		this.app_desc = app_desc;
	}
	public String getApp_step() {
		return app_step;
	}
	public void setApp_step(String app_step) {
		this.app_step = app_step;
	}
	public String getOp_no() {
		return op_no;
	}
	public String getHandle_reason() {
		return handle_reason;
	}
	public void setHandle_reason(String handleReason) {
		handle_reason = handleReason;
	}
	public String getFive_sts() {
		return five_sts;
	}
	public void setFive_sts(String fiveSts) {
		five_sts = fiveSts;
	}
	public String getApp_five_sts() {
		return app_five_sts;
	}
	public void setApp_five_sts(String appFiveSts) {
		app_five_sts = appFiveSts;
	}
	public String getHandle_way() {
		return handle_way;
	}
	public void setHandle_way(String handleWay) {
		handle_way = handleWay;
	}
	public String getHx_type() {
		return hx_type;
	}
	public void setHx_type(String hxType) {
		hx_type = hxType;
	}
	public String getHx_cause() {
		return hx_cause;
	}
	public void setHx_cause(String hxCause) {
		hx_cause = hxCause;
	}
	public void setOp_no(String op_no) {
		this.op_no = op_no;
	}
	public String getAppType() {
		return appType;
	}
	public void setAppType(String appType) {
		this.appType = appType;
	}
	public String getBr_no() {
		return br_no;
	}
	public void setBr_no(String br_no) {
		this.br_no = br_no;
	}
	public String getApp_date() {
		return app_date;
	}
	public void setApp_date(String app_date) {
		this.app_date = app_date;
	}
	public String getApp_time() {
		return app_time;
	}
	public double getRate_pifu() {
		return rate_pifu;
	}
	public void setRate_pifu(double rate_pifu) {
		this.rate_pifu = rate_pifu;
	}
	public void setApp_time(String app_time) {
		this.app_time = app_time;
	}
	public String getBeg_time() {
		return beg_time;
	}
	public void setBeg_time(String beg_time) {
		this.beg_time = beg_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public Double getConf_flt_ratio() {
		return conf_flt_ratio;
	}
	public void setConf_flt_ratio(Double conf_flt_ratio) {
		this.conf_flt_ratio = conf_flt_ratio;
	}
	public String getBegin_date() {
		return begin_date;
	}
	public void setBegin_date(String begin_date) {
		this.begin_date = begin_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public double getBal() {
		return bal;
	}
	public void setBal(double bal) {
		this.bal = bal;
	}
	public Double getPifu_amt() {
		return pifu_amt;
	}
	public void setPifu_amt(Double pifu_amt) {
		this.pifu_amt = pifu_amt;
	}
	public Double getPifu_rate() {
		return pifu_rate;
	}
	public void setPifu_rate(Double pifu_rate) {
		this.pifu_rate = pifu_rate;
	}
	public Integer getPifu_month() {
		return pifu_month;
	}
	public void setPifu_month(Integer pifu_month) {
		this.pifu_month = pifu_month;
	}
	public Integer getPifu_day() {
		return pifu_day;
	}
	public void setPifu_day(Integer pifu_day) {
		this.pifu_day = pifu_day;
	}
	public String getNextUser() {
		return nextUser;
	}
	public double getHide_pifu() {
		return hide_pifu;
	}
	public void setHide_pifu(double hide_pifu) {
		this.hide_pifu = hide_pifu;
	}
	public void setNextUser(String nextUser) {
		this.nextUser = nextUser;
	}
	public String getIsAssignNext() {
		return isAssignNext;
	}
	public void setIsAssignNext(String isAssignNext) {
		this.isAssignNext = isAssignNext;
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
	public Integer getBef_sts() {
		return bef_sts;
	}
	public void setBef_sts(Integer bef_sts) {
		this.bef_sts = bef_sts;
	}
	public Integer getAft_sts() {
		return aft_sts;
	}
	public void setAft_sts(Integer aft_sts) {
		this.aft_sts = aft_sts;
	}
	public String getApp_idea_state() {
		return app_idea_state;
	}
	public void setApp_idea_state(String app_idea_state) {
		this.app_idea_state = app_idea_state;
	}
	public String getApp_idea_seq() {
		return app_idea_seq;
	}
	public void setApp_idea_seq(String app_idea_seq) {
		this.app_idea_seq = app_idea_seq;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getPrdt_no() {
		return prdt_no;
	}
	public void setPrdt_no(String prdt_no) {
		this.prdt_no = prdt_no;
	}
	public String getAuth_type_apply() {
		return auth_type_apply;
	}
	public void setAuth_type_apply(String auth_type_apply) {
		this.auth_type_apply = auth_type_apply;
	}
	public String getCif_type() {
		return cif_type;
	}
	public void setCif_type(String cif_type) {
		this.cif_type = cif_type;
	}
	public String getPrj_type_apply() {
		return prj_type_apply;
	}
	public void setPrj_type_apply(String prj_type_apply) {
		this.prj_type_apply = prj_type_apply;
	}
	public String getActivityType() {
		return activityType;
	}
	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}
	public String getWork_item_id() {
		return work_item_id;
	}
	public void setWork_item_id(String work_item_id) {
		this.work_item_id = work_item_id;
	}
	public String getTime_kind() {
		return time_kind;
	}
	public void setTime_kind(String time_kind) {
		this.time_kind = time_kind;
	}
	public String getOld_rate() {
		return old_rate;
	}
	public void setOld_rate(String old_rate) {
		this.old_rate = old_rate;
	}
	public String getTransition() {
		return transition;
	}
	public void setTransition(String transition) {
		this.transition = transition;
	}
	public String getBase_rate_type() {
		return base_rate_type;
	}
	public void setBase_rate_type(String baseRateType) {
		base_rate_type = baseRateType;
	}
	public String getFloat_type() {
		return float_type;
	}
	public void setFloat_type(String floatType) {
		float_type = floatType;
	}
	public String getDue_ch_type() {
		return due_ch_type;
	}
	public void setDue_ch_type(String dueChType) {
		due_ch_type = dueChType;
	}
	public String getGage_name() {
		return gage_name;
	}
	public void setGage_name(String gageName) {
		gage_name = gageName;
	}
	public String getGage_type() {
		return gage_type;
	}
	public void setGage_type(String gageType) {
		gage_type = gageType;
	}
	public String getOwn_name() {
		return own_name;
	}
	public void setOwn_name(String ownName) {
		own_name = ownName;
	}
	public double getBank_amt() {
		return bank_amt;
	}
	public void setBank_amt(double bankAmt) {
		bank_amt = bankAmt;
	}
	public double getGage_ratio() {
		return gage_ratio;
	}
	public void setGage_ratio(double gageRatio) {
		gage_ratio = gageRatio;
	}
	public String getPrdt_name() {
		return prdt_name;
	}
	public void setPrdt_name(String prdtName) {
		prdt_name = prdtName;
	}
	public String getOpno_name() {
		return opno_name;
	}
	public void setOpno_name(String opnoName) {
		opno_name = opnoName;
	}
	public double getPact_amt() {
		return pact_amt;
	}
	public void setPact_amt(double pactAmt) {
		pact_amt = pactAmt;
	}
	public double getPut_out_amt() {
		return put_out_amt;
	}
	public void setPut_out_amt(double putOutAmt) {
		put_out_amt = putOutAmt;
	}
	public String getLn_due_type() {
		return ln_due_type;
	}
	public void setLn_due_type(String lnDueType) {
		ln_due_type = lnDueType;
	}
	public String getIs_mod() {
		return is_mod;
	}
	public void setIs_mod(String isMod) {
		is_mod = isMod;
	}
	public String getIc_type() {
		return ic_type;
	}
	public void setIc_type(String icType) {
		ic_type = icType;
	}
	public String getIs_ice() {
		return is_ice;
	}
	public void setIs_ice(String isIce) {
		is_ice = isIce;
	}
	public String getTask_type() {
		return task_type;
	}
	public void setTask_type(String taskType) {
		task_type = taskType;
	}
	public String getIceReason() {
		return iceReason;
	}
	public void setIceReason(String iceReason) {
		this.iceReason = iceReason;
	}
	public String getIfLowRisk() {
		return ifLowRisk;
	}
	public void setIfLowRisk(String ifLowRisk) {
		this.ifLowRisk = ifLowRisk;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String groupName) {
		group_name = groupName;
	}
	public String getSetup_date() {
		return setup_date;
	}
	public void setSetup_date(String setupDate) {
		setup_date = setupDate;
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
	public void setReg_fund(Double regFund) {
		reg_fund = regFund;
	}
	public String getIf_apply_chn() {
		return if_apply_chn;
	}
	public void setIf_apply_chn(String ifApplyChn) {
		if_apply_chn = ifApplyChn;
	}
	public String getProd_no() {
		return prod_no;
	}
	public void setProd_no(String prodNo) {
		prod_no = prodNo;
	}
	public String getVou_type() {
		return vou_type;
	}
	public void setVou_type(String vouType) {
		vou_type = vouType;
	}
	public String getLn_use() {
		return ln_use;
	}
	public void setLn_use(String lnUse) {
		ln_use = lnUse;
	}
	public Double getAmt() {
		return amt;
	}
	public void setAmt(Double amt) {
		this.amt = amt;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public Double getApp_amt() {
		return app_amt;
	}
	public void setApp_amt(Double appAmt) {
		app_amt = appAmt;
	}
	public Double getApp_rate() {
		return app_rate;
	}
	public void setApp_rate(Double appRate) {
		app_rate = appRate;
	}
	public Integer getApp_term_mon() {
		return app_term_mon;
	}
	public void setApp_term_mon(Integer appTermMon) {
		app_term_mon = appTermMon;
	}
	public Integer getApp_term_day() {
		return app_term_day;
	}
	public void setApp_term_day(Integer appTermDay) {
		app_term_day = appTermDay;
	}
	public String getVou_type_desc() {
		return vou_type_desc;
	}
	public void setVou_type_desc(String vouTypeDesc) {
		vou_type_desc = vouTypeDesc;
	}
	public void setReg_fund(double regFund) {
		reg_fund = regFund;
	}
	public String getPact_no() {
		return pact_no;
	}
	public void setPact_no(String pactNo) {
		pact_no = pactNo;
	}
	public String getState_tfer_type() {
		return state_tfer_type;
	}
	public void setState_tfer_type(String stateTferType) {
		state_tfer_type = stateTferType;
	}
	public String getPda_type() {
		return pda_type;
	}
	public void setPda_type(String pdaType) {
		pda_type = pdaType;
	}
	public Double getPlan_loan_amt() {
		return plan_loan_amt;
	}
	public void setPlan_loan_amt(Double planLoanAmt) {
		plan_loan_amt = planLoanAmt;
	}
	public Double getReal_loan_amt() {
		return real_loan_amt;
	}
	public void setReal_loan_amt(Double realLoanAmt) {
		real_loan_amt = realLoanAmt;
	}
	public String getPay_type() {
		return pay_type;
	}
	public void setPay_type(String payType) {
		pay_type = payType;
	}
	public String getNew_repay_type() {
		return new_repay_type;
	}
	public void setNew_repay_type(String newRepayType) {
		new_repay_type = newRepayType;
	}
	public String getOld_repay_type() {
		return old_repay_type;
	}
	public void setOld_repay_type(String oldRepayType) {
		old_repay_type = oldRepayType;
	}
	public String getOld_pay_type() {
		return old_pay_type;
	}
	public void setOld_pay_type(String oldPayType) {
		old_pay_type = oldPayType;
	}
	
}
