package accounting.interf.loan;


public class OpResult {

	private String authNo;//授权编号
	private Boolean bl;//成功标志
	private String idNo;//流水号
	private String msg;//错误提示信息
	private String type;//接口代码
	
	
	public Boolean getBl() {
		return bl;
	}
	public void setBl(Boolean bl) {
		this.bl = bl;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getAuthNo() {
		return authNo;
	}
	public void setAuthNo(String authNo) {
		this.authNo = authNo;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
