package  app.creditapp.ln.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.ln.entity.LnAcctMid;
/**
* Title: LnAcctMidDao.java
* Description:
**/
public interface LnAcctMidDao {

	public LnAcctMid getById(LnAcctMid lnAcctMid) throws DAOException;

	public void del(LnAcctMid lnAcctMid) throws DAOException;

	public void insert(LnAcctMid lnAcctMid) throws DAOException;

	public void update(LnAcctMid lnAcctMid) throws DAOException;
	
	public int getCount(LnAcctMid lnAcctMid) throws DAOException;

	public List<LnAcctMid > findByPage(LnAcctMid lnAcctMid) throws DAOException;

	public int getCountQuotaForLn(LnAcctMid lnAcctMid)throws DAOException;

	public List<LnAcctMid> findByPageQuotaForLn(LnAcctMid lnAcctMid)throws DAOException;

	public List<LnAcctMid> getListByLnAcctMid(LnAcctMid lnAcctMid)throws DAOException;

}