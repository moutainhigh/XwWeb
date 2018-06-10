package app.creditapp.sys.entity;
import app.base.BaseDomain;
/**
* Title: ParmDicRel.java
* Description:
* @version£º1.0
**/
public class ParmKey extends BaseDomain {
	private String key_name;//×ÖµäÃû³Æ
	private String key_desc;//×ÖµäÃèÊö
	private String key_sts;//×Öµä×´Ì¬[01ÆôÓÃ02Í£ÓÃ]
	public String getKey_name() {
		return key_name;
	}
	public void setKey_name(String keyName) {
		key_name = keyName;
	}
	public String getKey_desc() {
		return key_desc;
	}
	public void setKey_desc(String keyDesc) {
		key_desc = keyDesc;
	}
	public String getKey_sts() {
		return key_sts;
	}
	public void setKey_sts(String keySts) {
		key_sts = keySts;
	}

	
	
}