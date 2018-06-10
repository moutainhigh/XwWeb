package app.util;

public class AppConstant {
	
	/**********特殊的机构号*********/
	public final static String NC_BR_NO = "00000";//南充总行机构号---->用于在其它事业部没有配置流程时，拿该机构去匹配总行的流程
//	public final static String AUTH_BRNO = "189000000";//授信审批中心机构号---->用于授信审批，所有的授信审批都由该机构的人员去审批分发和确认
//	public final static String PUTOUT_BRNO = "190000000";//出账审批中心机构号--->用于出账审批，所有的出账流程由该机构的人员去审批
	public final static String SIGN_ROLE_NO="0001";//综合岗的号码
	public final static String CONFIRM_ROLE_NO="0007";//综合岗确认的
	public final static String PRESIDENT_ROLE="0045";//事业部总裁、总监
	public final static String BR_TYPE_JG="0";//机构类型:金融机构
	public final static String BR_TYPE_SYB="1";//机构类型:事业部
	public final static String APPLY_FLOW="flow1000000620";//申请流程
	public final static String FIVE_ROLE="1111";//五级分类管理员
	/************************ 缓存管理 ***********************/
	public final static String CACHE_DICT = "CACHE_DICT";		//数据字典
	public final static String CACHE_SECU = "CACHE_SECU";		//安全审计项
	public final static String CACHE_PROD = "CACHE_PROD";		//产品属性
	public final static String CACHE_TBLOG = "CACHE_TBLOG";		//记日志的表
	public final static String CACHE_MENU = "CACHE_MENU";		//菜单
	public final static String CACHE_BUTTON = "CACHE_BUTTON";	//按钮
	public final static String CACHE_ACCORG = "CACHE_ACCORG";	//核算机构
	public final static String CACHE_BUORG = "CACHE_BUORG";		//事业部机构
	public final static String CACHE_SYSSTS = "CACHE_SYSSTS";	//系统状态缓存
	
	public final static String SYS_STS = "SYS_STS";				//系统状态
	
	public final  static String CHANNEL_TXFLAG_STR = "1";      //通信接口启用
	public final  static String CHANNEL_TXFLAG_STOP = "0";     //通信接口停用
	/************************ 客户管理 ***********************/
	public final static int AFF_LEV = 3;						//关联方层级
	public final static String ADD_TYPE = "1";					//关联方加入类型(0:手动输入,1:自动引入)
	
	public final static String ID_TYPE_A = "A";					//证件类型 组织机构代码
	public final static String ID_TYPE_0 = "1";					//证件类型 身份证
	
	public final static String CIF_TYPE_CORP  = "1";			//对公客户
	public final static String CIF_TYPE_PERS  = "2";			//个人客户
	public final static String CIF_TYPE_GROUP = "3";			//集团客户
	public final static String CIF_TYPE_UNION = "4";			//客户群
	public final static String CIF_TYPE_BANK = "5";				//同业客户
	public final static String CIF_TYPE_HZ = "6";			//合作方
	public final static String CIF_TYPE_JG = "9";				//监管公司客户
	
//	public final static String CUST_TYPE_AGRI  = "1";			//农户
//	public final static String CUST_TYPE_UNAGRI  = "2";			//非农户
	
	public final static String COP_TYPE_HOU = "1";				//购房合作项目
	public final static String COP_TYPE_CAR = "2";				//购车合作项目
	public final static String COP_TYPE_DUR = "3";				//耐用消费合作项目
	public final static String COP_TYPE_EDU = "4";				//助学合作项目
	public final static String COP_TYPE_TRA = "5";				//通用合作项目--南充
	public final static String COP_TYPE_CUR = "11";				//通用合作项目--南充
	
//担保类型	
	public final static String VOU_TYPE_DBGS = "331";				//担保公司担保
	/************************ 授信管理 ***********************/
	
	/********************** 担保品出入库 ***********************/
	public final static String INSTORAGE = "1";                 //担保品入库状态
	public final static String OUTSTORAGE = "2";				//担保品出库状态
	/**********审批管理、流程管理*********/
	public final static String SIGN_KEY_NAME = "SIGN_PARM";	//会签策略数据字典key值
	public final static String FLOW_STS_ENABLE = "1";	//流程启用
	public final static String FLOW_STS_DISABLE = "2";//流程停用
	
