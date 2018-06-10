package accounting.plat;

import java.util.HashMap;
import java.util.Map;

import accounting.domain.loan.CorpParm;
import accounting.domain.loan.PrdtBase;

public class PUBConstant {

	/**
	 * 系统参数，系统启动时赋值
	 */
	public static String CUR_PRCS_DT;    // 当前营业日期
	public static String NEXT_PRCS_DT; // 下一个营业日期
	public static String LAST_PRCS_DT;   // 上一个营业日期
	public static String SYS_STS;        // 系统状态
	public static String CUR_DEF = "CNY" ;	//设置系统默认币种为"人民币"
	public static String NEW_ACC_IND;	// 新会计准则标志
	
	public static Boolean DEAL_ACC_IND = false; // 是否进行会计分录和记账
	public static Boolean LOAN_END_DT_PERDAY = true;// 贷款到期日是否提前一天

	
	/*
	 * 费用状态
	 */
	public static final String CHRG_STS_01="01";//正常
	public static final String CHRG_STS_02="02";//拖欠
	public static final String CHRG_STS_03="03";//结清
	
	/**
	 * 核算参数表映射Map
	 */
	public static final Map<String, PrdtBase> PUB_ACCOUNT_PRAM = new HashMap<String, PrdtBase>();
	
	/**
	 * 合作机构配置信息映射Map
	 */
	public static final Map<String, CorpParm> PUB_CORP_PRAM = new HashMap<String, CorpParm>();
	
	/**
	 * 注册文件路径
	 */
	public static String CONFIG_FILM_DIR = "accounting/config";
	public static String COMMON_ALL = "ALL";

	/*
	 * 获取流水号参数常量
	 */
	public static String TRACENOATYPE = "TRACE_NO";
	public static String OWNER = "ALL";

	/*
	 * 获取AC_ID参数常量
	 */
	public static String ACIDOATYPE = "AC_ID";

	/*
	 * 是否标识
	 */
	public static final String Y = "Y"; // 是
	public static final String N = "N"; // 否


	
	/*
	 * 使用标识
	 */
	public static final String USE = "USE"; // 使用
	public static final String UNUSE = "UNSE"; // 未使用

	/*
	 * 首次还款日确定方式
	 */
	// 
	public static final String FST_PAY_IND_M = "M"; // 指定还款日
	public static final String FST_PAY_IND_F = "F"; // 对日还款
	
	public static final String PAY_DAY_COMM = "21";

	/*
	 * 周期类型
	 */
	// 
	public static final String TERM_TYP_DAY = "1"; // 日
	public static final String TERM_TYP_WEEK = "2"; // 周
	public static final String TERM_TYP_MONTH = "3"; // 月
	public static final String TERM_TYP_SEASON = "4"; // 季
	public static final String TERM_TYP_YEAR = "5"; // 年
	public static final String TERM_TYP_ALL = "9"; // 利随本清

	/*
	 * 保留小数点方式
	 */
	public static final String ROUNDING_HALF_UP = "R"; // 四舍五入
	public static final String ROUNDING_UP = "H"; // 上取整
	public static final String ROUNDING_DOWN = "L"; // 下取整

	/*
	 * 还款选项
	 */
	public static final String REPAY_OPT_IO = "IO"; // 仅利息
	public static final String REPAY_OPT_PO = "PO"; // 仅本金
	public static final String REPAY_OPT_IP = "IP"; // 本金和利息
	public static final String REPAY_OPT_NO = "NONE"; // 什么都不还

