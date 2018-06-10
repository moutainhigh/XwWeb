package  app.creditapp.acc.option.bo;

import java.util.List;

import app.base.ServiceException;
import app.creditapp.acc.loan.entity.AcLnMst;
import app.creditapp.acc.option.entity.AcLnRepayPln;
import app.util.toolkit.Ipage;

/**
* Title: AcLnRepayPlnBo.java
* Description:
**/
public interface AcLnRepayPlnBo {

	public AcLnRepayPln getById(AcLnRepayPln acLnRepayPln) throws ServiceException;

	public void del(AcLnRepayPln acLnRepayPln) throws ServiceException;

	public void insert(AcLnRepayPln acLnRepayPln) throws ServiceException;

	public void update(AcLnRepayPln acLnRepayPln) throws ServiceException;

	public Ipage findByPage(Ipage ipg, AcLnRepayPln acLnRepayPln) throws ServiceException;
	
	public List calPlnsByParms (AcLnMst acLnMst) throws ServiceException;
	//根据合同号进行查询
	public AcLnRepayPln getByIdforpact(AcLnRepayPln acLnRepayPln) throws ServiceException;
	//获取贷款的应收本金、利息、已还本金、已还正常利息
	public AcLnRepayPln getAllAmt(AcLnRepayPln acLnRepayPln) throws ServiceException;

	public AcLnRepayPln getByBrNoPactNoPerdNo(AcLnRepayPln acLnRepayPln) throws ServiceException;
}