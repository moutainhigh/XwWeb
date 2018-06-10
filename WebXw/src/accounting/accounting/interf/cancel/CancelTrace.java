package accounting.interf.cancel;

import accounting.domain.sys.AfferentDomain;

/**
 * 撤销/冲正交易数据传输对象
 * 
 * 
 */
public class CancelTrace extends AfferentDomain {

	private String traceNo; // 交易流水号
	private int traceCnt;// 流水笔次
	private String type; // 处理方式（撤销：ROL/冲正：REV）

	/**
	 * @return 交易流水号
	 */
	public String getTraceNo() {
		return traceNo;
	}

	/**
	 * @param traceNo
	 *            交易流水号
	 */
	public void setTraceNo(String traceNo) {
		this.traceNo = traceNo;
	}

	/**
	 * @return 处理方式
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            处理方式
	 */
	public void setType(String type) {
		this.type = type;
	}

	public int getTraceCnt() {
		return traceCnt;
	}

	public void setTraceCnt(int traceCnt) {
		this.traceCnt = traceCnt;
	}

}
