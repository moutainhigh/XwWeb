package accounting.domain.sys;

import app.util.toolkit.Ipage;

public  abstract class CMISDomain {
	private int startNum;
	private int endNum;
	
	public int getStartNum() {
		return startNum;
	}
	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}
	public int getEndNum() {
		return endNum;
	}
	public void setEndNum(int endNum) {
		this.endNum = endNum;
	}
	
	public void setStartNumAndEndNum(Ipage ipg) {
		this.startNum = ipg.getStartRow();
		this.endNum = ipg.getEndNum();
	}
}
