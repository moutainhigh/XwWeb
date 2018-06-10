package  app.creditapp.fund.dao;

import java.util.List;

import app.base.DAOException;
import app.creditapp.fund.entity.RptFundDaily;
/**
* Title: RptFundDailyDao.java
* Description:
**/
public interface RptFundDailyDao {

	public RptFundDaily getById(RptFundDaily rptFundDaily) throws DAOException;

	public void del(RptFundDaily rptFundDaily) throws DAOException;

	public void insert(RptFundDaily rptFundDaily) throws DAOException;

	public void update(RptFundDaily rptFundDaily) throws DAOException;
	
	public int getCount(RptFundDaily rptFundDaily) throws DAOException;

	public List<RptFundDaily > findByPage(RptFundDaily rptFundDaily) throws DAOException;
	
	public List<RptFundDaily > findByFdType(RptFundDaily rptFundDaily) throws DAOException;
	
	public List<RptFundDaily> getList(RptFundDaily rptFundDaily) throws DAOException;

}