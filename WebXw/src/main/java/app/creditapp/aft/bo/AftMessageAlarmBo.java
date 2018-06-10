package app.creditapp.aft.bo;

import app.creditapp.aft.entity.aftMessage.AjaxData;


public interface AftMessageAlarmBo {
	public String initMessagePage(String userNo,String sendDate,boolean isRecent);
	public AjaxData initAjaxData(String userNo,String sendDate,boolean isRecent);
	public AjaxData initAjaxDataForBetween(String userNo,String beginDate,String endDate);
}
