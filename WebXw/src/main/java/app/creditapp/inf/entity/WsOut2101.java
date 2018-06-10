package app.creditapp.inf.entity;

import java.util.List;


/**
 * @作者 DHCC-LIU
 * @日期 Jun 23, 2016
 * list
 * @描述   预审批信息查询---输出实体类
 */
public class WsOut2101 {
	
	private String batNo; //批次编号
	private int    dataCnt; //接收记录数
	
	private List<WsOut2101_1> list;

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

	public List<WsOut2101_1> getList() {
		return list;
	}

	public void setList(List<WsOut2101_1> list) {
		this.list = list;
	}

	
	
	

}
