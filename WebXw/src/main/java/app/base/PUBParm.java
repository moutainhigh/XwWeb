package app.base;

public class PUBParm {
	
	/**
	 * 客户业务类型
	 */
	public static final String OLD_NEW_IND = "0"; // 第一代信贷和第二代信贷标识
	
	public static final String CIF_BIZ_TYPE_AFFILIATE = "6";//挂靠公司
	public static final String CIF_BIZ_TYPE_INVESTOR = "5";//出资人
	public static final String CIF_BIZ_TYPE_PARTNER = "4";//合作伙伴
	public static final String CIF_BIZ_TYPE_SUPPLIER = "3";//供应商
	public static final String CIF_BIZ_TYPE_DEALER = "2";//经销商
	public static final String CIF_BIZ_TYPE_CONSUMER = "1";//业务客户
	
	public static final String CIF_FORMAL_SEPARATOR = "|";//客户标识分隔符
	
	public static final String CIF_CORP_FORMAL_LESSEE = "1";//承租人
	public static final String CIF_CORP_FORMAL_GUARANTOR = "2";//保证人
	public static final String CIF_CORP_FORMAL_SUPPLIER = "3";//供货商
	public static final String CIF_CORP_FORMAL_DEALER = "4";//经销商
	public static final String CIF_CORP_FORMAL_PARTNER = "5";//其他合作伙伴
	public static final String CIF_CORP_FORMAL_AFFILIATE = "6";//挂靠公司
	public static final String CIF_CORP_FORMAL_MORTGAGOR = "7";//抵押人
	public static final String CIF_CORP_FORMAL_PLEDGOR = "8";//质押人

	public static final String CIF_PERS_FORMAL_LESSEE = "1";//承租人
	public static final String CIF_PERS_FORMAL_GUARANTOR = "2";//保证人
	public static final String CIF_PERS_FORMAL_MORTGAGOR = "3";//供货商
	public static final String CIF_PERS_FORMAL_PLEDGOR = "4";//经销商
	
	
	/**
	 * 租赁物状态
	 */
	public static String LAM_STATUS_CODE_1ST = "1";	//经销商录入           新增租赁物状态为1
	public static String LAM_STATUS_CODE_2ND = "2";	//申请提交                申请提交时，调租赁物接口将申请下所有租赁物状态该为申请提交
	public static String LAM_STATUS_CODE_3TH = "3";	//放款信息完成      放款车辆信息修改保存时更改为3
	public static String LAM_STATUS_CODE_5TH = "5";	//生效                          核算通讯放款成功后，修改业务台账状态时修改车的状态为生效    
	public static String LAM_STATUS_CODE_6TH = "6";	//失效                         产权转移时，调租赁物接口将车状态改为失效
	public static String LAM_STATUS_CODE_8TH = "8";	//否决                          申请否决时，调租赁物接口将申请下所有租赁物的状态改为否决
	public static String LAM_STATUS_CODE_9TH = "9";	//核销                          核销操作时，调租赁物接口将车状态改为核销
	/**
	 * 租赁合同状态
	 */
	public static final String PACT_STS_1TH = "1"; // 未签订
	public static final String PACT_STS_2TH = "2"; // 正常
	public static final String PACT_STS_3TH = "3"; // 逾期30天以内
	public static final String PACT_STS_4TH = "4"; // 逾期30到60
	public static final String PACT_STS_5TH = "5"; // 逾期60到90
	public static final String PACT_STS_6TH = "6"; // 逾期90天以上
	public static final String PACT_STS_7TH = "7"; // 已注销
	public static final String PACT_STS_8TH = "8"; // 未使用已注销
	
	/**
	 * 租赁台账状态
	 */
	public static final String ACC_STATUS_0TH ="0";//出帐未确认
	public static final String ACC_STATUS_1TH ="1";//正常
	public static final String ACC_STATUS_2TH ="2";//撤销
	public static final String ACC_STATUS_8TH ="8";//核销
	public static final String ACC_STATUS_9TH ="9";//已还清
	
		
	public static final String CIF_PERS_CHARACTER_NORMAL = "1";//一般客户：正常申请
	public static final String CIF_PERS_CHARACTER_HANDLER = "2";//经销商的高管：基准利率
	public static final String CIF_PERS_CHARACTER_STAFF = "3";//公司内部员工：基准利率-10%

	/**
	 * 系统登录用户角色类型
	 */
	public static final String ROLE_TYPE_INVESTOR = "SH100";//出资人
	public static final String ROLE_TYPE_PARTNER = "SH100";//合作伙伴
	public static final String ROLE_TYPE_SUPPLIER = "SH004";//供应商
	public static final String ROLE_TYPE_DEALER = "SH003";//经销商(自身业务)
	public static final String ROLE_TYPE_CONSUMER = "SH001";//业务客户
	public static final String ROLE_TYPE_DEALER_S = "SH005";//经销商(供货商业务)
	public static final String ROLE_TYPE_ADVISERS = "SH108";//金融顾问

	public static String CIF_TYPE_PERS = "2";//个人客户
	public static String CIF_TYPE_CORP = "1";//对公客户
	
	/**
	 * 客户细分类型
	 */
	public static String PJE_CUST_TYPE_STORE = "10";//经营户
	public static String PJE_CUST_TYPE_NAT = "11";//自然人
	public static String PJE_CUST_TYPE_CORP = "12";//法人客户
	
