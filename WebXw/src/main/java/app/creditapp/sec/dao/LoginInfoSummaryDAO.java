package app.creditapp.sec.dao;

import app.creditapp.sec.entity.LoginInfoSummary;

public interface LoginInfoSummaryDAO {
	void insert(LoginInfoSummary info);

	LoginInfoSummary getSummaryInfo(String sysdate);
}
