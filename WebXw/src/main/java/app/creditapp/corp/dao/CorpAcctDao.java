package  app.creditapp.corp.dao;

import java.util.List;

import app.base.DAOException;
import app.creditapp.corp.entity.CorpAcct;
/**
* Title: CorpAcctDao.java
* Description:
**/
public interface CorpAcctDao {

	public CorpAcct getById(CorpAcct corpAcct) throws DAOException;

	public void del(CorpAcct corpAcct) throws DAOException;
	
	public void delForBase(CorpAcct corpAcct) throws DAOException;

	public void insert(CorpAcct corpAcct) throws DAOException;

	public void update(CorpAcct corpAcct) throws DAOException;
	
	public int getCount(CorpAcct corpAcct) throws DAOException;

	public List<CorpAcct > findByPage(CorpAcct corpAcct) throws DAOException;
	public void updateAcctJS(CorpAcct corpAcct) throws DAOException;
	public int getCountQuotaForCorp(CorpAcct corpAcct)throws DAOException;

	public List<CorpAcct> findByPageQuotaForCorp(CorpAcct corpAcct)throws DAOException;
	//接口5202查询账户
	public CorpAcct getByIdFor5202(String opnAcno) throws DAOException;
	public String getKey()throws DAOException;

	public CorpAcct getByBrNoAndAcNo(CorpAcct corpAcct);
	public CorpAcct getByBrNo(CorpAcct corpAcct);
}