package app.creditapp.inf.entity;

import java.util.List;

/**
 * @作者 DHCC-ZKX
 * @日期 July 22, 2016
 * @描述   B类提前还款批量申请[2804] 请求数据 
 */
public class WsIn2804 {
	
	private String brNo;//合作机构号
	private String batNo;//批次号
	private int dataCnt;//记录数
	
	List<WsIn2804_1> list;
	List<WsIn2804_2> listPlan;
	
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
	public List<WsIn2804_1> getList() {
		return list;
	}
	public void setList(List<WsIn2804_1> list) {
		this.list = list;
	}
	public List<WsIn2804_2> getListPlan() {
		return listPlan;
	}
	public void setListPlan(List<WsIn2804_2> listPlan) {
		this.listPlan = listPlan;
	}	
}
