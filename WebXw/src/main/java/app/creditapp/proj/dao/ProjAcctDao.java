package  app.creditapp.proj.dao;

import java.util.List;

import app.base.DAOException;
import app.creditapp.proj.entity.ProjAcct;
/**
* Title: ProjAcctDao.java
* Description:
**/
public interface ProjAcctDao {

	public ProjAcct getById(ProjAcct projAcct) throws DAOException;
	
	public String getProjId(ProjAcct projAcct) throws DAOException;

	public ProjAcct getInterfaceById(ProjAcct projAcct) throws DAOException;
	
	public ProjAcct getByProjNoAndAcctType(ProjAcct projAcct) throws DAOException;

	public ProjAcct getByProjNoAndAcctTypeAndChn(ProjAcct projAcct) throws DAOException;

	public void del(ProjAcct projAcct) throws DAOException;

	public void insert(ProjAcct projAcct) throws DAOException;

	public void update(ProjAcct projAcct) throws DAOException;
	
	public void updateSts(ProjAcct projAcct) throws DAOException;
	
	public void updateAcctBal(ProjAcct projAcct) throws DAOException;
	
	public int getCount(ProjAcct projAcct) throws DAOException;
	
	public int getCountForPop(ProjAcct projAcct) throws DAOException;

	public List<ProjAcct > findByPage(ProjAcct projAcct) throws DAOException;
	public List<ProjAcct > findByPageForPop(ProjAcct projAcct) throws DAOException;

	public int getCountQuotaForCorp(ProjAcct projAcct)throws DAOException;

	public List<ProjAcct> findByPageQuotaForCorp(ProjAcct projAcct)throws DAOException;

	public List<ProjAcct> findListByProjNo(ProjAcct projAcct)throws DAOException;

	public List<ProjAcct> findByProjNoAndAcctType(ProjAcct projAcct)throws DAOException;
	
	public String getKey()throws DAOException;
	//获取软通TA借据视图VW_ACT_ACCOUNTINFO中部分信息
	public ProjAcct VW_ACT_ACCOUNTINFO(ProjAcct projAcct) throws DAOException;
	
	public void getInfForAcct(ProjAcct projAcct)throws DAOException;
	
	//<!--判断  某渠道的虚拟账户是否存在，存在的话不能进行新增-->
	public int getCountForX(ProjAcct projAcct) throws DAOException;

	public int getByProjNo(ProjAcct acct)throws DAOException;


}