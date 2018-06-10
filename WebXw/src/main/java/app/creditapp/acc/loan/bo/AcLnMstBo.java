package  app.creditapp.acc.loan.bo;

import app.base.ServiceException;
import app.creditapp.acc.loan.entity.AcLnMst;
import app.creditapp.acc.loan.entity.AcLnMstFail;
import app.util.toolkit.Ipage;

/**
* Title: AcLnMstBo.java
* Description:
**/
public interface AcLnMstBo {

	public AcLnMst getById(AcLnMst acLnMst) throws ServiceException;
	
	public AcLnMst getByPactNo(AcLnMst acLnMst) throws ServiceException;

	public void del(AcLnMst acLnMst) throws ServiceException;

	public void insert(AcLnMst acLnMst) throws ServiceException;

	public void update(AcLnMst acLnMst) throws ServiceException;

	public Ipage findByPage(Ipage ipg, AcLnMst acLnMst) throws ServiceException;
	
	public Ipage findByPagedk(Ipage ipg, AcLnMst acLnMst) throws ServiceException;
	
//	public  ResponseParm validateWsIn(WsIn2105 wsIn2105) throws ServiceException;
	
	public Ipage findByPageforList(Ipage ipg, AcLnMst acLnMst) throws ServiceException;
	
	public int countforListWs(AcLnMst acLnMst) throws ServiceException;
	
	public Ipage findByPageToLoanFail(Ipage ipg, AcLnMstFail acLnMstFail) throws ServiceException;
}