	public final static String APPROVE_AGREE = "01";//同意
	public final static String APPROVE_DISAGREE="2";//不同意
	public final static String APPROVE_ROLLBACK="3";//退回
	public final static String APPROVE_REFUSE="04";//否决
	public final static String APPROVE_SENDBACK="5";//发回补充资料
	public final static String APPROVE_NEXTMOVE="6";//下一步流程
	public final static String APPROVE_REFUSE_SIGN="7";
	
	public final static String APP_PENDING="1";//待审批
	public final static String APP_APPROVING="2";//审批中
	public final static String APP_APPROVED="3";//审批通过
	public final static String APP_REFUSEED="4";//审批否决
	public final static String APP_SENDBACK="5";//发回重审
	
	/*
	 * 审批状态
	 */
	public static final String APP_STS_1ST = "0";//未审批
	public static final String APP_STS_2ND = "1";//审批中
	public static final String APP_STS_3RD = "2";//发回重审
	public static final String APP_STS_4TH = "3";//审批否决
	public static final String APP_STS_5TH = "4";//审批通过
	public static final String APP_STS_6TH = "5";//未补录申请
	public static final String APP_STS_7TH = "6";//已补录申请
	
	/*
	 * 审批意见
	 */
	public static final String APP_IDEA_1ST = "1";//同意
	public static final String APP_IDEA_2ND = "3";//退回
	public static final String APP_IDEA_3RD = "4";//发回补充
	public static final String APP_IDEA_4TH = "6";//否决
	
	public final static String TASK_ACTIVE="1";//未签收
	public final static String TASK_SIGNED="2";//已签收
	public final static String FLOW_START="start";//流程开始节点
	public final static String FLOW_END="end";//流程结束节点
	
	public final static String APP_TYPE_AUTH="1";//授信申请
	public final static String APP_TYPE_RISK="5";//五级分类审批
	public final static String APP_TYPE_HX="3";//贷款核销审批
	public final static String APP_TYPE_LC="6";//信用证以及保函修改审批
	public final static String APP_TYPE_BAD="4";//不良资产处置
	public final static String APP_TYPE_NOTE="8";//出账审批
	public final static String APP_TYPE_RATE="2";//利率调整审批	
	public final static String APP_TYPE_GRP="9";//集团客户认定审批
	public final static String APP_TYPE_QUT="10";//额度调整审批流程
	public final static String APP_TYPE_TY="7";//同业代付审批
	public final static String APP_TYPE_AFF="11";//关联交易审批
	public final static String APP_TYPE_CONFIRM="12";//授信审批确认流程
	public final static String APP_TYPE_ABILL="13";//承兑信息修改审批
	public final static String APP_TYPE_RSGST="14";//风险预警发起审批
	public final static String APP_TYPE_RSGDS="15";//风险预警解除审批
	public final static String APP_TYPE_GAGE="16";//抵质押物出入库审批
	public final static String APP_TYPE_LPVCG="17";//担保合同价值变更
	public final static String APP_TYPE_GAGEVCG="18";//抵质押物价值变更审批
	public final static String APP_TYPE_LRE="19";//贷款提前还款发起
	public final static String APP_TYPE_CHAN="20";//还款方式变更审批
	public final static String APP_TYPE_CHAN1="30";//支付方式方式变更审批
	public final static String APP_TYPE_TYTZ="21";//同业投资非标业务审批
	public final static String APP_TYPE_TFER="22";//贷款形态变更审批
	public final static String APP_TYPE_ASSET="23";//资产证劵化审批
	public final static String APP_TYPE_PDAINFO="24";//抵债资产审批
	public final static String APP_TYPE_HXSH="31";//贷款核销收回审批
	public final static String APP_TYPE_PJJQ="32";//票据结清审批
	
	/**********资产证卷化Excel导入相关参数*********/
	public final static int SHEET_NUM = 0;//读第几个sheet页
	public final static int ROW_NUM = 3;//Excel从第几行开始读
	public final static int CELL_DUE_NO = 2;//Excel借据号为第几列 从0开始
	public final static int CELL_DUE_AMT = 9;//Excel借据金额为第几列 从0开始
	public final static int CELL_DUE_BAL = 10;//Excel借据余额为第几列 从0开始
	
	public final static int CELL_RPDUE_NO = 7;//Excel变更借据号为第几列 从0开始
	public final static int CELL_RPDUE_AMT = 8;//Excel变更借据金额为第几列 从0开始
	public final static int CELL_RPDUE_BAL = 9;//Excel变更借据余额为第几列 从0开始
	
	public static String BRNO = "100000"; 			// 总行
	public final static String BRNAME = "泸州市商业银行"; // 总行
	
