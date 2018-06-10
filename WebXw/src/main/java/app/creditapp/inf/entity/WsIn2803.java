package app.creditapp.inf.entity;

import java.util.List;

/**
 * A类提前还款批量申请[2803]
 *请求数据实体类
 */
public class WsIn2803 {

	private String brNo;//合作机构号
	private int dataCnt;//记录数
	private String batNo;//批次号
	
	private List<WsIn2803_1> list;

	public String getBrNo() {
		return brNo;
	}

	public void setBrNo(String brNo) {
		this.brNo = brNo;
	}

	public int getDataCnt() {
		return dataCnt;
	}

	public void setDataCnt(int dataCnt) {
		this.dataCnt = dataCnt;
	}

	public String getBatNo() {
		return batNo;
	}

	public void setBatNo(String batNo) {
		this.batNo = batNo;
	}

	public List<WsIn2803_1> getList() {
		return list;
	}

	public void setList(List<WsIn2803_1> list) {
		this.list = list;
	}

}