	/**
	 * 金融产品类型
	 */
	public static String PRD_TYPE_COMMERCIAL = "PRD0003";//商用车
	public static String PRD_TYPE_PASSENGER = "PRD0002";//乘用车
	public static String PRD_TYPE_MACHINE = "PRD0001";//工程机械
	
	
	public static final String AUTOCODE_TYP_LNP_LOAN = "CreateKey.LnpLoan.getKey"; // 贷款合同管理
	public static final String AUTOCODE_TYP_LNP_WLOAN = "CreateKey.LnpWLoan.getKey"; // 委托贷款合同管理
	public static final String AUTOCODE_TYP_LNP_LNEX = "CreateKey.LnpLoanExtend.getKey"; // 展期协议管理
	public static final String AUTOCODE_TYP_LNP_ACCP = "CreateKey.LnpAccp.getKey"; // 银承协议管理
	public static final String AUTOCODE_TYP_LNP_CVRG = "CreateKey.LnpCvrg.getKey"; // 保函协议管理
	public static final String AUTOCODE_TYP_LNP_DISC = "CreateKey.LnpDisc.getKey"; // 贴现协议管理
	public static final String AUTOCODE_TYP_LNP_REDISCIN = "CreateKey.LnpRediscin.getKey"; // 转贴现买入协议管理
	public static final String AUTOCODE_TYP_LNP_REDISCOUT = "CreateKey.LnpRediscout.getKey"; // 转贴现卖出协议管理
	public static final String AUTOCODE_TYP_LNP_CMT = "CreateKey.LnpCmt.getKey"; // 承诺协议管理
	public static final String AUTOCODE_TYP_LNP_PACK = "CreateKey.LnpPack.getKey"; // 打包贷款协议管理
	public static final String AUTOCODE_TYP_LNP_CRD = "CreateKey.LnpCrd.getKey"; // 信用证协议管理
	public static final String AUTOCODE_TYP_LNP_CRDADJUST = "CreateKey.LnpCrdAdjust.getKey"; // 信用证变更协议管理
	public static final String AUTOCODE_TYP_LNP_FACTORING = "CreateKey.LnpFactoring.getKey"; // 国内、出口保理协议管理
	public static final String AUTOCODE_TYP_LNP_INFACTORING = "CreateKey.LnpInfactoring.getKey"; // 进口保理协议管理
	public static final String AUTOCODE_TYP_LNP_BP = "CreateKey.LnpBp.getKey"; // 出口押汇协议管理
	public static final String AUTOCODE_TYP_LNP_BE = "CreateKey.LnpBe.getKey"; // 进口押汇协议管理
	public static final String AUTOCODE_TYP_LNP_FORFEITING = "CreateKey.LnpForfeiting.getKey"; // 福费廷协议管理
	public static final String AUTOCODE_TYP_LNP_DO_GTY = "CreateKey.LnpDoGty.getKey"; // 提货担保协议管理
	public static final String AUTOCODE_TYP_LNP_PRJ = "CreateKey.LnpPrj.getKey"; // 合作方协议管理
	public static final String AUTOCODE_TYP_PRD = "CreateKey.prd.getKey"; // 产品配置
	public static final String AUTOCODE_TYP_PRD_M = "CreateKey.prdm.getKey"; // 销售产品
	public static final String AUTOCODE_TYP_GRT = "CreateKey.grt.getKey"; // 担保管理
	public static final String AUTOCODE_TYP_LAM = "CreateKey.lam.getKey"; // 租赁物管理
	public static final String AUTOCODE_TYP_AC_TRACE = "CreateKey.acTraceLog.getKey";//交易流水
	public static final String AUTOCODE_TYP_AC_ATPY = "CreateKey.lmAtpyTraceNo.getKey";//互联流水号
	public static final String AUTOCODE_TYP_RCL_RECALL_BASE = "CreateKey.rclRecallBase.getKey";//催收人工发起序列
	public static final String AUTOCODE_TYP_AUTH_SUB = "CreateKey.authSub.getKey";//交易流水
	public static final String AUTOCODE_TYP_FUND_ID = "CreateKey.fundId.getKey";//资金明细流水号
	public static final String AUTOCODE_TYP_ATPY_ID = "CreateKey.atpyId.getKey";//批扣明细流水号
	public static final String AUTOCODE_AC_RECT_REG= "CreateKey.acRectReg.getKey"; // 纠错登记序列
	
	/*
	 * 金融产品类型
	 */
	public static final String DUE_GRT_REL_1ST = "1";//放款前抵押
	public static final String DUE_GRT_REL_2ND = "2";//放款后抵押
	
	
	/*
	 * 申请状态
	 */
	public static final String APP_STS_1TH = "1"; // 待审批
	public static final String APP_STS_2TH = "2"; // 审批中
	public static final String APP_STS_3TH = "3"; // 审批通过
	public static final String APP_STS_4TH = "4"; // 审批否决
	public static final String APP_STS_5TH = "5"; // 发回重审

	public static final String WKF_APP_STS_ZERO = "0"; // 未提交
	public static final String WKF_APP_STS_1ST = "1"; // 流程中
	public static final String WKF_APP_STS_2ND = "2"; // 退回
	public static final String WKF_APP_STS_3RD = "3"; // 否决
	public static final String WKF_APP_STS_4TH = "4"; // 通过
	public static final String WKF_APP_STS_6TH = "6"; // 上传资料



	
			
	/*
	 * 出帐类型
	 */
	public static final String CONT_LND_1TH = "0";// 未出帐
	public static final String CONT_LND_2TH = "1";// 部分出帐
	public static final String CONT_LND_3TH = "2";// 全部出帐
	/*
	 * 担保合同类型
	 */
	public static final String VOU_CONT_TYPE_1ST = "1";// 一般担保合同
	public static final String VOU_CONT_TYPE_2ND = "2";// 最高额担保合同
	
	/*
	 * 担保合同状态
	 */
	public static final String PLUS_PACT_STS_1ST = "1"; // 生效
	public static final String PLUS_PACT_STS_2ND = "2"; // 注销
	public static final String PLUS_PACT_STS_3RD = "3"; // 已校验
	public static final String PLUS_PACT_STS_ZERO = "0"; // 未生效
	
	// 抵质押物状态
	public static final String GP_STATUS_CODE_1ST = "10001"; // 登记
	public static final String GP_STATUS_CODE_3RD = "10003"; // 占用待担保
	public static final String GP_STATUS_CODE_4TH = "10004"; // 担保
	public static final String GP_STATUS_CODE_5TH = "10005"; // 入库未记账
	public static final String GP_STATUS_CODE_6TH = "10006"; // 入库已记账
	public static final String GP_STATUS_CODE_7TH = "10007"; // 出库未记账
	public static final String GP_STATUS_CODE_8TH = "10008"; // 出库已核销
	public static final String GP_STATUS_CODE_9TH = "10009"; // 未处理
	public static final String GP_STATUS_CODE_10TH = "10010"; // 它项已补录
	
	// 质押物类型

