package app.creditapp.inf.client.zf;

import java.util.List;

import app.creditapp.inf.client.IBody;


public class BodyLoan implements IBody{

	private List<TradeInfo> TradeList;
	
	@Override
	public String toXml() {
		
		StringBuffer s = new StringBuffer();
		s.append("<TradeList>");
		for (TradeInfo tradeInfo : this.TradeList) {
			s.append(tradeInfo.toXml());
		}
		s.append("</TradeList>");
		return s.toString();
	}

	public List<TradeInfo> getTradeList() {
		return TradeList;
	}

	public void setTradeList(List<TradeInfo> tradeList) {
		TradeList = tradeList;
	}
}