	/*
	 * 还款选项
	 */
	public static final String REPAY_ADV_OPT_AMT = "AMT"; 	// 改变金额，期数不变
	public static final String REPAY_ADV_OPT_PERD = "PERD"; // 缩短期数，期供不变
	/*
	 * 条目交易类型
	 */
	public static final String ENTRADE_TYPE_01 = "01"; //放款
	public static final String ENTRADE_TYPE_02 = "02"; //扣款
	public static final String ENTRADE_TYPE_03 = "03"; //充值
	public static final String ENTRADE_TYPE_04 = "04"; //提现
	public static final String ENTRADE_TYPE_05 = "05"; //挂账充值
	public static final String ENTRADE_TYPE_06 = "06"; //挂账提现
	/*
	 * 账户状态
	 */
	public static final String AC_STS_NORMAL = "1"; 		// 正常
	public static final String AC_STS_IN90DAYS = "2"; 		// 逾期90天内
	public static final String AC_STS_SLACK = "3"; 			// 呆滞
	public static final String AC_STS_BADDEBTS = "4"; 		// 呆账
	public static final String AC_STS_OUT90DAYS = "5"; 		// 逾期90天外
	public static final String AC_STS_CANCEL = "0"; 		// 销户
	public static final String AC_STS_UNAUDITED = "8"; 		// 开户未审核
	public static final String AC_STS_REPEAL = "10"; 		// 开户撤销
	public static final String AC_STS_GRANT_CANCEL = "12"; 	// 放款撤销
	
	/*
	 * 账户形态转移
	 */
	public static final String AC_STS_NORMAL_TO_OVER = "1"; // 正常转逾期
	public static final String AC_STS_NORMAL_TO_SLACK = "2"; // 正常转呆滞
	public static final String AC_STS_NORMAL_TO_BADDEBTS = "3"; // 正常转呆账
	public static final String AC_STS_OVER_TO_SLACK = "4"; // 逾期转呆滞
	public static final String AC_STS_OVER_TO_BADDEBTS = "5"; // 逾期转呆账
	public static final String AC_STS_SLACK_TO_BADDEBTS = "6"; // 呆滞转呆账

	/*
	 * 贷款状态
	 */
	public static final String LOAN_NORMAL = "0"; // 正常

	/*
	 * 机构类别
	 */
	public static final String BR_ACC_TYPE_A = "A"; // A类机构
	public static final String BR_ACC_TYPE_B = "B"; // B类机构

	
	/*
	 * 结息日标志
	 */
	public static final String INTST_IND_Y = "1"; // 结息日
	public static final String INTST_IND_N = "0"; // 非结息日
	
	/*
	 * 利息状态
	 */
	public static final String INT_STS_10 = "10"; // 正常
	public static final String INT_STS_20 = "20"; // 拖欠
	public static final String INT_STS_30 = "30"; // 逾期
	public static final String INT_STS_40 = "40"; // 结清

	
	/*
	 * 费用状态
	 */
	public static final String CHRG_STS_10="10";//正常
	public static final String CHRG_STS_20="20";//拖欠
	public static final String CHRG_STS_30="30";//逾期
	public static final String CHRG_STS_40="40";//结清

	/*
	 * 结清状态
	 */
	public static final String SETL_STS_Y = "Y"; // 已结清
	public static final String SETL_STS_N = "N"; // 未结清
	
	/*
	 * 贷款主文件状态
	 */
	public static final String LOAN_STS_01="01";//正常
	public static final String LOAN_STS_02="02";//待逾期
	public static final String LOAN_STS_03="03";//逾期
	public static final String LOAN_STS_04="04";//结清
	public static final String LOAN_STS_05="05";//已回购
	public static final String LOAN_STS_06="06";//已取消
	public static final String LOAN_STS_07="07";//待回购
	public static final String LOAN_STS_08="08";//回购中
	
	/*
	 * 扣款状态
	 */
	public static final String DDTL_STS_PEND = "01";//待处理
	public static final String DDTL_STS_SEND = "02";//已发送
	public static final String DDTL_STS_SUCC = "03";//扣款成功
	public static final String DDTL_STS_FAIL = "04";//扣款失败

	/*
	 * 反馈类型
	 */
	public static final String BACK_STS_01="01";//放款
	public static final String BACK_STS_02="02";//扣款
	public static final String BACK_STS_03="03";//发送异常
	public static final String BACK_STS_04="04";//发送失败
	public static final String BACK_STS_05="05";//异常回调/轮询
	public static final String BACK_STS_06="06";//成功回调/轮询
	