	public static final String GRT_P_METAL = "10011"; // 贵金属,grt0302,grtpmetal
	public static final String GRT_P_NATIONAL_DBT = "10012"; // 债券,grt0310,grtpnationaldbt
	public static final String GRT_P_REAL_POLICY = "10013"; // 保单,grt0309,grtppolicy
	public static final String GRT_P_BILL = "10014"; // 票据,grt0307,grtpbill
	public static final String GRT_P_DEPOT = "10015"; // 仓单/提单,grt0312,grtpdepot
	public static final String GRT_P_STOCK = "10016"; // 股权/股票/基金,grt0311,grtpstock
	public static final String GRT_P_DEPOSIT_REC = "10017"; // 存单,grt0305,grtpdepositrec
	public static final String GRT_P_ACCOUNT_REC = "10018"; // 应收账款,grt0314,grtpaccountrec
	public static final String GRT_P_STORE = "10019"; // 商铺使用权,grt0313,grtpstore
	public static final String GRT_P_OTHER = "10021"; // 质押其他类,grt0317,grtpother
	public static final String GRT_P_MOVABLE = "10022"; // 动产类,grt0315,grtpmovable
	public static final String GRT_P_TRADEMARK = "10023"; // 商标使用权,grt0316,grtptrademark
	
	//出帐状态
	public static final String DUE_APP_STS_1TH = "0"; // 未提交
	public static final String DUE_APP_STS_2TH = "1"; // 审批中
	public static final String DUE_APP_STS_3TH = "2"; // 发回重审
	public static final String DUE_APP_STS_4TH = "3"; // 否决
	public static final String DUE_APP_STS_5TH = "4"; // 审批通过
	
	//四级分类
	public static final String FOUR_STS_1TH = "1"; // 正常
	public static final String FOUR_STS_2TH = "2"; // 逾期
	public static final String FOUR_STS_3TH = "3"; // 呆滞
	public static final String FOUR_STS_4TH = "4"; // 呆账
	//五级分类
	public static final String FIVE_STS_1TH = "1"; // 正常
	public static final String FIVE_STS_2TH = "2"; // 关注
	public static final String FIVE_STS_3TH = "3"; // 次级
	public static final String FIVE_STS_4TH = "4"; // 可疑
	public static final String FIVE_STS_5TH = "5"; // 损失
	
	//是否
	public static final String YES_NO_Y = "1"; // 是
	public static final String YES_NO_N = "0"; // 不是
	
	//经销商级别
	public static final String DEALER_LEV_1TH = "1"; // 一级
	public static final String DEALER_LEV_2TH = "2"; // 二级
	
	//经销商级别
	public static final String PRJ_AUTH_MODE_SUP = "1"; // 供货商授信
	//public static final String PRJ_AUTH_MODE_DEAL = "2"; // 供货商分配授信
	public static final String PRJ_AUTH_MODE_UNIT = "3"; // 经销商授信
	
	
	//经销商级别
	public static final String PRJ_AUTH_ACC_MODE_SUP = "0"; // 供货商授信
	public static final String PRJ_AUTH_ACC_MODE_DEAL = "1"; // 供货商分配授信
	public static final String PRJ_AUTH_ACC_MODE_UNIT = "3"; // 经销商授信
	
	public static String AUTH_OCC_TYPE_OCC="2" ;  //占用
	public static String AUTH_OCC_TYPE_REBACK="9";   //恢复

	public static String AUTH_NORM_TYPE_Y="1";
	public static String AUTH_NORM_TYPE_N="0";
	
	
	//冻结/解冻 操作类型
	public static String AUTH_HOLD_TYPE_BLOCK = "2";	//冻结
	public static String AUTH_HOLD_TYPE_UNBLOCK = "4";	//解冻
	
	public static String AUTH_ICE_FLAG_BLOCKED = "0";	//已冻结
	public static String AUTH_ICE_FLAG_UNBLOCKED = "1";	//未冻结
	

	
	//租赁物类型
	public static String LAM_TYPE_1ST = "1";	//车辆
	public static String LAM_TYPE_8TH = "8";	//挂车

	public static final String AUTH_APP_STS_ZERO = "0"; // 未审批
	public static final String AUTH_APP_STS_1ST = "1"; // 审批中
	public static final String AUTH_APP_STS_2ND = "2"; // 发回重审
	public static final String AUTH_APP_STS_3RD = "3"; // 审批否决
	public static final String AUTH_APP_STS_4TH = "4"; // 审批通过
	
	
	public static final String EVAL_OCC_TYPE_NORMAL = "1"; // 正式评级
	public static final String EVAL_OCC_TYPE_TRACK = "2"; // 跟踪评级
	public static final String EVAL_OCC_TYPE_ADJUST = "3"; // 调整评级
	
	/**
	 * 客户类型:1供货商,2经销商,3法人(承租人),4经营户(承租人),5自然人(承租人)
	 */
	public static final String EVAL_CIF_TYPE_1ST = "1"; // 1供货商
	public static final String EVAL_CIF_TYPE_2ND = "2"; // 2经销商
	public static final String EVAL_CIF_TYPE_3RD = "3"; // 3法人(承租人)
	public static final String EVAL_CIF_TYPE_4TH = "4"; // 4经营户(承租人)
	public static final String EVAL_CIF_TYPE_5TH = "5"; // 5自然人(承租人)
	
	public static final String QUOTA_STS_1 = "1"; //生效
	public static final String QUOTA_STS_2 = "2"; //冻结
	public static final String QUOTA_STS_3 = "3"; //失效
	public static final String QUOTA_STS_4 = "4"; //终止
	
	
	public static final String TBL_DEPART_BIZ_TYPE_CORP = "1"; // 租赁公司机构
	public static final String TBL_DEPART_BIZ_TYPE_SUP = "2"; // 供货商
	public static final String TBL_DEPART_BIZ_TYPE_DEAL = "3"; // 经销商(自身业务)
	public static final String TBL_DEPART_BIZ_TYPE_DEAL_S = "4"; // 经销商(供货商业务)
	
	public static final String CIF_WFK_APP_STS_1ST = "0"; // 未生效
	public static final String CIF_WFK_APP_STS_2ND = "1"; // 审批通过
	public static final String CIF_WFK_APP_STS_3RD = "2"; // 审批否决
	
