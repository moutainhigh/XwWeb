package  app.creditapp.ln.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.ln.entity.LnAcct;
/**
* Title: LnAcctDao.java
* Description:
**/
public interface LnAcctDao {
	
	public List<LnAcct> getByIdAndSts(LnAcct lnAcct)throws DAOException;

	public LnAcct getById(LnAcct lnAcct) throws DAOException;
	
	//用于提前还款
	public LnAcct getByIdforAdv(LnAcct lnAcct) throws DAOException ;

	public void del(LnAcct lnAcct) throws DAOException;

	public void insert(LnAcct lnAcct) throws DAOException;

	public void update(LnAcct lnAcct) throws DAOException;
	
	public void updateByAppIdAndUse(LnAcct lnAcct) throws DAOException;
	
	public void updateAcSts(LnAcct lnAcct) throws DAOException;

	public int getCount(LnAcct lnAcct) throws DAOException;

	public List<LnAcct > findByPage(LnAcct lnAcct) throws DAOException;

	public int getCountQuotaForLn(LnAcct lnAcct)throws DAOException;

	public List<LnAcct> findByPageQuotaForLn(LnAcct lnAcct)throws DAOException;
	
	public List<LnAcct> getListByFk(LnAcct lnAcct)throws DAOException;

	//接口ws2501接口查询使用
	public int getCountFor2501(LnAcct lnAcct) throws DAOException;
	
	public LnAcct getOldCount(LnAcct lnAcct) throws DAOException;
	
	public void updateByAppIdAndUseForF(LnAcct lnAcct) throws DAOException;
	
	public void delById(LnAcct lnAcct) throws DAOException;


}