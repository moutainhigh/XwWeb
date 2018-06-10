package accounting.plat;

public class TransCode {

	/**
	 * 授权操作类的交易码
	 */
	public static final String LN01 = "LN01"; // 贷款放款
	public static final String LN04 = "LN04"; // 主动还款授权
	public static final String LN05 = "LN05"; // 贷款帐号变更授权
	public static final String LN06 = "LN06"; // 贷款展期
	public static final String LN07 = "LN07"; // 贷款形态调整
	public static final String LN17 = "LN17"; // 贷款减值
	public static final String LN18 = "LN18"; // 减值返回
	
	/**
	 * 日间交易码
	 */
	public static final String LNOP = "LNOP"; // 贷款开户
	public static final String LNC3 = "LNC3"; // 贷款发放
	public static final String C3BK = "C3BK"; // 贷款反馈
	public static final String LNSA = "LNSA"; // 贷款单户结息
	public static final String LNDC = "LNDC"; // 展期
	public static final String LNEC = "LNEC"; // 展期取消
	public static final String LNTA = "LNTA"; // 贷款形态调整
	public static final String LNC4 = "LNC4"; // 贷款还款
	
	//新核算使用
	
	public static final String LNUP = "LNUP"; // 帐号变更
	public static final String CGPD = "CGPD"; // 还款日变更
	public static final String LNCP = "LNCP"; // 批量生成待代偿数据
	public static final String LNRB = "LNRB"; // 批量生成待回购数据
	public static final String RBCF = "RBCF"; // 回购确认
	public static final String LNWV = "LNWV"; // 息费减免
	public static final String LNAD = "LNAD"; // 提前还款
	public static final String LNRP = "LNRP"; // 线下实收处理
	public static final String LNLS = "LNLS"; // 合同取消
	public static final String LNLO = "LNLO"; // 欠款归还
	public static final String LNSU = "LNSU"; // 扣款文件上传
	public static final String LNMU = "LNMU"; // A类合作机构与扣款文件上传
	
	public static final String LNDR = "LNDR"; // 新接口使用（扣款 ，提前清贷，溢缴款充值）扣款
	//子交易代码
	public static final String LNCL = "LNCL";//提前清贷扣款
	public static final String LNPY = "LNPY";//溢缴款充值
	
	//新核算使用 --日终
	public static final String LNAN = "LNAN"; // 形态转移
	public static final String LNWD = "LNWD"; // 合作机构费用提现

	public static final String LNRV = "LNRV"; // 冲正交易


	public static final String LNWI = "LNWI"; // 利息减免
	public static final String LNWP = "LNWP"; // 本金减免
	public static final String LNWB = "LNWB"; // 贷款核销收回

	public static final String LNDE = "LNDE"; // 贷款减值
	public static final String LNEQ = "LNEQ"; // 贷款减值扣款
	public static final String LNDB = "LNDB"; // 贷款减值返回

	
	
	
	/**
	 * 日间交易中的子交易
	 */
	
	public static final String LNFC = "LNFC"; // 手续费计算
	
	public static final String LNIC = "LNIC"; // 结当期利息
	
	public static final String XROR = "XROR"; // 利息试算

	/**
	 * 批处理交易码
	 */
	public static final String LNAM = "LNAM"; // 日终初始化
	public static final String BTDC = "BTDC"; // 展期批处理
	public static final String LNCT = "LNCT"; // 贷款自动扣款信息准备
	public static final String LNCV = "LNCV"; // 贷款自动扣款文件准备
	public static final String LNCQ = "LNCQ"; // 贷款自动扣款入账
	public static final String LNAO = "LNAO"; // 核算内部信息更新
	public static final String LNZB = "LNZB"; // 序号表定期清零处理
	public static final String LNCG = "LNCG"; // 贷款系统与核心系统汇总对账
	public static final String LNCS = "LNCS"; // 明细对账
	public static final String LIXZ = "LIXZ"; // 核心利率下载
	public static final String LNAV = "LNAV"; // 通知核心系统换日
	public static final String LNAT = "LNAT"; // 核算系统换日
	public static final String LNAA = "LNAA"; // 贷款批量结息
	public static final String LNCD = "LNCD"; // 数据清理
	public static final String PEND = "PEND"; // 日终结束
	public static final String UPLN = "UPLN"; // 更新还款计划表
	
	
}
