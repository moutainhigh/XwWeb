package app.creditapp.inf.entity;

import java.util.List;


/**
 * @作者 DHCC-LIU
 * @日期 Jun 23, 2016
 * @描述  预审批批量申请[2001] 输入信息
 */
public class WsIn2001 {
	
	private String brNo; // 合作机构编号
	private String batNo; // 批次号
	private int    dataCnt; //记录数
	
	private List<WsIn2001_1> list;
	
	
	public String getBrNo() {
		return brNo;
	}
	public void setBrNo(String brNo) {
		this.brNo = brNo;
	}
	public String getBatNo() {
		return batNo;
	}
	public void setBatNo(String batNo) {
		this.batNo = batNo;
	}
	public int getDataCnt() {
		return dataCnt;
	}
	public void setDataCnt(int dataCnt) {
		this.dataCnt = dataCnt;
	}
	public List<WsIn2001_1> getList() {
		return list;
	}
	public void setList(List<WsIn2001_1> list) {
		this.list = list;
	}
	
	
}
