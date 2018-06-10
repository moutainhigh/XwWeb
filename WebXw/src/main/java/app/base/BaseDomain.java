package app.base;

import app.util.toolkit.Ipage;

public abstract class BaseDomain {

	private int startNum;
	private int endNum;

	public int getStartNum() {
		return startNum;
	}

	public int getEndNum() {
		return endNum;
	}

	public void setStartNumAndEndNum(Ipage ipg) {
		this.startNum = ipg.getStartRow();
		this.endNum = ipg.getEndNum();
	}

}
