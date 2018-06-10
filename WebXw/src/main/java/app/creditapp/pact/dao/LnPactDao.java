package  app.creditapp.pact.dao;

import java.util.List;

import app.base.DAOException;
import app.creditapp.pact.entity.LnPact;
/**
* Title: LnPactDao.java
* Description:
**/
public interface LnPactDao {

	public LnPact getById(LnPact lnPact) throws DAOException;
	
	public LnPact getByIdForAppId(LnPact lnPact) throws DAOException;
	
	public List<LnPact > getByIdForBatchNo(LnPact lnPact) throws DAOException;
	
	public List<LnPact > getWfTaskList(LnPact lnPact) throws DAOException;

	public List<LnPact > getByIdForApp(LnPact lnPact) throws DAOException;
	
	public LnPact getByIdForAppr(LnPact lnPact) throws DAOException;
	
	public LnPact getByIdForAppAndBatch(LnPact lnPact) throws DAOException;
	
	public LnPact getByIdForList(LnPact lnPact) throws DAOException;
	
	public void del(LnPact lnPact) throws DAOException;

	public void insert(LnPact lnPact) throws DAOException;

	public void update(LnPact lnPact) throws DAOException;
	
	public void updateSts(LnPact lnPact) throws DAOException;
	
	public int getCount(LnPact lnPact) throws DAOException;
	
	public int getCountForTask(LnPact lnPact) throws DAOException;

	public int getCountForList(LnPact lnPact) throws DAOException;
	
	public int getCountForRel(LnPact lnPact) throws DAOException;

	public List<LnPact > findByPage(LnPact lnPact) throws DAOException;
	
	public List<LnPact > findByPageForTask(LnPact lnPact) throws DAOException;

	public List<LnPact > findByPageForList(LnPact lnPact) throws DAOException;
	
	public List<LnPact > findByPageForAppr(LnPact lnPact) throws DAOException;
	
	public List<LnPact > findByPageForBatchNo(LnPact lnPact) throws DAOException;
	
	public List<LnPact > findByPageAppr(LnPact lnPact) throws DAOException;
	
	public List<LnPact > findByPageForRel(LnPact lnPact) throws DAOException;

	public int getCountQuotaForCif(LnPact lnPact) throws DAOException;

	public Object findByPageQuotaForCif(LnPact lnPact) throws DAOException;
	
	public LnPact getPactNoAndBrNo(LnPact lnPact) throws DAOException;
	
	public LnPact getByAppId(LnPact lnPact) throws DAOException ;
	
	public List<LnPact> findListToWorkE() throws DAOException;
	
	//根据合同号取合同金额
	public int getPactAmtCount(LnPact lnPact) throws DAOException;
	
	//合同表（ln_pact）的到期日end_date
	public String getMaxEndDate(LnPact lnPact) throws DAOException;

}