package app.creditapp.sec.bo;

import app.creditapp.sec.entity.LoginInfoSummary;
import app.creditapp.sec.entity.LoginStatus;

public interface LoginInfoSummaryBO {
	void insertNewInfo(String loginId,LoginStatus status);
	LoginInfoSummary getSummaryInfo(String sysdate);
}
