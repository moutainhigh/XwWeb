package app.creditapp.inf.client;


public class ZFHead implements IBody{
	private String RequestType;//交易类型
	private String UUID;//唯一编码
	private String ComId;//平台代码
	private String ComIP;//平台ip地址
	private String SendTime;//发送时间
	private String Asyn;//是否异步接口
	private String ReturnUrl;//回调Url
	private String Signed;//签名串
	private String ClientType;//客户端
	private int PayCount;//交易数量
	

	@Override
	public String toXml() {
		StringBuffer s = new StringBuffer();

		s.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
		s.append("<Package><Header>");
		s.append("<RequestType>" + this.RequestType + "</RequestType>");
		s.append("<UUID>" + this.UUID + "</UUID>");
		s.append("<ComId>" + this.ComId + "</ComId>");
		s.append("<ComIP>" + this.ComIP + "</ComIP>");
		s.append("<SendTime>" + this.SendTime + "</SendTime>");
		s.append("<Asyn>" + this.Asyn + "</Asyn>");
		s.append("<ReturnUrl>" + this.ReturnUrl + "</ReturnUrl>");
		s.append("<Signed>" + this.Signed + "</Signed>");
		s.append("<ClientType>" + this.ClientType + "</ClientType>");
		s.append("<TradeCount>" + this.PayCount + "</TradeCount>");
		s.append("</Header>");

		return s.toString();
	}


	public String getRequestType() {
		return RequestType;
	}


	public String getUUID() {
		return UUID;
	}


	public String getComId() {
		return ComId;
	}


	public String getComIP() {
		return ComIP;
	}


	public String getSendTime() {
		return SendTime;
	}


	public String getAsyn() {
		return Asyn;
	}


	public String getReturnUrl() {
		return ReturnUrl;
	}


	public String getSigned() {
		return Signed;
	}


	public void setRequestType(String requestType) {
		RequestType = requestType;
	}


	public void setUUID(String uUID) {
		UUID = uUID;
	}


	public void setComId(String comId) {
		ComId = comId;
	}


	public void setComIP(String comIP) {
		ComIP = comIP;
	}


	public void setSendTime(String sendTime) {
		SendTime = sendTime;
	}


	public void setAsyn(String asyn) {
		Asyn = asyn;
	}


	public void setReturnUrl(String returnUrl) {
		ReturnUrl = returnUrl;
	}


	public void setSigned(String signed) {
		Signed = signed;
	}


	public String getClientType() {
		return ClientType;
	}


	public void setClientType(String clientType) {
		ClientType = clientType;
	}


	public int getPayCount() {
		return PayCount;
	}


	public void setPayCount(int payCount) {
		PayCount = payCount;
	}

}