	/*
	 * 贷后检查类型
	 */
	public static final String AFT1_CHK_TYPE_FIRST = "1"; // 首次检查
	public static final String AFT1_CHK_TYPE_FIX = "2"; // 定期检查
	public static final String AFT1_CHK_TYPE_UNFIX = "3"; // 不定期检查
	
	/*
	 * 贷后检查模板类型
	 */
	public static final String AFT1_CHK_MODEL_CON = "1"; // 台账模版
	public static final String AFT1_CHK_MODEL_CIF = "2"; // 客户模版
	
	/*
	 * 贷后检查检查选项
	 */
	public static final String AFT1_CHK_OPT_CON = "1"; // 按台账检查
	public static final String AFT1_CHK_OPT_CIF = "2"; // 按用户检查
	
	/*
	 * 贷后检查检查选项
	 */
	public static final String AFT1_TASK_STS_INIT = "0"; // 未指派
	public static final String AFT1_TASK_STS_APPT = "1"; // 已指派
	public static final String AFT1_TASK_STS_TASK = "2"; // 检查中
	public static final String AFT1_TASK_STS_OVER = "3"; // 已完成
	
	public static final String AFT1_CHECK_INIT = "0"; 	//未检查
	public static final String AFT1_CHECK_FINE = "1";	//已检查
	
	
	public static final String AUTOCODE_CLA_TASK_APP_NO = "CreateKey.claTask.getKey"; // 风险分类任务编号
	public static final String AUTOCODE_CLA_PARM_CONF_NO = "CreateKey.claParmConf.getKey"; // 风险分类指标配置编号
	public static final String AUTOCODE_CLA_MODEL_BIZ_REL_ID = "CreateKey.claModelBizRel.getKey"; // 风险分类模型与业务关联id
	public static final String AUTOCODE_CLA_MOD_LOG_ID = "CreateKey.claModLog.getKey"; // 风险分类强制调整id
	public static final String AUTOCODE_IQP_PROJECT_ID = "CreateKey.IqpProject.getKey"; // 立项申请ID(乘用车)
	public static final String AUTOCODE_IQP_PROJECT_COM_ID = "CreateKey.IqpProjectCom.getKey"; // 立项申请ID(商用车)
	public static final String AUTOCODE_PAY_FEE_NO = "CreateKey.PayFeeNo.getKey"; // 缴费编号
	
	/*
	 * 风险分类任务细分维度
	 */
	public static final String CLA_TASK_DETAIL_TYPE_CIF = "1"; //客户
	public static final String CLA_TASK_DETAIL_TYPE_ACC = "2"; //台账
	
	/*
	 * 风险分类，任务检查频率
	 */
	public static final String CLA_CHECK_FREQ_YEAR = "1"; //年
	public static final String CLA_CHECK_FREQ_SEASON = "2"; //季
	public static final String CLA_CHECK_FREQ_MONTH = "3"; //月
	
	/*
	 * 风险分类任务类型
	 */
	public static final String CLA_TASK_TYPE_01 = "01"; //系统发起
	public static final String CLA_TASK_TYPE_02 = "02"; //人工发起
	public static final String CLA_TASK_TYPE_03 = "03"; //人工发起(未提交)
	/*
	 * 风险分类生效类型
	 */
	public static final String CLA_APPLY_STATUS_0 = "0"; //未生效
	public static final String CLA_APPLY_STATUS_1 = "1"; //已生效
	
	/*
	 * 属性类型
	 */
	public static final String ATTRIBUTE_TYPE_1 = "1"; //流程变量
	public static final String ATTRIBUTE_TYPE_2 = "2"; //业务数据


	//生效状态
	public static String EFFECT_STS_ZERO = "0";	//未生效
	public static String EFFECT_STS_1ST = "1";	//生效
	public static String EFFECT_STS_2ND = "2";	//注销

	//保证金交易类型
	public static String CIF_SEC_KEY_NAME_1ST = "1";	//注资
	public static String CIF_SEC_KEY_NAME_2ND = "2";	//撤资
	public static String CIF_SEC_KEY_NAME_3RN = "3";	//抵扣租金
	
	
	//额度检查类型
	public static String QUOTA_CHECK_TYPE_1 = "1";	//检查总额
	public static String QUOTA_CHECK_TYPE_2 = "2";	//检查短期敞口
	public static String QUOTA_CHECK_TYPE_3 = "3";	//检查短期低风险
	public static String QUOTA_CHECK_TYPE_4 = "4";	//检查短期总额
	public static String QUOTA_CHECK_TYPE_5 = "5";	//检查中长期敞口
	public static String QUOTA_CHECK_TYPE_6 = "6";	//检查中长期低风险
	public static String QUOTA_CHECK_TYPE_7 = "7";	//检查中长期总额
	
	public static String QUOTA_TYPE_01 = "01";	//授信额度
	public static String QUOTA_TYPE_02 = "02";	//合作方额度
	public static String QUOTA_TYPE_03 = "03";	//担保公司额度
	public static String QUOTA_TYPE_04 = "04";	//同业额度
	public static String QUOTA_TYPE_05 = "05";	//客户群额度
	public static String QUOTA_TYPE_06 = "06";	//集团额度
	
	public static String CL_TYPE_CREDIT = "01";//授信额度		
	public static String CL_TYPE_GROUP = "02";	//集团额度		
	public static String CL_TYPE_UNION = "03";	//客户群小组额度	
	public static String CL_TYPE_HOUSE = "04";	//房地产项目额度	
	public static String CL_TYPE_CAR = "05";	//汽车经销商额度	
	public static String CL_TYPE_GUAR = "06";	//担保公司额度	
	public static String CL_TYPE_THRID = "07";	//第三方担保额度	
	public static String CL_TYPE_INTERBANK = "08";	//同业额度
	
	/**
	 * 普通消息SCHEME_ID编号
	 * REW_SCHEME_ID_1ST 不宜办理业务客户新增提醒
	 * REW_SCHEME_ID_2ND 审批退回修改
	 * REW_SCHEME_ID_3RN 审批结束
     * REW_SCHEME_ID_4ST 待办消息
     * REW_SCHEME_ID_5ST 重大事件提醒
	 */
	public static String REW_SCHEME_ID_1ST = "0000000000132067";	//不宜办理业务客户新增提醒
	public static String REW_SCHEME_ID_2ND = "0000000000132068";	//合同号变更提醒
	public static String REW_SCHEME_ID_3RN = "0000000000132069";	//审批结束
	public static String REW_SCHEME_ID_4ST = "0000000000132070";	//待办消息
	public static String REW_SCHEME_ID_5ST = "0000000000132071";	//重大事件提醒
	
