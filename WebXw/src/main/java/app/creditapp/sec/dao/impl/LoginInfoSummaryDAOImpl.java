package app.creditapp.sec.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.sec.dao.LoginInfoSummaryDAO;
import app.creditapp.sec.entity.LoginInfoSummary;

public class LoginInfoSummaryDAOImpl extends BaseIbatisDao implements LoginInfoSummaryDAO {

	@Override
	public void insert(LoginInfoSummary info) {
		try {
			getSqlMapClientTemplate().insert("loginInfoSummary.insert", info);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("ÐÂÔöÊ§°Ü");
		}
	}

	@Override
	public LoginInfoSummary getSummaryInfo(String sysdate) {
		try {
			Map<String,String> dateMap = new HashMap<String,String>();
			dateMap.put("firstDay", sysdate);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date thisDay = sdf.parse(sysdate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(thisDay);
			cal.add(cal.DATE, 1);
			String secondDay =  sdf.format(cal.getTime());
			dateMap.put("secondDay", secondDay);
			return (LoginInfoSummary) getSqlMapClientTemplate().queryForObject("loginInfoSummary.getSummary", dateMap);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("²éÑ¯Ê§°Ü");
		}
		
	}
}
