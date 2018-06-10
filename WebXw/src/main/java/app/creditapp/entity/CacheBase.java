package app.creditapp.entity;

import java.io.Serializable;

public class CacheBase implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7459602310879962191L;
	private String key_name;
	private String opt_code;
	private String opt_name;
	private String opt_seq;

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

	public String getOpt_Seq() {
		return opt_seq;
	}

	public void setSeq(String opt_seq) {
		this.opt_seq = opt_seq;
	}

}