	/**
	 * 客户生日:尊敬的 {cifName}，广西通盛感谢您的信任，祝您生日快乐！
	 */
	public static String SMS_SCENE_16TH = "16";
	/**
	 * 节假日:尊敬的 {cifName}，广西通盛感谢您的信任，祝您节日愉快！
	 */
	public static String SMS_SCENE_15TH = "15";
	
	/**
	 * 利率调整的前3天:尊敬的 {cifName}，您在广西通盛的月还款由于人民银行利率调整，已变为{amt}，
	 * 请您按时足额还款，如需帮助，请致电{phone}。
	 */
	public static String SMS_SCENE_14TH = "14";
	
	/**
	 * 商用车业务逾期(经销店业务负责人):您好：贵司{year}年{month}月{day}日推荐的申请号为{appNo}，申请人姓名为{cifName}的客户，目前出现逾期还款情况，
	 * 请协助我司督促客户还款，或按合同履行相关义务。如需帮助，请致电{phone}。
	 */
	public static String SMS_SCENE_13TH = "13";
	
	/**
	 * 乘用车业务逾期(客户):尊敬的 {cifName}，您在广西通盛的{year}年{month}月{day}日的月还款额为{amt}，
	 * 已逾期，请及时足额存款，以免影响您的个人信用，如需帮助，请致电{phone}。
	 */
	public static String SMS_SCENE_12TH = "12";
	
	/**
	 * 还款结清后3天:尊敬的 {cifName}，真诚地感谢您对广西通盛的信任，您的款项已结清，
	 * 请配合经销商完成相关文件接收。祝您工作顺利，生活愉快！
	 */
	public static String SMS_SCENE_11TH = "11";
	
	/**
	 * 正常还最后一期月还的前3天:尊敬的 {cifName}，您在广西通盛的最后一期还款额为{amt}，由保证金冲抵，
	 * 我司将在到期还款日后3个工作日内返还您保证金余额{secAmt}至您的还款账户，
	 * 请注意查收，如需帮助，请致电{phone}。
	 */
	public static String SMS_SCENE_10TH = "10";
	
	/**
	 * 扣款失败当日:尊敬的 {cifName}，您在广西通盛的{year}年{month}月{day}日的月还款额为{amt}，
	 * 扣款失败，请及时足额存款。如需帮助，请致电{phone}。
	 */
	public static String SMS_SCENE_9TH = "9";
	
	/**
	 * 扣款成功当日:尊敬的 {cifName}，您在广西通盛的{year}年{month}月{day}日的月还款额为{amt}，
	 * 已足额扣款成功。如需帮助，请致电{phone}。
	 */
	public static String SMS_SCENE_8TH = "8";
	
	/**
	 * 每月还款日前3天:尊敬的 {cifName}，您在广西通盛的{year}年{month}月{day}日的月还款额为{amt}，
	 * 请及时足额存款，如需帮助，请致电{phone}。
	 */
	public static String SMS_SCENE_7TH = "7";
	
	/**
	 * 寄送还款计划表前:尊敬的 {cifName}，感谢您选择广西通盛，您在广西通盛的还款计划表已经按照申请书邮寄地址寄出
	 * ，请注意查收，并按时还款，如需帮助，请致电{phone}。
	 */
	public static String SMS_SCENE_6TH = "6";
	
	/**
	 * 系统放款通过3天后:尊敬的 {cifName}，真诚地感谢您选择广西通盛，如需帮助，请致电{phone}。
	 */
	public static String SMS_SCENE_5TH = "5";
	
	/**
	 * 资金全部放款:您好：申请号为{appNo}，申请人姓名为{cifName}的申请已经完成放款{amt}。如需帮助，请致电{phone}。
	 */
	public static String SMS_SCENE_4TH = "4";
	
	/**
	 * 系统放款通过:您好：申请号为{appNo}，申请人姓名为{cifName}的申请已经通过放款审核，请协助客户办理下一流程手续。
	 */
	public static String SMS_SCENE_3RD = "3";
	
	/**
	 * 系统拒绝申请:您好：申请号为{appNo}，申请人姓名为{cifName}的申请未能满足我司资信审核条件，感谢您的配合！
	 */
	public static String SMS_SCENE_2ND = "2";
	
