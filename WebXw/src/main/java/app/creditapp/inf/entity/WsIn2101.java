package app.creditapp.inf.entity;

import java.util.List;

/**
 * @作者 DHCC-LIU
 * @日期 Jun 23, 2016
 * @描述   进件批量申请--输入实体类 ln_batch
 */
public class WsIn2101 {
	
	private String brNo; // 合作机构编号
	private int    dataCnt; //记录数[1,1000]
	private String batNo; //批次号
	private List<WsIn2101_1> list;
	

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

	public List<WsIn2101_1> getList() {
		return list;
	}

	public void setList(List<WsIn2101_1> list) {
		this.list = list;
	}
	
	
}
