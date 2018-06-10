package  app.creditapp.fund.bo;

import java.util.List;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.bat.entity.RptPrdtDaily;
import app.creditapp.fund.entity.RptFundDaily;

/**
* Title: RptFundDailyBo.java
* Description:
**/
public interface RptFundDailyBo {

	public RptFundDaily getById(RptFundDaily rptFundDaily) throws ServiceException;

	public void del(RptFundDaily rptFundDaily) throws ServiceException;

	public void insert(RptFundDaily rptFundDaily) throws ServiceException;

	public void update(RptFundDaily rptFundDaily) throws ServiceException;

	public Ipage findByPage(Ipage ipg, RptFundDaily rptFundDaily) throws ServiceException;
	
	public List<RptFundDaily> findByFdType(RptFundDaily rptFundDaily) throws ServiceException;
	
	public List<RptFundDaily> getList(RptFundDaily rptFundDaily) throws ServiceException;
}