	/**
	 * 系统批准申请:您好：申请号为{appNo}，申请人姓名为{cifName}的申请已通过我司资信审核，请协助客户办理下一流程手续
	 */
	public static String SMS_SCENE_1ST = "1";
	
	
	/**
	 * 文档管理中的客户类型
	 * DOC_CIF_TYPE_01 个人无保证人
	 */
	public static final String DOC_CIF_TYPE_01 = "01"; //个人无保证人
	/**
	 * 文档管理中的客户类型
	 * DOC_CIF_TYPE_02 个人自然人保证人
	 */
	public static final String DOC_CIF_TYPE_02 = "02"; //个人自然人保证人
	/**
	 * 文档管理中的客户类型
	 * DOC_CIF_TYPE_03 个人法人保证人
	 */
	public static final String DOC_CIF_TYPE_03 = "03"; //个人法人保证人
	/**
	 * 文档管理中的客户类型
	 * DOC_CIF_TYPE_04 对公无保证人
	 */
	public static final String DOC_CIF_TYPE_04 = "04"; //对公无保证人
	/**
	 * 文档管理中的客户类型
	 * DOC_CIF_TYPE_05 对公自然人保证人
	 */
	public static final String DOC_CIF_TYPE_05 = "05"; //对公自然人保证人
	/**
	 * 文档管理中的客户类型
	 * DOC_CIF_TYPE_06 对公法人保证人
	 */
	public static final String DOC_CIF_TYPE_06 = "06"; //对公法人保证人
	/**
	 * 文档管理中的客户类型
	 * DOC_CIF_TYPE_07 个人混合保证人
	 */
	public static final String DOC_CIF_TYPE_07 = "07"; //个人混合保证人
	/**
	 * 文档管理中的客户类型
	 * DOC_CIF_TYPE_08 对公混合保证人
	 */
	public static final String DOC_CIF_TYPE_08 = "08"; //对公混合保证人
	/**
	 * 文档管理中的文档场景
	 * DOC_SCENE_01 业务申请
	 */
	public static final String DOC_SCENE_01 = "01"; //业务申请
	/**
	 * 文档管理中的文档场景
	 * DOC_SCENE_02 放款申请
	 */
	public static final String DOC_SCENE_02 = "02"; //放款申请
	/**
	 * 文档管理中的文档场景
	 * DOC_SCENE_02_01 放款申请补充资料
	 */
	public static final String DOC_SCENE_02_01 = "02_01"; //放款申请补充资料
	/**
	 * 文档管理中的文档场景
	 * DOC_SCENE_03 经销商补充资料
	 */
	public static final String DOC_SCENE_03 = "01_03"; //经销商补充资料
	/**
	 * 文档管理中的文档场景
     * DOC_SCENE_04 家访
	 */
	public static final String DOC_SCENE_04 = "01_04"; //家访
	/**
	 * 文档管理中的文档场景
     * DOC_SCENE_05 合同管理
	 */
	public static final String DOC_SCENE_05 = "05"; //合同管理
	/**
	 * 文档管理中的文档场景
     * DOC_SCENE_05_01 经销商合同管理
	 */
	public static final String DOC_SCENE_05_01 = "05_01"; //经销商合同管理
	/**
	 * 文档管理中的文档场景
     * DOC_SCENE_06 厂商上传
	 */
	public static final String DOC_SCENE_06 = "01_06"; //厂商上传
	/**
	 * 文档管理中的文档场景
	 * DOC_SCENE_07 业务提交后操作
	 */
	public static final String DOC_SCENE_07 = "01_07"; //业务提交后操作
	/**
	 * 文档管理中的文档场景
     * DOC_SCENE_21 经销商客户信息
	 */
	public static final String DOC_SCENE_21 = "21"; //经销商客户信息
	/**
	 * 文档管理中的文档场景
     * DOC_SCENE_22 供货商客户信息
	 */
	public static final String DOC_SCENE_22 = "22"; //供货商客户信息
	/**
	 * 文档管理中的文档场景
     * DOC_SCENE_31 提前结清
	 */
	public static final String DOC_SCENE_31 = "31"; //提前结清
	/**
	 * 文档管理中的文档场景
     * DOC_SCENE_32 主动回购
	 */
	public static final String DOC_SCENE_32 = "32"; //主动回购
	/**
	 * 文档管理中的文档场景
     * DOC_SCENE_33 被动回购
	 */
	public static final String DOC_SCENE_33 = "33"; //被动回购
	/**
	 * 文档管理中的文档场景
     * DOC_SCENE_34 核销终止
	 */
	public static final String DOC_SCENE_34 = "34"; //核销终止
	/**
	 * 文档管理中的文档场景
     * DOC_SCENE_40 实地催收1
	 */
	public static final String DOC_SCENE_40 = "40"; //实地催收1
	/**
	 * 文档管理中的文档场景
     * DOC_SCENE_41 实地催收2
	 */
	public static final String DOC_SCENE_41= "41"; //实地催收2
	/**
	 * 文档管理中的文档场景
     * DOC_SCENE_42 展期
	 */
	public static final String DOC_SCENE_42 = "51"; //展期管理
	/**
	 * 文档管理中的文档场景
     * DOC_SCENE_61 合同终止
	 */
	public static final String DOC_SCENE_61 = "61"; //合同终止
	/**
	 * 文档管理中的上传标志
	 * DOC_UP_STS_01 未上传
	 */
	public static final String DOC_UP_STS_01 = "01"; //未上传
	/**
	 * 文档管理中的上传标志
	 * DOC_UP_STS_02 未上传
	 */
	public static final String DOC_UP_STS_02 = "02"; //已上传
	
	/**
	 * 文档管理中的上传标志
	 * DOC_READ_STS_01 未阅读
	 */
	public static final String DOC_READ_STS_01 = "01"; //未阅读
	/**
	 * 文档管理中的上传标志
	 * DOC_READ_STS_02 已阅读
	 */
	public static final String DOC_READ_STS_02 = "02"; //已阅读

	/**
	 * 文档管理中的收取标志
	 * DOC_GET_STS_01 未收取
	 */
	public static final String DOC_GET_STS_01 = "01"; //未收取
	/**
	 * 文档管理中的收取标志
	 * DOC_GET_STS_02 已收取
	 */
	public static final String DOC_GET_STS_02 = "02"; //已收取
	/**
	 * 文档管理中的授权标志
	 * DOC_AUTHORIZE_UPLOAD 只能上传
	 */
	public static final String DOC_AUTHORIZE_UPLOAD = "UPLOAD"; //只能上传
	/**
	 * 文档管理中的授权标志
	 * DOC_AUTHORIZE_INSERT 只能新增(尚未使用)
	 */
	public static final String DOC_AUTHORIZE_INSERT = "INSERT"; //只能新增
	/**
	 * 文档管理中的授权标志
	 * DOC_AUTHORIZE_READ   只能阅读
	 */
	public static final String DOC_AUTHORIZE_READ = "READ"; //只能阅读
	/**
	 * 文档管理中的授权标志
	 * DOC_AUTHORIZE_VIEW   只能查看
	 */
	public static final String DOC_AUTHORIZE_VIEW = "VIEW"; //只能查看
	/**
	 * 文档管理中的授权标志
	 * DOC_AUTHORIZE_VIEW   只能查看(含有返回按钮)
	 */
	public static final String DOC_AUTHORIZE_VIEW_ALL = "VIEWALL"; //只能查看
	/**
	 * 文档管理中的授权标志
	 * DOC_AUTHORIZE_GET    只能收取
	 */
	public static final String DOC_AUTHORIZE_GET = "GET"; //只能收取
	/**
	 * 不能上传的类型
	 */
	public static final String DOC_FORBID_TYPE="exe,bat";
	/**
	 * 图片类型
	 */
	public static final String IMAGE_TYPE="bmp,jpg,jpeg,png,gif,tiff,pcx,tga,exif,fpx,svg,psd,cdr,pcd,dxf,ufo,eps,ai,raw";
	/**
	 * 小图片固定名称xxxxsmall.xxx
	 */
	public static final String IMAGE_SMALL_NAME="SMALL";
	/**
	 * 图片缩小倍数
	 */
	public static final int IMAGE_SMALL_TIMES=100;
	/**
	 * 图片缩略图高
	 */
	public static final int IMAGE_SMALL_HEIGHT=150;
	/**
	 * 图片缩略图宽
	 */
	public static final int IMAGE_SMALL_WIDTH=150;
	/**
	 * 每行图片的个数
	 */
	public static final int ROW_IMAGE_COUNT=5;
	/**
	 * 关注客户类型:手动录入
	 */
	public static final String ENTR_TYPE_INPUT="0";
	/**
	 * 关注客户类型:系统生成
	 */
	public static final String ENTR_TYPE_SYS="0";
	
