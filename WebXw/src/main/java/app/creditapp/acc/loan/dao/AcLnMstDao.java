package  app.creditapp.acc.loan.dao;

import java.util.List;
import java.util.Map;

import app.base.DAOException;
import app.creditapp.acc.loan.entity.AcLnMst;
import app.creditapp.acc.loan.entity.AcLnMstFail;
import app.creditapp.inf.entity.WsOut2105_1;
/**
* Title: AcLnMstDao.java
* Description:
**/
public interface AcLnMstDao {

	public AcLnMst getById(AcLnMst acLnMst) throws DAOException;

	public AcLnMst getByPactNo(AcLnMst acLnMst) throws DAOException;

	public void del(AcLnMst acLnMst) throws DAOException;

	public void insert(AcLnMst acLnMst) throws DAOException;

	public void update(AcLnMst acLnMst) throws DAOException;
	
	public void updateLoanSts(AcLnMst acLnMst) throws DAOException;
	
	public int getCount(AcLnMst acLnMst) throws DAOException;
	
	public int getCountdk(AcLnMst acLnMst) throws DAOException;

	public List<AcLnMst > findByPage(AcLnMst acLnMst) throws DAOException;
	
	public List<AcLnMst > findByPagedk(AcLnMst acLnMst) throws DAOException;
	
	public int getCountforlist(AcLnMst acLnMst) throws DAOException;
	
	public List<WsOut2105_1> findByPageforlist(Map map) throws DAOException;
//
	public int getCountToLoanFail(AcLnMstFail acLnMstFail) throws DAOException;
//	
	public List<AcLnMstFail > findByPageToLoanFail(AcLnMstFail acLnMstFail) throws DAOException;
}