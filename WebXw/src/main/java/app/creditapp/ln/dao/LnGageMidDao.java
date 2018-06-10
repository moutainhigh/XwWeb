package  app.creditapp.ln.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.ln.entity.LnGageMid;
/**
* Title: LnGageMidDao.java
* Description:
**/
public interface LnGageMidDao {

	public LnGageMid getById(LnGageMid lnGageMid) throws DAOException;

	public void del(LnGageMid lnGageMid) throws DAOException;

	public void insert(LnGageMid lnGageMid) throws DAOException;

	public void update(LnGageMid lnGageMid) throws DAOException;
	
	public int getCount(LnGageMid lnGageMid) throws DAOException;

	public List<LnGageMid > findByPage(LnGageMid lnGageMid) throws DAOException;

	public int getCountQuotaForLn(LnGageMid lnGageMid) throws DAOException;

	public List<LnGageMid> findByPageQuotaForLn(LnGageMid lnGageMid) throws DAOException;

	public List<LnGageMid> getListByLnGageMid(LnGageMid lnGageMid) throws DAOException;

}