	/**
	 * 电子签章XMl模版
	 */
	public static final String PDF_SIGN_STR_1ST="<?xml version='1.0' encoding='gbk' ?><SealDocRequest><BASE_DATA><SYS_ID></SYS_ID><USER_ID></USER_ID><USER_PSD></USER_PSD></BASE_DATA><FILE_LIST><TREE_NODE><OTHER_NO></OTHER_NO><FILE_NO></FILE_NO><FILE_PATH></FILE_PATH><CJ_TYPE></CJ_TYPE><APP_NO></APP_NO><IS_SEAL></IS_SEAL><APP_DATA><Info><name></name><address></address></Info></APP_DATA></TREE_NODE></FILE_LIST><RULE_LIST><RULE_NOTE><RULE_NO></RULE_NO></RULE_NOTE></RULE_LIST></SealDocRequest>";
//	public static final String PDF_SIGN_STR_1ST="<?xml version='1.0' encoding='gbk' ?><SealDocRequest><BASE_DATA><SYS_ID></SYS_ID><USER_ID></USER_ID><USER_PSD></USER_PSD></BASE_DATA><FILE_LIST><TREE_NODE><OTHER_NO></OTHER_NO><FILE_NO></FILE_NO><FILE_PATH></FILE_PATH><CJ_TYPE></CJ_TYPE><IS_SEAL></IS_SEAL><ReceiverFax></ReceiverFax><APP_NO></APP_NO><REQUEST_TYPE></REQUEST_TYPE><IS_CODEBAR></IS_CODEBAR><CODEBAR_TYPE></CODEBAR_TYPE><CODEBAR_DATA></CODEBAR_DATA><X_COORDINATE></X_COORDINATE><Y_COORDINATE></Y_COORDINATE><ftp_address></ftp_address><ftp_port></ftp_port><ftp_user></ftp_user><ftp_pwd></ftp_pwd><AREA_SEAL></AREA_SEAL><AREA_DATA><Info></Info></AREA_DATA><APP_DATA><Info><name></name><address></address></Info></APP_DATA></TREE_NODE></FILE_LIST><RULE_LIST><RULE_NOTE><RULE_NO>1</RULE_NO></RULE_NOTE></RULE_LIST></SealDocRequest>";
	/**
	 * 电子签章规则编号
	 * 01 公章
	 * 
	 */
	public static final String PDF_ROLE_NO_01 = "1";
	
	/**
	 * 电子签章场景编号
	 * 01  打印申请
	 * 02 业务核准通知函
	 * 03 业务拒绝通知函
	 * 04 放款通知函
	 * 05 业务确认通知函
	 * 06 提前结清申请函
	 * 07 提前结清审批通知函
	 * 08 还款承诺书
	 * 09 催收函（逾期60天以内）
	 * 10 催收函（逾期60天以外）
	 * 11 租金垫付通知书
	 * 12 收车授权委托函及回执
	 * 13 车辆回购通知函
	 * 14 履行连带责任保证通知函
	 * 15 权益转让确认函(经销商)
	 * 16 权益转让确认函(厂商)
	 * 17 抵押合同打印
	 * 18 租赁合同打印
	 * 19 补充协议
	 * 20 车辆所有权转让确认书
	 * 21 收车通知函
	 * 22 回购申请书
	 * 23 租金支付表
	 */ 
	public static final String PDF_FLAG_01 = "01";//iqpProject_listAfter;iqpProjectApp*_TabFst;iqpProjectComm*_TabFst
	public static final String PDF_FLAG_02 = "02";//IqpProjectApp*_TabDueInfo;IqpProjectComm*_TabDueInfo
	public static final String PDF_FLAG_03 = "03";//IqpProjectReject_List
	public static final String PDF_FLAG_04 = "04";//IqpProject_ListForPass
	public static final String PDF_FLAG_05 = "05";//iqpprojectsup*_Tab
	public static final String PDF_FLAG_06 = "06";//AlpBaseActon tqjqsqh()
	public static final String PDF_FLAG_07 = "07";//AlpBaseActon tqjqsptzh()
	public static final String PDF_FLAG_08 = "08";//RecallBaseActon hkcns()
	public static final String PDF_FLAG_09 = "09";//RecallBaseActon csh60yn()
	public static final String PDF_FLAG_10 = "10";//RecallBaseActon csh60yw() 
	public static final String PDF_FLAG_11 = "11";//RecallBaseActon dftjz()
	public static final String PDF_FLAG_12 = "12";//AlpBaseActon scsqwthjhz()
	public static final String PDF_FLAG_13 = "13";//AlpBaseActon clhgtzh();
	public static final String PDF_FLAG_14 = "14";//AlpBaseActon lxldzrbztzh()
	public static final String PDF_FLAG_15 = "15";//AlpBaseActon qyzrqrhjxs()
	public static final String PDF_FLAG_16 = "16";//AlpBaseActon  qyzrqrhcs()
	public static final String PDF_FLAG_17 = "17";//SelectRptType.jsp
	public static final String PDF_FLAG_18 = "18";//SelectRptType.jsp
	public static final String PDF_FLAG_19 = "19";//SelectRptType.jsp
	public static final String PDF_FLAG_20 = "20";//LamTransfer_list.jsp
	public static final String PDF_FLAG_21 = "21";//RecallBaseActon sctzh()
	public static final String PDF_FLAG_22 = "22";//RecallBaseActon hgsqs()
	public static final String PDF_FLAG_23 = "23";//IqpProjectCont_ListForPrint.jsp
	/**
	 * 还款开户行行号
	 */
	public static final String ICBC_ACC_BANK = "102100099996";
	/**
	 * 还款开户行行名
	 */
	public static final String ICBC_ACC_BANK_NAME = "中国工商银行总行清算中心";
	