	/*
	 * 代偿状态
	 */
	public static final String COMPST_STS_01 = "01";//待代偿
	public static final String COMPST_STS_02 = "02";//已代偿
	
	/*
	 * 回购状态
	 */
	public static final String REBUY_STS_01 = "01";//待回购
	public static final String REBUY_STS_02 = "02";//已回购
	
	/*
	 * 扣款类型
	 */
	public static final String DEBIT_TYPE_01="01";//本利罚
	public static final String DEBIT_TYPE_02="02";//费用

	/*
	 * 增减标志
	 */
	public static final String DEC = "DEC"; // 减少
	public static final String INC = "INC"; // 增加

	/*
	 * 转账标志
	 */
	public static final String CASH = "1"; // 现金
	public static final String TRANS = "2"; // 转账

	/*
	 * 是否计入明细
	 */

	public static final String ACCOUNTED = "0"; // 已入
	public static final String ONE_TRADE = "1"; // 日终单笔
	public static final String GATHER_TRADE = "2";// 日中汇总

	/*
	 * 撤销标志
	 */
	public static final String ROL_IND_NORMAL = "0"; // 正常
	public static final String ROL_IND_CANCEL = "1"; // 撤销

	/*
	 * 贷款金额类型
	 */
	public static final String LOAN_AMT_TYP_P01 = "P01";// 正常本金
	/*
	 * 账户类型
	 */
	public static final String ACCT_TYPE_03 = "03";// 放款账户
	public static final String ACCT_TYPE_04 = "04";// 收款账户
	/*
	 * 首次还款日确定方式
	 */ 
	public static final String REM_DEAL_TYP_PERD = "1"; // 缩期
	public static final String REM_DEAL_TYP_AMT = "2"; 	// 改变期供
	
	/*
	 * 贷款明细,还款顺序中的金额类型
	 */
	public static final String LN_AMT_TYP_PRCP = "PRCP"; // 本金
	public static final String LN_AMT_TYP_CRI = "CRI"; 	 // 利息
	public static final String LN_AMT_TYP_ODI = "ODI";   // 罚息
	public static final String LN_AMT_TYP_FEE = "FEE";	 //	费用


	/*
	 * 还款顺序表中的交易种类
	 */
	public static final String REPAY_TX_TYPE_REPAY = "01"; // 还款
	public static final String REPAY_TX_TYPE_WVI = "02"; // 利息核销
	public static final String REPAY_TX_TYPE_WVL = "03"; // 贷款核销
	public static final String REPAY_TX_TYPE_DEI = "04"; // 利息减免
	public static final String REPAY_TX_TYPE_WVB = "05"; // 核销返回
	

	/*
	 * 贷款五级分类
	 */
	public static final String FIVE_STS_1ST = "1"; // 正常
	public static final String FIVE_STS_2ND = "2"; // 次级
	public static final String FIVE_STS_3RD = "3"; // 关注
	public static final String FIVE_STS_4TH = "4"; // 可疑
	public static final String FIVE_STS_5TH = "5"; // 损失
	
	/*
	 * 费用类型
	 */
	public static final String FEE_KIND_01 = "01";//自收
	public static final String FEE_KIND_02 = "02";//代收

	/*
	 * 收付标志
	 */
	public static final String PYCL_RECV = "R"; 	// 收
	public static final String PYCL_PAY = "P";	 	// 付

	// 用于欠款表,计划表的本金状态和利息状态
	public static final String AMT_STS_NORMAL = "10"; // 正常
	public static final String AMT_STS_DELQ = "20";	// 拖欠
	public static final String AMT_STS_OVER = "30"; // 逾期
	public static final String AMT_STS_SETL = "40"; // 逾期

