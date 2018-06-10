package  app.creditapp.ln.dao;

import java.util.List;

import app.base.DAOException;
import app.creditapp.ln.entity.LnDue;
/**
* Title: LnDueDao.java
* Description:
**/
public interface LnDueDao {

	public LnDue getById(LnDue lnDue) throws DAOException;
	public LnDue getByIdForPactNo(LnDue lnDue) throws DAOException;
	
	public LnDue getByPactId(String pactId) throws DAOException;
	
	public LnDue getByPactNoAndBrNo(LnDue lnDue) throws DAOException;

	public void del(LnDue lnDue) throws DAOException;

	public void insert(LnDue lnDue) throws DAOException;

	public void update(LnDue lnDue) throws DAOException;
	
	public int getCount(LnDue lnDue) throws DAOException;
	public int getCountForPop(LnDue lnDue) throws DAOException;
	public int getCountFail(LnDue lnDue) throws DAOException;
	
	public int getCountForRead(LnDue lnDue) throws DAOException;

	public List<LnDue > findByPage(LnDue lnDue) throws DAOException;
	public List<LnDue > findByPageForPop(LnDue lnDue) throws DAOException;
	
	public List<LnDue > findByPageForRead(LnDue lnDue) throws DAOException;
	
	public List<LnDue> findAllFail(LnDue lnDue) throws DAOException;
	
	public List<LnDue> findList() throws DAOException;

	public List<LnDue> findListToWorkF() throws DAOException;
	
	public int dueStsUpdate(LnDue LnDue) throws DAOException;
	
	public List<LnDue > findListByFundNo(LnDue lnDue) throws DAOException;
	//抽查回访列表
	public List<LnDue> getCheckList(LnDue lnDue) throws DAOException;
	//抽查回访记录数
	public int getCnt(LnDue lnDue) throws DAOException;
	
	public List<LnDue > getLndueList(LnDue lnDue) throws DAOException;
	public List<LnDue > getLndueListByBrNo(String brno) throws DAOException;
	public LnDue getByIdForDue(LnDue lnDue) throws DAOException;
	
}