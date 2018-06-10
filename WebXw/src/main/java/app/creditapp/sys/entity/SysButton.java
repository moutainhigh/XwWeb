package app.creditapp.sys.entity;
import app.base.BaseDomain;
/**
* Title: SysButton.java
* @version£∫1.0
**/
public class SysButton extends BaseDomain {
	private String menu_no;//≤Àµ•∫≈
	private String button_no;//∞¥≈•±‡∫≈
	private String button_desc;//∞¥≈•√Ë ˆ
	public String getMenu_no() {
		return menu_no;
	}
	public void setMenu_no(String menu_no) {
		this.menu_no = menu_no;
	}
	public String getButton_no() {
		return button_no;
	}
	public void setButton_no(String button_no) {
		this.button_no = button_no;
	}
	public String getButton_desc() {
		return button_desc;
	}
	public void setButton_desc(String button_desc) {
		this.button_desc = button_desc;
	}
}