	// 审批表发放状态
	public static final String STS_REVOKE = "0"; // 已撤销
	public static final String STS_UNDO = "1"; // 未处理
	public static final String STS_OPEN = "2"; // 已开户（待放款）
	public static final String STS_CANCEL = "5"; // 授权终止
	public static final String STS_DEAL = "9"; // 已处理

	/*
	 * 冲正撤销标志
	 */
	public static final String REV_ROL_NORM = "NORM"; 	// 正常
	public static final String REV_ROL_REV = "REV"; 	// 冲正
	
	/*
	 * 合作机构费用账户类型
	 */
	public static final String FEE_TYPE_01 = "01"; //服务费
	public static final String FEE_TYPE_02 = "02"; //保费
	public static final String FEE_TYPE_03 = "03"; //对公账户
	public static final String FEE_TYPE_04 = "04"; //展期费
	public static final String FEE_TYPE_05 = "05"; //趸交费
	public static final String FEE_TYPE_06 = "06"; //违约金

	

	/*
	 * 支付反馈类型
	 */
	public static final String BACK_TYPE_01 = "01";//放款
	public static final String BACK_TYPE_02 = "02";//扣款
	/*
	 * 批处理交易序号
	 */
	public static final String BAT_INIT = "100"; // 日终初始化
	public static final String BAT_EXPAND = "120"; // 贷款展期
	public static final String BAT_LOAN_REPAY_CUR = "140"; // 贷款自动扣款(日初扣当期)
	public static final String BAT_STS_TRAN = "220"; // 形态转移
	public static final String BAT_UPDATE = "360"; // 核算内部信息更新
	public static final String BAT_CHECK = "380"; // 贷款系统与核心系统对账
	public static final String BAT_CHECK_DETL = "400"; // 贷款系统与核心系统对明细账
	public static final String BAT_CHANGE_DATE = "500"; // 核算系统换日
	public static final String BAT_INTST_SETTLE = "600"; // 贷款结息
	public static final String BAT_LOAN_REPAY_LO = "620";//贷款自动扣款（日终扣欠款）
	public static final String BAT_DATA_CLEAR = "680	"; // 数据清理
	public static final String BAT_END = "999"; // 日终完成

	/*
	 * 批处理上下传文件名
	 */
	public static final String POST_BATCH_UP = "_up.txt"; // 上传文件后缀
	public static final String POST_BATCH_UP_END = "_up.end";//上传结束文件后缀
	public static final String POST_BATCH_DOWN = "_down.txt"; // 下传文件后缀
	public static final String POST_BATCH_DOWN_END = "_down.end";//下传结束文件后缀
	public static final String PRE_CVRG_BATCH = "E:\\batch\\cvrg_"; // 保函到期处理上传文件前缀
	public static final String PRE_ACCP_BATCH = "E:\\batch\\accp_"; // 银承自动扣款上传文件前缀
	public static final String PRE_CHECK_BATCH_DOWN = "E:\\batch\\check_"; // 核心与核算对帐汇总文件批处理下传文件前缀
	public static final String PRE_CHECK_DETAIL_BATCH_DOWN = "E:\\batch\\checkDetail_"; // 核心与核算对帐明细文件批处理下传文件前缀
	public static final String PRE_LOAN_REPAY_CUR_BATCH = "E:\\batch\\loanCur_"; // 贷款自动扣当期批处理文件前缀
	public static final String PRE_LOAN_REPAY_LO_BATCH="E:\\batch\\loanLo_";// 贷款自动扣欠款批处理文件前缀
	
	
	
	public static final String FILE_FLODER_BATCH = "F:\\batch\\";//上传文件
	public static final String FILE_UP_FLODER_BATCH = "_UP\\";
	public static final String FILE_DOWN_FLODER_BATCH ="_DOWN\\";
	
