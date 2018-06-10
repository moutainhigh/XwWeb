package app.creditapp.sec.bo.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import app.creditapp.sec.bo.LoginInfoSummaryBO;
import app.creditapp.sec.dao.LoginInfoSummaryDAO;
import app.creditapp.sec.entity.LoginInfoSummary;
import app.creditapp.sec.entity.LoginStatus;
import app.util.DateUtil;

public class LoginInfoSummaryBOImpl implements LoginInfoSummaryBO{
	private LoginInfoSummaryDAO logininfosummarydao;
	
	private Date getLoginTime(int type){
		switch (type) {
		case 1:
			//采用数据库日期+系统时间
			String sysdate  = DateUtil.getSysGlobal().getSys_date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String time = sdf.format(new Date()).substring(8);
			try {
				return sdf.parse(sysdate+time);
			} catch (ParseException e) {
				System.out.println("时间转换失败");
				e.printStackTrace();
				return new Date();
			}
		default:
			return new Date();
		}
	}
	@Override
	public void insertNewInfo(String loginId, LoginStatus status) {
		LoginInfoSummary info = new LoginInfoSummary(loginId,getLoginTime(1),status.getValue());
		logininfosummarydao.insert(info);
		
	}
	public void setLogininfosummarydao(LoginInfoSummaryDAO logininfosummarydao) {
		this.logininfosummarydao = logininfosummarydao;
	}
	@Override
	public LoginInfoSummary getSummaryInfo(String sysdate) {
		return logininfosummarydao.getSummaryInfo(sysdate);
	}

}