	/**
	 * 批量节点状态
	 */
	public static String  NODE_STATUS_1 = "1";//未执行
	public static String  NODE_STATUS_2 = "2";//执行中
	public static String  NODE_STATUS_3 = "3";//执行失败
	public static String  NODE_STATUS_4 = "4";//执行成功
	
	/**
	 * 影像场景
	 */
	
	public static String  PAC_SCENE_10="10";//客户
	public static String  PAC_SCENE_20="20";//授信
	public static String  PAC_SCENE_30="30";//批复
	public static String  PAC_SCENE_40="40";//合同
	public static String  PAC_SCENE_50="50";//借据
	public static String  PAC_SCENE_60="60";//担保
	public static String  PAC_SCENE_70="70";//出账
	public static String  PAC_SCENE_80="80";//贷后检查
	public static String  PAC_SCENE_90="90";//其他
	
	/**
	 * 拟调查方案发送状态	Apply_Scheme_Rel.send_flag
	 */
	public static final String SEND_FLAG_0 = "0";//未发送
	public static final String SEND_FLAG_1 = "1";//已发送
	public static final String SEND_FLAG_2 = "2";//发送失败
	/**
	 * 拟调查方案确认  APPLY_CONFIRM.confirm_sts
	 */
	public static final String CONFIRM_STS_0 = "0";//未确认
	public static final String CONFIRM_STS_1 = "1";//确认
	public static final String CONFIRM_STS_2 = "2";//否决

	/**
	 * 用户对客户权限有效性 
	 */
	public static final String EXIST_NO_0 = "0";//无
	public static final String EXIST_NO_1 = "1";//有
	
	/**
	 * 票据状态
	 */
	public static final String BILL_STS_0 = "0";//未承兑
	public static final String BILL_STS_1 = "1";//已承兑
	public static final String BILL_STS_2 = "2";//垫款
	

	/**
	 * 押品状态
	 */
	public static final String GAGE_STS_0 = "0";//未入库
	public static final String GAGE_STS_1 = "1";//已入库
	public static final String GAGE_STS_2 = "2";//已出库
	public static final String GAGE_STS_3 = "3";//待入库
	public static final String GAGE_STS_4 = "4";//待出库
	public static final String GAGE_STS_5 = "5"; //临时入库
	public static final String GAGE_STS_6 = "6"; //临时出库
	
	/**
	 * 担保合同变更拟状态
	 */
	public static final String PLUS_CHANGE_STS_1 = "1";//有效
	public static final String PLUS_CHANGE_STS_2 = "2";//拟新增
	public static final String PLUS_CHANGE_STS_3 = "3";//拟删除
	
	/**
	 * 出入库申请类型
	 */
	public static final String APPLY_TYPE_1 = "1";//入库申请
	public static final String APPLY_TYPE_2 = "2";//再回库申请
	public static final String APPLY_TYPE_3 = "3";//出库申请
	public static final String APPLY_TYPE_4 = "4";//临时出库
	
	/**
	 * 担保合同类型
	 */
	public static final String PACT_PLUS_TYPE_1 = "1";//一般担保合同
	public static final String PACT_PLUS_TYPE_2 = "2";//最高额担保合同
	
	/**
	 * 担保合同状态
	 */
	public static final String PACT_PLUS_STS_1 = "1";//未签订
	public static final String PACT_PLUS_STS_2 = "2";//已签订
	public static final String PACT_PLUS_STS_3 = "3";//已失效
	
	/**
	 * 担保合同有效性
	 */
	public static final String IS_VALD_0 = "0";//失效
	public static final String IS_VALD_1 = "1";//有效
	
	/**
	 * 担保合同担保方式
4	信用
3	保证
2	抵押
1	质押
	 */
	public static final String PACT_VOU_TYPE_DY = "2";//抵押
	public static final String PACT_VOU_TYPE_ZY = "1";//质押
	public static final String PACT_VOU_TYPE_BZ = "3";//保证
	public static final String PACT_VOU_TYPE_XY = "4";//信用
	public static final String PACT_VOU_TYPE_BZDBGS = "331";//担保公司保证
	/**
	 * 业务发生类型
	1	新发生
	2	展期
	3	借新还旧
	4	回收再贷
	5	续贷
	 */
	public static final String OCCUT_TYPE_XFS = "1";
	public static final String OCCUT_TYPE_ZQ = "2";
	public static final String OCCUT_TYPE_JXHJ = "3";
	public static final String OCCUT_TYPE_HSZD = "4";
	public static final String OCCUT_TYPE_XD = "5";
	/**
	 * 授信类型
	 */
	public static final String CRED_TYPE_1 = "1";//单笔授信
	public static final String CRED_TYPE_2 = "2";//额度授信
	
	/**
	 * 暂存标志
	 */
	public static final String TEMP_SAVE_FLAG_1 = "1";//暂存
	public static final String TEMP_SAVE_FLAG_2 = "2";//保存
	
	/**
	 * 五级分类类型
	 */
	public static final String CLASS_TYPE_1 = "1";//人工认定
	public static final String CLASS_TYPE_2 = "2";//系统认定
	

	/**
	 * 法律诉讼案件类型
1	一般案件
2	公正证执行/仲裁案件
3	破产案件
	 */
	public static final String SUIT_TYPE_YB = "1";//一般案件
	public static final String SUIT_TYPE_GZ = "2";//公正证执行/仲裁案件
	public static final String SUIT_TYPE_PC = "3";//破产案件
	
	/**
	 * 审查、核查岗位角色号
	 */
	public static final String WKF_HC = "1005|1014|1017|2001|3002|1060";//核查岗
	public static final String WKF_SC = "1015|1018|1022|2002|2003|3007|3012|7003";//审查岗
	
	/**
	 * 审查、核查文档
	 */
	public static final String PAC_HC = "B0001";//核查文档
	public static final String PAC_SC = "B0002";//审查文档
	
	/**
	 * 影像非只读
	 */
	public static final String READ = "0";//影像修改权限

}