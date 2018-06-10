package app.creditapp.inf.entity;

/**
 * @作者 DHCC-LIU
 * @日期 Jun 23, 2016 list listCom(共同联系人)
 * @描述 进件批次查询---输出实体类
 */
public class WsOut2104_1_3 {

	private String comName;// 借款人名称
	private String comIdtype;// 证件类型
	private String comIdno;// 证件号码
	private String comTel;// 联系电话

	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}

	public String getComIdtype() {
		return comIdtype;
	}

	public void setComIdtype(String comIdtype) {
		this.comIdtype = comIdtype;
	}

	public String getComIdno() {
		return comIdno;
	}

	public void setComIdno(String comIdno) {
		this.comIdno = comIdno;
	}

	public String getComTel() {
		return comTel;
	}

	public void setComTel(String comTel) {
		this.comTel = comTel;
	}

}
