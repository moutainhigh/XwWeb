package app.creditapp.inf.entity;

/**
 * @作者 DHCC-zhangwei
 * @日期 Jun 23, 2016
 * @描述 接口输出参数实体
 */
public class ResponseParm {
	public ResponseParm() {

	}

	private String respCode; // 响应编码
	private String batNo; // 批次号
	private int dataCnt; // 记录数量
	private String respDesc; // 响应描述
	private String content; // 响应数据（json格式）
	private String reqSerial;// 请求流水号

	public int getDataCnt() {
		return dataCnt;
	}

	public void setDataCnt(int dataCnt) {
		this.dataCnt = dataCnt;
	}

	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getBatNo() {
		return batNo;
	}

	public void setBatNo(String batNo) {
		this.batNo = batNo;
	}

	public String getRespDesc() {
		return respDesc;
	}

	public void setRespDesc(String respDesc) {
		this.respDesc = respDesc;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReqSerial() {
		return reqSerial;
	}

	public void setReqSerial(String reqSerial) {
		this.reqSerial = reqSerial;
	}

}
