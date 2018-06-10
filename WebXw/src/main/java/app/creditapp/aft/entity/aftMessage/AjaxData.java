package app.creditapp.aft.entity.aftMessage;

import java.util.List;

public class AjaxData {
	private SysTaskInfoArray sysTaskInfoArray;
	private List<PasBigType> pasBigType;
	private List<PasSubType> pasSubType;
	private Integer pasAwareCount;
	private String SysDate;
	private Integer sumCount;
	public Integer getSumCount() {
		return sumCount;
	}
	public void setSumCount(Integer sumCount) {
		this.sumCount = sumCount;
	}
	public SysTaskInfoArray getSysTaskInfoArray() {
		return sysTaskInfoArray;
	}
	public void setSysTaskInfoArray(SysTaskInfoArray sysTaskInfoArray) {
		this.sysTaskInfoArray = sysTaskInfoArray;
	}
	public Integer getPasAwareCount() {
		return pasAwareCount;
	}
	public void setPasAwareCount(Integer pasAwareCount) {
		this.pasAwareCount = pasAwareCount;
	}
	public String getSysDate() {
		return SysDate;
	}
	public void setSysDate(String sysDate) {
		SysDate = sysDate;
	}
	public List<PasBigType> getPasBigType() {
		return pasBigType;
	}
	public void setPasBigType(List<PasBigType> pasBigType) {
		this.pasBigType = pasBigType;
	}
	public List<PasSubType> getPasSubType() {
		return pasSubType;
	}
	public void setPasSubType(List<PasSubType> pasSubType) {
		this.pasSubType = pasSubType;
	}
	
}
