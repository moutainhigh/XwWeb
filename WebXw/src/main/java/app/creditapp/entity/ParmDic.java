package app.creditapp.entity;

public class ParmDic implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 变量定义
	private String key_name; // 字段名
	private String opt_code; // 选项值
	private String opt_name; // 选项名
	private Integer opt_seq; // 顺序
	private String opt_sts; // 状态
	public String getKey_name() {
		return key_name;
	}

	public void setKey_name(String key_name) {
		this.key_name = key_name;
	}

	public String getOpt_code() {
		return opt_code;
	}

	public void setOpt_code(String opt_code) {
		this.opt_code = opt_code;
	}

	public String getOpt_name() {
		return opt_name;
	}

	public void setOpt_name(String opt_name) {
		this.opt_name = opt_name;
	}

	public Integer getOpt_seq() {
		return opt_seq;
	}

	public void setOpt_seq(Integer optSeq) {
		opt_seq = optSeq;
	}

	public String getOpt_sts() {
		return opt_sts;
	}

	public void setOpt_sts(String optSts) {
		opt_sts = optSts;
	}



}