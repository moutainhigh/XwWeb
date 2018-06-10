package app.creditapp.inf.client.zf;

import java.util.List;

import app.creditapp.inf.client.IBody;

public class PayInfo implements IBody{
	private String OrderNo;
	private String ItemNo;
	private String TotalEntryNum;
	private List<PayDetail> PayDetailList;
	private String BusinessOrderType;
	@Override
	public String toXml() {
		StringBuffer s = new StringBuffer();
		s.append("<PayInfo>");
		s.append("<OrderNo>"+this.OrderNo+"</OrderNo>");
		s.append("<BusinessOrderType>"+this.BusinessOrderType+"</BusinessOrderType>");
		s.append("<ItemNo>"+this.ItemNo+"</ItemNo>");
		s.append("<TotalEntryNum>"+this.TotalEntryNum+"</TotalEntryNum>");
		s.append("<PayDetailList>");
		for (PayDetail payDetail : this.PayDetailList) {
			s.append(payDetail.toXml());
		}
		s.append("</PayDetailList>");
		s.append("</PayInfo>");

		return s.toString();
	}
	
	public String getOrderNo() {
		return OrderNo;
	}
	public String getItemNo() {
		return ItemNo;
	}
	public String getTotalEntryNum() {
		return TotalEntryNum;
	}
	public List<PayDetail> getPayDetailList() {
		return PayDetailList;
	}
	public void setOrderNo(String orderNo) {
		OrderNo = orderNo;
	}
	public void setItemNo(String itemNo) {
		ItemNo = itemNo;
	}
	public void setTotalEntryNum(String totalEntryNum) {
		TotalEntryNum = totalEntryNum;
	}
	public void setPayDetailList(List<PayDetail> payDetailList) {
		PayDetailList = payDetailList;
	}

	public String getBusinessOrderType() {
		return BusinessOrderType;
	}

	public void setBusinessOrderType(String businessOrderType) {
		BusinessOrderType = businessOrderType;
	}
	
	
}