	public static String SocketUrl = "10.5.186.2";	// Socket 通信地址
	public static int SocketPort = 8010;			// Socket 通信端口。
	public static int SocketTime = 0;				// Socket 通信端口。
	/******************产品号常量配置***************/
	
//	public final static String PROD_PERS_TRUST = "2090";	//个人委托贷款 南充
	public final static String PROD_HOUSE_AJ = "10";	//个人住房按揭
	public final static String PROD_CORP_ZTX = "48";	//转贴现
	public final static String PROD_CORP_DZTXZR = "4801";	//电票转贴现转入
	public final static String PROD_CORP_DZTXZC = "4802";	//电票转贴现转出
	public final static String PROD_PERS_TRUST = "55";	//个人委托贷款
	public final static String PROD_CORP_TRUST = "5501";	//单位委托贷款
	public final static String PROD_GJJ_TRUST = "95";	//住房公积金贷款
//	public final static String PROD_CORP_TRUST = "2080";	//单位委托贷款 南充
	public final static String PROD_PRJ_COP_HOU = "3020";	//房地产按揭项目担保额度 
	public final static String PROD_GUAR_COMP = "3040";		//第三方担保额度授信 
	public final static String PROD_PRJ_COP_CAR = "3025";	//汽车经营商担保额度 
	public final static String PROD_GUAR_AGEC = "3030";		//担保机构担保额度
	public final static String PROD_REGULATORS = "3070";	//监管公司担保额度
	public final static String PROD_UNIN_GROP = "3050";		//客户群小组担保额度
	public final static String PROD_PRJ_CORP_CURR = "3035";	//通用贷款合作项目担保额度
	public final static String PROD_GROP = "3060";			//集团客户授信额度
	public final static String PROD_PERS = "3005";			//个人客户综合授信
	public final static String PROD_CORP = "3010";			//公司客户综合授信
	public final static String PROD_PRJ_COP_EDU = "3080";	//助学项目授信
	public final static String PROD_PRJ_COP_DUR = "3085";	//大额消费项目授信
	public final static String PROD_INT_BANK = "2011";		//同业客户授信
	public final static String PROD_DKCL = "2060";			//2060-贷款承诺书 
//	public final static String PROD_BH = "2020";			//2020-保函业务 南充
	public final static String PROD_BH = "53";			    //52-保函业务
	public final static String PROD_TYDF = "1080070";		//1080070-同业代付
	public final static String PROD_BH_SUB = "202001";		//202001-保函项下业务
//	public final static String PROD_YCTX = "1020010";		//1020010-银承贴现  南充
	public final static String PROD_YCTX = "50";		    //50-银承贴现    
	public final static String PROD_SCTX = "1020020";		//1020010-商承贴现
//	public final static String PROD_YCHP= "2010";			//2010-银行承兑汇票  南充
	public final static String PROD_YCHP= "56";			    //56-银行承兑汇票
	public final static String PROD_KLXYZ= "2030";			//2030-开立信用证
	public final static String PROD_XYFXPJTX = "1020030";	//1020030-协议付息票据贴现（银承、商承）
	public final static String PROD_THDB= "2040";			//2040-提货担保
	public final static String PROD_FIN_INV = "1140010";	//理财投资非标资产
	public final static String PROD_IND_INV = "1140020";	//同业投资非标资产(行内)
	public final static String PROD_IND_INV1 = "1140021";	//同业投资非标资产(行外)
	public final static String PROD_GRBH = "2095";			//个人付款保函
//	public final static String PROD_PERS_ZQ = "1150010";	//个人展期
//	public final static String PROD_CORP_ZQ = "1150020";	//对公展期
	public final static String PROD_PERS_ZQ = "98";	        //个人展期
	public final static String PROD_CORP_ZQ = "9801";	    //对公展期
	public final static String PROD_BHDK = "1130010";	//保函垫款
	public final static String PROD_CDHPDK = "1130020";	//承兑汇票垫款
	public final static String PROD_XYZDK = "1130030";	//信用证垫款
	public final static String PROD_GRXFXH = "1110050";	//个人消费循环贷款
	public final static String PROD_GRLHBZDK = "1115010";	//个人联合保证贷款
	/***************合同管理**********************/
	public final  static String PACT_STS_PEND = "1";        //合同状态：1待处理
	public final  static String PACT_STS_COMP = "2";        //2	登记完成
	public final  static String PACT_STS_PART = "3";        //3	部分出账
	public final  static String PACT_STS_OUT = "4";         //4	已出账
	public final  static String PACT_TYPE_GEN = "1";        //合同类型：1普通合同
	public final  static String PACT_TYPE_CYCLE = "2";      //合同类型：2循环合同
	public final  static String ICE_FLAG_NO = "0";          //合同冻结标志：0正常
	public final  static String ICE_FLAG_YES = "1";         //合同冻结标志：1冻结
	public final  static String ICE_FLAG_END = "4";         //合同冻结标志：4终止
	public final  static String CHK_STS_PEND ="1";          //待审核
	public final  static String CHK_STS_ING = "2";          //审核中
	public final  static String CHK_STS_SEND = "3";         //已发送
	public final  static String CHK_STS_SUP = "4";          //待补充
	public final  static String CHK_STS_REP = "5";         //建议撤销
	public final  static String CHK_STS_PASS = "6";         //审核通过
	public final  static String TRAN_STS_PEND = "0";         //待发送  交易状态
	public final  static String TRAN_STS_SEND = "1";         //已发送 交易状态
	/***************通信接口文件类型**********************/
	public final  static String TRADEFILETYPE_YCHPQF = "0";        //银承汇票签发文件
	public final  static String TRADEFILETYPE_HKJHS = "1";         //还款计划书文件
	public final  static String TRADEFILETYPE_ZDHKJE = "2";        //指定还款金额文件
	public final  static String TRADEFILETYPE_DWTR = "3";          //多委托人文件
	public final  static String TRADEFILETYPE_TXRZ = "4";          //通信日志文件
	public final  static String TRADEFILETYPE_BC = "5";          //保证金补存
	public final  static String TRADEFILETYPE_SF = "6";          //保证金释放
	public final  static String TRADEFILETYPE_DKZRZC = "7";          //贷款转让转出
	public final  static String TRADEFILETYPE_DKZRZR = "8";          //贷款转让转入
	public final  static String TRADEFILETYPE_BHXXWTDK = "9";          //保函项下业务委托贷款
	public final  static String TRADE_CHANNEL_ID = "100000";		//信贷系统渠道编号
	
