package app.creditapp.inf.client.zf;

import java.util.List;

import app.creditapp.inf.client.IBody;

public class TradeInfo implements IBody{
	private String TradeNo;
	private String TradeTypeNo;
	private int TotalEntryNum;
	private List<EntryInfo> EntryList;
	
	public String toXml(){
		StringBuffer s = new StringBuffer();
		s.append("<TradeInfo>");
		s.append("<TradeNo>"+this.TradeNo+"</TradeNo>");
		s.append("<TradeTypeNo>"+this.TradeTypeNo+"</TradeTypeNo>");
		s.append("<TotalEntryNum>"+this.TotalEntryNum+"</TotalEntryNum>");
		s.append("<EntryList>");
		for (EntryInfo entryInfo : this.EntryList) {
			s.append(entryInfo.toXml());
		}
		s.append("</EntryList>");
		s.append("</TradeInfo>");
		return s.toString();
		
	}


	public String getTradeNo() {
		return TradeNo;
	}


	public void setTradeNo(String tradeNo) {
		TradeNo = tradeNo;
	}


	public String getTradeTypeNo() {
		return TradeTypeNo;
	}


	public void setTradeTypeNo(String tradeTypeNo) {
		TradeTypeNo = tradeTypeNo;
	}


	public int getTotalEntryNum() {
		return TotalEntryNum;
	}

	public void setTotalEntryNum(int totalEntryNum) {
		TotalEntryNum = totalEntryNum;
	}


	public List<EntryInfo> getEntryList() {
		return EntryList;
	}


	public void setEntryList(List<EntryInfo> entryList) {
		EntryList = entryList;
	}


	
}