	public static final String SYS_INFO_UP_BATCH="0101up.txt";			// 系统信息上传文件
	public static final String LOAN_RECOVERY_BAIL_UP_BATCH="0201up.txt";// 贷款保证金恢复上传文件
	public static final String LOAN_REPAY_UP_BATCH="0301up.txt";		// 日终扣款上传文件
	public static final String CVRG_UP_BATCH="0302up.txt";				// 银承到期处理上传文件
	public static final String ACCP_UP_BATCH="0303up.txt";				// 保函到期处理上传文件
	public static final String QRY_BAL_UP_BATCH="0401up.txt";			// 查询账户余额上传文件
	public static final String LOAN_SBSY_UP_BATCH="0501up.txt";			// 查询贴息账户上传文件
	
	public static final String SYS_INFO_DOWN_BATCH="0101down.txt";			// 系统信息下传文件
	public static final String LOAN_RECOVERY_BAIL_DOWN_BATCH="0201down.txt";// 贷款保证金恢复下传文件
	public static final String LOAN_REPAY_DOWN_BATCH="0301down.txt";		// 日终扣款下传文件
	public static final String CVRG_DOWN_BATCH="0302down.txt";				// 银承到期处理下传文件
	public static final String ACCP_DOWN_BATCH="0303down.txt";				// 保函到期处理下传文件
	public static final String QRY_BAL_DOWN_BATCH="0401down.txt";			// 查询账户余额下传文件
	public static final String LOAN_SBSY_DOWN_BATCH="0501down.txt";			// 查询贴息账户下传文件
	
	
	

	/*
	 * 自动扣款交易主表批扣状态
	 */
	public static final String AC_LM_ATPY_STS_CREATE = "SU"; // 生成扣款准备记录
	public static final String AC_LM_ATPY_STS_SEND = "SP"; // 扣款文件已生成，已发核心
	public static final String AC_LM_ATPY_STS_FREEZE = "FZ"; // 冻结
	public static final String AC_LM_ATPY_STS_CREATEDC = "CP"; // 表示当期已经生成分录
	
	/*
	 * 批扣类型
	 */
	public static final String BATCH_REPAY_LOAN_CUR = "CUR";	//日初扣当期
	public static final String BATCH_REPAY_LOAN_LO = "LO";		//日终扣欠款

	/*
	 * 借贷标志
	 */
	public static final String DC_IND_D = "D"; // 借
	public static final String DC_IND_C = "C"; // 贷
	public static final String DC_IND_ANY="*"; //任意
	
	/*
	 * 拖欠标识
	 */
	public static final String DELQ_IND_YES = "Y"; // 已拖欠
	public static final String DELQ_IND_NO = "N"; // 未拖欠
	
	/*
	 * 贷款承诺状态
	 */
	public static final String PROV_STS_NORM = "NORM" ;				//正常
	public static final String PROV_STS_DS = "DS" ;					//日终自动结清
	public static final String PROV_STS_MS = "MS" ;					//柜面结清

	/*
	 * 贷款期限类型
	 */
	public static final String LOAN_PERD_ST = "ST";					//短期
	public static final String LOAN_PERD_LT = "LT";					//中长期
	public static final String LOAN_PERD_ALL="ALL";					//所有
	
	/*
	 * 提前还款利息计算基础
	 */
	public static final String AC_FDRP_INTST_BASE_NO="NO";			//不计息
	public static final String AC_FDRP_INTST_BASE_OSP="OSP";		//贷款剩余本金
	public static final String AC_FDRP_INTST_BASE_PPP="PPP";		//提前还本金额
	
	/*
	 * 序号清零标志
	 */
	public static final String AC_ZERO_FLAG_DAY="D";		//每天
	public static final String AC_ZERO_FLAG_MON="M";		//每月
	public static final String AC_ZERO_FLAG_SEA="S";		//每季
	public static final String AC_ZERO_FLAG_YEAR="Y";		//每年
	
	/*
	 * 贷款状态
	 */
	public static final String LOAN_STS_NBAP="01";		//未放款
	public static final String LOAN_STS_ACTV="02";		//已发放
	public static final String LOAN_STS_SETL="03";		//已结清
	public static final String LOAN_STS_OPEN_CANCEL="04";	//开户撤销
	public static final String LOAN_STS_GRANT_CANCEL="05";	//放款撤销
	
