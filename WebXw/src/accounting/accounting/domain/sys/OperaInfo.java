package accounting.domain.sys;

import java.sql.Connection;

import accounting.plat.PUBConstant;

public class OperaInfo {

	private String txBrNo ;		// 核算交易机构号
	private String bizDt ;		// 业务发生日期
	private String txDt ;		// 系统交易日期
	private String traceNo ;		// 交易流水号
	private int traceCnt=1 ;	// 交易流水笔次
	private Connection conn ;	// 数据库连接

	public OperaInfo(Connection conn){
		this.conn = conn ;
		this.txDt = PUBConstant.CUR_PRCS_DT ;		// 系统交易日期
		this.bizDt = PUBConstant.CUR_PRCS_DT ;		// 营业日期
	}
	
	public OperaInfo(AfferentDomain afferentDomain){
		this.txBrNo = afferentDomain.getBrNo() ;	// 核算交易机构号
		this.txDt = PUBConstant.CUR_PRCS_DT ;		// 系统交易日期
		this.bizDt = PUBConstant.CUR_PRCS_DT ;		// 营业日期
	}
	
	public OperaInfo(AfferentDomain afferentDomain, String traceNo, Connection conn){
		this.txBrNo = afferentDomain.getBrNo() ;	// 核算交易机构号
		this.txDt = afferentDomain.getTxDt() ;		// 系统日期
		this.bizDt = afferentDomain.getBizDt();		// 交易日期
		this.traceNo = traceNo ;					// 业务流水号
		this.conn = conn ;							// 数据库链接
	}
	
	
	/**
	 * 获得 核算交易机构号
	 * @return 核算交易机构号
	 */
	public String getTxBrNo() {
		return txBrNo;
	}
	
	/**
	 * 设置 核算交易机构号
	 * @param brNo	
	 */
	public void setTxBrNo(String txBrNo) {
		this.txBrNo = txBrNo;
	}
	
	/**
	 * 获得 业务发生日期
	 * @return 业务发生日期
	 */
	public String getBizDt() {
		return bizDt;
	}
	
	/**
	 * 设置 业务发生日期
	 * @param bizDt
	 */
	public void setBizDt(String bizDt) {
		this.bizDt = bizDt;
	}
	
	/**
	 * 获得 系统交易日期
	 * @return 系统交易日期
	 */
	public String getTxDt() {
		return txDt;
	}
	
	/**
	 * 设置 系统交易日期
	 * @param txDt
	 */
	public void setTxDt(String txDt) {
		this.txDt = txDt;
	}

	/**
	 * 获得 交易流水号
	 * @return 交易流水号
	 */
	public String getTraceNo() {
		return traceNo;
	}

	/**
	 * 设置 交易流水号
	 * @param traceNo
	 */
	public void setTraceNo(String traceNo) {
		this.traceNo = traceNo;
	}

	/**
	 * 获得 数据库链接
	 * @return 数据库链接
	 */
	public Connection getConn() {
		return conn;
	}

	/**
	 * 设置 数据库链接
	 * @param conn
	 */
	public void setConn(Connection conn) {
		this.conn = conn;
	}

	/**
	 * 获得流水笔次
	 * @return 流水笔次
	 */
	public int getTraceCnt() {
		return traceCnt;
	}

	/**
	 * 设置 流水笔次
	 * @param 流水笔次
	 */
	public void setTraceCnt(int traceCnt) {
		this.traceCnt = traceCnt;
	}
	
}