	public final  static String IF_IB1 = "1";			   //是否国际 1 是
	public final  static String SEND_YCHT_0 = "0";			//银承合同给录入不成功标识
	public final  static String SEND_YCHT_1 = "1";			//银承合同给录入成功标识
	public final  static String BILL_FLAG_1 = "1";			//电票类型1电票
	public final  static String BILL_FLAG_2 = "2";			//纸票
	public final  static String BILL_APP_FLAG_1 = "1";		//是电票，贴现出账、银承汇票签发成功标识 1
	public final  static String BILL_APP_FLAG_0 = "0";		//非电票，银承汇票签发成功标识 0
	
	public final static String DEAL_STS_1 = "1";				//核销成功
	public final static String DEAL_STS_0 = "0";				//未核销
	
	public final static String PAY_INTE_WAY_1 = "1";				//仅本金
	public final static String PAY_INTE_WAY_2 = "2";				//本金+本息
	public final static String PAY_INTE_WAY_3 = "3";				//利随本清
	
	public final static String OCCUR_TYPE_ZQ = "2";				//发生类型-展期

	public final static String ASSE_TYPE_CB = "1";//资产出表
	public final static String ASSE_TYPE_SH = "2";//资产赎回
	public final static String ASSE_TYPE_BG = "3";//资产变更
	
	public final static String REPAY_TYPE_6 = "6";//按月付息，分期还款
	public final static String REPAY_TYPE_8 = "8";//按季付息，分期还款
	
	/******************   与ecif通讯开关 【0关1开】 **************************/
	public final static String CHANNEL_PERS_OPEN = "0";//个人开户接口
	public final static String CHANNEL_PERS_UPDATE = "0";//个人客户信息修改
	public final static String CHANNEL_PERS_SEARCH = "0";//个人客户信息查询
	public final static String CHANNEL_CORP_OPEN = "0";//对公客户开户
	public final static String CHANNEL_CORP_UPDATE = "0";//对公客户信息修改
	public final static String CHANNEL_CORP_SEARCH = "0";//对公客户信息查询
	/******************   贷款审批审批结果 **************************/
	public final static String LN_APPR = "00";//待审批
	public final static String LN_APPR_PENDING = "01";//同意
	public final static String LN_APPR_AGREE = "02";//否决
	public final static String LN_APPR_REFUSE = "03";//否决
}
