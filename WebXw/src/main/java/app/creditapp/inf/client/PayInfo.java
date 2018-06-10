package app.creditapp.inf.client;

public class PayInfo {
	private String FlowNo;//接口流水号
	private String TradeNo;//交易号
	private String TradeTypeNo;//交易业务类型
	private String EntryNo;//条目号
	private String EntryTypeNo;//条目业务类型
	private double RpayMoney;//请求支付金额
	private double PayMoney;//实际支付金额
	private String EnTradeType;//条目交易类型
	private String PayTime;//交易时间
	private String Status;//交易状态
	private String Message;//交易反馈信息
	private String TradeChannel;//交易渠道
	
	public String getFlowNo() {
		return FlowNo;
	}
	public void setFlowNo(String flowNo) {
		FlowNo = flowNo;
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
	public String getEntryNo() {
		return EntryNo;
	}
	public void setEntryNo(String entryNo) {
		EntryNo = entryNo;
	}
	public String getEntryTypeNo() {
		return EntryTypeNo;
	}
	public void setEntryTypeNo(String entryTypeNo) {
		EntryTypeNo = entryTypeNo;
	}
	public double getRpayMoney() {
		return RpayMoney;
	}
	public void setRpayMoney(double rpayMoney) {
		RpayMoney = rpayMoney;
	}
	public double getPayMoney() {
		return PayMoney;
	}
	public void setPayMoney(double payMoney) {
		PayMoney = payMoney;
	}

	public String getPayTime() {
		return PayTime;
	}
	public void setPayTime(String payTime) {
		PayTime = payTime;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
	public String getEnTradeType() {
		return EnTradeType;
	}
	public void setEnTradeType(String enTradeType) {
		EnTradeType = enTradeType;
	}
	public String getTradeChannel() {
		return TradeChannel;
	}
	public void setTradeChannel(String tradeChannel) {
		TradeChannel = tradeChannel;
	}
	
}