	/*
	 * 贷款处理状态
	 */
	public static final String DEAL_STS_PEND="01";	//待处理
	public static final String DEAL_STS_ACTV="02";	//已发送
	public static final String LOAN_STS_SUCC="03";	//放款成功
	public static final String LOAN_STS_FAIL="04";	//放款失败
	
	
	
	/*
	 * 减值标志
	 */
	public static final String DEVA_IND_Y="Y";		//是
	public static final String DEVA_IND_N="N";		//否
	
	public static final String ACCT_INFO_OS="OS";		//剩余金额
	public static final String ACCT_INFO_SETL="SETL";	//已还金额
	
	/*
	 * 形态转移条件
	 */
	public static final String STS_NORM_TO_OVER_DAYS="0";	//正常转逾期期限
	public static final String STS_IN_TO_OUT_DAYS="90";		//转表外期限
	public static final String STS_IN_TO_OUT_OPT=PUBConstant.AMT_STS_NORMAL+","+PUBConstant.AMT_STS_OVER+","+PUBConstant.AMT_STS_DELQ;//转表外条件
	public static final String STS_NORM_TO_OVER_OPT=PUBConstant.AMT_STS_NORMAL+","+PUBConstant.AMT_STS_DELQ;//转逾期条件
	
	/*
	 * 科目余额方向
	 */
	public static final String ACC_DC_IND_D="1";	//借
	public static final String ACC_DC_IND_C="2";	//贷
	public static final String ACC_DC_IND_ANY="0";	//两性
	
	
	/*
	 * 减值标示
	 */
	public static final String DEVA_TYP_COMBIN = "C" ;	//组合减值
	public static final String DEVA_TYP_SINGLE = "S" ;  //单笔减值
	
	/*
	 * 实际利率精确位数
	 */
	public static final int ACCURATE_DIGITS = 5;	//实际利率精确位数
	
	/*
	 * 首期不足期处理方式
	 */
	public static final String AC_FST_PERD_OPT_INTST = "1";//仅收利息
	public static final String AC_FST_PERD_OPT_DELAY = "2";//什么都不收，后推一期
	public static final String AC_FST_PERD_OPT_PI = "3";//本金和利息都要收
	
	/**
	 * 贷款帐号类型
	 */
	public static final String ACCT_TYP_REPAY = "1"; // 还款帐号
	
	
	/**
	 * 批扣类型
	 */
	public static final String ATPY_TYPE_LOAN = "01";	//贷款扣款

	/*
	 * 交易类型
	 */
	public static final String AC_TRANTP_TR="TR";		//TR:转账
	public static final String AC_TRANTP_CS="CS";		//CS:现金
	
	/*
	 * 币种
	 */
	public static final String AC_CRCYCD_01="01";		//01，人民币
	
	/*
	 * 系统ID
	 */
	public static final String AC_SYSTID_02="02";		//02
	
	/*
	 * 余额方向
	 */
	 public static final String AC_AMNTCD_D="C10001002";		//借
	 public static final String AC_AMNTCD_C="C10001001";		//贷
	/*
	 * 还款类型
	 */
	public static final String DEBIT_MOLD_01="01";		//正常扣款
	public static final String DEBIT_MOLD_02="02";		//正常提前清贷
	public static final String DEBIT_MOLD_03="03";		//逾期还款
	public static final String DEBIT_MOLD_04="04";		//手工代扣
	public static final String DEBIT_MOLD_05="05";		//部分提前还款
	public static final String DEBIT_MOLD_06="06";		//全部提前还款
	public static final String DEBIT_MOLD_07="07";		//合同取消
	public static final String DEBIT_MOLD_08="08";		//溢缴款充值
	public static final String DEBIT_MOLD_09="09";		//自动减免提前清贷实收
}
